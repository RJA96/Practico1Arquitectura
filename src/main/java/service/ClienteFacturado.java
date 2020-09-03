package service;

import entity.Cliente;
import lombok.Data;

/** Objeto de datos para listar los clientes por total facturado. */
@Data
public class ClienteFacturado implements Comparable<ClienteFacturado> {
  private Cliente cliente;
  private Double totalGastado;

  @Override
  public int compareTo(ClienteFacturado o) {
    return Double.compare(this.totalGastado, totalGastado);
  }
}
