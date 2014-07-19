package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import vista.GUI;

import com.alee.laf.desktoppane.WebDesktopPane;

public class Proveedor extends Controlador {
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public Proveedor(WebDesktopPane dsk) {
		vista.Proveedores prov = new vista.Proveedores(modelo);
		prov.setControlador(this);
		dsk.add(prov);
		prov.open();
	}
	
	public void updateTable() {
		clearModel();
		try {
			ResultSet res = manager.getSelectValues("select * from proveedor where empresa_idempresa = '"+manager.getIdEmp()+"';");
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("idproveedor")	, i, 0);
				modelo.setValueAt(res.getString("nombre")		, i, 1);
				modelo.setValueAt(res.getString("telefono")		, i, 2);
				modelo.setValueAt(res.getString("direccion")	, i, 3);
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
			vista.Proveedor prov = new vista.Proveedor();
			prov.setLocationRelativeTo(null);
			try {
				prov.setMyId(manager.getValueofDB("IFNULL((select max(idproveedor) + 1 from proveedor " +
						"where empresa_idempresa = '"+manager.getIdEmp()+"'),1)"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			prov.setVisible(true);
			updateValues(prov);
			break;
		case "edit":
			try {
				ResultSet res = manager.getSelectValues("select * from proveedor where idproveedor = '"+
												args[1]+"' and empresa_idempresa = '"+manager.getIdEmp()+"';");
				vista.Proveedor prov2 = new vista.Proveedor();
				prov2.setLocationRelativeTo(null);
				
				prov2.setMyId(res.getString("idproveedor"));
				prov2.nombre.setText(res.getString("nombre"));
				prov2.setDir(res.getString("direccion"));
				prov2.telefono.setText(res.getString("telefono"));
				
				prov2.setVisible(true);
				updateValues(prov2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "delete":
			if(GUI.getBoolean("Seguro que desea borrarlo")) {
				try {
					this.manager.setUpdate("delete from proveedor where idproveedor = '"+
												args[1]+"' and empresa_idempresa = '"+manager.getIdEmp()+"';");
					this.updateTable();
				} catch (SQLException e) {
					GUI.displayError("Error "+ e.toString() + " al borrar", "DB Fail");
				}
			}			
			break;
		}
		
	}
	
	private void updateValues(vista.Proveedor prov) {
		if(prov.getMyId()!=null) {
			String sql = "REPLACE INTO proveedor values('"+prov.getMyId()+"',"+
							"'"+prov.nombre.getText()+"',"+
							"'"+prov.getDir()+"',"+
							"'"+prov.telefono.getText()+"',"+
							"'"+manager.getIdEmp()+"');";
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
