package logic;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import Grafica.Dibujable1;
import controlador.Controlador;


/**
 * 
 * @author Yair Cipres
 *
 */


public class Juego implements Runnable {

	private static Juego instancia = null;
	
	/*Atributos*/
	//Constantes
	public static final int ANCHO=800;
	public static final int ALTO=600;
	private static final int NIVEL_MAS_BAJO=200;
	public static final int ALTURADEBIFURCACION = 400;
	private static final int P_MAB_SINUSAR=5;
	private static final int P_MBI_DESTRUIDO=25;
	private static final int P_MISIL_CRUCERO_DESTRUIDO=125;
	private static final int P_CIUDAD_SALVADA=100;
	private static final int BONUSCITY=10000;
	private static final int RADIOEXPLOSION = 40;
	private static final int DURACION = 100;
	//Arrays
	private static ArrayList<Bombardero> bombarderos;
	private static ArrayList<Ciudad> ciudades;
	private static ArrayList<BaseMilitar> bases;
	private static ArrayList<MisilKrytoliano> misilesmalos; 
	private static ArrayList<Explosion> explosiones;
	static ArrayList<Dibujable1> dibujos;
	private static Pos [] posiciones;
	private static ArrayList<MisilBalisticoInterplanetario> temporales;
	//Vars
	private static int nivelactual=1;
	private static int multiplicador=1;
	private static int puntajetotal;
	private static int puntajetotalfinal;
	private static int vidas=0;
	private static boolean bonus=false;
	private static int alturabombarderos=450;
	private static int puntajeactualxmisil;
	private static int puntajeactualxcrucero;
	private static int puntajeactualxciudadsalvada;
	private static int entidadesenrecamara=12;
	private static int cant_misiles_este_nivel=12;
	private static int misileslanzados=0;
	protected static double velocidad=2;
	
	private static boolean funcionando;
	private static Timer timer;
	private static BufferIntermedio bff;
	private static int tempoentreoleadas=8000;
	private static long inicio,fin;
	private static Controlador controller;
	/*metodos*/
	/**
	 * Constructor inicializa los arrays
	 */
	private Juego(){
		bombarderos=new ArrayList<Bombardero>();
		explosiones=new ArrayList<Explosion>();
		bases=new ArrayList<BaseMilitar>();
		ciudades=new ArrayList<Ciudad>();
		misilesmalos=new ArrayList<MisilKrytoliano>();
		dibujos=new ArrayList<Dibujable1>();
		posiciones=new Pos[9];
		temporales=new ArrayList<MisilBalisticoInterplanetario>();
		timer = new Timer();
		bff = new BufferIntermedio();
		System.out.println("se inicio..................");
		estadoini();
	}
	
	public static Juego getInstance(int nivel,Controlador control) {
		
		controller=control;
		nivelactual=nivel;
        if(instancia==null)
        {
        	instancia = new Juego();
        }
        return instancia;
    }
	
	public static void setMisiles()
	{
		misilesmalos.addAll(temporales);
		temporales.clear();
	}
	
	public static int getNivel()
	{
		return nivelactual;
	}
	
	public static void lanzarUnMisil(Pos blanco)
	{
		Pos blancoDef = new Pos();
		blancoDef.setPosX(blanco.getPosX());
		blancoDef.setPosY(ALTO-blanco.getPosY());
		if(blancoDef.getPosY()>NIVEL_MAS_BAJO)
		{
			if(blancoDef.getPosX()<ANCHO/3)
			{
				if(!bases.get(0).lanzarmisil(blancoDef))
				{
					if(!bases.get(1).lanzarmisil(blancoDef))
					{
						if(!bases.get(2).lanzarmisil(blancoDef))
						{}
					}
				}
			}
			if(blancoDef.getPosX()>=ANCHO/3 && blancoDef.getPosX()<=((ANCHO/3)*2))
			{
					if(!bases.get(1).lanzarmisil(blancoDef))
					{
						if(!bases.get(0).lanzarmisil(blancoDef))
						{
							if(!bases.get(2).lanzarmisil(blancoDef))
							{}
						}
					}
				
			}
			if(blancoDef.getPosX()>((ANCHO/3)*2))
			{
					if(!bases.get(2).lanzarmisil(blancoDef))
					{
						if(!bases.get(0).lanzarmisil(blancoDef))
						{
							if(!bases.get(1).lanzarmisil(blancoDef))
							{}
						}
					}
			}
		}
			
	}

