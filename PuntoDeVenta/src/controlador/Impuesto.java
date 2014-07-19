package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import vista.GUI;

import com.alee.laf.desktoppane.WebDesktopPane;

public class Impuesto extends Controlador{
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public Impuesto(WebDesktopPane dsk) {
		vista.Impuestos imp = new vista.Impuestos(modelo);
		imp.setControlador(this);
		dsk.add(imp);
		imp.open();
	}
	
	public void updateTable() {
		clearModel();
		try {
			ResultSet res = manager.getSelectValues("select * from impuesto;");
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("idimpuesto")		, i, 0);
				modelo.setValueAt(res.getString("nombre")			, i, 1);
				modelo.setValueAt(res.getString("porcentaje") + "%"	, i, 2);
				modelo.setValueAt(res.getString("valorFijo")		, i, 3);
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
			vista.Impuesto imp = new vista.Impuesto();
			imp.setLocationRelativeTo(null);
			try {
				imp.setMyId(manager.getValueofDB("IFNULL((select max(idimpuesto) + 1 from impuesto),1)"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			imp.setVisible(true);
			updateValues(imp);
			break;
		case "edit":
			try {
				ResultSet res = manager.getSelectValues("select * from impuesto where idimpuesto = '"+args[1]+"';");
				vista.Impuesto imp2 = new vista.Impuesto();
				imp2.setLocationRelativeTo(null);
				
				imp2.setMyId(res.getString("idimpuesto"));
				imp2.nombre.setText(res.getString("nombre"));
				imp2.porcentaje.setValue(new Integer(res.getString("porcentaje")));
				imp2.fijo.setText(res.getString("valorFijo"));
				imp2.setInicial();
				
				imp2.setVisible(true);
				updateValues(imp2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "delete":
			if(GUI.getBoolean("Seguro que desea borrarlo")) {
				try {
					this.manager.setUpdate("delete from impuesto where idimpuesto = '"+args[1]+"';");
					this.updateTable();
				} catch (SQLException e) {
					GUI.displayError("Error "+ e.toString() + " al borrar", "DB Fail");
				}
			}			
			break;
		}
		
	}
	
	private void updateValues(vista.Impuesto imp) {
		if(imp.getMyId()!=null) {
			String sql = "REPLACE INTO impuesto values('"+imp.getMyId()+"',"+
							"'"+imp.nombre.getText()+"',"+
							"'"+imp.porcentaje.getValue()+"',"+
							"'"+imp.fijo.getText()+"');";
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
