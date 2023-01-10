package logic;

import java.util.ArrayList;
/**
 * 
 * @author Yair
 *
 */

public abstract class Bombardero extends Entity implements Movil{
	
	public Bombardero(Pos p,boolean dead,int altura,double velocidad,boolean derecha,int delay) {
		super(p,dead);
		this.setAltura(altura);
		this.setVelocidad(velocidad);
		this.setDerecha(derecha);
		this.setDelay(delay);
		this.setmisiles(new ArrayList<MisilBalisticoInterplanetario>());
	}

	private boolean derecha;
	private double velocidad;
	private int altura;
	private int delay;
	private ArrayList<MisilBalisticoInterplanetario> misiles;
	
	public void setmisiles(ArrayList<MisilBalisticoInterplanetario> misiles)
	{
		this.misiles=misiles;
	}
	
	public ArrayList<MisilBalisticoInterplanetario> getmisiles()
	{
		return this.misiles;
	}
	/**
	 * Lanza sus misiles en las coordenadas especificadas
	 * 
	 */
	public void lanzarmisil()
	{
		this.misiles.add(new MisilBalisticoInterplanetario(this.getPos(),false,Utilidades.generarDestinoRandom(),Juego.velocidad,Juego.ALTURADEBIFURCACION,Juego.getPosiciones(),false));
	}
	
	
	public boolean getDerecha() {
		return this.derecha;
	}
	public void setDerecha(boolean derecha) {
		this.derecha=derecha;
		if(this.getDerecha()){
			Pos aux=new Pos(Juego.ANCHO,this.getPos().getPosY());
			this.setPos(aux);
		}
	}
	

	public double getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
		Pos aux=new Pos(this.getPos().getPosX(),this.getAltura());
		this.setPos(aux);
	}
	
	public void mover()
	{
		if(!this.isDead()){
		Pos aux = new Pos();
		aux.setPosY(this.getPos().getPosY());
		if(!this.derecha)
		{
			aux.setPosX(this.getPos().getPosX()+((int) Math.round(this.velocidad)));
			this.setPos(aux);
		}
		else
		{
			aux.setPosX(this.getPos().getPosX()-((int) Math.round(this.velocidad)));
			this.setPos(aux);
		}
		if(Utilidades.outOfWindow(this))
		{
			this.matar();
		}
		this.delay--;
		if(this.delay==0)
			this.lanzarmisil();
		}
		for(MisilKrytoliano i : misiles)
		{
			i.mover();
		}
		
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}


}
