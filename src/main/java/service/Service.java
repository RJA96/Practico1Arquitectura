package service;

import config.JdbcConnection;
import dao.ClienteDaoImpl;
import dao.ProductoDaoImpl;
import entity.Cliente;
import entity.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.ConstantFields;

/** Servicio creado para resolver los puntos 3 y 4 */
public class Service {

  private static final ClienteDaoImpl clienteDao = new ClienteDaoImpl();
  private static final ProductoDaoImpl productoDao = new ProductoDaoImpl();
  private JdbcConnection jdbcConnection;

  public Service() {
    this.jdbcConnection = JdbcConnection.getInstance();
  }

  /**
   * Este metodo obtiene el producto que mas recaudo.
   *
   * @return El producto que mas recaudo y el total recaudado por ese producto.
   */
  public Map<Producto, Double> getMostCollectedProduct() {
    Map<Producto, Double> productos = new HashMap<>();
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql =
          "SELECT fp.idProducto, SUM(fp.cantidad * p.valor) AS RECAUDACION FROM FacturaProducto fp "
              + "JOIN Producto p ON (fp.idProducto = p.idProducto) GROUP BY 1 ORDER BY 2 DESC LIMIT 1";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.executeQuery();
      ResultSet resultSet = preparedStatement.getResultSet();
      if (resultSet.next()) {
        Producto producto =
            productoDao.getById(
                preparedStatement.getResultSet().getInt(ConstantFields.ID_PRODUCTO));
        productos.put(
            producto, preparedStatement.getResultSet().getDouble(ConstantFields.RECAUDACION));
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return productos;
  }

  /**
   * Este metodo obtiene los clientes, los guarda en una lista y los ordena.
   *
   * @return Lista de Clientes y el total facturado para cada cliente ordenada de menor a mayor.
   */
  public List<ClienteFacturado> GetMostBilledCustomers() {
    List<ClienteFacturado> clienteResponse = new ArrayList<>();
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql =
          "SELECT c.idCliente, SUM(cantidad * valor) AS Gasto FROM "
              + "Factura f JOIN FacturaProducto fp ON f.idFactura = fp.idFactura JOIN Producto p "
              + "ON fp.idProducto = p.idProducto JOIN Cliente c ON f.idCliente = c.idCliente GROUP BY 1";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.executeQuery();
      ResultSet resultSet = preparedStatement.getResultSet();
      while (resultSet.next()) {
        Cliente cliente =
            clienteDao.getById(preparedStatement.getResultSet().getInt(ConstantFields.ID_CLIENTE));
        ClienteFacturado clienteFacturado = new ClienteFacturado();
        clienteFacturado.setCliente(cliente);
        clienteFacturado.setTotalGastado(
            preparedStatement.getResultSet().getDouble(ConstantFields.GASTO));
        clienteResponse.add(clienteFacturado);
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    Collections.sort(clienteResponse);
    return clienteResponse;
  }
}
