package dao;

import entity.Cliente;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class ClienteDaoImpl implements Dao<Cliente> {

  private final EntityManager entityManager;


  public ClienteDaoImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public List<Cliente> getAll() {
    Query query = entityManager.createQuery("SELECT e FROM Cliente e");
    return query.getResultList();
  }

  @Override
  public void save(Cliente cliente) {
    executeInsideTransaction(entityManager -> entityManager.persist(cliente));
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
