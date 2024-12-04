package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExcepcionRepository extends JpaRepository<Excepcion, Long>{
    Excepcion findByMidAndSidAndUid(MID mid, SID sid, UID uid);
    Excepcion findByAbonadoAndMotivoAndMidAndSidAndUidAndFechaEvento(Abonado abonado, Motivo motivo, MID mid, SID sid, UID uid, LocalDate fechaEvento);

}
