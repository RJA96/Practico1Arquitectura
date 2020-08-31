package service;

import entity.Cliente;
import entity.Factura;
import entity.FacturaProducto;
import entity.Producto;
import utils.DaoUtils;

import java.util.ArrayList;
import java.util.List;

public class EjercicioService {
  /**
   * Busca el producto que mas recaudo
   * @return el producto que mas recaudo o null
   */
  public Producto getMaxRecaudacion() {
    List<FacturaProducto> facturaProductos = DaoUtils.facturaProodcutoDao.getAll();
    List<Producto> productos = DaoUtils.productoDao.getAll();

    Producto productoQueMasRecaudo = null;
    float maximaRecaudacion = 0f;
    for(Producto producto: productos) {
      Integer cantidadProductos = getCantidadProductosCompradosByProducto(facturaProductos, producto); // Dame el total y sino devolve 0
      float result = producto.getValor() * cantidadProductos;
      if(result > maximaRecaudacion) {
        maximaRecaudacion = result;
        productoQueMasRecaudo = producto;
      }
    }
    return productoQueMasRecaudo;
  }



  private Integer getCantidadProductosCompradosByProducto(List<FacturaProducto> facturaProductos, Producto producto) {
    return facturaProductos.stream()
        .filter(fp -> fp.getProductoId() == producto.getIdProducto()) // Filtra por id de producto = id de facturaProducto
        .map(facturaProducto -> facturaProducto.getCantidad()) // List<Integer> representando la cantidad
        .reduce(Integer::sum) // x -> total + valorDentroDelArray
        .orElse(0);
  }

  public List<Cliente> getClientesByFacturoMas() {
    List<FacturaProducto> facturaProductos = DaoUtils.facturaProodcutoDao.getAll();
    List<Producto> productos = DaoUtils.productoDao.getAll();
    List<Cliente> clientes  = DaoUtils.clienteDao.getAll();
    List<Factura> facturas = DaoUtils.facturaDao.getAll();

    clientes = DaoUtils.clienteDao.getAll();
    productos = DaoUtils.productoDao.getAll();
    facturas = DaoUtils.facturaDao.getAll();
    facturaProductos = DaoUtils.facturaProodcutoDao.getAll();

    for (Factura factura: facturas) {
      facturaProductos.stream()
          .filter(facturaProducto -> facturaProducto.getFacturaId() == factura.getIdFactura())
          .
      return new ArrayList<>();
    }




  }
}
