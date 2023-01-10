package logic;

import java.util.ArrayList;

import Grafica.InfoDibujable;
import Grafica.Tipo;

/**
 * 
 * @author Yair
 *
 */

public class MisilBalisticoInterplanetario extends MisilKrytoliano{

	private int alturabifurcacion;
	private boolean bif=false;
	
	
	public void setAlturabifurcacion(int alturabif)
	{
		this.alturabifurcacion=alturabif;
	}
	
	public int getAlturabifurcacion()
	{
		return this.alturabifurcacion;
	}

	public MisilBalisticoInterplanetario(Pos p,boolean dead, Pos destino, double velocidad,int alturabif,Pos [] posiciones,boolean bif) {
		super(p,dead, destino, velocidad,posiciones);
		this.setAlturabifurcacion(alturabif);
		this.bif=bif;
	//	this.estela = new Estela(this.getPos(),false,tiempoestela);
	}

	@Override
	public void mover(){
		super.mover();
		if(Juego.getNivel()>=3){
			ArrayList<MisilBalisticoInterplanetario> nuevos= new ArrayList<MisilBalisticoInterplanetario>();
		if(this.getPos().getPosY()<=this.getAlturabifurcacion() && !bif)
		{
			this.bif=true;
			Pos nue = new Pos(this.getPos().getPosX(),this.getPos().getPosY()-2);
			nuevos.add(new MisilBalisticoInterplanetario(nue,false,Utilidades.generarDestinoRandom(),Juego.velocidad,this.getAlturabifurcacion(),this.arr,true));
			nuevos.add(new MisilBalisticoInterplanetario(nue,false,Utilidades.generarDestinoRandom(),Juego.velocidad,this.getAlturabifurcacion(),this.arr,true));
			Juego.nuevosMisiles(nuevos);
		}
		}
		
	//	this.estela.mover(this.getPos());
	}

	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.MBIVIVO);
		}
		else
		{
			dib.setEnumeracion(Tipo.MBIMUERTO);
		}
		return dib;
	}

}
