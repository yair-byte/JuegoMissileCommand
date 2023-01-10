package logic;

import Grafica.InfoDibujable;
import Grafica.Tipo;

/**
 * 
 * @author Yair
 *
 */

public class MisilInteligente extends MisilCrucero{
	
	private Pos pos_final;
	private int radio=50;
	

	public MisilInteligente(Pos p,boolean dead, Pos destino, double velocidad,Pos [] posiciones) {
		super(p,dead, destino, velocidad,posiciones);
		this.setPos_final(destino);
	}

	@Override
	public void mover() {
		super.mover();
		
		if(this.getDestino()!=this.pos_final)
			this.setDead(false);
		Pos destinor = DetectarObjetos.detectarobjeto(this);
		
		if(destinor != null)
		{
			this.setDestino(destinor);
		}
		else
			this.setDestino(this.pos_final);
	}

	
	public Pos getPos_final() {
		return pos_final;
	}

	public void setPos_final(Pos pos) {
		this.pos_final = pos;
	}

	public int getRadio() {
		return radio;
	}

	public void setRadio(int radio) {
		this.radio = radio;
	}
	
	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.MISILINTELIGENTEVIVO);
		}
		else
		{
			dib.setEnumeracion(Tipo.MISILINTELIGENTEMUERTO);
		}
		return dib;
	}

}
