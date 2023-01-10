package logic;

import Grafica.InfoDibujable;
import Grafica.Tipo;

/**
 * 
 * @author Yair
 *	
 */

public class Avion extends Bombardero{

	public Avion(Pos p,boolean dead, int altura, double velocidad, boolean derecha,int delay) {
		super(p,dead, altura, velocidad, derecha,delay);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.AVIONVIVO);
		}
		else
		{
			dib.setEnumeracion(Tipo.AVIONMUERTO);
		}
		return dib;
	}

	
	
	

}
