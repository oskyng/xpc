package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.Motivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MotivoRepository extends JpaRepository<Motivo, Long> {
    Motivo findByDescripcion(String descripcion);
}
