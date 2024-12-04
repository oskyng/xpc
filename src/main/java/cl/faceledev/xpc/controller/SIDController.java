package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.SIDService;
import io.facele.facilito.dto.v10.xpc.OutputSIDConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SIDController implements ISIDController{
    @Autowired
    private SIDService sidService;

    @Override
    public OutputSIDConsultar getSIDConsultar(int limit, int offset, String descripcion) {
        return sidService.getOutputSIDConsultar(limit, offset, descripcion);
    }
}
