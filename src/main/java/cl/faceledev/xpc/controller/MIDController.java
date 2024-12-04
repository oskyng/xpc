package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.MIDService;
import io.facele.facilito.dto.v10.xpc.OutputMIDConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MIDController implements IMIDController{
    @Autowired
    private MIDService midService;

    @Override
    public OutputMIDConsultar getMIDConsultar(int limit, int offset, String descripcion) {
        return midService.getOutputMIDConsultar(limit, offset, descripcion);
    }
}
