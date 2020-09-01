package dao;

import entity.Cliente;
import entity.Producto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductoDaoImpl implements Dao<Producto> {
	Conexion c = new Conexion();

	public ProductoDaoImpl() {
	}

	public Producto getById(Integer idProducto) {
		try {
			String select = "select * from Producto where idProducto= ?";
			PreparedStatement ps = c.getConnection().prepareStatement(select);
			ps.setInt(1, idProducto);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				Producto f = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
				return f;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Producto> getAll() {
		List<Producto> aDevolver = new ArrayList<>();
		try {
			String select = "select * from facturaproducto";
			PreparedStatement ps = c.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Producto f = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
				aDevolver.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDevolver;
	}

	@Override
	public void addData(Producto t) {
		try {
			c.conectar();
			String insert = "insert into producto (idProducto, nombre, valor) values (?,?,?)";
			PreparedStatement ps = c.getConnection().prepareStatement(insert);
			ps.setInt(1, t.getIdProducto());
			ps.setString(2, t.getNombre());
			ps.setFloat(3, t.getValor());
			ps.executeUpdate();
			c.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveAll(List<Producto> tlist) {
		for (Producto F : tlist) {
			this.addData(F);
		}
	}
}
