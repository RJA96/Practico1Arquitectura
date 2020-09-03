package dao;

import entity.FacturaProducto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.ResultSet;
import java.util.List;
import utils.ConstantFields;

public class FacturaProductoDaoImpl extends Dao<FacturaProducto> {

  public FacturaProductoDaoImpl() {}

  @Override
  public List<FacturaProducto> getAll() {
    try {
      String sql = "SELECT * FROM FacturaProducto";
      Connection connection = jdbcConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<FacturaProducto> facturaProductos = mapResult(resultSet);
      jdbcConnection.closeConnection(connection);
      return facturaProductos;
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public void save(FacturaProducto facturaProducto) {
    saveAll(Arrays.asList(facturaProducto));
  }

  @Override
  public void saveAll(List<FacturaProducto> facturaProductos) {
    try {
      Connection connection = jdbcConnection.getConnection();
      for (FacturaProducto row : facturaProductos) {
        String sql =
            "INSERT INTO FacturaProducto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, row.getFacturaId());
        preparedStatement.setInt(2, row.getProductoId());
        preparedStatement.setInt(3, row.getCantidad());
        preparedStatement.executeUpdate();
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }

  @Override
  List<FacturaProducto> mapResult(ResultSet resultSet) {
    List<FacturaProducto> facturaProductos = new ArrayList<>();
    try {
      while (resultSet.next()) {
        FacturaProducto facturaProducto =
            new FacturaProducto(
                resultSet.getInt(ConstantFields.ID_FACTURA),
                resultSet.getInt(ConstantFields.ID_PRODUCTO),
                resultSet.getInt(ConstantFields.CANTIDAD));
        facturaProductos.add(facturaProducto);
      }
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return facturaProductos;
  }

  @Override
  public void createTable() {
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql =
          "create table if not exists FacturaProducto(idFactura int, idProducto int, cantidad int, primary key(idFactura, idProducto))";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.execute();
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }

  @Override
  public void dropTable() {
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql = "DROP TABLE if EXISTS FacturaProducto";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.execute();
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }
}
