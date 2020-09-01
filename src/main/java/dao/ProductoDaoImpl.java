package dao;

import entity.Cliente;
import entity.Factura;
import entity.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Query;
import java.sql.ResultSet;
import java.util.List;
import utils.ConstantFields;

public class ProductoDaoImpl extends Dao<Producto> {

  public ProductoDaoImpl() {
  }


  public Producto getById(Integer id) {
    try {
      String sql = "SELECT * FROM Producto where idProducto = ?";
      Connection connection = jdbcConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Producto> productos = mapResult(resultSet);
      jdbcConnection.closeConnection(connection);
      if (Objects.nonNull(productos) && productos.size() > 0) {
        return productos.get(0);
      } else {
        return null;
      }
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Producto> getAll() {
    try {
      String sql = "SELECT * FROM Producto";
      Connection connection = jdbcConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Producto> productos = mapResult(resultSet);
      jdbcConnection.closeConnection(connection);
      return productos;
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public void save(Producto producto) {
    saveAll(Arrays.asList(producto));
  }

  @Override
  public void saveAll(List<Producto> productos) {
    try {
      Connection connection = jdbcConnection.getConnection();
      for (Producto row : productos) {
        String sql = "INSERT INTO Producto (idProducto, nombre, valor) values (?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, row.getIdProducto());
        preparedStatement.setString(2, row.getNombre());
        preparedStatement.setFloat(3, row.getValor());
        preparedStatement.executeUpdate();
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }

  @Override List<Producto> mapResult(ResultSet resultSet) {
    List<Producto> productos = new ArrayList<>();
    try {
      while (resultSet.next()) {
        Producto producto = new Producto(resultSet.getInt(ConstantFields.ID_PRODUCTO), resultSet.getString(ConstantFields.NOMBRE),resultSet.getFloat(ConstantFields.VALOR));
        productos.add(producto);
      }
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return productos;
  }

  @Override
  public void createTable() {
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql = "CREATE TABLE if NOT EXISTS Producto(idProducto INT, nombre VARCHAR (500), valor FLOAT , PRIMARY KEY (idProducto))";
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
      String sql = "DROP TABLE if EXISTS Producto";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.execute();
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }
}
