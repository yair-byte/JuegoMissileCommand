package Grafica;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;

import controlador.Controlador;


import javax.swing.JCheckBox;

public class Configuracion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JComboBox<String> comboBox,comboBox_1;
	public JCheckBox checkBox,checkBox_1;


	/**
	 * Create the frame.
	 */
	public Configuracion(Controlador controlador) {
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"}));
		comboBox.setBounds(225, 90, 40, 20);
		contentPane.add(comboBox);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setBounds(185, 51, 52, 28);
		contentPane.add(lblSettings);
		
		JLabel lblNivel = new JLabel("Nivel");
		lblNivel.setBounds(161, 86, 54, 28);
		contentPane.add(lblNivel);
		
		JLabel lblCantidadDeValores = new JLabel("Cantidad de valores en el Ranking");
		lblCantidadDeValores.setBounds(21, 125, 194, 20);
		contentPane.add(lblCantidadDeValores);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] {"3", "4", "5", "6", "7", "8", "9", "10"}));
		comboBox_1.setBounds(225, 125, 40, 20);
		contentPane.add(comboBox_1);
		
		checkBox = new JCheckBox("");
		checkBox.setBounds(271, 89, 97, 23);
		contentPane.add(checkBox);
		
		checkBox_1 = new JCheckBox("");
		checkBox_1.setBounds(271, 124, 97, 23);
		contentPane.add(checkBox_1);
		comboBox.addMouseListener(controlador);
		comboBox_1.addMouseListener(controlador);
		checkBox.addMouseListener(controlador);
		checkBox_1.addMouseListener(controlador);
		addWindowListener(controlador);
		

	}
}
