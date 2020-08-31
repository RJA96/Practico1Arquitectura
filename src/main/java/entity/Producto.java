package entity;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@ToString
public class Producto {

  @Id
  private Integer idProducto;

  private String nombre;

  private Float valor;

  public Producto() {}

  public Producto(Integer idProducto, String nombre, Float valor) {
    this.idProducto = idProducto;
    this.nombre = nombre;
    this.valor = valor;
  }
}
