package vista;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import com.alee.laf.button.WebButton;

public class Producto extends JDialog {
	private static final long serialVersionUID = -3496422637963314268L;
	private JPanel contentPane;
	public JTextField codigo;
	public JTextField nombre;
	private JLabel lblPrecio;
	public JFormattedTextField precio;
	protected String myId;
	private JRadioButton kilos;
	private JRadioButton litros;
	private JRadioButton unidades;
	private JRadioButton otros;
	private JComboBox<String> impuesto;
	private JComboBox<String> departamento;

	/**
	 * Create the frame.
	 */
	public Producto() {
		setModal(true);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Producto.class.getResource("/vista/recursos/compras.png")));
		setBounds(100, 100, 470, 410);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Producto.class.getResource("/vista/recursos/compras2.png")));
		label.setBounds(10, 11, 64, 76);
		contentPane.add(label);
		
		JLabel lblCodigo = new JLabel("Codigo :");
		lblCodigo.setBounds(84, 21, 46, 14);
		contentPane.add(lblCodigo);
		
		codigo = new JTextField();
		codigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		codigo.setBounds(158, 11, 250, 31);
		contentPane.add(codigo);
		codigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre : ");
		lblNombre.setBounds(84, 55, 70, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				nombre.setText(nombre.getText().toUpperCase());
			}
		});
		nombre.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nombre.setColumns(10);
		nombre.setBounds(158, 50, 250, 31);
		contentPane.add(nombre);
		
		lblPrecio = new JLabel("Precio :");
		lblPrecio.setBounds(10, 107, 46, 14);
		contentPane.add(lblPrecio);
		
		precio = new JFormattedTextField();
		precio.setHorizontalAlignment(SwingConstants.RIGHT);
		precio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		precio.setColumns(10);
		precio.setBounds(50, 104, 80, 31);
		precio.setValue(new Float(0.0));
		contentPane.add(precio);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 165, 226, 85);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		ButtonGroup group = new ButtonGroup();
		kilos = new JRadioButton("Kilogramos");
		panel.add(kilos);
		group.add(kilos);
		
		litros = new JRadioButton("Litros");
		panel.add(litros);
		group.add(litros);
		
		unidades = new JRadioButton("Unidades");
		panel.add(unidades);
		group.add(unidades);
		
		otros = new JRadioButton("Otro");
		panel.add(otros);
		group.add(otros);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida :");
		lblUnidadDeMedida.setBounds(48, 146, 122, 14);
		contentPane.add(lblUnidadDeMedida);
		
		JLabel lblImpuesto = new JLabel("Impuesto :");
		lblImpuesto.setBounds(292, 92, 72, 14);
		contentPane.add(lblImpuesto);
		
		impuesto = new JComboBox<String>();
		impuesto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		impuesto.setBounds(251, 125, 157, 35);
		impuesto.addItem("Excento");
		contentPane.add(impuesto);
		
		JLabel lblDepartamento = new JLabel("Departamento :");
		lblDepartamento.setBounds(266, 190, 121, 14);
		contentPane.add(lblDepartamento);
		
		departamento = new JComboBox<String>();
		departamento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departamento.setBounds(251, 215, 157, 35);
		departamento.addItem("Ninguno");
		contentPane.add(departamento);
		
		WebButton btnAceptar = new WebButton("Aceptar");
		btnAceptar.setRolloverDecoratedOnly(true);
		btnAceptar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/yes.png")));
		btnAceptar.setBounds(48, 271, 125, 46);
		contentPane.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(esValido()) {
					myId = codigo.getText();
					dispose();
				} else {
					GUI.displayError("Llene todos los datos", "Faltan campos");
				}
			}
		});
		
		WebButton btnCancelar = new WebButton("Cancelar");
		btnCancelar.setRolloverDecoratedOnly(true);
		btnCancelar.setIcon(new ImageIcon(Usuario.class.getResource("/vista/recursos/no.png")));
		btnCancelar.setBounds(239, 271, 125, 46);
		contentPane.add(btnCancelar);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				myId = null;
				dispose();
			}
		});

	}
	
	public boolean esValido() {
		if (nombre.getText().isEmpty()) return false;
		if (codigo.getText().isEmpty()) return false;
		if (getUnidadMEdida() == null) return false;
		return true;
	}
	
	public String getUnidadMEdida() {
		String unidad = null;
		if (kilos.isSelected()) 	unidad = kilos.getText();
		if (litros.isSelected()) 	unidad = litros.getText();
		if (unidades.isSelected()) 	unidad = unidades.getText();
		if (otros.isSelected()) 	unidad = otros.getText();
		return unidad;
	}
	
	public void setUnidadMedida(String unidad) {
		if (kilos.getText().equals(unidad)) 	kilos.setSelected(true);
		if (litros.getText().equals(unidad)) 	litros.setSelected(true);
		if (unidades.getText().equals(unidad)) 	unidades.setSelected(true);
		if (otros.getText().equals(unidad)) 	otros.setSelected(true);
	}
	
	public String getMyId() {
		return this.myId;
	}
	
	public void setMyId(String id) {
		codigo.setText(id);
		codigo.setEnabled(false);
	}
	
	public String getImpuesto() {
		return impuesto.getItemAt(impuesto.getSelectedIndex());
	}
	
	public String getDepartamento() {
		return departamento.getItemAt(departamento.getSelectedIndex());
	}
	
	public void setDepartamento(String dep) {
		departamento.setSelectedItem(dep);
	}
	
	public void setImpuesto(String imp) {
		impuesto.setSelectedItem(imp);
	}
	
	public void setDepartamentos(String[] args) {
		for(int i=0; i<args.length; i++) {
			departamento.addItem(args[i]);
		}
	}
	
	public void setImpuestos(String[] args) {
		for(int i=0; i<args.length; i++) {
			impuesto.addItem(args[i]);
		}		
	}
}
