package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.MID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MIDRepository extends JpaRepository<MID, Long> {
    Optional<MID> findByDescripcion(String descripcion);
}
