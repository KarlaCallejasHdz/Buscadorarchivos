package Buscador;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Ventana extends JFrame //implements ActionListener
{
	private JButton btnOk, btnLmp;
	private JPanel pnl, pnl2;
	public static JTextArea area;
	private JTextField txtBus, txtRuta;
	private JLabel lb1, lb2;
	private Buscador buscador;

	public Ventana() {
		super("Buscador");
		setSize(600,550);
		setLayout(new FlowLayout() );
		initComponents();
		setVisible(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initComponents() {

		pnl = new JPanel();
		pnl.setPreferredSize(new Dimension(500,120) );
		pnl2 = new JPanel();

		btnOk = new JButton("OK");
		btnOk.setPreferredSize(new Dimension(100,40));
		btnLmp = new JButton("Limpiar");
		btnLmp.setPreferredSize(new Dimension(100,40));
		btnLmp.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent ev){
				area.setText("");
				txtRuta.setText("");
				txtBus.setText("");
		}});

		EventoBuscar evB = new EventoBuscar();
		btnOk.addActionListener( evB );

		area = new JTextArea(20,40);
		txtBus = new JTextField(30);
		txtRuta = new JTextField(30);
		lb1 = new JLabel("Ruta Inicial: ");
		lb2 = new JLabel("Buscar:       ");

		pnl.add(lb1);
		pnl.add(txtRuta);
		pnl.add(lb2);
		pnl.add(txtBus);
		pnl.add(btnOk);
		pnl.add(btnLmp);
		pnl2.add(area);
		add(pnl);
		add(pnl2);

	}

	class EventoBuscar implements ActionListener {
		public void actionPerformed ( ActionEvent ev ) {
			if( txtBus.getText().isEmpty() ) {
				JOptionPane.showMessageDialog(null,"No se ha ingresado"+
							" cadena de busqueda","BUSCADOR: ERROR",0);
				return;
			}
			buscador = new Buscador();
			buscador.setRuta( txtRuta.getText() );
			buscador.setNombreArch( txtBus.getText() );
			
			buscador.start();

		}
	}

}
