package co.parameta.empleados.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import co.parameta.empleados.dbmodel.Configuracion;

public interface ConfiguracionRepository extends CrudRepository<Configuracion, String> {

	@Query(value = ("SELECT u FROM CONFIGURACION u WHERE u.codigo = ?1"))
	Optional<Configuracion> finByCodigo(String codigo);
}
