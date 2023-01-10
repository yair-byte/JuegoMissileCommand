package controlador;

import java.io.Serializable;



public class Jugador implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1420098470653641112L;
	private String nombre;
	private double tiempo;
	private int puntaje;
	
	public Jugador(String nombre,double tiempo,int puntaje)
	{
		this.setNombre(nombre);
		this.setPuntaje(puntaje);
		this.setTiempo(tiempo);
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getTiempo() {
		return tiempo;
	}
	public void setTiempo(double tiempo) {
		this.tiempo = tiempo;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	
}
