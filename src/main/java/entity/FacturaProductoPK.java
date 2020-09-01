package entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


/**
 * ComposedPrimary of FacturaProducto
 */
@EqualsAndHashCode
@Embeddable
@Getter
public class FacturaProductoPK implements Serializable {

  @Column(name = "id_factura")
  private Integer idFactura;
  @Column(name = "id_producto")
  private Integer idProducto;

  public FacturaProductoPK(Integer idFactura, Integer idProducto) {
    this.idFactura = idFactura;
    this.idProducto = idProducto;
  }

  public FacturaProductoPK() {
  }
}
