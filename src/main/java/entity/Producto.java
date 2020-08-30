package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer idProducto;

  private String nombre;

  private Float valor;

  public Producto() {
  }

  public Producto(String nombre, Float valor) {
    this.nombre = nombre;
    this.valor = valor;
  }

}
