package Grafica;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.ImageIcon;

import controlador.Controlador;

import java.awt.Color;



public class VentanaPrincipal extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Controlador controlador;
	public JButton botonReglas,botonJugar,botonRanking,botonOpciones;
	private String imgFileName1 = "/Imagenes/reglas_opt.png"; 
	private String imgFileName2 = "/Imagenes/jugar_opt.png"; 
	private String imgFileName3 = "/Imagenes/ranking_opt.png"; 
	private String imgFileName4 = "/Imagenes/options_opt.jpg"; 

	/**
	 * Create the application.
	 */
	public VentanaPrincipal(Controlador oyente) {
		controlador=oyente;
		initialize();
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		getContentPane().setBackground(Color.WHITE);
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().setLayout(null);
		setResizable(false);
		
		botonReglas = new JButton("");
		botonReglas.addMouseListener(controlador);
		botonReglas.setIcon(new ImageIcon(this.getClass().getResource(imgFileName1)));
		botonReglas.setBounds(53, 66, 100, 100);
		getContentPane().add(botonReglas);
		
		botonJugar = new JButton("");
		botonJugar.addMouseListener(controlador);
		botonJugar.setIcon(new ImageIcon(this.getClass().getResource(imgFileName2)));
		botonJugar.setBounds(178, 66, 100, 100);
		getContentPane().add(botonJugar);
		
		botonRanking = new JButton("");
		botonRanking.addMouseListener(controlador);
		botonRanking.setIcon(new ImageIcon(this.getClass().getResource(imgFileName3)));
		botonRanking.setBounds(306, 66, 100, 100);
		getContentPane().add(botonRanking);
		
	    botonOpciones = new JButton("");
		botonOpciones.addMouseListener(controlador);
		botonOpciones.setIcon(new ImageIcon(this.getClass().getResource(imgFileName4)));
		botonOpciones.setBounds(404, 0, 30, 30);
		getContentPane().add(botonOpciones);
		
	}
}
