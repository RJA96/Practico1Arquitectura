package entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
public class FacturaProducto {
  @Getter
  @Id
  private Integer idFactura;

  private Integer idProducto;

  private Integer cantidad;

  public FacturaProducto() {
  }

  public FacturaProducto (Integer idFactura, Integer idProducto, Integer cantidad) {
    this.idFactura = idFactura;
    this.idProducto = idProducto;
    this.cantidad = cantidad;
  }
}
