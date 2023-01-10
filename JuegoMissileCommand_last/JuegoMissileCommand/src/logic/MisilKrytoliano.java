package logic;
/**
 * 
 * @author Yair
 *
 */

public abstract class MisilKrytoliano extends Misil{
	
	protected Pos [] arr ;

	public MisilKrytoliano(Pos p,boolean dead, Pos destino, double velocidad,Pos [] arr) {
		super(p,dead, destino, velocidad);
		this.arr=arr;
	}

	@Override
	public void mover()
	{
		super.mover();
		if(this.getPos().getPosX()==this.getDestino().getPosX() && this.getPos().getPosY()==this.getDestino().getPosY() && !this.isDead())
		{
			for(int i=0;i<9;i++)
			{
				if(this.getDestino()==this.arr[i])
				{
					if(i<=5)
					{
						Juego.destruirCiudad(i);
						
						break;
					}
					else
					{
						Juego.destruirBase(i-6);
						break;
					}
				}
			}
			//Juego.generarExplosion(this.getPos());
			this.matar();
		}
		
	}
	
}
