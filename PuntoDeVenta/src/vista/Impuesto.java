package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import java.awt.Font;

import javax.swing.JFormattedTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.JCheckBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.alee.laf.button.WebButton;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.SwingConstants;

public class Impuesto extends JDialog {
	private static final long serialVersionUID = 482778066274063991L;
	private JPanel contentPane;
	protected String myId = null;
	public JTextField nombre;
	private JCheckBox na;
	public JFormattedTextField fijo;
	public JSpinner porcentaje;

	/**
	 * Create the frame.
	 */
	public Impuesto() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				myId = null;
			}
		});
		setTitle("Impuesto");
		setBounds(100, 100, 320, 290);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(27, 21, 46, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				nombre.setText(nombre.getText().toUpperCase());
			}
		});
		nombre.setBounds(119, 18, 115, 20);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblPorcentaje = new JLabel("Porcentaje");
		lblPorcentaje.setBounds(27, 63, 72, 14);
		contentPane.add(lblPorcentaje);
		
		porcentaje = new JSpinner();
		porcentaje.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		porcentaje.setFont(new Font("Tahoma", Font.PLAIN, 16));
		porcentaje.setBounds(119, 51, 115, 35);
		contentPane.add(porcentaje);
		
		JLabel label = new JLabel("%");
		label.setFont(new Font("Tahoma", Font.BOLD, 16));
		label.setBounds(244, 51, 28, 35);
		contentPane.add(label);
		
		JLabel lblValorFijo = new JLabel("Valor fijo");
		lblValorFijo.setToolTipText("En caso de ser un valor fijo y el porcentaje no aplica");
		lblValorFijo.setBounds(36, 120, 63, 14);
		contentPane.add(lblValorFijo);
		
		fijo = new JFormattedTextField();
		fijo.setHorizontalAlignment(SwingConstants.RIGHT);
		fijo.setEnabled(false);
		fijo.setBounds(119, 117, 115, 20);
		fijo.setValue(Math.PI);
		contentPane.add(fijo);
		
		na = new JCheckBox("N/A");
		na.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(na.isSelected()) {
					fijo.setEnabled(true);
					porcentaje.setEnabled(false);
				} else {
					fijo.setEnabled(false);
					porcentaje.setEnabled(true);
				}
			}
		});
		na.setToolTipText("En caso de que porcentaje no aplica");
		na.setBounds(68, 84, 63, 23);
		contentPane.add(na);
		
		WebButton btnAceptar = new WebButton("Aceptar");
		btnAceptar.setRolloverDecoratedOnly(true);
		btnAceptar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/yes.png")));
		btnAceptar.setBounds(10, 150, 125, 46);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esValido()) {
					if(fijo.isEnabled()) {
						porcentaje.setValue(0);
					} else {
						fijo.setText("0");
					}
					dispose();
				} else {
					GUI.displayError("Llene todos los datos", "Faltan campos");
				}
			}
		});
		
		WebButton btnCancelar = new WebButton("Cancelar");
		btnCancelar.setRolloverDecoratedOnly(true);
		btnCancelar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/no.png")));
		btnCancelar.setBounds(137, 150, 125, 46);
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
	
	public boolean esValido() {
		if(nombre.getText().isEmpty()) return false;
		if(this.fijo.isEnabled() && this.fijo.getText().isEmpty()) return false;
		return true;
	}

	public void setInicial() {
		if(!fijo.getText().equals("0.0")) {
			fijo.setEnabled(true);
			this.na.setSelected(true);
			porcentaje.setEnabled(false);
		}
	}
}
