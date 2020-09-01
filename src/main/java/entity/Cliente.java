package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Cliente {

  @Getter
  @Id
  private Integer idCliente;

  @Getter
  @Setter
  private String nombre;

  @Getter
  @Setter
  private String email;

  public Cliente() {
  }
  public Cliente (Cliente cliente) {
    this.idCliente = cliente.getIdCliente();
    this.nombre = cliente.getNombre();
    this.email = cliente.getEmail();
  }
  public Cliente(Integer id) {
    this.idCliente = id;
  }

  public Cliente(Integer idCliente, String nombre, String email) {
    this.idCliente = idCliente;
    this.nombre = nombre;
    this.email = email;
  }

  @Override
  public String toString() {
    return "Cliente{" + "idCliente=" + idCliente + ", nombre='" + nombre + '\'' + ", email='"
        + email + '\'' + '}';
  }
}
