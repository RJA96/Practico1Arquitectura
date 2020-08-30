package dao;

import config.JpaEntityManagerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class Dao<T> {
  protected final EntityManager entityManager;

  public Dao() {
    this.entityManager = new JpaEntityManagerFactory().getEntityManager();
  }

  abstract List<T> getAll();
  abstract void save(T t);
  abstract void saveAll(List<T> tList);

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
