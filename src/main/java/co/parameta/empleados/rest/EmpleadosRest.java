package co.parameta.empleados.rest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.mapstruct.Context;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.parameta.empleados.business.EmpleadosBusinessInt;
import co.parameta.empleados.rq.EmpleadoRq;
import co.parameta.empleados.rs.Response;
import co.parameta.empleados.util.ConstantsUtil;
import co.parameta.empleados.util.ValidatorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/api/v1")
@Api(tags = "Empleados Api", produces = "application/json")
public class EmpleadosRest {
	
	private final EmpleadosBusinessInt empleadosBusinessInt;
	
	@Autowired
	public EmpleadosRest(EmpleadosBusinessInt empleadosBusinessInt) {
		this.empleadosBusinessInt = empleadosBusinessInt;
	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Operación para registrar un empleado enviando un objeto json", tags = "Empleados")
	@PostMapping(value = "registrar")
	@ResponseBody
	public <T> Response<T> guardarEmpleado(@Context HttpServletRequest request,
				@ApiParam(value = "Objeto con los datos del empleado") @RequestBody EmpleadoRq empleadoRq) {
		
		try {
			ValidatorUtil.validateRequest(empleadoRq);
			
			return this.empleadosBusinessInt.registrarEmpleado(empleadoRq);
		} catch (ConstraintViolationException e) {
			return (Response<T>) new Response<String>(400, ConstantsUtil.BAD_REQUEST, e.getMessage());
		}
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Operación para registrar un empleado enviando parámetros en la url", tags = "Empleados")
	@GetMapping(value = "registrar")
	@ResponseBody
	public <T> Response<T> guardarEmpleadoGet(@Context HttpServletRequest request,
			EmpleadoRq empleadoRq) {
		
		try {
			ValidatorUtil.validateRequest(empleadoRq);
			
			return this.empleadosBusinessInt.registrarEmpleado(empleadoRq);
		} catch (ConstraintViolationException e) {
			return (Response<T>) new Response<String>(400, ConstantsUtil.BAD_REQUEST, e.getMessage());
		}
	}
}
