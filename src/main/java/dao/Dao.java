package dao;

import config.JdbcConnection;

import entity.Factura;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import lombok.Setter;

/** Data Access Object generico */
public abstract class Dao<T> {

  protected JdbcConnection jdbcConnection;

  public Dao() {
    this.jdbcConnection = JdbcConnection.getInstance();
  }

  /**
   * Encuentra todos los registros de determinada clase.
   *
   * @return una lista con los objetos
   */
  abstract List<T> getAll();

  /**
   * Persiste una entidad.
   *
   * @param t cualquier entidad.
   */
  abstract void save(T t);

  /**
   * Persiste una lista de entidades.
   *
   * @param tList lista con entidades.
   */
  abstract void saveAll(List<T> tList);

  /**
   * Mapea los datos obtenidos de la base de datos a los objetos correspondientes.
   *
   * @param resultSet Datos obtenidos de una consulta a la base de datos.
   * @return Lista de objetos mapeados.
   */
  abstract List<T> mapResult(ResultSet resultSet);

  /** Crea la Tabla en la base de datos. */
  abstract void createTable();

  /** Elimina la tabla de la base de datos */
  abstract void dropTable();

  /** Crea la base de datos */
  public void createDataBase() {
    try {
      Connection connection = jdbcConnection.getConnection();
      String sql = "CREATE DATABASE IF NOT EXISTS Practico1";
      PreparedStatement preparedStatement = connection.prepareStatement(sql);
      preparedStatement.execute();
      jdbcConnection.closeConnection(connection);
      jdbcConnection.setDB_URL("jdbc:mysql://localhost:3306/Practico1");
    } catch (SQLException throwable) {
      throwable.printStackTrace();
    }
  }
}
