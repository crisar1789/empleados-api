package co.parameta.empleados.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import co.parameta.empleados.dbmodel.Configuracion;
import co.parameta.empleados.repository.ConfiguracionRepository;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

public class Utilities {

	public static String calcularDiferenciafechas(String fecha) throws DateTimeParseException {
	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ConstantsUtil.DATE_FORMATT);
		
		LocalDate firstDate = LocalDate.parse(fecha, formatter);
		LocalDate secondDate = LocalDate.now();
		
		Period period = Period.between(firstDate, secondDate);
		
		return period.getYears() + " años, " + period.getMonths() + " meses y " + period.getDays() + " días";
	}
	
	public static String obtenerValorConfig(ConfiguracionRepository repository, String codigo) {
		
		Optional<Configuracion> configuracion = repository.finByCodigo(codigo);
		
		if (configuracion.isPresent()) {
			return configuracion.get().getValor();
		}
		
		return "";
	}
	
	public static ApiInfo apiInfo(String apiName) {
		Contact contact  = new Contact(ConstantsUtil.COMPANY_NAME, 
				ConstantsUtil.COMPANY_WEB_SITE, 
				ConstantsUtil.COMPANY_EMAIL);
		
		return new ApiInfoBuilder().title(ConstantsUtil.COMPANY_NAME)
				.description(apiName + " Api Rest")
				.contact(contact)
				.build();
	}
}
