package logic;

import Grafica.InfoDibujable;
import Grafica.Tipo;

/**
 * 
 * @author Yair
 *
 */

public class Explosion extends Entity implements Movil{
	
	private int radio;
	private int duracionactual;
	
	public Explosion(Pos pos,boolean dead,int radio,int duracion)
	{
		super(pos, dead);
		this.setDuracion(duracion);
		this.setRadio(radio);
	}
	
	public int getRadio() {
		return radio;
	}
	public void setRadio(int radio) {
		this.radio = radio;
	}
	public int getDuracion() {
		return duracionactual;
	}
	public void setDuracion(int duracion) {
		this.duracionactual = duracion;
	}

	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.EXPLOSIONVIVA);
		}
		else
		{
			dib.setEnumeracion(Tipo.EXPLOSIONMUERTA);
		}
		return dib;
	}

	@Override
	public void mover() {

		if(!this.isDead())
		{
			this.setDuracion(this.getDuracion()-1);
			if(this.getDuracion()<=0)
			{
				this.matar();
			}
		}
	}

	
	

}
