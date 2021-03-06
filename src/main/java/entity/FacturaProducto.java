package entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;

@Entity
public class FacturaProducto {

  @EmbeddedId
  private FacturaProductoPK id;

  @Getter
  private Integer cantidad;


  public FacturaProducto(Integer facturaId, Integer productoId, Integer cantidad) {
    this.id = new FacturaProductoPK(facturaId, productoId);
    this.cantidad = cantidad;
  }

  public FacturaProducto() {
  }

  public Integer getFacturaId() {
    return id.getIdFactura();
  }

  public Integer getProductoId() {
    return id.getIdProducto();
  }
}
