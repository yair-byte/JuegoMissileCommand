package logic;
/**
 * 
 * @author Yair
 *	Patrones de los diferentes niveles
 */

public class Nivel {
	
	
	public static boolean tonto_o_inteligente(int nivel)
	{
		
		boolean misil = false;
		switch(nivel)
		{
		case 1:	misil=false;
			break;
		case 2: misil=false;
			break;
		case 3: misil=true;
			break;
		case 4: misil=true;
			break;
		case 5: misil=false;
			break;
		case 6: misil=false;
			break;
		case 7: misil=true;
			break;
		case 8: misil=true;
			break;
		case 9: misil=false;
			break;
		case 10: misil=false;
			break;
		case 11: misil=true;
			break;
		case 12: misil=true;
			break;
		case 13:misil=false;
			break;
		case 14:misil=false;
			break;
		case 15:misil=true;
			break;
		case 16:misil=true;
			break;
		}
		return misil;
	}
	
	
	public static int multiplicadornivel(int nivel)
	{
		int mul;
		switch(nivel)
		{
		case 1:	mul=1;
			break;
		case 2: mul=1;
			break;
		case 3: mul=2;
			break;
		case 4: mul=2;
			break;
		case 5: mul=3;
			break;
		case 6: mul=3;
			break;
		case 7: mul=4;
			break;
		case 8: mul=4;
			break;
		case 9: mul=5;
			break;
		case 10: mul=5;
			break;
		default: mul=6;
			break;
		
		}
		return mul;
	}
	

}
