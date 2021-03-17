package co.parameta.empleados.business;

import java.time.format.DateTimeParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parameta.service.empleados.exception.SrvException;
import com.parameta.service.empleados.srv.EmpleadoRequest;
import com.parameta.service.empleados.srv.EmpleadoResponse;
import com.parameta.service.empleados.wsclient.SrvEmpleadosImpl;
import com.parameta.service.empleados.wsclient.SrvEmpleadosInt;

import co.parameta.empleados.repository.ConfiguracionRepository;
import co.parameta.empleados.rq.EmpleadoRq;
import co.parameta.empleados.rs.EmpleadoRs;
import co.parameta.empleados.rs.Response;
import co.parameta.empleados.util.ConstantsUtil;
import co.parameta.empleados.util.Utilities;

@Service
public class EmpleadosBusinessImpl implements EmpleadosBusinessInt {

	private static final Logger log = LoggerFactory.getLogger(EmpleadosBusinessImpl.class);
	private SrvEmpleadosInt srvEmpleados;
	private ConfiguracionRepository configuracionRepository;

	@Autowired
	public EmpleadosBusinessImpl(ConfiguracionRepository configuracionRepository) {
		super();
		this.configuracionRepository = configuracionRepository;
		String endpoint = Utilities.obtenerValorConfig(this.configuracionRepository, "ENDPOINT_EMPLEADO_SRV");
		log.info("Endpoint: " + endpoint);
		String rqTimeout = Utilities.obtenerValorConfig(this.configuracionRepository, "RQTIMEOUT_EMPLEADO_SRV");
		String cnTimeout = Utilities.obtenerValorConfig(this.configuracionRepository, "CNXTIMEOUT_EMPLEADO_SRV");
		this.srvEmpleados = new SrvEmpleadosImpl(endpoint, Integer.parseInt(rqTimeout),
               Integer.parseInt(cnTimeout));
	}
	
	/**
	 * Método encargado de consumir el servicio SOAP para registrar un meplado
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <T> Response<T> registrarEmpleado(EmpleadoRq request) {
		try {
			String fechaNacimiento = Utilities.calcularDiferenciafechas(request.getFechaNacimiento());
			String[] datos = fechaNacimiento.split(" años");
			
			// Se verifica si el empleado es menor de edad
			if (Integer.valueOf(datos[0]) < 18) {
				return (Response<T>) new Response<String>(400, ConstantsUtil.BAD_REQUEST, "Empleado no válido, es menor de edad");
			}
			
			EmpleadoRequest requestWs = new EmpleadoRequest();
			requestWs.setNombres(request.getNombres());
			requestWs.setApellidos(request.getApellidos());
			requestWs.setTipoDocumento(request.getTipoDocumento());
			requestWs.setNroDocumento(request.getNroDocumento());
			requestWs.setFechaNacimiento(request.getFechaNacimiento());
			requestWs.setFechaVinculacion(request.getFechaVinculacion());
			requestWs.setCargo(request.getCargo());
			requestWs.setSalario(request.getSalario());
			
			EmpleadoResponse responseWs = srvEmpleados.registrarEmpleado(requestWs);
			
			if ("200".equals(responseWs.getContext().getCodigo())) {
				EmpleadoRs<EmpleadoRq> empleadoRs = new EmpleadoRs<EmpleadoRq>(Utilities.calcularDiferenciafechas(request.getFechaVinculacion()), fechaNacimiento, request);
				return (Response<T>) new Response<EmpleadoRs>(200, ConstantsUtil.SUCCESS_TRANSACTION, empleadoRs);
			} else {
				return (Response<T>) new Response<EmpleadoResponse>(500, ConstantsUtil.INTERNAL_SERVER_ERROR, responseWs);
			}
		} catch(DateTimeParseException e) {
			log.error("Business error", e);
			return (Response<T>) new Response<String>(400, ConstantsUtil.BAD_REQUEST, "Las fechas deben tener datos válidos");
		} catch(SrvException e) {
			log.error("Service Client error", e);
			return (Response<T>) new Response<String>(500, ConstantsUtil.INTERNAL_SERVER_ERROR, e.getMessage());
		} catch(Exception e) {
			log.error("Business error", e);
			return new Response<>(500, ConstantsUtil.INTERNAL_SERVER_ERROR);
		}
	}	
}
