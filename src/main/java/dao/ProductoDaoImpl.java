package dao;

import entity.Producto;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class ProductoDaoImpl implements Dao<Producto> {
  private final EntityManager entityManager;


  public ProductoDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Producto> getAll() {
    Query query = entityManager.createQuery("SELECT e FROM Producto e");
    return query.getResultList();
  }

  @Override
  public void save(Producto producto) {
    executeInsideTransaction(entityManager -> entityManager.persist(producto));
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
