package dao;

import entity.FacturaProducto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class FacturaProductoDaoImpl extends Dao<FacturaProducto> {

  public FacturaProductoDaoImpl() {
  }

  @Override
  public List<FacturaProducto> getAll() {
    Query query = entityManager.createQuery("SELECT e FROM FacturaProducto e");
    return query.getResultList();
  }

  @Override
  public void save(FacturaProducto facturaProducto) {
    executeInsideTransaction(entityManager -> entityManager.persist(facturaProducto));
  }

  @Override
  public void saveAll(List<FacturaProducto> facturaProductos) {

  }

  private void executeInsideTransaction(Consumer<EntityManager> action) {
    final EntityTransaction entityTransaction = entityManager.getTransaction();
    try {
      entityTransaction.begin();
      action.accept(entityManager);
      entityTransaction.commit();
    }
    catch (RuntimeException e) {
      entityTransaction.rollback();
      throw e;
    }
  }
}
