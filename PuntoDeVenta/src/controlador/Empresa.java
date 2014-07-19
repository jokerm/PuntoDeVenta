package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import vista.GUI;

public class Empresa extends Controlador {
	private DefaultTableModel modelo;
	
	public Empresa() throws ClassNotFoundException, SQLException {
		//Start conn
		this.manager = new DataBaseManager(null);
		this.manager.onCreate();
		//
		modelo =  new DefaultTableModel();
		vista.Empresas emp  = new vista.Empresas(modelo);
		emp.setControlador(this);
		updateTable();
		emp.setLocationRelativeTo(null);
		emp.setVisible(true);
	}
	@Override
	public void iniAction(String... args) {
		
	}

	@SuppressWarnings("deprecation")
	@Override
	public void dataToController(String... args) {
		if(args[0].equals("close")) {
			try {
				System.out.print(this.manager.saveAndClose());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if(args[0].equals("add")) {
			vista.Empresa emp = new vista.Empresa();
			try {
				emp.setDate(manager.getValueofDB("date('now')"));
				emp.myId = manager.getValueofDB("IFNULL((select max(idempresa) + 1 from empresa),1)");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			emp.setLocationRelativeTo(null);
			emp.setVisible(true);
			updateValues(emp);
		} else if(args[0].equals("delete")) {
			if(GUI.getBoolean("Seguro que desea borrarlo")) {
				try {
					this.manager.setUpdate("delete from empresa where idempresa = '"+args[1]+"';");
					this.updateTable();
				} catch (SQLException e) {
					GUI.displayError("Error "+ e.toString() + " al borrar", "DB Fail");
				}
			}
		} else if(args[0].equals("edit")) {
			try {
				ResultSet res = manager.getSelectValues("select * from empresa where idempresa = '"+args[1]+"';");
				vista.Empresa emp = new vista.Empresa();
				
				emp.myId = res.getString("idempresa");
				emp.nombre.setText(res.getString("nombre"));
				emp.setDate(res.getString("creacion"));
				String[] tmp = res.getString("domicilio").split(";");
				emp.calle.setText(tmp[0]);
				emp.cp.setText(tmp[1]);
				emp.muni.setText(tmp[2]);
				emp.estado.setText(tmp[3]);
				emp.rfc.setText(res.getString("rfc"));
				
				emp.setModal(true);
				emp.setLocationRelativeTo(null);
				emp.setVisible(true);
				updateValues(emp);
			} catch (SQLException e) {
				GUI.displayError("Error "+ e.toString() + " al editar", "DB Fail");
			}
			
		} else if(args[0].equals("ok")) {
			this.manager.setIdEmp(args[1]);
			String sql = "select privilegios from usuario where empresa_idempresa = '"+args[1]+"' and privilegios = 'admin';";
			boolean empty = false;
			try {
				if(!this.manager.exists(sql)) {
					GUI.displayMessage("No existe administrador, cree uno");
					empty = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			vista.Login login = new vista.Login();
			login.setLocationRelativeTo(null);
			do {
				login.setVisible(true);
				if(login.getIntentos()<0) { //If cancel, exit all
					System.exit(1);
				} else {
					sql = "select privilegios from usuario where empresa_idempresa = '"+args[1]+"' and nombre = "+
							"'"+login.user.getText()+"' and password = '"+login.pass.getText()+"' and privilegios = 'admin';";
					try {
						if(empty) {			//If was empty, create user
							this.manager.setInsert("insert into usuario values((select count(idusuario) from usuario)+1,"+ 
									"'"+login.user.getText()+"','"+login.pass.getText()+"','admin','"+args[1]+"');");
							new Administrador(this.manager);
							login.setIntentos(-1);
											//Else, check if user match
						} else if(this.manager.exists(sql)) {
							new Administrador(this.manager);
							login.setIntentos(-1);
						} else {
							GUI.displayError("Usuario o password incorrecto", "Incorrect data");
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				login.setIntentos(login.getIntentos()-1);
			}while(login.getIntentos() >=0);
		}
	}
	
	private void updateValues(vista.Empresa emp) {
		if(emp.myId!=null) {
			String dir = emp.calle.getText() + ";C.P. " + emp.cp.getText() + ";" + emp.muni.getText() + ";" + emp.estado.getText();
			String sql = "REPLACE INTO empresa values('"+emp.myId+"',"+
							"'"+emp.nombre.getText()+"',"+
							"'"+emp.fecha.getText()+"',"+
							"null,"+
							"'"+dir+"',"+
							"'"+emp.rfc.getText()+"');";
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
	
	public void updateTable() {
		clearModel();
		try {
			ResultSet res = manager.getSelectValues("select * from empresa");
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("idempresa"), i, 0);
				modelo.setValueAt(res.getString("nombre")	, i, 1);
				modelo.setValueAt(res.getString("creacion")	, i, 2);
				modelo.setValueAt(res.getString("rfc")		, i, 3);
				modelo.setValueAt(res.getString("domicilio"), i, 4);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void clearModel() {
		modelo.setRowCount(0);
	}

}
