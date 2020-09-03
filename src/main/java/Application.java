import dao.ClienteDaoImpl;
import entity.Cliente;
import java.util.Collections;
import java.util.List;
import service.ClienteFacturado;
import service.Service;
import utils.CsvUtils;

import java.io.FileReader;
import java.io.IOException;

public class Application {

  private static final ClienteDaoImpl clienteDao = new ClienteDaoImpl();

  /**
   * No muestra logs al haber utilizado execute() para las operaciones SQL.
   *
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    CsvUtils csvUtils = new CsvUtils();
    Service service = new Service();

    csvUtils.dropAllTables();
    System.out.println("Drop Tables DONE");
    csvUtils.createAllTables();
    System.out.println("Create All Tables DONE");
    System.out.println();

    csvUtils.uploadClientes(new FileReader("src/main/resources/clientes.csv"));
    System.out.println("Upload clientes.csv DONE");
    csvUtils.uploadProductos(new FileReader("src/main/resources/productos.csv"));
    System.out.println("Upload productos.csv DONE");
    csvUtils.uploadFactura(new FileReader("src/main/resources/facturas.csv"));
    System.out.println("Upload facturas.csv DONE");
    csvUtils.uploadFacturaProducto(new FileReader("src/main/resources/facturasProductos.csv"));
    System.out.println("Upload facturasProductos.csv DONE");
    System.out.println();

    System.out.println("Ej 3: Producto que mas recaudo y la cantidad recaudada");
    System.out.println(service.getMostCollectedProduct());
    System.out.println();

    System.out.println("Ej 4: lista de clientes ordanada por por valores de facturacion");
    List<ClienteFacturado> clienteFacturados = service.GetMostBilledCustomers();
    for (ClienteFacturado c : clienteFacturados) {
      System.out.println(c);
    }
  }
}
