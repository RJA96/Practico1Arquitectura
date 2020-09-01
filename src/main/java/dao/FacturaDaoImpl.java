package dao;

import entity.Factura;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacturaDaoImpl implements Dao<Factura> {
	Conexion c = new Conexion();


	public FacturaDaoImpl() {
	}

	public Factura getById(Integer id) {
		try {
			String select = "select * from cliente where idCliente= ?";
			PreparedStatement ps = c.getConnection().prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				Factura f = new Factura(rs.getInt(1), rs.getInt(2));
				return f;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<Factura> getAll() {
		List<Factura> aDevolver = new ArrayList<>();
		try {
			String select = "select * from factura";
			PreparedStatement ps =c.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Factura f = new Factura(rs.getInt(1), rs.getInt(2));
				aDevolver.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDevolver;
	}

	@Override
	public void addData(Factura t) {
		try {
			c.conectar();
			String insert = "insert into factura (idFactura, idCliente) values (?,?)";
			PreparedStatement ps = c.getConnection().prepareStatement(insert);
			ps.setInt(1, t.getIdFactura());
			ps.setInt(2, t.getidCliente());
			ps.executeUpdate();
			c.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveAll(List<Factura> tlist) {
		for (Factura F : tlist) {
			this.addData(F);
		}
	}
}
