CREATE TABLE IF NOT EXISTS `empresa` ( `idempresa` INT NOT NULL , `nombre` VARCHAR(45) NULL, `creacion` DATE NULL, `logo` BLOB NULL, `domicilio` TEXT NULL, `rfc` VARCHAR(45) NULL, PRIMARY KEY (`idempresa`));
; 
CREATE TABLE IF NOT EXISTS `impuesto` ( `idimpuesto` INT NOT NULL , `nombre` VARCHAR(45) NULL, `porcentaje` INT NULL, `valorFijo` FLOAT NULL, PRIMARY KEY (`idimpuesto`));
; 
CREATE TABLE IF NOT EXISTS `departamento` ( `iddepartamento` INT NOT NULL , `nombre` VARCHAR(45) NULL, `empresa_idempresa` INT NOT NULL, PRIMARY KEY (`iddepartamento`), FOREIGN KEY (`empresa_idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `producto` ( `idproducto` VARCHAR(45) NOT NULL, `nombre` VARCHAR(45) NULL, `precio` FLOAT NULL, `medida` VARCHAR(45) NULL, `impuesto_idimpuesto` INT NOT NULL, `departamento_iddepartamento` INT NOT NULL, PRIMARY KEY (`idproducto`), FOREIGN KEY (`impuesto_idimpuesto`) REFERENCES `impuesto` (`idimpuesto`) ON DELETE NO ACTION ON UPDATE NO ACTION, FOREIGN KEY (`departamento_iddepartamento`) REFERENCES `departamento` (`iddepartamento`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `cliente` ( `idcliente` INT NOT NULL , `nombre` VARCHAR(45) NULL, `descuento` INT NULL, `credito` FLOAT NULL, `adeuda` FLOAT NULL, `abona` FLOAT NULL, PRIMARY KEY (`idcliente`));
; 
CREATE TABLE IF NOT EXISTS `venta` ( `idventa` VARCHAR(200) NOT NULL, `fecha` DATETIME NULL, `cliente_idcliente` INT NOT NULL, `pagado` TINYINT(1) NULL, PRIMARY KEY (`idventa`), FOREIGN KEY (`cliente_idcliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `detalleProducto` ( `producto_idproducto` VARCHAR(45) NOT NULL, `tipo` VARCHAR(45) NULL, `detalle` VARCHAR(45) NULL, PRIMARY KEY (`producto_idproducto`), FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `corte` ( `idcorte` VARCHAR(200) NOT NULL, `fecha` DATETIME NULL, PRIMARY KEY (`idcorte`));
; 
CREATE TABLE IF NOT EXISTS `usuario` ( `idusuario` INT NOT NULL , `nombre` VARCHAR(45) NULL, `password` VARCHAR(45) NULL, `privilegios` VARCHAR(45) NULL, `empresa_idempresa` INT NOT NULL, PRIMARY KEY (`idusuario`), FOREIGN KEY (`empresa_idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `proveedor` ( `idproveedor` INT NOT NULL , `nombre` VARCHAR(45) NULL, `direccion` VARCHAR(256) NULL, `telefono` VARCHAR(45) NULL, `empresa_idempresa` INT NOT NULL, PRIMARY KEY (`idproveedor`), FOREIGN KEY (`empresa_idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `salidas` ( `idsalidas` VARCHAR(200) NOT NULL, `fecha` VARCHAR(45) NULL, `cantidad` FLOAT NULL, `proveedor_idproveedor` INT NOT NULL, `detalles` VARCHAR(255) NULL, PRIMARY KEY (`idsalidas`), FOREIGN KEY (`proveedor_idproveedor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `ticket` ( `producto_idproducto` VARCHAR(45) NOT NULL, `venta_idventa` VARCHAR(200) NOT NULL, `corte_idcorte` VARCHAR(200) NOT NULL, `tipoventa` VARCHAR(45) NULL, `detalle` VARCHAR(45) NULL, `precio` FLOAT NULL, `porcentaje` INT NULL, `nombreimpuesto` VARCHAR(45) NULL, PRIMARY KEY (`producto_idproducto`, `venta_idventa`, `corte_idcorte`), FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION, FOREIGN KEY (`venta_idventa`) REFERENCES `venta` (`idventa`) ON DELETE NO ACTION ON UPDATE NO ACTION, FOREIGN KEY (`corte_idcorte`) REFERENCES `corte` (`idcorte`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `inventario` ( `proveedor_idproveedor` INT NOT NULL, `producto_idproducto` VARCHAR(45) NOT NULL, `cantidadMinima` FLOAT NULL, `cantidadExistencia` FLOAT NULL, `ultimoPedido` DATE NULL, `precioReal` FLOAT NULL, `unidadMedida` VARCHAR(45) NULL, `precioUnitario` FLOAT NULL, PRIMARY KEY (`proveedor_idproveedor`, `producto_idproducto`), FOREIGN KEY (`proveedor_idproveedor`) REFERENCES `proveedor` (`idproveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION, FOREIGN KEY (`producto_idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 
CREATE TABLE IF NOT EXISTS `moneda` ( `idmoneda` INT NOT NULL, `nombre` VARCHAR(45) NULL, `pesos` FLOAT NULL, `empresa_idempresa` INT NOT NULL, PRIMARY KEY (`idmoneda`), FOREIGN KEY (`empresa_idempresa`) REFERENCES `empresa` (`idempresa`) ON DELETE NO ACTION ON UPDATE NO ACTION);
; 