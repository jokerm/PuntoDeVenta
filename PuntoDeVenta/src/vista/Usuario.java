package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.alee.laf.StyleConstants;
import com.alee.laf.button.WebButton;
import com.alee.laf.panel.WebPanel;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Usuario extends JDialog {
	private static final long serialVersionUID = -7685859553228926764L;
	private JPanel contentPane;
	protected String myId;
	public WebTextField nombre;
	public WebPasswordField pass;
	private JCheckBox admin;
	private JCheckBox cajero;
	private JCheckBox corte;
	private JCheckBox dev;
	
	/**
	 * Create the frame.
	 */
	public Usuario() {
		setTitle("Usuario");
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				myId = null;
			}
		});
		setBounds(100, 100, 390, 370);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		WebPanel panel = new WebPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(20, 118, 258, 101);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
        panel.setUndecorated ( false );
		panel.setRound ( StyleConstants.largeRound );
		contentPane.add(panel);
		
		admin = new JCheckBox("admin");
		admin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(admin.isSelected()) {
					cajero.setEnabled(false);
					corte.setEnabled(false);
					dev.setEnabled(false);
				} else {
					cajero.setEnabled(true);
					corte.setEnabled(true);
					dev.setEnabled(true);
				}
			}
		});
		panel.add(admin);
		
		cajero = new JCheckBox("cajero");
		panel.add(cajero);
		
		corte = new JCheckBox("corte");
		panel.add(corte);
		
		dev = new JCheckBox("devoluciones");
		panel.add(dev);
		
		JLabel lblPermisos = new JLabel("Permisos");
		lblPermisos.setForeground(Color.RED);
		lblPermisos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPermisos.setBounds(20, 93, 70, 14);
		contentPane.add(lblPermisos);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(20, 18, 70, 14);
		contentPane.add(lblNombre);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(20, 63, 70, 14);
		contentPane.add(lblPassword);
		
		WebButton btnAceptar = new WebButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(nombre.getText().isEmpty() || pass.getPassword().length < 1) {
					GUI.displayError("Llene correctamente los campos", "Incompletos");
				} else {
					dispose();
				}
			}
		});
		btnAceptar.setRolloverDecoratedOnly(true);
		btnAceptar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/yes.png")));
		btnAceptar.setBounds(10, 230, 125, 46);
		contentPane.add(btnAceptar);
		
		WebButton btnCancelar = new WebButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myId = null;
				dispose();
			}
		});
		btnCancelar.setRolloverDecoratedOnly(true);
		btnCancelar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/no.png")));
		btnCancelar.setBounds(145, 230, 125, 46);
		contentPane.add(btnCancelar);
		
		nombre = new WebTextField();
		nombre.setInputPrompt("nombre");
		nombre.setBounds(91, 8, 187, 35);
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		pass = new WebPasswordField();
		pass.setInputPrompt("*****");
		pass.setBounds(91, 53, 187, 35);
		contentPane.add(pass);
		
	}

	public String getMyId() {
		return myId;
	}

	public void setMyId(String myId) {
		this.myId = myId;
	}
	
	public String getPerm() {
		String perm = "";
		if(admin.isSelected()) {
			perm = "admin";
		} else {
			perm = cajero.isSelected()?"cajero":"";
			perm += corte.isSelected()?";corte":"";
			perm += dev.isSelected()?";devoluciones":"";
		}
		return perm;
	}
	
	public void setPerm(String perm) {
		if(perm.contains("admin")) {
			admin.setSelected(true);
		}
		if(perm.contains("cajero")) {
			cajero.setSelected(true);
		}
		if(perm.contains("corte")) {
			corte.setSelected(true);
		}
		if(perm.contains("devoluciones")) {
			dev.setSelected(true);
		}
			
	}
}
