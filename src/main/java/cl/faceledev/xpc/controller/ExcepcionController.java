package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.ExcepcionService;
import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionConsultar;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExceptionObtener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcepcionController implements IExcepcionController{
    @Autowired
    private ExcepcionService excepcionService;

    @Override
    public OutputExcepcionConsultar getExcepcion(Long motivoId, Long midId, Long sidId, Long abonadoId, Long licenciaId, String fechaEventoDesde, String fechaEventoHasta, int limit, int offset) {
        return excepcionService.consultarExcepcion(motivoId, midId, sidId, abonadoId, licenciaId, fechaEventoDesde, fechaEventoHasta, limit, offset);
    }

    @Override
    public OutputExcepcionCrear createExcepcion(InputExcepcionCrear inputExcepcionCrear) {
        return excepcionService.crearExcepcion(inputExcepcionCrear);
    }

    @Override
    public OutputExceptionObtener obtenerExcepcion(Long id) {
        return excepcionService.obtenerExcepcion(id);
    }
}
