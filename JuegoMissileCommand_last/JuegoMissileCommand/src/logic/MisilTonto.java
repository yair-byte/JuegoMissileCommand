package logic;

import Grafica.InfoDibujable;
import Grafica.Tipo;

/**
 * 
 * @author Yair
 *
 */

public class MisilTonto extends MisilCrucero{

	public MisilTonto(Pos p,boolean dead, Pos destino, double velocidad,Pos [] posiciones) {
		super(p,dead, destino, velocidad,posiciones);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.MISILTONTOVIVO);
		}
		else
		{
			dib.setEnumeracion(Tipo.MISILTONTOMUERTO);
		}
		return dib;
	}

	

}
