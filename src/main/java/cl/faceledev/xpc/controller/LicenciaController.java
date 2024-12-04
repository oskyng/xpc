package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.LicenciaService;
import io.facele.facilito.dto.v10.xpc.OutputLicenciaConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LicenciaController implements ILicenciaController {
    @Autowired
    private LicenciaService licenciaService;

    @Override
    public OutputLicenciaConsultar getLicencia(int limit, int offset, String descripcion) {
        return licenciaService.getOutputLicenciaConsultar(limit, offset, descripcion);
    }
}