	/**
	 * HardCode
	 */
	/*private static void hardCode()
	{
		for(int i=0;i<3;i++){
			bases.get(0).lanzarmisil(Utilidades.generarRandomXY(0, ANCHO/3, NIVEL_MAS_BAJO, ALTO));
			bases.get(1).lanzarmisil(Utilidades.generarRandomXY(ANCHO/3, ((ANCHO/3)*2), NIVEL_MAS_BAJO, ALTO));
			bases.get(2).lanzarmisil(Utilidades.generarRandomXY(((ANCHO/3)*2), ANCHO, NIVEL_MAS_BAJO, ALTO));
		}
	}*/
	//getters
	public static ArrayList<Ciudad> getCiudades()
	{
		return ciudades;
	}
	
	public static ArrayList<Bombardero> getBombarderos()
	{
		return bombarderos;
	}
	
	public static ArrayList<Explosion> getExplosiones()
	{
		return explosiones;
	}
	
	public static ArrayList<BaseMilitar> getBases()
	{
		return bases;
	}
	
	public static void nuevosMisiles(ArrayList<MisilBalisticoInterplanetario> nuevos)
	{
		temporales.addAll(nuevos);
	}
	
	public static Pos [] getPosiciones()
	{
		return posiciones;
	}
	
	public static void destruirBase(int index)
	{
		bases.get(index).matar();
	}
	public static void destruirCiudad(int index)
	{
		ciudades.get(index).matar();
	}
	
	/**
	 * verfica si termino la ronda o nivel
	 * @return retorna true en caso de que termine
	 */
	private static boolean verificarfinronda() 
	{
		boolean cond=true;
		if(entidadesenrecamara>0)
			return false;
		else
		{	
			for(Bombardero i : bombarderos)
			{		
				if(!i.isDead())
					return false;
				else
				{
					for(MisilBalisticoInterplanetario j : i.getmisiles())
					{
						if(!j.isDead())
							return false;
					}
				}
			}
			for(MisilKrytoliano k : misilesmalos)
			{
				if(!k.isDead())
					return false;
			}
			for(Explosion e : explosiones)
			{
				if(!e.isDead())
					return false;
			}
			for(BaseMilitar b : bases)
			{
				if(!b.isDead())
				{
					for(MisilAntiBalistico l : b.getMismisiles())
					{
						if(!l.isDead())
							return false;
					}
				}
			}
		return cond;
		}
		
	}

	//en recamara hay solo misilesmalos y bombarderos
	private static void calccantmisilesestenivel()
	{
		if(cant_misiles_este_nivel<17)
			cant_misiles_este_nivel++;
	}
	
	private static void calccantentidadesestenivel(){
		
		entidadesenrecamara=cant_misiles_este_nivel+4;
		
	}
	
