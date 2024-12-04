package cl.faceledev.xpc.service;

import io.facele.facilito.dto.v10.xpc.InputUploadCrear;
import io.facele.facilito.dto.v10.xpc.OutputUploadCrear;

public interface ArchivoService {
    OutputUploadCrear uploadCrear(InputUploadCrear crear);
}
