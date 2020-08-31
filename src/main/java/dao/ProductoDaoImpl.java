package dao;

import entity.Factura;
import entity.Producto;

import javax.persistence.Query;
import java.util.List;

public class ProductoDaoImpl extends Dao<Producto> {

  public ProductoDaoImpl() {
  }


  public Factura getById(Integer id) {
    Query query = entityManager.createQuery("select e from Factura e where idFactura = :id");
    query.setParameter("id",id);
    return (Factura) query.getSingleResult();
  }

  @Override
  public List<Producto> getAll() {
    String queryS = "SELECT e FROM Producto e";
    Query query = entityManager.createQuery(queryS);
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
