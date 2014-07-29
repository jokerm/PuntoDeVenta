package controlador;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseManager {
	private static final String DATABASE_NAME = "tpv.db";
	private static String idEmp;
	private Connection c = null;
	private Statement stmt = null;
	
	public DataBaseManager(String id) throws ClassNotFoundException, SQLException {
		idEmp = id;
		Class.forName("org.sqlite.JDBC");
		c = DriverManager.getConnection("jdbc:sqlite:"+DATABASE_NAME);
		c.setAutoCommit(true);
		stmt = c.createStatement();
	}
	
	public Statement getNewStatement() throws SQLException {
		return c.createStatement();
	}
	
	public String getIdEmp() {
		return idEmp;
	}
	
	public void setIdEmp(String id) {
		idEmp = id;
	}
	
	public boolean saveAndClose() throws SQLException {
		stmt.close();
		if(c.getAutoCommit()==false) {
		   c.commit();
		}
		c.close();
		return true;
	}
	
	public int rowCount(String table) throws SQLException {
		ResultSet rs = stmt.executeQuery("select count(*) as total from "+table);
		return rs.getInt(1);
	}
	
	public String getValueofDB(String req) throws SQLException {
		ResultSet rs = stmt.executeQuery("select "+req+";");
		return rs.getString(1);
	}
	
	public boolean exists(String sql) throws SQLException {
		ResultSet rs = stmt.executeQuery(sql);
		return rs.next();
	}
	
	public int setInsert(String sql) throws SQLException {
		return stmt.executeUpdate(sql);
	}
	
	public int setUpdate(String sql) throws SQLException {
		return stmt.executeUpdate(sql);
	}
	
	public ResultSet getSelectValues(String sql) throws SQLException {
		return stmt.executeQuery(sql);
	}
	
	public boolean onCreate() throws SQLException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(DataBaseManager.class.getResourceAsStream("/modelo/dbstructure.sql")));
		String tmp,DB = "";
		while((tmp = in.readLine()) != null) {
			DB += tmp;
		}
		in.close();
		stmt = c.createStatement();
		stmt.executeUpdate(DB);
		
		System.out.println("DB updated sucessful =)");
		return true;
	}
	
	public boolean onUpgrade() throws SQLException, IOException {
		String t1 = "DROP TABLE IF EXISTS `empresa` ;";
		String t2 = "DROP TABLE IF EXISTS `impuesto` ; ";
		String t3 =	"DROP TABLE IF EXISTS `departamento` ; ";
		String t4 =	"DROP TABLE IF EXISTS `producto` ; ";
		String t5 =	"DROP TABLE IF EXISTS `cliente` ; ";
		String t6 = "DROP TABLE IF EXISTS `venta` ; ";
		String t7 = "DROP TABLE IF EXISTS `detalleProducto` ; ";	
		String t8 =	"DROP TABLE IF EXISTS `corte` ; ";
		String t9 =	"DROP TABLE IF EXISTS `usuario` ; ";
		String t10 = "DROP TABLE IF EXISTS `proveedor` ; ";
		String t11 = "DROP TABLE IF EXISTS `salidas` ; ";
		String t12 = "DROP TABLE IF EXISTS `ticket` ; ";
		String t13 = "DROP TABLE IF EXISTS `inventario` ; ";
		String t14 = "DROP TABLE IF EXISTS `moneda` ; ";
		
		stmt = c.createStatement();
		stmt.executeUpdate(t1);
		stmt.executeUpdate(t2);
		stmt.executeUpdate(t3);
		stmt.executeUpdate(t4);
		stmt.executeUpdate(t5);
		stmt.executeUpdate(t6);
		stmt.executeUpdate(t7);
		stmt.executeUpdate(t8);
		stmt.executeUpdate(t9);
		stmt.executeUpdate(t10);
		stmt.executeUpdate(t11);
		stmt.executeUpdate(t12);
		stmt.executeUpdate(t13);
		stmt.executeUpdate(t14);
		
		System.out.println("DB updated sucessful =)");
		return onCreate();
	}
	
	
}
