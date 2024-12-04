package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.UIDService;
import io.facele.facilito.dto.v10.xpc.OutputUIDConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UIDController implements IUIDController {
    @Autowired
    private UIDService uidService;

    @Override
    public OutputUIDConsultar getUIDConsultar(int limit, int offset, String descripcion) {
        return uidService.getOutputUIDConsultar(limit, offset, descripcion);
    }
}
