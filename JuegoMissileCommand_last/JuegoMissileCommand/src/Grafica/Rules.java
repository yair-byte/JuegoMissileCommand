package Grafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import controlador.Controlador;



public class Rules extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public Rules(Controlador controlador) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 424, 250);
		contentPane.add(scrollPane);
		
		JTextArea txtrElJuegoEl = new JTextArea();
		txtrElJuegoEl.setEditable(false);
		txtrElJuegoEl.setText("EL JUEGO\r\n\r\nEl objetivo del juego es defender tus ciudades y bases de misiles, El enemigo dispara misiles bal\u00EDsticos interplanetarios (MBI) y misiles crucero, ambos tienen como objetivo destruir tus ciudades y bases militares. Hay 2 tipos de misil crucero: misiles cruceros tontos (que caen en l\u00EDnea recta) y misiles crucero inteligentes, los cuales tratar\u00E1n de evadir tus misiles antibal\u00EDsticos (MABs). \r\n\r\nEl enemigo ataca en una serie de oleadas que puede variar en el n\u00FAmero de misiles bal\u00EDsticos interplanetarios que atacan. Cada oleada de misiles se mueve m\u00E1s r\u00E1pido que la oleada anterior. Mientras m\u00E1s r\u00E1pida es la oleada, m\u00E1s dif\u00EDcil es defender las ciudades. Por este motivo, mientras m\u00E1s r\u00E1pida es la oleada, m\u00E1s alto ser\u00E1 el puntaje ganado. \r\n\r\nCon cada oleada, ten\u00E9s 30 MABs para defenderte, 10 en cada uno de los silos, los cuales est\u00E1n ubicados en la parte inferior de la pantalla, a la izquierda, al centro y a la derecha. Una vez que has disparado los 30 MABs, estar\u00E1s indefenso hasta que comience una nueva oleada.\r\n\r\nCada vez que un MAB llega a su destino o alcanza alg\u00FAn misil enemigo ocurre una explosi\u00F3n. La onda expansiva de esta explosi\u00F3n puede ocasionar que otros misiles bal\u00EDsticos interplanetarios o crucero que se encuentran en la zona de impacto tambi\u00E9n sean destruidos. Si la explosi\u00F3n del MAB no alcanza la cabeza del misil, \u00E9ste seguir\u00E1 con su trayectoria, s\u00F3lo se ver\u00E1 afectada la estela que deja a su paso..\r\nDebes tener en cuenta que hay un umbral (l\u00EDnea horizontal imaginaria) en el campo de juego, debajo de la cual no podr\u00E1s disparar misiles. De esta manera se protege a los silos y las ciudades de la autodestrucci\u00F3n.\r\n\r\nSi logras sobrevivir a la oleada, pasar\u00E1s al siguiente nivel, si no logras sobrevivir a la oleada, tendr\u00E1s s\u00F3lo otra oportunidad para volver a defenderte.\r\n\r\nEl juego finaliza cuando todas las ciudades son destruidas.\r\n\r\nPUNTAJE\r\n\r\nGanar\u00E1s puntuaci\u00F3n cuando destruyas misiles bal\u00EDstico interplanetarios y misiles crucero. Tambi\u00E9n pod\u00E9s sumar puntaje por los misiles antibal\u00EDsticos no usados y ciudades salvadas. Dado que cada oleada de misiles bal\u00EDsticos interplanetarios se mueve m\u00E1s r\u00E1pido, el puntaje ganado tambi\u00E9n ser\u00E1 mayor. Por ejemplo, oleadas 9 y 10 valen 5 veces el puntaje original. \r\n\r\n");
		scrollPane.setViewportView(txtrElJuegoEl);
		
		addWindowListener(controlador);
		
	}
}
