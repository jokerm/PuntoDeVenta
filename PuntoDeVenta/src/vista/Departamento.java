package vista;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.alee.laf.button.WebButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Toolkit;

public class Departamento extends JDialog {
	private static final long serialVersionUID = -941316524711903714L;
	private JPanel contentPane;
	protected String myId = null;
	public JTextField nombre;

	/**
	 * Create the frame.
	 */
	public Departamento() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Departamento.class.getResource("/vista/recursos/apartment.png")));
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				myId = null;
			}
		});
		setTitle("Departamento");
		setBounds(100, 100, 350, 200);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setIcon(new ImageIcon(Departamento.class.getResource("/vista/recursos/apartment.png")));
		lblNombre.setBounds(10, 18, 97, 35);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				nombre.setText(nombre.getText().toUpperCase());
			}
		});
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombre.setBounds(93, 16, 194, 35);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		WebButton btnAceptar = new WebButton("Aceptar");
		btnAceptar.setRolloverDecoratedOnly(true);
		btnAceptar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/yes.png")));
		btnAceptar.setBounds(27, 64, 125, 46);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nombre.getText().isEmpty()) {
					GUI.displayError("Llene todos los datos", "Faltan campos");
				} else {
					dispose();
				}
			}
		});
		
		WebButton btnCancelar = new WebButton("Cancelar");
		btnCancelar.setRolloverDecoratedOnly(true);
		btnCancelar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/no.png")));
		btnCancelar.setBounds(162, 64, 125, 46);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myId = null;
				dispose();
			}
		});
	}
	
	public String getMyId() {
		return this.myId;
	}
	
	public void setMyId(String id) {
		this.myId = id;
	}

}
