package service;

import config.JdbcConnection;
import dao.ClienteDaoImpl;
import dao.ProductoDaoImpl;
import entity.Cliente;
import entity.FacturaProducto;
import entity.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.ConstantFields;

public class Service {

  private static final ClienteDaoImpl clienteDao = new ClienteDaoImpl();
  private static final ProductoDaoImpl productoDao = new ProductoDaoImpl();
  private JdbcConnection jdbcConnection;

  public Service() {
    this.jdbcConnection = JdbcConnection.getInstance();
  }

  public Map<Producto, Double> getMostCollectedProduct() {
    Map<Producto, Double> productos = new HashMap<>();
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql = "SELECT fp.idProducto, SUM(fp.cantidad * p.valor) AS RECAUDACION FROM FacturaProducto fp JOIN Producto p ON (fp.idProducto = p.idProducto) GROUP BY 1 ORDER BY 2 DESC";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.executeQuery();
      ResultSet resultSet = preparedStatement.getResultSet();
      if (resultSet.next()) {
        Producto producto = productoDao.getById(preparedStatement.getResultSet().getInt(
            ConstantFields.ID_PRODUCTO));
        productos.put(producto, preparedStatement.getResultSet().getDouble(
            ConstantFields.RECAUDACION));
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return productos;
  }

  public Map<Cliente, Double> GetMostBilledCustomers() {
    Map<Cliente, Double> clienteResponse = new HashMap<>();
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql = "SELECT c.idCliente, SUM(cantidad * valor) AS Gasto FROM "
          + "Factura f JOIN FacturaProducto fp ON f.idFactura = fp.idFactura JOIN Producto p "
          + "ON fp.idProducto = p.idProducto JOIN Cliente c ON f.idCliente = c.idCliente GROUP BY 1 ORDER BY 2 DESC";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.executeQuery();
      ResultSet resultSet = preparedStatement.getResultSet();
      while (resultSet.next()) {
        Cliente cliente = clienteDao.getById(preparedStatement.getResultSet().getInt(
            ConstantFields.ID_CLIENTE));
        clienteResponse.put(cliente, preparedStatement.getResultSet().getDouble(
            ConstantFields.GASTO));
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    //TODO sort
    return clienteResponse;
  }
}
