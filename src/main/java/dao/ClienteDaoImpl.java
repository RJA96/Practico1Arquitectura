package dao;

import entity.Cliente;
import entity.FacturaProducto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClienteDaoImpl implements Dao<Cliente> {
	
	Conexion c = new Conexion();

	public ClienteDaoImpl() {
	}

	public Cliente getById(Integer id) {
		try {
			String select = "select * from cliente where idCliente= ?";
			PreparedStatement ps = c.getConnection().prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
				return c;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Cliente> getAll() {
		List<Cliente> aDevolver = new ArrayList<>();
		try {
			String select = "select * from cliente";
			PreparedStatement ps = c.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente a = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
				aDevolver.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDevolver;
	}

	@Override
	public void addData(Cliente t) {
		try {
			c.conectar();
			String insert = "insert into cliente (idCliente, nombre, email) values (?,?,?)";
			PreparedStatement ps = c.getConnection().prepareStatement(insert);
			ps.setInt(1, t.getIdCliente());
			ps.setString(2, t.getNombre());
			ps.setNString(3, t.getEmail());
			ps.executeUpdate();
			c.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveAll(List<Cliente> tlist) {
		for (Cliente F : tlist) {
			this.addData(F);
		}
	}
}
