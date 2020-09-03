package dao;

import entity.Cliente;
import entity.Factura;
import java.util.Arrays;
import utils.ConstantFields;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FacturaDaoImpl extends Dao<Factura> {

  public FacturaDaoImpl() {}

  public Factura getById(Integer id) {
    try {
      String sql = "select * from Factura where idFactura = ?";
      Connection connection = jdbcConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      ResultSet resultSet = preparedStatement.executeQuery();
      List<Factura> facturas = mapResult(resultSet);
      jdbcConnection.closeConnection(connection);
      if (Objects.nonNull(facturas) && facturas.size() > 0) {
        return facturas.get(0);
      } else {
        return null;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return null;
  }

  @Override
  public List<Factura> getAll() {
    List<Factura> facturas = new ArrayList<>();
    try {
      String sql = "select * from factura";
      Connection connection = jdbcConnection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      ;
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        ClienteDaoImpl clienteDao = new ClienteDaoImpl();
        Cliente cliente =
            new Cliente(clienteDao.getById(resultSet.getInt(ConstantFields.ID_CLIENTE)));
        Factura factura = new Factura(resultSet.getInt(ConstantFields.ID_FACTURA), cliente);
        facturas.add(factura);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return facturas;
  }

  @Override
  public void save(Factura factura) {
    saveAll(Arrays.asList(factura));
  }

  @Override
  public void saveAll(List<Factura> facturas) {
    try {
      Connection connection = jdbcConnection.getConnection();
      for (Factura row : facturas) {
        String sql = "INSERT INTO Factura (idFactura, idCliente) VALUE (?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, row.getIdFactura());
        preparedStatement.setInt(2, row.getCliente().getIdCliente());
        preparedStatement.executeUpdate();
      }
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  @Override
  List<Factura> mapResult(ResultSet resultSet) {
    List<Factura> facturas = new ArrayList<>();
    try {
      while (resultSet.next()) {
        ClienteDaoImpl clienteDao = new ClienteDaoImpl();
        Cliente cliente =
            new Cliente(clienteDao.getById(resultSet.getInt(ConstantFields.ID_CLIENTE)));
        Factura factura = new Factura(resultSet.getInt(ConstantFields.ID_FACTURA), cliente);
        facturas.add(factura);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return facturas;
  }

  @Override
  public void createTable() {
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql =
          "CREATE TABLE if NOT EXISTS Factura(idFactura INT, idCliente INT , PRIMARY KEY (idFactura))";
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
      String sql = "DROP TABLE if EXISTS Factura";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.execute();
      jdbcConnection.closeConnection(connection);
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }
}
