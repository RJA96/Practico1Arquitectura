package dao;

import entity.Cliente;
import utils.ConstantFields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClienteDaoImpl extends Dao<Cliente> {

  public ClienteDaoImpl() {}

  public Cliente getById(Integer id) {
    try {
      String sql = "SELECT * FROM Cliente where idCliente = ?";
      Connection connection = jdbcConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Cliente> clientes = mapResult(resultSet);
      jdbcConnection.closeConnection(connection);
      if (Objects.nonNull(clientes) && clientes.size() > 0) {
        return clientes.get(0);
      } else {
        return null;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Cliente> getAll() {
    try {
      String sql = "SELECT * FROM Cliente";
      Connection connection = jdbcConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Cliente> clientes = mapResult(resultSet);
      jdbcConnection.closeConnection(connection);
      return clientes;
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return new ArrayList<>();
  }

  @Override
  public void save(Cliente cliente) {
    saveAll(Arrays.asList(cliente));
  }

  @Override
  public void saveAll(List<Cliente> clientes) {
    try {
      Connection connection = jdbcConnection.getConnection();
      for (Cliente row : clientes) {
        String sql = "INSERT INTO Cliente (idCliente, nombre, email)VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, row.getIdCliente());
        preparedStatement.setString(2, row.getNombre());
        preparedStatement.setString(3, row.getEmail());
        preparedStatement.executeUpdate();
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }

  @Override
  public List<Cliente> mapResult(ResultSet resultSet) {
    List<Cliente> clientes = new ArrayList<>();
    try {
      while (resultSet.next()) {
        Cliente cliente =
            new Cliente(
                resultSet.getInt(ConstantFields.ID_CLIENTE),
                resultSet.getString(ConstantFields.NOMBRE),
                resultSet.getString(ConstantFields.EMAIL));
        clientes.add(cliente);
      }
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
    return clientes;
  }

  @Override
  public void createTable() {
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql =
          "CREATE TABLE if NOT EXISTS Cliente(idCliente INT, nombre VARCHAR (500), email VARCHAR (500), PRIMARY KEY (idCliente))";
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
      String sql = "DROP TABLE if EXISTS Cliente";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.execute();
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }
}
