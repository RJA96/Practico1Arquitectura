package entity;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;

@Entity
public class FacturaProducto {

  @EmbeddedId
  private FacturaProductoPK id;

  private Integer cantidad;


  public FacturaProducto(Integer facturaId, Integer productoId, Integer cantidad) {
    this.id = new FacturaProductoPK(facturaId, productoId);
    this.cantidad = cantidad;
  }

  public FacturaProducto() {
  }
}
