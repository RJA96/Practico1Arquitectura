package config;

import dao.ClienteDaoImpl;
import dao.FacturaDaoImpl;
import dao.FacturaProductoDaoImpl;
import dao.ProductoDaoImpl;
import lombok.Getter;

@Getter
public class StaticDaos {
  private static final ClienteDaoImpl CLIENTE_DAO = new ClienteDaoImpl(new JpaEntityManagerFactory().getEntityManager());
  private static final FacturaDaoImpl FACTURA_DAO = new FacturaDaoImpl(new JpaEntityManagerFactory().getEntityManager());
  private static final FacturaProductoDaoImpl FACTURA_PRODUCTO_DAO = new FacturaProductoDaoImpl(new JpaEntityManagerFactory().getEntityManager());
  private static final ProductoDaoImpl PRODUCTO_DAO = new ProductoDaoImpl(new JpaEntityManagerFactory().getEntityManager());
}
