package entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
public class Factura {

  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idFactura;

  @ManyToOne
  private Cliente cliente;

  public Factura() {
  }



  public Factura(Cliente cliente) {
    this.cliente = cliente;
  }
}
