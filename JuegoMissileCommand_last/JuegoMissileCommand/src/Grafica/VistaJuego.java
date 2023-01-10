package Grafica;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.JButton;




import logic.Juego;
import logic.Pos;
import prog3.listagenerica.ListaGenericaEnlazada;
import controlador.Controlador;
import controlador.Jugador;


public class VistaJuego extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnAre; 
	private String imgmira="Imagenes/mira_opt.png"; 
	private Image img,imgNula; // es un objeto BufferedImage
	private Image [] imagenes;
	private String avionURL,sateliteURL,ciudadURL,torreURL,cruceroURL,explosionURL,mabURL,mbiURL,fondoURL,torreMURL;
	public int mouseX,mouseY;
	ArrayList<Dibujable1> entidades=null;
	private ArrayList<String> URLs;
	Font f1,f2,f3;
	JLabel label0,label1,label2,label3,label4,label5,label6,label7;
	Controlador controlador;
	public JTextField field1;
	public boolean ingrese=false;
	double tiempo;
	int puntaje;

	/**
	 * Create the frame.
	 */
	public VistaJuego(Controlador controlador) {
		
		
		super("Missile Command");
		this.controlador=controlador;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		URLs = new ArrayList<String>();
		entidades = new ArrayList<Dibujable1>();
		setResizable(false);
		setVisible(true);
		setBounds(100, 100, Juego.ANCHO, Juego.ALTO);
		contentPane = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void paint(Graphics g) {
				
				super.paint(g);
				dibujarMira(g);
				dibujarEntidades(g);
			}
		};
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.setBackground(Color.BLACK);
		setContentPane(contentPane);
		f1 = new Font("Arial",Font.PLAIN,60);
		f2 = new Font("Arial",Font.PLAIN,40);
		f3 = new Font("Arial",Font.PLAIN,100);
		cargarURL();
		imagenes = new Image [URLs.size()];
		cargarImganes();
		URL imgUrl = getClass().getClassLoader().getResource(imgmira);
		if (imgUrl == null) {
		System.out.println("error");
		} else {
		try {
		img = ImageIO.read(imgUrl); // carga imagen en img
		} catch (IOException ex) {
		ex.printStackTrace();
		}
		}
	
		label0 = new JLabel();
		label0.setFont(f1);
		label0.setBounds((Juego.ANCHO/2)-200, (Juego.ALTO/2)-280, 500, 100);
		label0.setForeground(Color.BLUE);
		label0.setVisible(false);
		
	
		label1 = new JLabel();
		label1.setFont(f1);
		label1.setBounds((Juego.ANCHO/2)-250, (Juego.ALTO/2)-100, 100, 100);
		label1.setForeground(Color.RED);
		label1.setVisible(false);
		
		
		label2 = new JLabel();
		label2.setFont(f1);
		label2.setBounds((Juego.ANCHO/2)-250, Juego.ALTO/2, 100, 100);
		label2.setForeground(Color.RED);
		label2.setVisible(false);
		
		label3 = new JLabel();
		label3.setFont(f1);
		label3.setBounds((Juego.ANCHO/2)-200, 100+Juego.ALTO/2, 500, 100);
		label3.setForeground(Color.BLUE);
		label3.setVisible(false);
		
		label4 = new JLabel();
		label4.setFont(f2);
		label4.setBounds((Juego.ANCHO/2)-100, (Juego.ALTO/2)-100, 400, 100);
		label4.setForeground(Color.GREEN);
		label4.setVisible(false);
		
		label5 = new JLabel();
		label5.setFont(f2);
		label5.setBounds((Juego.ANCHO/2)-100, Juego.ALTO/2, 400, 100);
		label5.setForeground(Color.GREEN);
		label5.setVisible(false);
		
		label6 = new JLabel();
		label6.setFont(f2);
		label6.setBounds((Juego.ANCHO/2)-100, (Juego.ALTO/2)-200, 400, 100);
		label6.setForeground(Color.MAGENTA);
		label6.setVisible(false);
		
		label7 = new JLabel();
		label7.setFont(f1);
		label7.setBounds((Juego.ANCHO/2)-200, (Juego.ALTO/2)-280, 500, 100);
		label7.setForeground(Color.RED);
		label7.setVisible(false);
		
		field1=new JTextField();
		field1.setVisible(false);
		field1.setBounds((Juego.ANCHO/2)-250, Juego.ALTO/2, 600, 100);
		field1.setColumns(10);
		field1.setFont(f2);
		field1.setForeground(Color.CYAN);
		field1.setBackground(Color.BLACK);
		
		
		contentPane.add(label0);
		contentPane.add(label1);
		contentPane.add(label2);
		contentPane.add(label3);
		contentPane.add(label4);
		contentPane.add(label5);
		contentPane.add(label6);
		contentPane.add(label7);
		contentPane.add(field1);
		
		field1.addKeyListener(controlador);
		addWindowListener(controlador);		
		addMouseListener(controlador);
		addMouseMotionListener(new MouseMotionAdapter(){
	            @Override
	            public void mouseMoved(MouseEvent e){
	           
	            	
	            	mouseX=e.getX();
	            	mouseY=e.getY();
	        
	            //  repaint();  
	              }
	            @Override
	            public void mouseDragged(MouseEvent e){
	            
	            	mouseX=e.getX();
	            	mouseY=e.getY();
	              //repaint();
	              
	            }
	          });
	     
	}
	
	
	public Pos getPosMouse()
	{
		Pos mouse = new Pos();
		mouse.setPosX(this.mouseX);
		mouse.setPosY(this.mouseY);
		return mouse;
	}
	
	private void cargarURL()
	{
		this.avionURL="Imagenes/avion.png"; 
		this.sateliteURL="Imagenes/satelite.png"; 
		this.ciudadURL="Imagenes/ciudad_opt.jpg"; 
		this.torreURL="Imagenes/torre.png"; 
		this.cruceroURL="Imagenes/crucero.png"; 
		this.explosionURL="Imagenes/explosion.png"; 
		this.mabURL="Imagenes/mab_opt.png"; 
		this.mbiURL="Imagenes/mbi_opt.png"; 
		this.fondoURL="Imagenes/fondo_opt.png"; 
		this.torreMURL="Imagenes/torreMuerta.png"; 
		this.URLs.add(avionURL); this.URLs.add(cruceroURL);
		this.URLs.add(sateliteURL); this.URLs.add(torreURL);
		this.URLs.add(ciudadURL); this.URLs.add(explosionURL);
		this.URLs.add(mabURL); this.URLs.add(mbiURL); this.URLs.add(fondoURL);
		this.URLs.add(torreMURL);
	}
	private void cargarImganes()
	{
		int i=0;
		for(String s : this.URLs){
		URL imgUrl = getClass().getClassLoader().getResource(s);
		if (imgUrl == null) {
		System.out.println("error");
		} else {
		try {
		this.imagenes[i] = ImageIO.read(imgUrl); // carga imagen en img
		} catch (IOException ex) {
		ex.printStackTrace();
		}
		}
		i++;
		}
	}
	
	
	public void dibujarTodo(ArrayList<Dibujable1> entidades)
	{
	     this.entidades=entidades;
	     repaint();
	
	}

	private void dibujarEntidades(Graphics g)
	{
		if(!this.entidades.isEmpty()){
			for(Dibujable1 e : this.entidades)
			{
				InfoDibujable info = e.dibujar();
				g.drawImage(devolverImagen(info.getEnumeracion()), info.getPos().getPosX(), Juego.ALTO-info.getPos().getPosY(), null);
				
			}
		}
		g.drawImage(this.imagenes[8], 0, Juego.ALTO-100, null);
	}
	
	public void dibujarPantallaEND(int puntaje,ListaGenericaEnlazada<Jugador> lista)
	{
		label7.setVisible(true);
		label7.setText("Your Score: "+puntaje);
		lista.comenzar();
		for(int i=0;i<lista.tamanio();i++)
		{
			Jugador jaux=lista.proximo();
			JLabel aux = new JLabel();
			contentPane.add(aux);
			aux.setFont(f2);
			aux.setForeground(Color.RED);
			aux.setBounds((Juego.ANCHO/2)-100, ((Juego.ALTO/2)-150)+(i*50), 500, 100);
			aux.setText(jaux.getNombre()+"      "+jaux.getPuntaje());
			aux.setVisible(true);	
		}
		
	}
	
	public void dibujarIngresarNombre()
	{
		
		label6.setText("Ingrese su nombre");
		label6.setVisible(true);
		field1.setVisible(true);
		//Juego.finalizarEjecucion();
		while(!ingrese)
		{}
		ingrese=false;
		label6.setVisible(false);
		field1.setVisible(false);
		controlador.agregarAlRank();
		//dibujarPantallaEND();	
	}
	
	public void dibujarNivel(int lvl)
	{
		JLabel l = new JLabel();
		contentPane.add(l);
		l.setFont(f3);
		l.setText("NIVEL "+lvl);
		l.setBounds((Juego.ANCHO/2)-200, (Juego.ALTO/2)-200, 500, 300);
		l.setForeground(Color.LIGHT_GRAY);
		l.setVisible(true);
		controlador.pausarJuego(3000);
		l.setVisible(false);
	}
	
	public void dibujarPuntaje(int puntajexciudad,int puntajexmisil,boolean bonus,int ciudades,int misiles)
	{
		String s1=Integer.toString(puntajexmisil);
		String s2=Integer.toString(puntajexciudad);
		System.out.println(s1);
		System.out.println(s2);
		label1.setText(s1);
		label1.setVisible(true);
		label2.setText(s2);
		label2.setVisible(true);
		label0.setText("BONUS POINTS");
		label0.setVisible(true);
		label3.setText("BONUS CITY!!");
		label4.setText("x"+misiles+" misiles sin usar");
		label4.setVisible(true);
		label5.setText("x"+ciudades+" ciudades salvadas");
		label5.setVisible(true);
		
		
		if(bonus)
		{
			label3.setVisible(true);

		}
		controlador.pausarJuego(10000);
		label0.setVisible(false);
		label1.setVisible(false);
		label2.setVisible(false);
		label3.setVisible(false);
		label4.setVisible(false);
		label5.setVisible(false);
		
	}


	private Image devolverImagen(Tipo enumeracion) {
		
		switch(enumeracion)
		{
		case CIUDADVIVA:
			return this.imagenes[4];
		case CIUDADMUERTA:
			return imgNula;
		case BASEMILITARVIVA:
			return this.imagenes[3];
		case BASEMILITARMUERTA:
			return this.imagenes[9];
		case AVIONVIVO:
			return this.imagenes[0];
		case AVIONMUERTO:
			return imgNula;
		case SATELITEVIVO:
			return this.imagenes[2];
		case SATELITEMUERTO:
			return imgNula;
		case EXPLOSIONVIVA:
			return this.imagenes[5];
		case EXPLOSIONMUERTA:
			return imgNula;
		case MBIVIVO:
			return this.imagenes[7];
		case MBIMUERTO:
			return imgNula;
		case MABVIVO:
			return this.imagenes[6];
		case MABMUERTO:
			return imgNula;
		case MISILINTELIGENTEVIVO:
			return this.imagenes[1];
		case MISILINTELIGENTEMUERTO:
			return imgNula;
		case MISILTONTOVIVO:
			return this.imagenes[1];
		case MISILTONTOMUERTO:
			return imgNula;
		default:
			break;
		}
		return null;
	}
	
	private void dibujarMira(Graphics g)
	{
		g.drawImage(img, this.mouseX, this.mouseY, null);
	}

	
}











