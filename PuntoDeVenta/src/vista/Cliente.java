package vista;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.button.WebButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class Cliente extends JDialog {
	private static final long serialVersionUID = -941316524711903714L;
	private JPanel contentPane;
	protected String myId = null;
	public JTextField nombre;
	public JSpinner porcentaje;
	protected String adeuda, abona;
	public JFormattedTextField credito;

	/**
	 * Create the frame.
	 */
	public Cliente() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				myId = null;
			}
		});
		setTitle("Cliente");
		setBounds(100, 100, 440, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 21, 46, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		nombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				nombre.setText(nombre.getText().toUpperCase());
			}
		});
		nombre.setBounds(83, 18, 276, 35);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblPorcentaje = new JLabel("Descuento");
		lblPorcentaje.setBounds(27, 63, 72, 14);
		contentPane.add(lblPorcentaje);
		
		porcentaje = new JSpinner();
		porcentaje.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		porcentaje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		porcentaje.setBounds(83, 63, 115, 35);
		contentPane.add(porcentaje);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(208, 63, 28, 35);
		contentPane.add(label);
		
		WebButton btnAceptar = new WebButton("Aceptar");
		btnAceptar.setRolloverDecoratedOnly(true);
		btnAceptar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/yes.png")));
		btnAceptar.setBounds(27, 150, 125, 46);
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
		btnCancelar.setBounds(162, 150, 125, 46);
		contentPane.add(btnCancelar);
		
		JLabel lblCredito = new JLabel("Credito");
		lblCredito.setBounds(27, 104, 72, 14);
		contentPane.add(lblCredito);
		
		credito = new JFormattedTextField();
		credito.setToolTipText("0 significa ilimitado");
		credito.setHorizontalAlignment(SwingConstants.RIGHT);
		credito.setFont(new Font("Tahoma", Font.PLAIN, 16));
		credito.setColumns(10);
		credito.setBounds(83, 104, 115, 35);
		credito.setValue(new Float(0.0));
		contentPane.add(credito);
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

	public String getAdeuda() {
		return adeuda;
	}

	public void setAdeuda(String adeuda) {
		this.adeuda = adeuda;
	}

	public String getAbona() {
		return abona;
	}

	public void setAbona(String abona) {
		this.abona = abona;
	}
}
