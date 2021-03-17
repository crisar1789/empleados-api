package co.parameta.empleados.rq;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Objeto de petición para la operación guardar")
public class EmpleadoRq {

	@NotNull
	@Size(min = 1, message = "Debe ingresar un valor")
	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$", message = "Valor inválido")
	@ApiModelProperty(value = "Nombres del empleado", required = true)
	private String nombres;
	@NotNull
	@Size(min = 1, message = "Debe ingresar un valor")
	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$", message = "Valor inválido")
	@ApiModelProperty(value = "Apellidos del empleado", required = true)
	private String apellidos;
	@NotNull
	@Size(min = 1, message = "Debe ingresar un valor")
	@ApiModelProperty(value = "Tipo documento del empleado", required = true)
	private String tipoDocumento;
	@NotNull
	@Size(min = 1, message = "Debe ingresar un valor")
	@Digits(fraction = 0, integer = 50, message="Valor no válido. Debe ingresar números enteros")
	@ApiModelProperty(value = "número de documento del empleado", required = true)
	private String nroDocumento;
	@NotNull
	@Size(min = 10, message = "Debe ingresar un valor en formato yyyy-MM-dd")
	@Pattern(regexp = "\\d{4}\\-\\d{1,2}\\-\\d{1,2}$", message = "Debe ingresar un valor en formato yyyy-MM-dd")
	@ApiModelProperty(value = "fecha de nacimiento del empleado del empleado", required = true)
	private String fechaNacimiento;
	@NotNull
	@Size(min = 10, message = "Debe ingresar un valor en formato yyyy-MM-dd")
	@Pattern(regexp = "\\d{4}\\-\\d{1,2}\\-\\d{1,2}$", message = "Debe ingresar un valor en formato yyyy-MM-dd")
	@ApiModelProperty(value = "fecha de vinculación a la empresa del empleado", required = true)
	private String fechaVinculacion;
	@NotNull
	@Size(min = 1, message = "Debe ingresar un valor")
	@Pattern(regexp = "^[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+$", message = "Valor inválido")
	@ApiModelProperty(value = "Cargo del empleado", required = true)
	private String cargo;
	@NotNull
	@Digits(fraction = 0, integer = 100, message="Valor no válido. Debe ingresar números enteros")
	@Size(min = 1, message = "Debe ingresar un valor")
	@ApiModelProperty(value = "Salario del empleado", required = true)
	private String salario;

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getFechaVinculacion() {
		return fechaVinculacion;
	}

	public void setFechaVinculacion(String fechaVinculacion) {
		this.fechaVinculacion = fechaVinculacion;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getSalario() {
		return salario;
	}

	public void setSalario(String salario) {
		this.salario = salario;
	}
}
