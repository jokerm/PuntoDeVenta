package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import vista.GUI;

import com.alee.laf.desktoppane.WebDesktopPane;

public class Cliente extends Controlador {
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public Cliente(WebDesktopPane dsk) {
		vista.Clientes cl = new vista.Clientes(modelo);
		cl.setControlador(this);
		dsk.add(cl);
		cl.open();
	}
	
	public void updateTable() {
		clearModel();
		try {
			ResultSet res = manager.getSelectValues("select * from cliente;");
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("idcliente")	, i, 0);
				modelo.setValueAt(res.getString("nombre")		, i, 1);
				modelo.setValueAt(res.getString("descuento")+"%", i, 2);
				modelo.setValueAt(res.getString("credito")		, i, 3);
				modelo.setValueAt(res.getString("adeuda")		, i, 4);
				modelo.setValueAt(res.getString("abona")		, i, 5);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void clearModel() {
		modelo.setRowCount(0);
	}

	@Override
	public void iniAction(String... args) {
		
	}

	@Override
	public void dataToController(String... args) {
		switch(args[0]) {
		case "add":
			vista.Cliente cl = new vista.Cliente();
			cl.setLocationRelativeTo(null);
			try {
				cl.setMyId(manager.getValueofDB("IFNULL((select max(idcliente) + 1 from cliente),1)"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			cl.setAbona("0");
			cl.setAdeuda("0");
			cl.setVisible(true);
			updateValues(cl);
			break;
		case "edit":
			try {
				ResultSet res = manager.getSelectValues("select * from cliente where idcliente = '"+args[1]+"';");
				vista.Cliente cl2 = new vista.Cliente();
				cl2.setLocationRelativeTo(null);
				
				cl2.setMyId(res.getString("idcliente"));
				cl2.nombre.setText(res.getString("nombre"));
				cl2.porcentaje.setValue(new Integer(res.getString("descuento")));
				cl2.credito.setText(res.getString("credito"));
				cl2.setAdeuda(res.getString("adeuda"));
				cl2.setAbona(res.getString("abona"));
				
				cl2.setVisible(true);
				updateValues(cl2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "delete":
			if(GUI.getBoolean("Seguro que desea borrarlo")) {
				try {
					this.manager.setUpdate("delete from cliente where idcliente = '"+args[1]+"';");
					this.updateTable();
				} catch (SQLException e) {
					GUI.displayError("Error "+ e.toString() + " al borrar", "DB Fail");
				}
			}			
			break;
		}
		
	}
	
	private void updateValues(vista.Cliente cl) {
		if(cl.getMyId()!=null) {
			String sql = "REPLACE INTO cliente values('"+cl.getMyId()+"',"+
							"'"+cl.nombre.getText()+"',"+
							"'"+cl.porcentaje.getValue()+"',"+
							"'"+cl.credito.getText()+"',"+
							"'"+cl.getAdeuda()+"',"+
							"'"+cl.getAbona()+"');";
			try {
				this.manager.setInsert(sql);
				this.updateTable();
			} catch (SQLException e) {
				GUI.displayError("Error "+ e.toString() + " al grabar", "DB Fail");
			}
		}
	}

	@Override
	public void dataToView(String... args) {
		
	}
}
