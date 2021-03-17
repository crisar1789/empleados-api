package co.parameta.empleados.dbmodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "CONFIGURACION")
public class Configuracion {

	@Id
	@Column(name = "CODIGO")
	private String codigo;
	@Column(name = "VALOR")
	private String valor;
	
	public Configuracion() {
		super();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}
