package vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.button.WebButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;

public class Selector extends JFrame {
	private static final long serialVersionUID = 7188507530257987164L;
	private JPanel contentPane;
	private controlador.Controlador controlador;
	
	/**
	 * Create the frame.
	 */
	public Selector() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Selector.class.getResource("/vista/recursos/barcode.png")));
		setTitle("Selector de aplicaciones");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		WebButton btnNewButton = new WebButton("Administrador");
		btnNewButton.setRolloverDecoratedOnly(true);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				controlador.iniAction("A");
				dispose();
			}
		});
		btnNewButton.setBounds(31, 118, 143, 119);
		contentPane.add(btnNewButton);
		
		WebButton btnPuntoDeVenta = new WebButton("Punto de venta");
		btnPuntoDeVenta.setRolloverDecoratedOnly(true);
		btnPuntoDeVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				controlador.iniAction("P");
				dispose();
			}
		});
		btnPuntoDeVenta.setBounds(215, 118, 143, 119);
		contentPane.add(btnPuntoDeVenta);
		
		/*Visual effects to the frame*/		
		setLocationRelativeTo(null);
	}
	
	public void setControlador(controlador.Controlador cont) {
		this.controlador = cont;
	}
}