	/**
	 * Crea un bombardero aleatoriamente
	 * @param lado Recibe el lado del cual aparece la entidad
	 * @return Retorna true si se creo, false de lo contrario
	 */
	private static boolean crearunbombardero(boolean lado) 
	{
		if(nivelactual>=2)
		{
			if((Utilidades.generarNumeroRandom(0, 1)==0))
					bombarderos.add(new Avion(Utilidades.generarRandomXY(0,0,0,0),false,alturabombarderos,velocidad-1,lado,100));
			else
				bombarderos.add(new Satelite(Utilidades.generarRandomXY(0,0,0,0),false,alturabombarderos,velocidad-1,lado,100));
			return true;
		}
		return false;
	}
	/**
	 * Crea un misil malo aleatoraimente ya sea balistico, tonto o inteligente dependiendo del nivel
	 */
	private static void crearunmisilmalo()
	{
		/** Crear misiles balisticos y cruceros*/
		int op=Utilidades.generarNumeroRandom(0, 2);
		if(op<=1)
			misilesmalos.add(new MisilBalisticoInterplanetario(Utilidades.generarRandomXY(0,ANCHO,ALTO,ALTO),false,Utilidades.generarDestinoRandom(),velocidad,ALTURADEBIFURCACION,posiciones,false));
		else
		{
			if(Nivel.tonto_o_inteligente(nivelactual)) //inteligente
			{
				misilesmalos.add(new MisilInteligente(Utilidades.generarRandomXY(0,ANCHO,ALTO,ALTO),false,Utilidades.generarDestinoRandom(),velocidad,posiciones));
			}
			else //tonto
			{
				misilesmalos.add(new MisilTonto(Utilidades.generarRandomXY(0,ANCHO,ALTO,ALTO),false,Utilidades.generarDestinoRandom(),velocidad,posiciones));
			
			}
		}
	}
	/**
	 * Produce una explosion
	 * @param posicionexplosion 
	 */
	public static void generarExplosion(Pos posicionexplosion){
		
		explosiones.add(new Explosion(posicionexplosion,false,RADIOEXPLOSION,DURACION));
		
	}
	
	private static void verificarColisionExplosion()
	{
		for(Explosion e : explosiones)
		{
			if(!e.isDead()){
				for(MisilKrytoliano m : misilesmalos)
				{
					if(!m.isDead())
					{
						if(Utilidades.distanciaentredospuntos(e.getPos(),m.getPos())<e.getRadio())
						{
							if(m instanceof MisilBalisticoInterplanetario)
								puntajeactualxmisil+=P_MBI_DESTRUIDO;
							else
								puntajeactualxcrucero+=P_MISIL_CRUCERO_DESTRUIDO;
							m.matar();
						}
					}
				}
				for(Bombardero b : bombarderos)
				{
					for(MisilBalisticoInterplanetario i : b.getmisiles())
					{
						if(!i.isDead())
						{
							if(Utilidades.distanciaentredospuntos(e.getPos(),i.getPos())<e.getRadio())
							{
								puntajeactualxmisil+=P_MBI_DESTRUIDO;
								i.matar();
							}
						}
					}
					if(!b.isDead())
					{
						if(Utilidades.distanciaentredospuntos(e.getPos(),b.getPos())<e.getRadio())
						{
							b.matar();
						}
					}
				}
			}
		}
	}
	
	private class BufferIntermedio
	{
		int Bmisileslanzados=0;
		int Bentidadesenrecamara=0;
		int Bmisilesmalosalanzar=0;
		int Bbombarderosalanzar=0;
		boolean modif;
		boolean modif()
		{
			return this.modif;
		}
		void reset()
		{
			this.Bmisileslanzados=0;
			this.Bmisilesmalosalanzar=0;
			this.Bentidadesenrecamara=0;
			this.Bbombarderosalanzar=0;
			this.modif=false;
		}
	}
	
	/**
	 * Lanza las entidad en un tiempo estipulado
	 */
	private static void lanzarEntidades(int period)

	{
		TimerTask timertask = new TimerTask()
		{
			
			@Override
			public void run() {
				
				if(entidadesenrecamara>0)
				{
							if(misileslanzados<=cant_misiles_este_nivel)
							{	
								if(cant_misiles_este_nivel-misileslanzados<4)
								{
									for(int i=0;i<cant_misiles_este_nivel-misileslanzados;i++)
									{
										bff.Bmisilesmalosalanzar++;
										bff.Bmisileslanzados++;
										bff.Bentidadesenrecamara++;
									}
								}
								else
								{
									for(int i=0;i<4;i++)
									{
										bff.Bmisilesmalosalanzar++;
										bff.Bmisileslanzados++;
										bff.Bentidadesenrecamara++;
									}
								}
							}		
							
							bff.Bbombarderosalanzar++;
							bff.Bentidadesenrecamara++;
							bff.modif=true;
				}
			
			}
			
		};
		
		timer.schedule(timertask, 1000, period);
	}
	
