package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.AbonadoService;
import io.facele.facilito.dto.v10.xpc.OutputAbonadoConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class AbonadoController implements IAbonadoController {
    @Autowired
    private AbonadoService abonadoService;

    @Override
    public OutputAbonadoConsultar getAbonadoConsultar(int limit, int offset, Integer estado, String nombre, String abonadoIdentificacion) {
        return abonadoService.findAll(limit, offset, estado, nombre, abonadoIdentificacion);
    }
}
