package Grafica;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controlador.Controlador;
import controlador.Jugador;
import prog3.listagenerica.ListaGenericaEnlazada;

public class VentanaRanking extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	
	private ListaGenericaEnlazada<Jugador> lista;


	/**
	 * Create the frame.
	 */
	public VentanaRanking(Controlador controlador,ListaGenericaEnlazada<Jugador> lista) {
	
		this.lista=lista;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(controlador);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 240);
		contentPane.add(scrollPane);
		String [] col;
		String [][] matriz = new String [this.lista.tamanio()][4];
		this.lista.comenzar();
		for(int f=0;f<this.lista.tamanio();f++)
		{
			col = new String [4];
			Jugador aux=this.lista.proximo();
			col[0]=Integer.toString(f+1);
			col[1]=aux.getNombre();
			col[2]=Integer.toString(aux.getPuntaje());
			col[3]=Double.toString(aux.getTiempo());
			matriz[f]=col;
		}
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			matriz,
			new String[] {
				"N\u00B0", "Jugador", "Puntaje", "Tiempo"
			}
		));
		table.setEnabled(false);
		scrollPane.setViewportView(table);
	}

}
