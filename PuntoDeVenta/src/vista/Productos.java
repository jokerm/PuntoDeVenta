package vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.alee.extended.image.WebImage;
import com.alee.laf.button.WebButton;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.table.WebTable;
import com.alee.laf.text.WebTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Productos extends WebInternalFrame {
	private static final long serialVersionUID = -141552633254327219L;
	private WebTable table;
	private controlador.Controlador controlador;
	private WebTextField busqueda;
	private JComboBox<String> departamento;
	private JComboBox<String> impuesto;

	/**
	 * Create the frame.
	 */
	public Productos(DefaultTableModel modelo) {
		super( "Productos",true, true, true, true );
		setFrameIcon(new ImageIcon(Productos.class.getResource("/vista/recursos/compras3.png")));
		setBounds(100, 100, 450, 400);
		getContentPane().setLayout(null);
		
		WebButton btnNewButton = new WebButton("Agregar");
		btnNewButton.setText("Nuevo");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.dataToController("add");
			}
		});
		btnNewButton.setBounds(338, 11, 80, 42);
		getContentPane().add(btnNewButton);
		
		WebButton btnBorrar = new WebButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount() != 1) {
					GUI.displayError("Solo puede borrar uno a la vez", "Multiple");
				} else if(table.getSelectedRow()>=0) {
					controlador.dataToController("delete",""+table.getValueAt(table.getSelectedRow(),0));
				}
			}
		});
		btnBorrar.setBounds(338, 59, 80, 42);
		getContentPane().add(btnBorrar);
		
		WebButton btnModificar = new WebButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(table.getSelectedRowCount() != 1) {
					GUI.displayError("Solo puede editar uno a la vez", "Multiple");
				} else if(table.getSelectedRow()>=0) {
					controlador.dataToController("edit",""+table.getValueAt(table.getSelectedRow(),0));
				}
			}
		});
		btnModificar.setBounds(242, 59, 80, 42);
		getContentPane().add(btnModificar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 136, 420, 209);
		getContentPane().add(scrollPane);
		
		table = new WebTable();
		scrollPane.setViewportView(table);
		
		String[] x = {"Codigo","Nombre","Precio","Medida","Impuesto","Departamento"};
		modelo.setColumnCount(x.length);
		modelo.setRowCount(0);
		modelo.setColumnIdentifiers(x);
		table.setModel(modelo);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode(WebTable.AUTO_RESIZE_OFF);
		
        // Text field with image as trailing component
		busqueda = new WebTextField ( "", 10 );
		busqueda.setInputPrompt("Codigo o nombre del producto");
		busqueda.setMargin ( 0, 0, 0, 2 );
		WebImage webImage = new WebImage ( new ImageIcon(Producto.class.getResource("/vista/recursos/search.png" )) );
		webImage.setToolTipText("Haga click en la lupa para buscar");
		webImage.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		webImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				busqueda.setText(busqueda.getText().toUpperCase());
				controlador.dataToController("buscar",busqueda.getText(),departamento.getSelectedItem()+"",impuesto.getSelectedIndex()+"");
			}
		});
		busqueda.setTrailingComponent ( webImage );
		busqueda.setBounds(10, 11, 210, 20);
		getContentPane().add(busqueda);
		busqueda.setColumns(10);
		
		JLabel lblBuscarPor = new JLabel("Buscar por :");
		lblBuscarPor.setBounds(10, 42, 101, 11);
		getContentPane().add(lblBuscarPor);
		
		JLabel lblDepartamento = new JLabel("Departamento :");
		lblDepartamento.setBounds(10, 73, 90, 14);
		getContentPane().add(lblDepartamento);
		
		JLabel lblImpuesto = new JLabel("Impuesto :");
		lblImpuesto.setBounds(130, 73, 90, 14);
		getContentPane().add(lblImpuesto);
		
		departamento = new JComboBox<String>();
		departamento.addItem("Todos");
		departamento.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				controlador.dataToController("dep",departamento.getItemAt(departamento.getSelectedIndex())+"");
			}
		});
		departamento.setBounds(10, 98, 90, 20);
		getContentPane().add(departamento);
		
		impuesto = new JComboBox<String>();
		impuesto.addItem("Todos");
		impuesto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				controlador.dataToController("imp",impuesto.getItemAt(impuesto.getSelectedIndex())+"");
			}
		});
		impuesto.setBounds(130, 98, 90, 20);
		getContentPane().add(impuesto);
		
		WebButton wbtnMostrarTodo = new WebButton("Agregar");
		wbtnMostrarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.dataToController("todos");
			}
		});
		wbtnMostrarTodo.setText("Todo el catalogo");
		wbtnMostrarTodo.setBounds(77, 34, 143, 26);
		getContentPane().add(wbtnMostrarTodo);
	}
	
	public void setControlador(controlador.Controlador cont) {
		this.controlador = cont;
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
