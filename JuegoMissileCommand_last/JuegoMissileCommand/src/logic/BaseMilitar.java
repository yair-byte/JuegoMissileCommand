package logic;

import java.util.ArrayList;

import Grafica.InfoDibujable;
import Grafica.Tipo;


/**
 * 
 * @author Yair
 *
 */

public class BaseMilitar extends Entity implements Movil{
	
	private int cantmisilesrestantes=10;
	private double velmisiles=15;
	public ArrayList<MisilAntiBalistico> mismisiles;
	/**
	 * lanza los misiles hacia las coordenadas
	 * @param coordenadas Coordenadas del misil
	 */
	public boolean lanzarmisil(Pos coordenadas)
	{
		if(cantmisilesrestantes>0 && !this.isDead())
		{
			System.out.println(this.velmisiles);
			this.mismisiles.add(new MisilAntiBalistico(this.getPos(),false,coordenadas,this.getVelmisiles()));
			this.cantmisilesrestantes--;
			//System.out.println("X: "+coordenadas.getPosX());
		//	System.out.println("Y: "+coordenadas.getPosY());
			return true;
		}
		return false;
	}
	
	public void setmismisiles(ArrayList<MisilAntiBalistico> arr)
	{
		this.mismisiles=arr;
	}
	public int getcantmisilesrestantes() {
		return cantmisilesrestantes;
	}

	public void setcantmisilesrestantes(int cant) {
		this.cantmisilesrestantes = cant;
	}
	
	
	public BaseMilitar(Pos p,boolean dead)
	{
		super(p,dead);
		this.setmismisiles(new ArrayList<MisilAntiBalistico>());
	}

	public double getVelmisiles() {
		return velmisiles;
	}

	public void setVelmisiles(double velmisiles) {
		this.velmisiles = velmisiles;
	}

	public ArrayList<MisilAntiBalistico> getMismisiles() {
		return mismisiles;
	}

	public void setMismisiles(ArrayList<MisilAntiBalistico> mismisiles) {
		this.mismisiles = mismisiles;
	}
	
	/**
	 * Mueve sus misiles
	 */
	@Override
	public void mover() {

		for(MisilAntiBalistico i : mismisiles)
		{
			i.mover();
		}
		
	}

	@Override
	public InfoDibujable dibujar() {
		InfoDibujable dib = new InfoDibujable();
		dib.setPos(this.getPos());
		if(!this.isDead()){
			dib.setEnumeracion(Tipo.BASEMILITARVIVA);
		}
		else
		{
			dib.setEnumeracion(Tipo.BASEMILITARMUERTA);
		}
		return dib;
	}

}
