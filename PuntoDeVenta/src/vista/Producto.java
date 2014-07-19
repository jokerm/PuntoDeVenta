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
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JRadioButton;
import javax.swing.JComboBox;

public class Producto extends JDialog {
	private static final long serialVersionUID = -3496422637963314268L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField nombre;
	private JLabel lblPrecio;
	private JFormattedTextField precio;

	/**
	 * Create the frame.
	 */
	public Producto() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Producto.class.getResource("/vista/recursos/compras.png")));
		setBounds(100, 100, 490, 300);
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
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField.setBounds(158, 11, 250, 31);
		contentPane.add(textField);
		textField.setColumns(10);
		
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
		precio.setBounds(48, 104, 106, 31);
		precio.setValue(new Float(0.0));
		contentPane.add(precio);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(10, 165, 226, 85);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		ButtonGroup group = new ButtonGroup();
		JRadioButton rdbtnKilogramos = new JRadioButton("Kilogramos");
		panel.add(rdbtnKilogramos);
		group.add(rdbtnKilogramos);
		
		JRadioButton rdbtnLitros = new JRadioButton("Litros");
		panel.add(rdbtnLitros);
		group.add(rdbtnLitros);
		
		JRadioButton rdbtnUnidades = new JRadioButton("Unidades");
		panel.add(rdbtnUnidades);
		group.add(rdbtnUnidades);
		
		JRadioButton rdbtnOtro = new JRadioButton("Otro");
		panel.add(rdbtnOtro);
		group.add(rdbtnOtro);
		
		JLabel lblUnidadDeMedida = new JLabel("Unidad de medida :");
		lblUnidadDeMedida.setBounds(48, 146, 122, 14);
		contentPane.add(lblUnidadDeMedida);
		
		JLabel lblImpuesto = new JLabel("Impuesto :");
		lblImpuesto.setBounds(190, 107, 72, 14);
		contentPane.add(lblImpuesto);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(251, 102, 157, 35);
		contentPane.add(comboBox);
	}
}
