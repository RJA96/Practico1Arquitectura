package entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Factura {

	private Integer idFactura;

	@Setter
	@ManyToOne
	private Integer idCliente;

	public Factura(Integer idFactura, Integer cliente) {
		this.idFactura = idFactura;
		this.idCliente = cliente;
	}

	public Integer getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}

	public void setCliente(Integer cliente) {
		this.idCliente = cliente;
	}

	public Integer getidCliente() {
		return this.idCliente;
	}

}
