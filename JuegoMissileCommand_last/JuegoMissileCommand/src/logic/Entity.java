package logic;

import Grafica.Dibujable1;

/**
 * 
 * @author Yair
 *
 */

public abstract class Entity implements Dibujable1{
	
	private Pos pos;
	private boolean dead;
	
	public void matar()
	{
		this.setDead(true);
	}
	
	public Entity(Pos p,boolean dead) {
		this.setPos(p);
		this.setDead(dead);
	}

	public Pos getPos() {
		return pos;
	}

	public void setPos(Pos pos) {
		this.pos = pos;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

}
