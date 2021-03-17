package co.parameta.empleados.rs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Objeto para el contenido de la respuesta exitosa de la api")
public class EmpleadoRs<T> {

	@ApiModelProperty(value = "Tiempo de vionculación del empleado a la compañía")
	private String tiempoVinculacion;
	@ApiModelProperty(value = "Edad del empleado")
	private String edad;
	@ApiModelProperty(value = "Datos del empleado registrado")
	private T empleado;
	
	public EmpleadoRs(String tiempoVinculacion, String edad, T empleado) {
		this.tiempoVinculacion = tiempoVinculacion;
		this.edad = edad;
		this.empleado = empleado;
	}
	
	public String getTiempoVinculacion() {
		return tiempoVinculacion;
	}
	
	public void setTiempoVinculacion(String tiempoVinculacion) {
		this.tiempoVinculacion = tiempoVinculacion;
	}
	
	public String getEdad() {
		return edad;
	}
	
	public void setEdad(String edad) {
		this.edad = edad;
	}

	public T getEmpleado() {
		return empleado;
	}

	public void setEmpleado(T empleado) {
		this.empleado = empleado;
	}
}
