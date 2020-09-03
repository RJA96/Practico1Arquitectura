package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

/** Primary Key de la tabla FacturaProducto */
@EqualsAndHashCode
@Getter
public class FacturaProductoPK implements Serializable {

  private Integer idFactura;

  private Integer idProducto;

  public FacturaProductoPK(Integer idFactura, Integer idProducto) {
    this.idFactura = idFactura;
    this.idProducto = idProducto;
  }

  public FacturaProductoPK() {}
}
