package dao;

import config.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;


/**
 * Data Access Object generico
 */
public abstract class Dao<T> {
  protected final EntityManager entityManager;

  public Dao() {
    this.entityManager = new JpaEntityManagerFactory().getEntityManager();
  }

  /**
   * Encuentra todos los registros de determinada clase
   * @return una lista con los objetos
   */
  abstract List<T> getAll();

  /**
   * Persiste una entidad
   * @param t cualquier entidad
   */
  abstract void save(T t);

  /**
   * Persiste una lista de entidades
   * @param tList lista con entidades
   */
  abstract void saveAll(List<T> tList);

  /**
   * Persiste en una sola transaccion una lista determinada de T
   */
  protected void execute(List<T> list) {
    final EntityTransaction entityTransaction = entityManager.getTransaction();
    try {
      entityTransaction.begin();
      list.forEach(x -> entityManager.persist(x));
      entityTransaction.commit();
    }
    catch (RuntimeException e) {
      entityTransaction.rollback();
      throw e;
    }
  }

  protected void execute(T t) {
    execute(Arrays.asList(t));
  }
}
