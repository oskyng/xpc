package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoRepository extends JpaRepository<Archivo, Long> {
}
