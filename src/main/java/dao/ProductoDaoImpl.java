package dao;

import entity.Producto;

import javax.persistence.Query;
import java.util.List;

public class ProductoDaoImpl extends Dao<Producto> {

  public ProductoDaoImpl() {
  }

  @Override
  public List<Producto> getAll() {
    Query query = entityManager.createQuery("SELECT e FROM Producto e");
    return query.getResultList();
  }

  @Override
  public void save(Producto producto) {
    execute(producto);
  }

  @Override
  public void saveAll(List<Producto> productos) {
    execute(productos);
  }
}
