package logic;

import Grafica.InfoDibujable;
import Grafica.Tipo;


/**
 * 
 * @author Yair
 *
 */

public class MisilAntiBalistico extends Misil{

	public MisilAntiBalistico(Pos p,boolean dead, Pos destino, double velocidad) {
		super(p,dead, destino, velocidad);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void mover()
	{
		super.mover();
		if(this.getPos().getPosX()==this.getDestino().getPosX() && this.getPos().getPosY()==this.getDestino().getPosY() && !this.isDead())
		{
			Juego.generarExplosion(this.getPos());
			this.matar();
		}
		
	}
	

	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.MABVIVO);
		}
		else
		{
			dib.setEnumeracion(Tipo.MABMUERTO);
		}
		return dib;
	}

	

	

}
