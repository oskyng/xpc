package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.Abonado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonadoRepository extends JpaRepository<Abonado, Long> {
    Abonado findByIdentificacion(String identificacion);
}
