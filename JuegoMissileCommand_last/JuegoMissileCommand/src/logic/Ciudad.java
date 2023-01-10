package logic;

import Grafica.InfoDibujable;
import Grafica.Tipo;

/**
 * 
 * @author Yair
 *
 */

public class Ciudad extends Entity{
	
	public Ciudad(Pos p,boolean dead)
	{
		super(p,dead);
	}

	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.CIUDADVIVA);
		}
		else
		{
			dib.setEnumeracion(Tipo.CIUDADMUERTA);
		}
		return dib;
	}

}
