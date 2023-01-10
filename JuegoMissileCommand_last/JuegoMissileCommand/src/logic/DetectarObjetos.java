package logic;


/**
 * 
 * @author Yair
 *
 */

public class DetectarObjetos {
	
	/**
	 * Detecta un objecto 
	 * @param m un misil
	 * @return Retorna la nueva posicion
	 */
	public static Pos detectarobjeto(MisilInteligente m)
	{
		for(Explosion e : Juego.getExplosiones())
		{
			if(!e.isDead())
			{
				double distancia = Utilidades.distanciaentredospuntos(m.getPos(),e.getPos());
				if(distancia<m.getRadio() && (m.getPos().getPosY()-(e.getPos().getPosY())>=0))
				{
					return m.getPos();
				}
			}
		}
		return null; //sino detecto nada reotrna null;
	}
	
	

}
