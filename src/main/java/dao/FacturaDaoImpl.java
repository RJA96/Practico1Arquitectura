package dao;

import entity.Cliente;
import entity.Factura;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;
import java.util.function.Consumer;

public class FacturaDaoImpl extends Dao<Factura>{

  public FacturaDaoImpl() {
  }


  public Factura getById(Integer id) {
    Query query = entityManager.createQuery("select e from Factura e where idFactura = :id");
    query.setParameter("id",id);
    return (Factura) query.getSingleResult();
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

  @Override
  public void saveAll(List<Factura> facturas) {
    execute(facturas);
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
