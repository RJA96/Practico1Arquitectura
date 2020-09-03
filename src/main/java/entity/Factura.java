package entity;

import lombok.Getter;
import lombok.Setter;

/** Entidad de la base de datos Factura */
public class Factura {

  @Getter private Integer idFactura;

  @Setter @Getter private Cliente cliente;

  public Factura() {}

  public Factura(Integer id) {
    this.idFactura = id;
  }

  public Factura(Integer idFactura, Cliente cliente) {
    this.idFactura = idFactura;
  }

  public Factura(Cliente cliente) {
    this.cliente = cliente;
  }

  @Override
  public String toString() {
    return "Factura{" + "idFactura=" + idFactura + ", cliente=" + cliente + '}';
  }
}
