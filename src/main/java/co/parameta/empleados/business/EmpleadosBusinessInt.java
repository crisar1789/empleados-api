package co.parameta.empleados.business;

import co.parameta.empleados.rq.EmpleadoRq;
import co.parameta.empleados.rs.Response;

public interface EmpleadosBusinessInt {

	/**
	 * Método encargado de consumir el servicio SOAP para registrar un meplado
	 * @param <T> Tipo de dato en el cuerpo del response
	 * @param request Objeto con los datos del empleado
	 * @return Objeto con la respuesta del servicio
	 */
	public <T> Response<T> registrarEmpleado(EmpleadoRq request);
}
