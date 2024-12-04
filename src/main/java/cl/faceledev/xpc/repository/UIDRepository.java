package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.UID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UIDRepository extends JpaRepository<UID, Long> {
    Optional<UID> findByDescripcion(String descripcion);
}
