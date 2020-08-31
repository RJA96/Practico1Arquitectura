import utils.CsvUtils;

import java.io.FileReader;
import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        CsvUtils csvUtils = new CsvUtils();
        csvUtils.uploadClientes(new FileReader("src/main/resources/clientes.csv"));
        csvUtils.uploadProductos(new FileReader("src/main/resources/productos.csv"));
        csvUtils.uploadFactura(new FileReader("src/main/resources/facturas.csv"));
        csvUtils.uploadFacturaProducto(new FileReader("src/main/resources/facturasProductos.csv"));
    }
}
