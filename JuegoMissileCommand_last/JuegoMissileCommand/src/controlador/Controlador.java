package controlador;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Timer;

import logic.Juego;
import prog3.listagenerica.ListaGenericaEnlazada;
import Grafica.Configuracion;
import Grafica.Dibujable1;
import Grafica.Rules;
import Grafica.VentanaPrincipal;
import Grafica.VentanaRanking;
import Grafica.VistaJuego;

public class Controlador extends WindowAdapter implements MouseListener,KeyListener{
	
	VentanaPrincipal menu;
	Rules ventanaReglas;
	VistaJuego ventanaJuego;
	Configuracion ventanaConfiguracion=null;
	VentanaRanking ventanaRank;
	Timer timer;
	int nivel_a_cargar=1;
	Thread juegohilo;
	ObjectInputStream in;
	ObjectOutputStream out;
	ListaGenericaEnlazada<Jugador> lista;
	int cant_jugadores;
	int pos;
	double tiempoTot;
	int puntaje;
	//InputStream i;
	
	
	String nombre;
	
	@SuppressWarnings("unchecked")
	public Controlador()
	{
		
		lista=new ListaGenericaEnlazada<Jugador>();
		
		try {
			
			
			//i = getClass().getClassLoader().getResourceAsStream("objeto.dat");
			
			 in = new ObjectInputStream(new FileInputStream("objeto.dat"));
			 Object obj = in.readObject();
			 lista = (ListaGenericaEnlazada<Jugador>) obj;
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al abrir el archivo");
		}finally
		{
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		if(this.lista.tamanio()<3)
			this.cant_jugadores=3;
		else
			this.cant_jugadores=this.lista.tamanio();
		
		
		menu = new VentanaPrincipal(this);
		menu.setVisible(true);
		timer = new Timer();
	}
	
	private void persistir()
	{
		for(int i=this.lista.tamanio();i>=this.cant_jugadores;i--)
		{
			this.lista.eliminarEn(i);
		}
		try {
			out = new ObjectOutputStream(new FileOutputStream("objeto.dat"));
			out.writeObject(this.lista);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.print("Error al escribir sobre el archivo. Archivo no encontrado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void agregarAlRank()
	{
		this.lista.agregarEn(new Jugador(this.nombre,this.tiempoTot,this.puntaje), this.pos);
		persistir();
	}
	
	public boolean verifRank(double tiempoTotal, int puntajetotalfinal)
	{
		int c=0;
		if(this.lista.esVacia()){
			pos=0;
		}
		else{	
				if(puntajetotalfinal<this.lista.elemento(this.lista.tamanio()-1).getPuntaje() && this.cant_jugadores>this.lista.tamanio())
				{
					pos=this.lista.tamanio();
					return true;
				}
			
				this.lista.comenzar();
				while(!this.lista.fin())
				{
					Jugador aux=this.lista.proximo();
					if(puntajetotalfinal>aux.getPuntaje())
					{
						pos=c;
						return true;
					}
					else
					{
						if(puntajetotalfinal==aux.getPuntaje())
						{
							if(tiempoTotal<aux.getTiempo())
							{
								pos=c;
								return true;
							}
							else{
								pos=c+1;
								if(pos+1>this.lista.tamanio())
									return false;
								else
									return true;
							}
					
						}
						
			
					}
				 c++;	
				}
				return false;
			}
		
		return true;
		
	}
	
	public void dibujarNivel(int lvl)
	{
		this.ventanaJuego.dibujarNivel(lvl);
	}
	
	public void dibujarPantallaEND(int punt)
	{
		this.ventanaJuego.dibujarPantallaEND(punt,this.lista);
	}
	
	public void pintar(ArrayList<Dibujable1> entidades){

		this.ventanaJuego.dibujarTodo(entidades);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if(e.getSource() == menu.botonReglas)
		{
			this.menu.setVisible(false);
			this.ventanaReglas= new Rules(this);
		}
		if(e.getSource() == menu.botonJugar)
		{
			this.menu.setVisible(false);
			this.ventanaJuego = new VistaJuego(this);
			juegohilo=new Thread(Juego.getInstance(nivel_a_cargar,this));
			juegohilo.start();
			System.out.println("se presionooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
		}
		if(e.getSource() == menu.botonOpciones)
		{
			this.menu.setVisible(false);
			this.ventanaConfiguracion = new Configuracion(this);
			
		}	
		if(e.getSource() == menu.botonRanking)
		{
			this.menu.setVisible(false);
			this.ventanaRank = new VentanaRanking(this,this.lista);
			
		}	
		if(this.ventanaConfiguracion!=null && e.getSource() == this.ventanaConfiguracion.checkBox)
		{
			if(this.ventanaConfiguracion.checkBox.isSelected())
			{
				this.nivel_a_cargar=1+this.ventanaConfiguracion.comboBox.getSelectedIndex();
			}
		}	
		if(this.ventanaConfiguracion!=null && e.getSource() == this.ventanaConfiguracion.checkBox_1)
		{
			if(this.ventanaConfiguracion.checkBox_1.isSelected())
			{
				this.cant_jugadores=this.ventanaConfiguracion.comboBox_1.getSelectedIndex()+3;
				persistir();
			}
		}	
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		

		if(e.getSource() == this.ventanaJuego)
		{
			Juego.lanzarUnMisil(this.ventanaJuego.getPosMouse());
			System.out.println("MisilLanzado.......................................");
			
		}
		
	}
	
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}
	@Override
	public void mouseExited(MouseEvent arg0) {}
	@Override
	public void mouseReleased(MouseEvent arg0) {}

	@Override
    public void windowClosing(WindowEvent evt) {
		if(this.juegohilo!=null && evt.getSource() == this.ventanaJuego )
		{
			Juego.finalizarEjecucion();
			juegohilo=null;
		}
        this.menu.setVisible(true);
    }

	public void dibujarIngresarNombre(double tiempoTotal, int puntajetotalfinal) {
		
		this.tiempoTot=tiempoTotal;
		this.puntaje=puntajetotalfinal;
		this.ventanaJuego.dibujarIngresarNombre();
		
	}

	public void dibujarPuntaje(int puntajeactualxciudadsalvada,
			int puntajeactualxmisil, boolean bonus, int vivas,
			int misilesnousados) {
		
		this.ventanaJuego.dibujarPuntaje(puntajeactualxciudadsalvada, puntajeactualxmisil, bonus, vivas, misilesnousados);
		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	
		if(this.ventanaJuego!=null && e.getSource() == this.ventanaJuego.field1)
		{
			int key = e.getKeyCode();
			if(key==KeyEvent.VK_ENTER)
			{
				nombre=this.ventanaJuego.field1.getText();
				//guardamos en el rank
				this.ventanaJuego.ingrese=true;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	public void pausarJuego(int i) {
		
		Juego.pausar(i);
		
	}
	
	
	
	public static void main(String[] args) {

	
		new Controlador();

		
	}

	

	

}
