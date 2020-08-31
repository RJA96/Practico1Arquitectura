package config;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * JPA Entity Eanager
 */
public class JpaEntityManagerFactory {

  /**
   * Returns an instance of EntityManager
   * @return
   */
  public EntityManager getEntityManager() {
    return getEntityManagerFactory().createEntityManager();
  }

  private EntityManagerFactory getEntityManagerFactory() {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Practico1");
    return entityManagerFactory;
  }

}
