package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import vista.GUI;

import com.alee.laf.desktoppane.WebDesktopPane;



public class Usuario extends Controlador {
	private DefaultTableModel modelo = new DefaultTableModel();
	
	public Usuario(WebDesktopPane dsk) {
		vista.Usuarios imp = new vista.Usuarios(modelo);
		imp.setControlador(this);
		dsk.add(imp);
		imp.open();
	}
	
	public void updateTable() {
		clearModel();
		try {
			ResultSet res = manager.getSelectValues("select * from usuario where empresa_idempresa = '"+manager.getIdEmp()+"';");
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("idusuario")	, i, 0);
				modelo.setValueAt(res.getString("nombre")		, i, 1);
				modelo.setValueAt(res.getString("privilegios")	, i, 2);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dataToController(String... args) {
		switch(args[0]) {
		case "add":
			vista.Usuario usr = new vista.Usuario();
			usr.setLocationRelativeTo(null);
			try {
				usr.setMyId(manager.getValueofDB("IFNULL((select max(idusuario) + 1 from usuario " +
						"where empresa_idempresa = '"+manager.getIdEmp()+"'),1)"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			usr.setVisible(true);
			updateValues(usr);
			break;
		case "edit":
			try {
				ResultSet res = manager.getSelectValues("select * from usuario where idusuario = '"+
									args[1]+"' and empresa_idempresa = '"+manager.getIdEmp()+"';");
				vista.Usuario usr2 = new vista.Usuario();
				usr2.setLocationRelativeTo(null);
				
				usr2.setMyId(res.getString("idusuario"));
				usr2.nombre.setText(res.getString("nombre"));
				usr2.pass.setText(res.getString("password"));
				usr2.setPerm(res.getString("privilegios"));
				
				usr2.setVisible(true);
				updateValues(usr2);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;			
		case "delete":
			if(GUI.getBoolean("Seguro que desea borrarlo")) {
				try {
					this.manager.setUpdate("delete from usuario where idusuario = '"+
											args[1]+"' and empresa_idempresa = '"+manager.getIdEmp()+"';");
					this.updateTable();
				} catch (SQLException e) {
					GUI.displayError("Error "+ e.toString() + " al borrar", "DB Fail");
				}
			}			
			break;
		}
		
	}

	@Override
	public void dataToView(String... args) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateValues(vista.Usuario usr) {
		if(usr.getMyId()!=null) {
			@SuppressWarnings("deprecation")
			String sql = "REPLACE INTO usuario values('"+usr.getMyId()+"',"+
							"'"+usr.nombre.getText()+"',"+
							"'"+usr.pass.getText()+"',"+
							"'"+usr.getPerm()+"',"+
							"'"+manager.getIdEmp()+"');";
			try {
				this.manager.setInsert(sql);
				this.updateTable();
			} catch (SQLException e) {
				GUI.displayError("Error "+ e.toString() + " al grabar", "DB Fail");
			}
		}
	}

}
