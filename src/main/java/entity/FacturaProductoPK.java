package entity;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class FacturaProductoPK implements Serializable {
  private Integer idFactura;
  private Integer idProducto;

  public FacturaProductoPK(Integer idFactura, Integer idProducto) {
    this.idFactura = idFactura;
    this.idProducto = idProducto;
  }
}
