package cl.faceledev.xpc.controller;

import cl.faceledev.xpc.service.ArchivoService;
import io.facele.facilito.dto.v10.xpc.InputUploadCrear;
import io.facele.facilito.dto.v10.xpc.OutputUploadCrear;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoadController implements ILoadController {
    @Autowired
    private ArchivoService archivoService;
    @Override
    public OutputUploadCrear upload(InputUploadCrear input) {
        return archivoService.uploadCrear(input);
    }
}
