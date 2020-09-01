package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Conexion {

	private String driver = "com.mysql.cj.jdbc.Driver";

	private String uri = "jdbc:mysql://localhost:3306/dbEjercicio1";

	protected Connection conn;

	public Connection getConnection() {
		return this.conn;
	}

	public void conectar() throws SQLException{
		if (Objects.isNull(this.conn)) {
			try {
				this.conn = DriverManager.getConnection(this.uri, "root", "password");
				this.conn.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	public void desconectar() throws SQLException {
		if (this.conn != null) {
			this.conn.close();
		}
	}

	public void createSchema() throws SQLException {
		String s = "create database dbEjercicio1";
		this.conn.prepareStatement(s).execute();
		this.conn.commit();
	}

	public void crearTablas() throws SQLException {
		try {
			String data1 = "create table if not exists cliente(idCliente int, nombre varchar(500), email varchar(500), primary key(idCliente))";
			String data2 = "create table if not exists factura(idFactura int, idCliente int, primary key(idFactura))";
			String data3 = "create table if not exists factura_producto(idFactura int, idProducto int, cantidad int, primary key(idFactura, idProducto))";
			String data4 = "create table if not exists producto(idProducto int, nombre varchar(500), valor float, primary key(idProducto))";
			this.conn.prepareStatement(data1).execute();
			this.conn.commit();
			this.conn.prepareStatement(data2).execute();
			this.conn.commit();
			this.conn.prepareStatement(data3).execute();
			this.conn.commit();
			this.conn.prepareStatement(data4).execute();
			this.conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
