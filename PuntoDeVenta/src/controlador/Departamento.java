package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import vista.GUI;

import com.alee.laf.desktoppane.WebDesktopPane;

public class Departamento extends Controlador{
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public Departamento(WebDesktopPane dsk) {
		vista.Gestion dep = new vista.Gestion(modelo,"Departamentos");
		dep.setControlador(this);
		dsk.add(dep);
		dep.open();
	}
	
	public void updateTable() {
		clearModel();
		try {
			ResultSet res = manager.getSelectValues("select * from departamento  where empresa_idempresa = '"+manager.getIdEmp()+"';");
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("iddepartamento")	, i, 0);
				modelo.setValueAt(res.getString("nombre")			, i, 1);
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
			vista.Departamento dep = new vista.Departamento();
			dep.setLocationRelativeTo(null);
			try {
				dep.setMyId(manager.getValueofDB("IFNULL((select max(iddepartamento) + 1 from departamento),1)"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			dep.setVisible(true);
			updateValues(dep);
			break;
		case "edit":
			try {
				ResultSet res = manager.getSelectValues("select * from departamento where iddepartamento = '"+
						args[1]+"' and empresa_idempresa = '"+manager.getIdEmp()+"';");
				vista.Departamento dep2 = new vista.Departamento();
				dep2.setLocationRelativeTo(null);
				
				dep2.setMyId(res.getString("iddepartamento"));
				dep2.nombre.setText(res.getString("nombre"));
				
				dep2.setVisible(true);
				updateValues(dep2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "delete":
			if(GUI.getBoolean("Seguro que desea borrarlo")) {
				try {
					this.manager.setUpdate("delete from departamento where iddepartamento = '"+
							args[1]+"' and empresa_idempresa = '"+manager.getIdEmp()+"';");
					this.updateTable();
				} catch (SQLException e) {
					GUI.displayError("Error "+ e.toString() + " al borrar", "DB Fail");
				}
			}			
			break;
		}
		
	}
	
	private void updateValues(vista.Departamento dep) {
		if(dep.getMyId()!=null) {
			String sql = "REPLACE INTO departamento values('"+dep.getMyId()+"',"+
							"'"+dep.nombre.getText()+"',"+
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
