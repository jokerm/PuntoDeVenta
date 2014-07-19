package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JButton;
import com.alee.laf.text.WebTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Empresa extends JDialog {
	private static final long serialVersionUID = -4034001827672923393L;
	private JPanel contentPane;
	public WebTextField nombre;
	public WebTextField rfc;
	public WebTextField fecha;
	public WebTextField calle;
	public WebTextField cp;
	public WebTextField muni;
	public WebTextField estado;
	public String myId;

	public Empresa() {
		setTitle("Empresa");
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				myId = null;
			}
		});
		setBounds(100, 100, 460, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogo = new JLabel("Logo");
		lblLogo.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(10, 11, 100, 80);
		contentPane.add(lblLogo);
		
		JButton btnCargar = new JButton("Cargar ...");
		btnCargar.setBounds(10, 94, 100, 23);
		contentPane.add(btnCargar);
		
		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setBounds(120, 19, 59, 14);
		contentPane.add(lblNombre);
		
		nombre = new WebTextField();
		nombre.setBounds(189, 11, 200, 30);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblRfc = new JLabel("RFC :");
		lblRfc.setBounds(133, 60, 46, 14);
		contentPane.add(lblRfc);
		
		rfc = new WebTextField();
		rfc.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				rfc.setText(rfc.getText().toUpperCase());
			}
		});
		rfc.setColumns(13);
		rfc.setBounds(189, 52, 200, 30);
		contentPane.add(rfc);
		
		JLabel lblFechaDeRegistro = new JLabel("Fecha de registro :");
		lblFechaDeRegistro.setBounds(133, 98, 100, 14);
		contentPane.add(lblFechaDeRegistro);
		
		fecha = new WebTextField();
		fecha.setEnabled(false);
		fecha.setColumns(10);
		fecha.setBounds(247, 93, 142, 30);
		contentPane.add(fecha);
		
		JLabel lblDomicilio = new JLabel("Domicilio :");
		lblDomicilio.setBounds(133, 138, 100, 14);
		contentPane.add(lblDomicilio);
		
		calle = new WebTextField();
		calle.setInputPrompt("Calle y No.");
		calle.setColumns(10);
		calle.setBounds(10, 160, 200, 30);
		contentPane.add(calle);
		
		cp = new WebTextField();
		cp.setInputPrompt("C.P.");
		cp.setColumns(10);
		cp.setBounds(247, 160, 142, 30);
		contentPane.add(cp);
		
		muni = new WebTextField();
		muni.setInputPrompt("Municipio");
		muni.setColumns(10);
		muni.setBounds(68, 201, 142, 30);
		contentPane.add(muni);
		
		estado = new WebTextField();
		estado.setInputPrompt("Estado");
		estado.setColumns(10);
		estado.setBounds(247, 201, 142, 30);
		contentPane.add(estado);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(isEmpty()) {
					GUI.displayMessage("Llene todos los datos");
				} else {
					dispose();
				}
			}
		});
		btnAceptar.setBounds(120, 242, 90, 70);
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myId = null;
				dispose();
			}
		});
		btnCancelar.setBounds(247, 242, 90, 70);
		contentPane.add(btnCancelar);
	}
	
	public boolean isEmpty() {
		if(nombre.getText().isEmpty()) return true;
		if(rfc.getText().isEmpty()) return true;
		if(calle.getText().isEmpty()) return true;
		if(cp.getText().isEmpty()) return true;
		if(muni.getText().isEmpty()) return true;
		if(estado.getText().isEmpty()) return true;
		return false;
	}
	
	public void setDate(String date) {
		fecha.setText(date);
	}

}
