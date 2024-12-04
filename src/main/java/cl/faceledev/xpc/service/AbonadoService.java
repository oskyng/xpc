package cl.faceledev.xpc.service;

import cl.faceledev.xpc.domain.Abonado;
import io.facele.facilito.dto.v10.xpc.OutputAbonadoConsultar;

public interface AbonadoService {
    Abonado save(Abonado abonado);
    OutputAbonadoConsultar findAll(int limit, int offset, Integer estado, String nombre, String abonadoIdentificacion);
}
