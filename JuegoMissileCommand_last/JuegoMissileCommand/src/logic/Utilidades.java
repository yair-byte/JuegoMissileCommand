package logic;
/**
 * 
 * @author Yair
 *
 */

public class Utilidades {
	
	public static int generarNumeroRandom(int menor,int mayor)//entre que y que inclusive
	{
		return ((int) (Math.random()*((mayor+1)-menor)+(menor)));
	}
	
	public static Pos generarRandomXY(int iniX,int finX,int iniY,int finY)
	{
		Pos posi=new Pos();
		int x=(int) (Math.random()*(finX-iniX)+iniX);
		int y=(int) (Math.random()*(finY-iniY)+iniY);
		posi.setPosX(x); posi.setPosY(y);
		return posi;
	}
	
	public static boolean outOfWindow(Entity e)
	{
		if(e.getPos().getPosY()>Juego.ALTO || e.getPos().getPosY()<0 || e.getPos().getPosX()>Juego.ANCHO || e.getPos().getPosX()<0)
			return true;
		else
			return false;
	}
	
	public static double distanciaentredospuntos(Pos ini, Pos fin)
	{
		return Math.sqrt(Math.pow(fin.getPosX()-ini.getPosX(), 2)+Math.pow(fin.getPosY()-ini.getPosY(), 2));
	}
	
	public static boolean esMultiplo(long n1,int n2){
		if (n1%n2==0)
			return true;
		else
			return false;
	}
	/**
	 * Genera un destino random entre las ciudades y las bases
	 * @return retorna la posicion random
	 */
	protected static Pos generarDestinoRandom()
	{
		Pos posiciondestino = new Pos();
		int aux=Utilidades.generarNumeroRandom(0,1);
		if(aux==0)//es base
		{
			int index=Utilidades.generarNumeroRandom(0,2);
			posiciondestino=(Juego.getBases().get(index)).getPos();
		}
		else
		{
			int index=Utilidades.generarNumeroRandom(0,5);
			posiciondestino=(Juego.getCiudades().get(index)).getPos();
		}
		return posiciondestino;
	}
	
	

}
