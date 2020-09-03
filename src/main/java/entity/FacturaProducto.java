package entity;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.EmbeddedId;

/** Entidad de la base de datos FacturaProducto */
public class FacturaProducto {

  private FacturaProductoPK id;

  @Getter private Integer cantidad;

  public FacturaProducto(Integer facturaId, Integer productoId, Integer cantidad) {
    this.id = new FacturaProductoPK(facturaId, productoId);
    this.cantidad = cantidad;
  }

  public FacturaProducto() {}

  public Integer getFacturaId() {
    return id.getIdFactura();
  }

  public Integer getProductoId() {
    return id.getIdProducto();
  }

  @Override
  public String toString() {
    return "FacturaProducto{" + "id=" + id + ", cantidad=" + cantidad + '}';
  }
}
