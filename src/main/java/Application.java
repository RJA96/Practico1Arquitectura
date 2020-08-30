import config.JpaEntityManagerFactory;
import dao.ClienteDaoImpl;

import dao.FacturaDaoImpl;
import dao.FacturaProductoDaoImpl;
import dao.ProductoDaoImpl;
import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import utils.ConstantFields;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Application {

    private static final ClienteDaoImpl clienteDao = new ClienteDaoImpl();
    private static final FacturaDaoImpl facturaDao = new FacturaDaoImpl();
    private static final FacturaProductoDaoImpl facturaProodcutoDao = new FacturaProductoDaoImpl();
    private static final ProductoDaoImpl productoDao = new ProductoDaoImpl();

    public static void main(String[] args) throws IOException {
        Cliente cliente = new Cliente(1, "Juan", "email");
        clienteDao.save(cliente);
        facturaDao.save(new Factura(cliente));
        facturaProodcutoDao.save(new FacturaProducto(1,1,1));
//        Producto producto = new Producto("nombre", 123F);
//        productoDao.saveAll(Arrays.asList(producto));


        CSVParser productosCsv = CSVFormat.DEFAULT.withHeader().parse(new FileReader("src/main/resources/productos.csv"));

        List<Producto> productos = new ArrayList<>();
        for(CSVRecord row: productosCsv) {
            Integer id = Integer.parseInt(row.get(ConstantFields.ID_PRODUCTO));
            String nombre = row.get("nombre");
            Float valor = Float.parseFloat(row.get("valor"));
            productos.add(new Producto(id, nombre, valor));
        }
        productoDao.saveAll(productos);
    }


    /**
     * Busca el producto que mas recaudo
     * @return el producto que mas recaudo o null
     */
    public Producto getMaxRecaudacion() {
        List<FacturaProducto> facturaProductos = facturaProodcutoDao.getAll();
        List<Producto> productos = productoDao.getAll();

        Producto productoQueMasRecaudo = null;
        float maximaRecaudacion = 0f;
        for(Producto producto: productos) {
            Integer cantidadProductos = facturaProductos.stream()
                    .filter(fp -> fp.getProductoId() == producto.getIdProducto())
                    .map(facturaProducto -> facturaProducto.getCantidad())
                    .reduce(Integer::sum)
                    .orElse(0);
            float result = producto.getValor() * cantidadProductos;
            if(result > maximaRecaudacion) {
                maximaRecaudacion = result;
                productoQueMasRecaudo = producto;
            }
        }
        return productoQueMasRecaudo;
    }

}
