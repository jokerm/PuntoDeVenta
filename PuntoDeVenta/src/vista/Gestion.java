package vista;
/*
 * This class is the interface to all Objects in administrator to :
 * Add, Edit and Delete
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import com.alee.laf.button.WebButton;
import com.alee.laf.desktoppane.WebInternalFrame;
import com.alee.laf.table.WebTable;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Insets;

public class Gestion extends WebInternalFrame {
	private static final long serialVersionUID = 4338402715051733869L;
	private ZebraJTable table;
	private controlador.Controlador controlador;

	/**
	 * Create the frame.
	 */
	public Gestion(DefaultTableModel modelo, String objeto) {
		super( "",true, true, true, true );
		setBounds(100, 100, 663, 492);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 21, 420, 324);
		setContentPane(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[] {462, 0};
		gbl_panel.rowHeights = new int[] {80, 55, 226, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel botonPanel = new JPanel();
		GridBagConstraints gbc_botonPanel = new GridBagConstraints();
		gbc_botonPanel.fill = GridBagConstraints.BOTH;
		gbc_botonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_botonPanel.gridx = 0;
		gbc_botonPanel.gridy = 0;
		panel.add(botonPanel, gbc_botonPanel);
		
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
		
		WebButton btnAceptar = new WebButton("Aceptar");
		botonPanel.setLayout(new GridLayout(0, 5, 0, 0));
		
		WebButton btnNewButton = new WebButton("Agregar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controlador.dataToController("add");
			}
		});
		botonPanel.add(btnNewButton);
		botonPanel.add(btnBorrar);
		botonPanel.add(btnModificar);
		
		Component verticalGlue = Box.createVerticalGlue();
		botonPanel.add(verticalGlue);
		botonPanel.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		
		Component horizontalGlue = Box.createHorizontalGlue();
		GridBagConstraints gbc_horizontalGlue = new GridBagConstraints();
		gbc_horizontalGlue.fill = GridBagConstraints.BOTH;
		gbc_horizontalGlue.insets = new Insets(0, 0, 5, 0);
		gbc_horizontalGlue.gridx = 0;
		gbc_horizontalGlue.gridy = 1;
		panel.add(horizontalGlue, gbc_horizontalGlue);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		panel.add(scrollPane, gbc_scrollPane);
		
		table = new ZebraJTable();
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		table.setAutoResizeMode(WebTable.AUTO_RESIZE_OFF);
		
		String[] x = {"Null"};
		switch(objeto) {
		case "Departamentos":
			this.setTitle("Departamentos");
			setFrameIcon(new ImageIcon(Departamentos.class.getResource("/vista/recursos/depa.png")));
			x = new String[]{"Id","Nombre"};
			break;
		}
		modelo.setColumnCount(x.length);
		modelo.setRowCount(0);
		modelo.setColumnIdentifiers(x);
	}
	
	public void setControlador(controlador.Controlador cont) {
		this.controlador = cont;
	}
}
