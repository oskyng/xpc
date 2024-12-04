package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.Licencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LicenciaRepository extends JpaRepository<Licencia, Long> {
    Licencia findByIdentificacion(String identificacion);
}
