import config.JpaEntityManagerFactory;
import dao.ClienteDaoImpl;

import dao.FacturaDaoImpl;
import dao.FacturaProductoDaoImpl;
import dao.ProductoDaoImpl;
import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utils.ConstantFields;
import utils.CsvUtils;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {





    public static void main(String[] args) throws IOException {
        CsvUtils csvUtils = new CsvUtils();
        csvUtils.uploadClientes(new FileReader("src/main/resources/clientes.csv"));
        csvUtils.uploadProductos(new FileReader("src/main/resources/productos.csv"));
        csvUtils.uploadFactura(new FileReader("src/main/resources/facturas.csv"));
        csvUtils.uploadFacturaProducto(new FileReader("src/main/resources/facturasProductos.csv"));


    }

}
