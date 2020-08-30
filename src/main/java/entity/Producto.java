package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Producto {

  @Id
  private Integer idProducto;

  private String nombre;

  private Float valor;

  public Producto() {
  }

  public Producto(Integer idProducto, String nombre, Float valor) {
    this.idProducto = idProducto;
    this.nombre = nombre;
    this.valor = valor;
  }
}
