package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.alee.laf.button.WebButton;
import com.alee.laf.label.WebLabel;
import com.alee.laf.text.WebPasswordField;
import com.alee.laf.text.WebTextField;

import java.awt.Font;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class Login extends JDialog {
	private static final long serialVersionUID = 4330590175575518607L;
	private JPanel contentPane;
	public WebTextField user;
	public WebPasswordField pass;
	protected int intentos = 2;
	private WebButton btnAceptar;

	public Login() {
		setModal(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent arg0) {
				btnAceptar.grabFocus();
			}
			@Override
			public void windowClosing(WindowEvent arg0) {
				intentos = -1;
			}
		});
		setTitle("Login area");
		setBounds(100, 100, 350, 330);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		WebLabel lblNewLabel = new WebLabel("Control de acceso");
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD, 17));
		lblNewLabel.setBounds(55, 11, 163, 30);
		contentPane.add(lblNewLabel);
		
		WebLabel lblUsuario = new WebLabel("");
		lblUsuario.setIcon(new ImageIcon(Login.class.getResource("/vista/recursos/user.png")));
		lblUsuario.setBounds(33, 51, 58, 62);
		contentPane.add(lblUsuario);
		
		user = new WebTextField();
		user.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				user.setText("");
			}
		});
		user.setInputPrompt("Usuario");
		user.setBounds(101, 65, 180, 30);
		contentPane.add(user);
		user.setColumns(10);
		
		pass = new WebPasswordField();
		pass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				pass.setText("");
			}
		});
		pass.setInputPrompt("********");
		pass.setColumns(10);
		pass.setBounds(101, 139, 180, 30);
		contentPane.add(pass);
		
		WebLabel webLabel = new WebLabel("");
		webLabel.setIcon(new ImageIcon(Login.class.getResource("/vista/recursos/key2.png")));
		webLabel.setBounds(45, 124, 27, 62);
		contentPane.add(webLabel);
		
		btnAceptar = new WebButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!user.getText().isEmpty() && !user.getText().isEmpty()) {
					dispose();
				} else {
					GUI.displayError("Llena ambos campos", "Incompleto");
				}
			}
		});
		btnAceptar.setText("Ok");
		btnAceptar.setIcon(new ImageIcon(Login.class.getResource("/vista/recursos/ok.png")));
		btnAceptar.setBounds(33, 197, 111, 41);
		btnAceptar.setRolloverDecoratedOnly(true);
		contentPane.add(btnAceptar);
		
		WebButton btnCancelar = new WebButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				intentos = -1;
				dispose();
			}
		});
		btnCancelar.setRolloverDecoratedOnly(true);
		btnCancelar.setText("No");
		btnCancelar.setIcon(new ImageIcon(Login.class.getResource("/vista/recursos/cancel.png")));
		btnCancelar.setBounds(170, 197, 111, 41);
		contentPane.add(btnCancelar);
	}
	
	public int getIntentos() {
		return intentos;
	}
	
	public void setIntentos(int i) {
		intentos = i;
	}
}
