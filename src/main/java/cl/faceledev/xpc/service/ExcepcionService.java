package cl.faceledev.xpc.service;

import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionConsultar;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExceptionObtener;

public interface ExcepcionService {
    OutputExceptionObtener obtenerExcepcion(Long id);
    OutputExcepcionCrear crearExcepcion(InputExcepcionCrear inputExcepcionCrear);
    OutputExcepcionConsultar consultarExcepcion(Long motivoId, Long midId, Long sidId, Long abonadoId, Long licenciaId, String fechaEventoDesde, String fechaEventoHasta, int limit, int offset);
}
