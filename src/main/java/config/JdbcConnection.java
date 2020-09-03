package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;
import lombok.Setter;

/** Patron singleton para la conexion a la base de datos. */
public class JdbcConnection {

  // JDBC driver name and database URL
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  @Setter String DB_URL = "jdbc:mysql://localhost:3306/";
  //  Database credentials
  static final String USER = "root";
  static final String PASS = "password";
  private static JdbcConnection jdbcConnection;

  private JdbcConnection() {}

  public static JdbcConnection getInstance() {
    if (Objects.isNull(jdbcConnection)) {
      return jdbcConnection = new JdbcConnection();
    } else {
      return jdbcConnection;
    }
  }

  public Connection getConnection() {
    try {
      return DriverManager.getConnection(DB_URL, USER, PASS);
    } catch (SQLException sqlException) {
      System.out.println(sqlException.toString());
    }
    return null;
  }

  public void closeConnection(Connection connection) {
    try {
      connection.close();
    } catch (SQLException sqlException) {
      System.out.println(sqlException.toString());
    }
  }
}
