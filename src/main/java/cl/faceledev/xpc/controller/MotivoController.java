package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.MotivoService;
import io.facele.facilito.dto.v10.xpc.OutputMotivoConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MotivoController implements IMotivoController{
    @Autowired
    private MotivoService motivoService;

    @Override
    public OutputMotivoConsultar getMotivo(int limit, int offset, String descripcion) {
        return motivoService.getOutputMotivoConsultar(limit, offset, descripcion);
    }
}
