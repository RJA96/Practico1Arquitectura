package dao;

import entity.Factura;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class FacturaDaoImpl implements Dao<Factura>{

  private final EntityManager entityManager;


  public FacturaDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Factura> getAll() {
    Query query = entityManager.createQuery("SELECT e FROM Factura e");
    return query.getResultList();
  }

  @Override
  public void save(Factura factura) {
    executeInsideTransaction(entityManager -> entityManager.persist(factura));
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
