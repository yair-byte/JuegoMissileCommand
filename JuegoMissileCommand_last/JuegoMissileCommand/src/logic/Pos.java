package logic;
/**
 * 
 * @author Yair
 *
 */

public class Pos {
	
	private int PosX;
	private int PosY;
	
	public Pos(int Posx,int Posy)
	{
		this.setPosX(Posx);
		this.setPosY(Posy);
	}
	
	public Pos()
	{}
	
	public int getPosX() {
		return this.PosX;
	}
	public void setPosX(int posX) {
		this.PosX = posX;
	}
	public int getPosY() {
		return this.PosY;
	}
	public void setPosY(int posY) {
		this.PosY = posY;
	}
	
	//sobreescrito
	public boolean equals(Pos o)
	{
		if(o.getPosX()==this.getPosX() && o.getPosY()==this.getPosY())
			return true;
		return false;
	}

}
