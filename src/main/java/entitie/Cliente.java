package entitie;

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

  @OneToMany
  private List<Factura> facturas;

  public Cliente() {
  }
  public Cliente(Integer idCliente, String nombre, String email) {
    this.idCliente = idCliente;
    this.nombre = nombre;
    this.email = email;
  }

}
