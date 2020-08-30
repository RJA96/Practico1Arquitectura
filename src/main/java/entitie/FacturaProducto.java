package entitie;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@IdClass(FacturaProductoPK.class)
public class FacturaProducto {
  @Getter
  @Id
  @Column (name = "idFactura", insertable = false, nullable = false)
  private Integer idFactura;

  @Id
  @Column (name = "idProducto",insertable = false, nullable = false)
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
