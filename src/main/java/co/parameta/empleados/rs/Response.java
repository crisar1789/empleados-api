package co.parameta.empleados.rs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Objeto para respuesta de la Api")
public class Response<T> {

	@ApiModelProperty(value = "Estado Http de la respuesta del servicio", required = true)
	private int status;
	@ApiModelProperty(value = "Mensaje de la respuestad el servicio", required = true)
	private String message;
	@ApiModelProperty(value = "Contenido con la información de lo ocurrido en el proceso", required = true)
	private T body;
	
	public Response(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public Response(int status, String message, T body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}
}
