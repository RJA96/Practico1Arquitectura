import config.JpaEntityManagerFactory;
import dao.ClienteDaoImpl;

import dao.FacturaDaoImpl;
import dao.FacturaProductoDaoImpl;
import dao.ProductoDaoImpl;
import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;

public class Application {

    private static final ClienteDaoImpl clienteDao = new ClienteDaoImpl(new JpaEntityManagerFactory().getEntityManager());
    private static final FacturaDaoImpl facturaDao = new FacturaDaoImpl(new JpaEntityManagerFactory().getEntityManager());
    private static final FacturaProductoDaoImpl facturaProodcutoDao = new FacturaProductoDaoImpl(new JpaEntityManagerFactory().getEntityManager());
    private static final ProductoDaoImpl productoDao = new ProductoDaoImpl(new JpaEntityManagerFactory().getEntityManager());

    public static void main(String[] args) {
        Cliente cliente = new Cliente(1, "Juan", "email");
        clienteDao.save(cliente);
        facturaDao.save(new Factura(cliente));
        facturaProodcutoDao.save(new FacturaProducto(1,1,1));
        productoDao.save(new Producto("nombre",123F));
    }

}
