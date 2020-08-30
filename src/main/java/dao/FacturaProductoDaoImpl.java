package dao;

import entitie.Factura;
import entitie.FacturaProducto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class FacturaProductoDaoImpl implements Dao<FacturaProducto> {

  private final EntityManager entityManager;


  public FacturaProductoDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
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
