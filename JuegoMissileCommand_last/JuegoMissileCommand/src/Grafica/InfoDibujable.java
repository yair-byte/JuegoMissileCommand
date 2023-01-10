package Grafica;

import logic.Pos;

public class InfoDibujable {
	
	private Pos pos;
	private Tipo enumeracion;
	
	public Tipo getEnumeracion() {
		return enumeracion;
	}
	public void setEnumeracion(Tipo enumeracion) {
		this.enumeracion = enumeracion;
	}
	public Pos getPos() {
		return pos;
	}
	public void setPos(Pos pos) {
		this.pos = pos;
	}

}