	/**
	 * Dibuja todas las entidades
	 */
	private static void DrawAll()
	{
		dibujos.clear();
		dibujos.removeAll(dibujos);
		dibujos.addAll(bases);
		dibujos.addAll(bombarderos);
		dibujos.addAll(ciudades);
		dibujos.addAll(explosiones);
		dibujos.addAll(misilesmalos);
		for(BaseMilitar i : getBases())
		{
			dibujos.addAll(i.getMismisiles());
		}
		for(Bombardero z : getBombarderos())
		{
			dibujos.addAll(z.getmisiles());
		}
		controller.pintar(dibujos);
	}
	
	private static void verifBuffer()
	{
		if(bff.modif())
		{
			misileslanzados-=bff.Bmisileslanzados;
			entidadesenrecamara-=bff.Bentidadesenrecamara;
			for(int i=0;i<bff.Bmisilesmalosalanzar;i++)
				crearunmisilmalo();
			for(int j=0;j<bff.Bbombarderosalanzar;j++){
				if(Utilidades.generarNumeroRandom(0, 1)==0)
					crearunbombardero(true);
				else
					crearunbombardero(false);
			}
			bff.reset();
		}
	}
	
	/**
	 * GAMELOOP principal del juego llama a todos los metodos correspondiente 
	 */
	public static void gameLoop()
	{
		lanzarEntidades(tempoentreoleadas);
		while(funcionando){
		verifBuffer();
		//hardcode
		actualizarestado();
		//fueraDePantalla();
		//verificarbifurcacion();
		//veriftiempoexplosion();
		verificarColisionExplosion();
		//drawAll;
		DrawAll();
		if(verificarfinronda()){
			
			int[] puntajes=obtenerpuntajetotal();
			int vivas=puntajes[0];
			int misilesnousados=puntajes[1];
			if(vivas<6 && vidas>0)
			{
				levantarCiudad();
				vidas--;
				bonus=true;
			}
			mostrarpuntaje(vivas,misilesnousados);
			if(verifGameOver())
				gameOver();
			else{
				controller.dibujarNivel(nivelactual+1);
				reiniciarnivel();
				lanzarEntidades(tempoentreoleadas);
				System.out.println("Nivel:"+(nivelactual-1)+"Completado");
			}
		 }	
		//System.out.println(Tick);
		try {
			Thread.sleep(1000/60);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	}
	
	private static void gameOver(){
		
		fin=System.currentTimeMillis();
		double tiempoTotal=(fin-inicio)/1000;
		finalizarEjecucion();
		if(controller.verifRank(tiempoTotal, puntajetotalfinal))
		{
			controller.dibujarIngresarNombre(tiempoTotal, puntajetotalfinal);
		}
		controller.dibujarPantallaEND(puntajetotalfinal);
		
	}
	/**
	 * Verifica si termino el Juego es decir si todas la ciudades fueron destruidas
	 * @return
	 */
	private static boolean verifGameOver()
	{
		for(Ciudad i : ciudades)
		{
			if(!i.isDead())
				return false;
		}
		return true;
	}
	
	public static void pausar(int milis)
	{
		try {
			Thread.sleep(milis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void mostrarpuntaje(int vivas,int misilesnousados){
		System.out.println("PuntajeTotal en este nivel:"+puntajetotal);
		controller.dibujarPuntaje(puntajeactualxciudadsalvada,puntajeactualxmisil,bonus,vivas,misilesnousados);
	}
	/**
	 * "Revive" una ciudad aleatoriamente
	 */
	private static void levantarCiudad() {
		
		for(Ciudad i : ciudades)
		{
			if(i.isDead())
			{
				i.setDead(false);
				
			}
		}
		
	}
	/**
	 * Llama al metodo mover de las entidades correspondiente
	 * @param e Recibe todas la entidades que implementan la Interface Movil, es decir aquellas que se mueven
	 */
	private static void moverentidades(Movil e)
	{
		e.mover();
	}
	/**
	 * Mueve todas las entidades
	 */
	private static void actualizarestado()
	{
		for(BaseMilitar b : bases)
			moverentidades(b);
		for(MisilKrytoliano m : misilesmalos)
			moverentidades(m);
		for(Bombardero b : bombarderos)
			moverentidades(b);
		for(Explosion e : explosiones)
			moverentidades(e);
		setMisiles();
	}
	/**
	 * Setea el puntaje total obtenido en la ronda
	 */
	private static int[] obtenerpuntajetotal()
	{
		int misilessinusar = 0;
		int ciu=0;
		for(Ciudad c : ciudades)
		{
			if(!c.isDead()){
				puntajeactualxciudadsalvada+=P_CIUDAD_SALVADA;
				ciu++;
			}
		}
		for(BaseMilitar b : bases)
		{
				misilessinusar+=b.getcantmisilesrestantes();
		}
		int puntajexmisilessinusar=misilessinusar*P_MAB_SINUSAR;
		puntajetotal=(puntajeactualxciudadsalvada+puntajeactualxcrucero+puntajeactualxmisil+puntajexmisilessinusar)*multiplicador;
		puntajetotalfinal+=puntajetotal;
		if(Utilidades.esMultiplo(puntajetotalfinal, BONUSCITY))
		{
			vidas++;
		}
		int [] aux = new int[2];
		aux[0]=ciu; aux[1]=misilessinusar;
		return aux;
	}
	/**
	 * Reinicia las entidades luego de que termina el nivel
	 */
	private static void reiniciarEntidades()
	{
		for(BaseMilitar i : bases)
		{
			i.setDead(false);
			i.setcantmisilesrestantes(10);
			i.mismisiles.removeAll(i.getMismisiles());
		}
		bombarderos.clear();
		misilesmalos.clear();
		explosiones.clear();
	}
	
	
	/**
	 * Reinicia todos los parametros luego que termina el nivel
	 */
	private static void reiniciarnivel()
	{
		nivelactual++;
		if(nivelactual>16)
			gameOver();
		puntajeactualxciudadsalvada=0;
		puntajeactualxcrucero=0;
		puntajeactualxmisil=0;
		puntajetotal=0;
		multiplicador=Nivel.multiplicadornivel(nivelactual);
		if(alturabombarderos-10>=NIVEL_MAS_BAJO)
			alturabombarderos-=10;
		reiniciarEntidades();
		misileslanzados=0;
		calccantmisilesestenivel();
		calccantentidadesestenivel();
		velocidad=2+(multiplicador/2);
		tempoentreoleadas=8000-(500*multiplicador);
		bonus=false;
		
	}
	/**
	 * Estado inicial de las ciduades y las bases
	 */
	private static void estadoini()
	{
		Pos aux = new Pos();
		int espacio=ANCHO/7;
		int x=espacio;
		aux.setPosX(x); aux.setPosY(100);
		for(int i=0;i<6;i++)
		{
			ciudades.add(new Ciudad(aux,false));
			posiciones[i]=aux;
			x=x+espacio;
			aux=new Pos(x,100);
		}
		x=60; int b=6;
		aux.setPosX(x);aux.setPosY(130);
		for(int i=0;i<3;i++)
		{
			bases.add(new BaseMilitar(aux,false));
			posiciones[i+b]=aux;
			if(i==0)
				x=(ANCHO/2)-30;
			else
				if(i==1)
					x=ANCHO-60;
			aux=new Pos(x,130);
		}
		multiplicador=Nivel.multiplicadornivel(nivelactual);
		puntajetotal=0;
		puntajetotalfinal=0;
		vidas=0;
		bonus=false;
		alturabombarderos=450;
		puntajeactualxmisil=0;
		puntajeactualxcrucero=0;
		puntajeactualxciudadsalvada=0;
		cant_misiles_este_nivel=12;
		calccantentidadesestenivel();
		misileslanzados=0;
		velocidad=2+(multiplicador/2);
		tempoentreoleadas=8000-(500*multiplicador);
		
	}

	@Override
	public void run() {

		funcionando=true;
		inicio=System.currentTimeMillis();
		controller.dibujarNivel(nivelactual);
		gameLoop();
		
	}
	
	public static void finalizarEjecucion()
	{
		funcionando=false;
		instancia=null;
	}
	
	
	

	
	
	
	

}
