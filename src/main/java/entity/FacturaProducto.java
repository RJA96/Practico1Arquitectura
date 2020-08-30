package entity;

import javax.persistence.*;

@Entity
public class FacturaProducto {

  @EmbeddedId
  private FacturaProductoPK id;

//  @ManyToOne
//  @MapsId("idFactura")
//  @JoinColumn(name = "id_factura")
//  private Factura factura;
//
//  @ManyToOne
//  @MapsId("idFactura")
//  @JoinColumn(name = "id_producto")
//  private Producto producto;

  private Integer cantidad;


  public FacturaProducto(Integer facturaId, Integer productoId, Integer cantidad) {
    this.id = new FacturaProductoPK(facturaId, productoId);
    this.cantidad = cantidad;
  }

  public FacturaProducto() {
  }
}
