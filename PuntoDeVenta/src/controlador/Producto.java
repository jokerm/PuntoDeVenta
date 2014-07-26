package controlador;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import vista.GUI;

import com.alee.laf.desktoppane.WebDesktopPane;

public class Producto extends Controlador {
	private DefaultTableModel modelo = new DefaultTableModel();
	private String[] departamentos;
	private String[] impuestos;
	
	public Producto(WebDesktopPane dsk, DataBaseManager manager) {
		this.manager = manager;
		try {
			departamentos = new String[manager.rowCount("departamento")];
			ResultSet res = manager.getSelectValues("select nombre from departamento");
			int i = 0;
			while(res.next()) {
				departamentos[i] = res.getString("nombre");
				i++;
			}
			impuestos = new String[manager.rowCount("impuesto")];
			res = manager.getSelectValues("select nombre from impuesto");
			i = 0;
			while(res.next()) {
				impuestos[i] = res.getString("nombre");
				i++;
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		vista.Productos prod = new vista.Productos(modelo);
		prod.setControlador(this);
		dsk.add(prod);
		
		prod.setDepartamentos(departamentos);
		prod.setImpuestos(impuestos);
		prod.open();
	}
	
	public void updateTable() {
		clearModel();
		try {
			//The next line is for a new statment, because after this we need another query for other statment
			ResultSet res = manager.getNewStatement().executeQuery("select * from producto;");
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("idproducto")	, i, 0);
				modelo.setValueAt(res.getString("nombre")		, i, 1);
				modelo.setValueAt(res.getString("precio")		, i, 2);
				modelo.setValueAt(res.getString("medida")		, i, 3);
				modelo.setValueAt(getImpuesto(res.getString("impuesto_idimpuesto"))					, i, 4);
				modelo.setValueAt(getDepartamento(res.getString("departamento_iddepartamento"))		, i, 5);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTable(String sql) {
		clearModel();
		try {
			//The next line is for a new statment, because after this we need another query for other statment
			ResultSet res = manager.getNewStatement().executeQuery(sql);
			int i = 0;
			while ( res.next() ) {
				Object[] num ={};
				modelo.addRow(num);
				modelo.setValueAt(res.getString("idproducto")	, i, 0);
				modelo.setValueAt(res.getString("nombre")		, i, 1);
				modelo.setValueAt(res.getString("precio")		, i, 2);
				modelo.setValueAt(res.getString("medida")		, i, 3);
				modelo.setValueAt(getImpuesto(res.getString("impuesto_idimpuesto"))					, i, 4);
				modelo.setValueAt(getDepartamento(res.getString("departamento_iddepartamento"))		, i, 5);
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getImpuesto(String id) {
		ResultSet res;
		try {
			res = manager.getSelectValues("select nombre from impuesto where idimpuesto = '"+id+"'");
			return res.getString("nombre");
		} catch (SQLException e) {
			return "Excento";
		}
	}
	
	public String getDepartamento(String id) {
		ResultSet res;
		try {
			res = manager.getSelectValues("select nombre from departamento where iddepartamento = '"+id+"'");
			return res.getString("nombre");
		} catch (SQLException e) {
			return "No asignado";
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
			vista.Producto prod = new vista.Producto();
			prod.setLocationRelativeTo(null);
			try {				
				prod.setDepartamentos(departamentos);
				prod.setImpuestos(impuestos);
				boolean existe;
				do{
					prod.setVisible(true);
					if((existe=manager.exists("select nombre from producto where idproducto = '"+prod.getMyId()+"'"))) {
						GUI.displayError("El codigo de producto ya existe, utilízalo para encontrarlo","Duplicado");
					}
				}while(existe);			
				updateValues(prod);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				ResultSet res =	manager.getNewStatement().executeQuery("select * from producto where idproducto = '"+args[1]+"';");
				vista.Producto produ = new vista.Producto();
				produ.setLocationRelativeTo(null);
				produ.setDepartamentos(departamentos);
				produ.setImpuestos(impuestos);
				
				produ.setMyId(res.getString("idproducto"));
				produ.nombre.setText(res.getString("nombre"));
				produ.precio.setText(res.getString("precio"));
				produ.setUnidadMedida(res.getString("medida"));
				produ.setImpuesto(getImpuesto(res.getString("impuesto_idimpuesto")));
				produ.setDepartamento(getDepartamento(res.getString("departamento_iddepartamento")));
				
				produ.setVisible(true);
				updateValues(produ);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		case "delete":
			if(GUI.getBoolean("Seguro que desea borrarlo")) {
				try {
					this.manager.setUpdate("delete from producto where idproducto = '"+args[1]+"';");
					this.updateTable();
				} catch (SQLException e) {
					GUI.displayError("Error "+ e.toString() + " al borrar", "DB Fail");
				}
			}			
			break;
		case "todos":
			this.updateTable();
			break;
		case "buscar":
			this.updateTable("select * from producto where idproducto like "+
									"'%"+args[1]+"%' or nombre like "+
									"'%"+args[1]+"%';");
			break;
		case "dep":
			ResultSet rs;
			if(args[1].equals("Todos")) {this.updateTable(); break;}		
			String dep2 = args[1];
			try {
				rs = manager.getSelectValues("select iddepartamento from departamento where nombre = '"+dep2+"'");
				if(rs.next()) dep2 = rs.getString(1);
				this.updateTable("select * from producto where  departamento_iddepartamento = '"+dep2+"'");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "imp":
			ResultSet rs2;
			if(args[1].equals("Todos")) {this.updateTable(); break;}
			String imp2 = args[1];
			try {
				rs2 = manager.getSelectValues("select idimpuesto from impuesto where nombre = '"+imp2+"'");
				if(rs2.next()) imp2 = rs2.getString(1);
				this.updateTable("select * from producto where impuesto_idimpuesto = '"+imp2+"';");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		}
	}
	
	private void updateValues(vista.Producto prod) throws SQLException {
		if(prod.getMyId()!=null) {
			String imp = null,dep = null;
			ResultSet rs = manager.getSelectValues("select idimpuesto from impuesto where nombre = '"+prod.getImpuesto()+"'");
			if(rs.next()) imp = rs.getString(1);
			rs = manager.getSelectValues("select iddepartamento from departamento where nombre = '"+prod.getDepartamento()+"'");
			if(rs.next()) dep = rs.getString(1);
			String sql = "REPLACE INTO producto values('"+prod.getMyId()+"',"+
							"'"+prod.nombre.getText()+"',"+
							"'"+prod.precio.getText()+"',"+
							"'"+prod.getUnidadMEdida()+"',"+
							"'"+imp+"',"+
							"'"+dep+"');";
			try {
				this.manager.setInsert(sql);
				this.updateTable();
				GUI.displayMessage("Producto Guardado/Actualizado con éxito");
			} catch (SQLException e) {
				GUI.displayError("Error "+ e.toString() + " al grabar", "DB Fail");
			}
		}
	}

	@Override
	public void dataToView(String... args) {
		
	}
}
