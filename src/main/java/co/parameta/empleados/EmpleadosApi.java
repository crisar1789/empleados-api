package co.parameta.empleados;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.google.common.base.Predicates;

import co.parameta.empleados.util.Utilities;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class EmpleadosApi {

	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(EmpleadosApi.class);
		log.info("Start Empleados Api");
		SpringApplication.run(EmpleadosApi.class, args);
	}
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(Utilities.apiInfo("Empleados"))
						.select().apis(Predicates.not(
									RequestHandlerSelectors.basePackage("org.springframework.boot")))
					.build().pathMapping("empleados");
	}
}
