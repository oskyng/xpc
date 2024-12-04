package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.SID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SIDRepository extends JpaRepository<SID, Long> {
    Optional<SID> findByDescripcion(String descripcion);
}
