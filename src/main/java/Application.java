import utils.CsvUtils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

import dao.Conexion;

public class Application {

    public static void main(String[] args) throws IOException, SQLException {
    	
    	Conexion c = new Conexion();

       	c.conectar();
    	//c.createSchema();
    	c.crearTablas();
    	
        CsvUtils csvUtils = new CsvUtils();
        csvUtils.uploadClientes(new FileReader("src/main/resources/clientes.csv"));
        csvUtils.uploadProductos(new FileReader("src/main/resources/productos.csv"));
        csvUtils.uploadFactura(new FileReader("src/main/resources/facturas.csv"));
        csvUtils.uploadFacturaProducto(new FileReader("src/main/resources/facturasProductos.csv"));
        
        c.desconectar();
    }
}
