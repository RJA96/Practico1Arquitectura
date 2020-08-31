package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Factura {

  @Getter
  @Id
  private Integer idFactura;

  @Setter
  @ManyToOne
  private Cliente cliente;

  public Factura () {
  }

  public Factura(Integer id) {
    this.idFactura = id;
  }
  public Factura(Integer idFactura, Cliente cliente) {
    this.idFactura = idFactura;
  }


  public Factura(Cliente cliente) {
    this.cliente = cliente;
  }
}
