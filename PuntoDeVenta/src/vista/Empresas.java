package vista;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import com.alee.laf.button.WebButton;
import com.alee.laf.scroll.WebScrollPane;
import com.alee.laf.table.WebTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Empresas extends JFrame {

	private static final long serialVersionUID = 8176415082213415549L;
	private WebTable table;
	private controlador.Controlador controlador;

	/**
	 * Create the frame.
	 */
	public Empresas(DefaultTableModel modelo) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Empresas.class.getResource("/vista/recursos/logo.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				controlador.dataToController("close");
			}
		});
		setBounds(100, 100, 420, 410);
		getContentPane().setLayout(null);
		
		WebButton btnNewButton = new WebButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.dataToController("add");
			}
		});
		btnNewButton.setBounds(10, 21, 80, 80);
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
		btnBorrar.setBounds(100, 21, 80, 80);
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
		btnModificar.setBounds(190, 21, 80, 80);
		getContentPane().add(btnModificar);
		
		WebButton btnAceptar = new WebButton("Cargar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargar();
			}
		});
		btnAceptar.setBounds(310, 21, 80, 80);
		getContentPane().add(btnAceptar);
		
		table = new WebTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() > 1) {
					cargar();
				}
			}
		});
		table.setEditable(false);
		WebScrollPane scrollPane = new WebScrollPane(table);
		scrollPane.setBounds(10, 119, 380, 242);
		getContentPane().add(scrollPane);
		scrollPane.setViewportView(table);
		table.setAutoCreateRowSorter(true);
		table.setAutoResizeMode ( WebTable.AUTO_RESIZE_OFF );
		
		String[] x = {"Id","Nombre","Fecha creacion","RFC","Domicilio"};
		modelo.setColumnCount(x.length);
		modelo.setRowCount(0);
		modelo.setColumnIdentifiers(x);
		table.setModel(modelo);
	}
	
	private void cargar() {
		if(table.getSelectedRowCount() != 1) {
			GUI.displayError("Solo puede cargar uno a la vez", "Multiple");
		} else if(table.getSelectedRow()>=0) {
			controlador.dataToController("ok",""+table.getValueAt(table.getSelectedRow(),0));
			dispose();
		}
	}
	
	public void setControlador(controlador.Controlador cont) {
		this.controlador = cont;
	}

}
