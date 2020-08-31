package utils;

import dao.ClienteDaoImpl;
import dao.FacturaDaoImpl;
import dao.FacturaProductoDaoImpl;
import dao.ProductoDaoImpl;

public class DaoUtils {
  public static final FacturaProductoDaoImpl facturaProodcutoDao = new FacturaProductoDaoImpl();
  public static final ProductoDaoImpl productoDao = new ProductoDaoImpl();
  public static final ClienteDaoImpl clienteDao = new ClienteDaoImpl();
  public static final FacturaDaoImpl facturaDao = new FacturaDaoImpl();
}
