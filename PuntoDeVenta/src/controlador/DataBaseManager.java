package controlador;

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
	
	public boolean onCreate() throws SQLException {
		String t1 = 
				"CREATE TABLE IF NOT EXISTS `empresa` ( "+
				"`idempresa` INT NOT NULL , "+
				"`nombre` VARCHAR(45) NULL, "+
				"`creacion` DATE NULL, "+
				"`logo` BLOB NULL, "+
				"`domicilio` TEXT NULL, "+
				"`rfc` VARCHAR(45) NULL, "+
				"PRIMARY KEY (`idempresa`));";
		String t2 = 
				"CREATE TABLE IF NOT EXISTS `impuesto` ( "+
				"`idimpuesto` INT NOT NULL , "+
				"`nombre` VARCHAR(45) NULL, "+
				"`porcentaje` INT NULL, "+
				"`valorFijo` FLOAT NULL, "+
				"PRIMARY KEY (`idimpuesto`));";
		String t3 =
				"CREATE TABLE IF NOT EXISTS `departamento` ( "+
				"`iddepartamento` INT NOT NULL , "+
				"`nombre` VARCHAR(45) NULL, "+
				"`empresa_idempresa` INT NOT NULL, "+
				"PRIMARY KEY (`iddepartamento`), "+ 
				"FOREIGN KEY (`empresa_idempresa`) "+
				"REFERENCES `empresa` (`idempresa`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION);";
		String t4 =
				"CREATE TABLE IF NOT EXISTS `producto` ( "+
				"`idproducto` VARCHAR(45) NOT NULL, "+
				"`nombre` VARCHAR(45) NULL, "+
				"`precio` FLOAT NULL, "+
				"`medida` VARCHAR(45) NULL, "+
				"`impuesto_idimpuesto` INT NOT NULL, "+
				"`departamento_iddepartamento` INT NOT NULL, "+
				"PRIMARY KEY (`idproducto`), "+
				"FOREIGN KEY (`impuesto_idimpuesto`) "+
				"REFERENCES `impuesto` (`idimpuesto`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION, "+
				"FOREIGN KEY (`departamento_iddepartamento`) "+
				"REFERENCES `departamento` (`iddepartamento`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION);";
		String t5 =
				"CREATE TABLE IF NOT EXISTS `cliente` ( "+
				"`idcliente` INT NOT NULL , "+
				"`nombre` VARCHAR(45) NULL, "+
				"`descuento` INT NULL, "+
				"`credito` FLOAT NULL, "+
				"`adeuda` FLOAT NULL, "+
				"`abona` FLOAT NULL, "+
				"PRIMARY KEY (`idcliente`));";
		String t6 = 
				"CREATE TABLE IF NOT EXISTS `venta` ( "+
				"`idventa` VARCHAR(200) NOT NULL, "+
				"`fecha` DATETIME NULL, "+
				"`cliente_idcliente` INT NOT NULL, "+
				"`pagado` TINYINT(1) NULL, "+
				"PRIMARY KEY (`idventa`), "+
				"FOREIGN KEY (`cliente_idcliente`) "+
				"REFERENCES `cliente` (`idcliente`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION);";
		String t7 = 
				"CREATE TABLE IF NOT EXISTS `detalleProducto` ( "+
				"`producto_idproducto` VARCHAR(45) NOT NULL, "+
				"`tipo` VARCHAR(45) NULL, "+
				"`detalle` VARCHAR(45) NULL, "+
				"PRIMARY KEY (`producto_idproducto`), "+
				"FOREIGN KEY (`producto_idproducto`) "+
				"REFERENCES `producto` (`idproducto`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION);";	
		String t8 =
				"CREATE TABLE IF NOT EXISTS `corte` ( "+
				"`idcorte` VARCHAR(200) NOT NULL, "+
				"`fecha` DATETIME NULL, "+
				"PRIMARY KEY (`idcorte`));";
		String t9 =
				"CREATE TABLE IF NOT EXISTS `usuario` ( "+
				"`idusuario` INT NOT NULL , "+
				"`nombre` VARCHAR(45) NULL, "+
				"`password` VARCHAR(45) NULL, "+
				"`privilegios` VARCHAR(45) NULL, "+
				"`empresa_idempresa` INT NOT NULL, "+
				"PRIMARY KEY (`idusuario`), "+
				"FOREIGN KEY (`empresa_idempresa`) "+
				"REFERENCES `empresa` (`idempresa`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION); ";
		String t10 = 
				"CREATE TABLE IF NOT EXISTS `proveedor` ( "+
				"`idproveedor` INT NOT NULL , "+
				"`nombre` VARCHAR(45) NULL, "+
				"`direccion` VARCHAR(256) NULL, "+
				"`telefono` VARCHAR(45) NULL, "+
				"`empresa_idempresa` INT NOT NULL, "+
				"PRIMARY KEY (`idproveedor`), "+
				"FOREIGN KEY (`empresa_idempresa`) "+
				"REFERENCES `empresa` (`idempresa`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION); ";
		String t11 =
				"CREATE TABLE IF NOT EXISTS `salidas` ( "+
				"`idsalidas` VARCHAR(200) NOT NULL, "+
				"`fecha` VARCHAR(45) NULL, "+
				"`cantidad` FLOAT NULL, "+
				"`proveedor_idproveedor` INT NOT NULL, "+
				"`detalles` VARCHAR(255) NULL, "+
				"PRIMARY KEY (`idsalidas`), "+
				"FOREIGN KEY (`proveedor_idproveedor`) "+
				"REFERENCES `proveedor` (`idproveedor`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION); ";
		String t12 =
				"CREATE TABLE IF NOT EXISTS `ticket` ( "+
				"`producto_idproducto` VARCHAR(45) NOT NULL, "+
				"`venta_idventa` VARCHAR(200) NOT NULL, "+
				"`corte_idcorte` VARCHAR(200) NOT NULL, "+
				"`tipoventa` VARCHAR(45) NULL, "+
				"`detalle` VARCHAR(45) NULL, "+
				"`precio` FLOAT NULL, "+
				"`porcentaje` INT NULL, "+
				"`nombreimpuesto` VARCHAR(45) NULL, "+
				"PRIMARY KEY (`producto_idproducto`, `venta_idventa`, `corte_idcorte`), "+
				"FOREIGN KEY (`producto_idproducto`) "+
				"REFERENCES `producto` (`idproducto`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION, "+
				"FOREIGN KEY (`venta_idventa`) "+
				"REFERENCES `venta` (`idventa`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION, "+
				"FOREIGN KEY (`corte_idcorte`) "+
				"REFERENCES `corte` (`idcorte`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION); ";
		String t13 = 
				"CREATE TABLE IF NOT EXISTS `inventario` ( "+
				"`proveedor_idproveedor` INT NOT NULL, "+
				"`producto_idproducto` VARCHAR(45) NOT NULL, "+
				"`cantidadMinima` FLOAT NULL, "+
				"`cantidadExistencia` FLOAT NULL, "+
				"`ultimoPedido` DATE NULL, "+
				"`precioReal` FLOAT NULL, "+
				"`unidadMedida` VARCHAR(45) NULL, "+
				"`precioUnitario` FLOAT NULL, "+
				"PRIMARY KEY (`proveedor_idproveedor`, `producto_idproducto`), "+
				"FOREIGN KEY (`proveedor_idproveedor`) "+
				"REFERENCES `proveedor` (`idproveedor`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION, "+
				"FOREIGN KEY (`producto_idproducto`) "+
				"REFERENCES `producto` (`idproducto`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION); ";
		String t14 = 
				"CREATE TABLE IF NOT EXISTS `moneda` ( "+
				"`idmoneda` INT NOT NULL, "+
				"`nombre` VARCHAR(45) NULL, "+
				"`pesos` FLOAT NULL, "+
				"`empresa_idempresa` INT NOT NULL, "+
				"PRIMARY KEY (`idmoneda`), "+
				"FOREIGN KEY (`empresa_idempresa`) "+
				"REFERENCES `empresa` (`idempresa`) "+
				"ON DELETE NO ACTION "+
				"ON UPDATE NO ACTION);";
		stmt = c.createStatement();
		System.out.println(stmt.executeUpdate(t1));
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
		
		return true;
	}
	
	public boolean onUpgrade() throws SQLException {
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
		System.out.println(stmt.executeUpdate(t1));
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
		
		return onCreate();
	}
	
	
}
