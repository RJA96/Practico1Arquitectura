package dao;

import entity.FacturaProducto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FacturaProductoDaoImpl implements Dao<FacturaProducto> {
	Conexion c = new Conexion();

	public FacturaProductoDaoImpl() {
	}

	public FacturaProducto getById(Integer idFactura, Integer idProducto) {
		try {
			String select = "select * from Factura_Producto where idFactura= ? and idProducto= ?";
			PreparedStatement ps = c.getConnection().prepareStatement(select);
			ps.setInt(1, idFactura);
			ps.setInt(2, idProducto);
			ResultSet rs = ps.executeQuery();
			if (rs != null) {
				FacturaProducto f = new FacturaProducto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				return f;
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@Override
	public List<FacturaProducto> getAll() {
		List<FacturaProducto> aDevolver = new ArrayList<>();
		try {
			String select = "select * from factura_producto";
			PreparedStatement ps = c.getConnection().prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				FacturaProducto f = new FacturaProducto(rs.getInt(1), rs.getInt(2), rs.getInt(3));
				aDevolver.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return aDevolver;
	}

	@Override
	public void addData(FacturaProducto t) {
		try {
			c.conectar();
			String insert = "insert into factura_producto (idFactura, idProducto, cantidad) values (?,?,?)";
			PreparedStatement ps = c.getConnection().prepareStatement(insert);
			ps.setInt(1, t.getIdFactura());
			ps.setInt(2, t.getIdProducto());
			ps.setInt(3, t.getCantidad());
			ps.executeUpdate();
			c.getConnection().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void saveAll(List<FacturaProducto> tlist) {
		for (FacturaProducto F : tlist) {
			this.addData(F);
		}
	}
}
