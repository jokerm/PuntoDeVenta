package vista;

import com.alee.laf.button.WebButton;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.table.WebTable;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class Impuestos extends WebInternalFrame {
	private static final long serialVersionUID = 4338402715051733869L;
	private WebTable table;
	private controlador.Controlador controlador;

	/**
	 * Create the frame.
	 */
	public Impuestos(DefaultTableModel modelo) {
		super( "Impuestos",true, true, true, true );
		setFrameIcon(new ImageIcon(Impuestos.class.getResource("/vista/recursos/tax.png")));
		setBounds(100, 100, 450, 400);
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
		
		WebButton btnAceptar = new WebButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnAceptar.setBounds(350, 21, 80, 80);
		getContentPane().add(btnAceptar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 119, 420, 226);
		getContentPane().add(scrollPane);
		
		table = new WebTable();
		scrollPane.setViewportView(table);
		
		String[] x = {"Id","Nombre","Porcentaje","Valor fijo"};
		modelo.setColumnCount(x.length);
		modelo.setRowCount(0);
		modelo.setColumnIdentifiers(x);
		table.setModel(modelo);
	}
	
	public void setControlador(controlador.Controlador cont) {
		this.controlador = cont;
	}
}
