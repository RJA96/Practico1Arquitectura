package utils;

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

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CsvUtils {

  private static final FacturaProductoDaoImpl facturaProodcutoDao = new FacturaProductoDaoImpl();
  private static final ProductoDaoImpl productoDao = new ProductoDaoImpl();
  private static final ClienteDaoImpl clienteDao = new ClienteDaoImpl();
  private static final FacturaDaoImpl facturaDao = new FacturaDaoImpl();

  public CsvUtils(){

  }
  public void uploadProductos(FileReader fileReader) throws IOException {
    CSVParser productosCsv = CSVFormat.DEFAULT.withHeader().parse(fileReader);
    List<Producto> productos = new ArrayList<>();
    for(CSVRecord row: productosCsv) {
      Integer id = Integer.parseInt(row.get(ConstantFields.ID_PRODUCTO));
      String nombre = row.get(ConstantFields.NOMBRE);
      Float valor = Float.parseFloat(row.get(ConstantFields.VALOR));
      productos.add(new Producto(id, nombre, valor));
    }
    productoDao.saveAll(productos);
  }

  public void uploadClientes(FileReader fileReader) throws IOException {
    CSVParser clientesCsv = CSVFormat.DEFAULT.withHeader().parse(fileReader);
    List<Cliente> clientes = new ArrayList<>();
    for(CSVRecord row: clientesCsv) {
      Integer idCliente = Integer.parseInt(row.get(ConstantFields.ID_CLIENTE));
      String nombre = row.get(ConstantFields.NOMBRE);
      String email = row.get(ConstantFields.EMAIL);
      clientes.add(new Cliente(idCliente, nombre, email));
    }
    clienteDao.saveAll(clientes);
  }

  public void uploadFactura (FileReader fileReader) throws IOException {
    CSVParser facturaCsv = CSVFormat.DEFAULT.withHeader().parse(fileReader);
    List<Factura> facturas = new ArrayList<>();
    for(CSVRecord row: facturaCsv) {

      Integer idFactura = Integer.parseInt(row.get(ConstantFields.ID_FACTURA));
      Integer idCliente = Integer.parseInt(row.get(ConstantFields.ID_CLIENTE));
      Factura factura = new Factura(idFactura);
      if (Objects.nonNull(clienteDao.getById(idCliente))) {
        factura.setCliente(clienteDao.getById(idCliente));
      }
      else {
        factura.setCliente(new Cliente(idCliente));
      }
      facturas.add(factura);
    }
    facturaDao.saveAll(facturas);
  }

  public void uploadFacturaProducto (FileReader fileReader) throws IOException {
    CSVParser facturasProductosCsv = CSVFormat.DEFAULT.withHeader().parse(fileReader);
    List<FacturaProducto> facturaProductos = new ArrayList<>();
    for(CSVRecord row: facturasProductosCsv) {
      Integer idFactura = Integer.parseInt(row.get(ConstantFields.ID_FACTURA));
      Integer idProducto = Integer.parseInt(row.get(ConstantFields.ID_PRODUCTO));
      Integer cantidad = Integer.parseInt(row.get(ConstantFields.CANTIDAD));
      FacturaProducto facturaProducto = new FacturaProducto(idFactura,idProducto,cantidad);
      facturaProductos.add(facturaProducto);
    }
    facturaProodcutoDao.saveAll(facturaProductos);
  }

  /**
   * Busca el producto que mas recaudo
   * @return el producto que mas recaudo o null
   */
  public Producto getMaxRecaudacion() {
    List<FacturaProducto> facturaProductos = facturaProodcutoDao.getAll();
    List<Producto> productos = productoDao.getAll();

    Producto productoQueMasRecaudo = null;
    float maximaRecaudacion = 0f;
    for(Producto producto: productos) {
      Integer cantidadProductos = facturaProductos.stream()
              .filter(fp -> fp.getProductoId() == producto.getIdProducto())
              .map(facturaProducto -> facturaProducto.getCantidad())
              .reduce(Integer::sum)
              .orElse(0);
      float result = producto.getValor() * cantidadProductos;
      if(result > maximaRecaudacion) {
        maximaRecaudacion = result;
        productoQueMasRecaudo = producto;
      }
    }
    return productoQueMasRecaudo;
  }
}
