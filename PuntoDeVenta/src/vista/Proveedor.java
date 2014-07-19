package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import com.alee.laf.button.WebButton;
import com.alee.laf.text.WebTextField;

import java.awt.Toolkit;
import java.text.ParseException;

public class Proveedor extends JDialog {
	private static final long serialVersionUID = 7503835781243265647L;
	private JPanel contentPane;
	public WebTextField nombre;
	public JFormattedTextField telefono;
	private WebTextField calle;
	private WebTextField cp;
	private WebTextField muni;
	private WebTextField estado;
	protected String myId;

	public Proveedor() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Proveedor.class.getResource("/vista/recursos/shipping.png")));
		setTitle("Proveedor");
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				myId = null;
			}
		});
		setBounds(100, 100, 460, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(68, 19, 59, 14);
		contentPane.add(lblNombre);
		
		nombre = new WebTextField();
		nombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				nombre.setText(nombre.getText().toUpperCase());
			}
		});
		nombre.setBounds(133, 11, 256, 30);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblRfc = new JLabel("Telefono :");
		lblRfc.setBounds(68, 60, 77, 14);
		contentPane.add(lblRfc);
		
		MaskFormatter mf2 = null;
		try {
			mf2 = new MaskFormatter("(###) ###-####");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		telefono = new JFormattedTextField(mf2);
		telefono.setColumns(13);
		telefono.setBounds(133, 52, 100, 30);
		contentPane.add(telefono);
		
		JLabel lblDomicilio = new JLabel("Domicilio :");
		lblDomicilio.setBounds(133, 93, 100, 14);
		contentPane.add(lblDomicilio);
		
		calle = new WebTextField();
		calle.setInputPrompt("Calle y No.");
		calle.setColumns(10);
		calle.setBounds(10, 115, 200, 30);
		contentPane.add(calle);
		
		cp = new WebTextField();
		cp.setInputPrompt("C.P.");
		cp.setColumns(10);
		cp.setBounds(247, 115, 142, 30);
		contentPane.add(cp);
		
		muni = new WebTextField();
		muni.setInputPrompt("Municipio");
		muni.setColumns(10);
		muni.setBounds(68, 156, 142, 30);
		contentPane.add(muni);
		
		estado = new WebTextField();
		estado.setInputPrompt("Estado");
		estado.setColumns(10);
		estado.setBounds(247, 156, 142, 30);
		contentPane.add(estado);
		
		WebButton btnAceptar = new WebButton("Aceptar");
		btnAceptar.setRolloverDecoratedOnly(true);
		btnAceptar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/yes.png")));
		btnAceptar.setBounds(85, 197, 125, 46);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isEmpty()) {
					GUI.displayError("Llene todos los datos", "Faltan campos");
				} else {
					dispose();
				}
			}
		});
		
		WebButton btnCancelar = new WebButton("Cancelar");
		btnCancelar.setRolloverDecoratedOnly(true);
		btnCancelar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/no.png")));
		btnCancelar.setBounds(247, 197, 125, 46);
		contentPane.add(btnCancelar);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Proveedor.class.getResource("/vista/recursos/shipping.png")));
		label.setBounds(10, 19, 59, 46);
		contentPane.add(label);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myId = null;
				dispose();
			}
		});
	}
	
	public boolean isEmpty() {
		if(nombre.getText().isEmpty()) return true;
		if(telefono.getText().isEmpty()) return true;
		if(calle.getText().isEmpty()) return true;
		if(cp.getText().isEmpty()) return true;
		if(muni.getText().isEmpty()) return true;
		if(estado.getText().isEmpty()) return true;
		return false;
	}
	
	public void setDir(String dir) {
		String[] tmp = dir.split(";");
		calle.setText(tmp[0]);
		cp.setText(tmp[1]);
		muni.setText(tmp[2]);
		estado.setText(tmp[3]);
	}
	
	public String getDir() {
		return calle.getText() + ";C.P. " + cp.getText() + ";" + muni.getText() + ";" + estado.getText();
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}
	
	
}
