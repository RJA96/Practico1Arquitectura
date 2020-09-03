package entity;

import lombok.Getter;

/** Entidad de la tabla Producto */
@Getter
public class Producto {

  private Integer idProducto;

  private String nombre;

  private Float valor;

  public Producto() {}

  public Producto(Integer idProducto, String nombre, Float valor) {
    this.idProducto = idProducto;
    this.nombre = nombre;
    this.valor = valor;
  }

  @Override
  public String toString() {
    return "Producto{"
        + "idProducto="
        + idProducto
        + ", nombre='"
        + nombre
        + '\''
        + ", valor="
        + valor
        + '}';
  }
}
