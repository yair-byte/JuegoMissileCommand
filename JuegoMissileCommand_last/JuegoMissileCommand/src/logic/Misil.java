package logic;
/**
 * 
 * @author Yair
 *
 */

public abstract class Misil extends Entity implements Movil{
	
	public Misil(Pos p,boolean dead,Pos destino,double velocidad2) {
		super(p,dead);
		this.setDestino(destino);
		this.setVelocidad(velocidad2);
		this.setDead(false);
	}

	private Pos destino;
	private double velocidad;

	public void mover(){
		
		if(!this.isDead()){
		
		double movX,movY,pendiente;
		int x=this.getDestino().getPosX()-this.getPos().getPosX();
		int y=this.getDestino().getPosY()-this.getPos().getPosY();
		if(Math.abs(x)<this.velocidad&&Math.abs(y)<this.velocidad)
			this.setPos(this.getDestino());
		else{
		if(x==0)
		{
			movX=0;
			if(y<0)
				movY=-this.velocidad;
			else
				movY=+this.velocidad;
			
		}
		else{
			
			pendiente = (double) y/x;
			movX =(Math.sqrt(Math.pow(this.velocidad,2) / (1+Math.pow(pendiente,2))));
			if(x<0) 
				movX = -movX;
			movY =((pendiente*movX));
			
		}
		
		
		Pos sig=new Pos((int) (movX+this.getPos().getPosX()),(int) (movY+this.getPos().getPosY()));

		this.setPos(sig);
				
		}
		if(Utilidades.outOfWindow(this))
		{
			this.matar();
		}
		
		}
		
	}

	public Pos getDestino() {
		return this.destino;
	}

	public void setDestino(Pos destino) {
		this.destino = destino;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad2) {
		this.velocidad = velocidad2;
	}


}
