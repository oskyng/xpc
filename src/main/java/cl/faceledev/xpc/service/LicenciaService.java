package cl.faceledev.xpc.service;

import cl.faceledev.xpc.domain.Licencia;
import io.facele.facilito.dto.v10.xpc.OutputLicenciaConsultar;

public interface LicenciaService {
    Licencia getLicenciaById(Long id);
    Licencia saveLicencia(Licencia licencia);
    Licencia updateLicencia(Licencia licencia, Long id);
    Void deleteLicencia(Long id);
    OutputLicenciaConsultar getOutputLicenciaConsultar(int limit, int offset, String descripcion);
}
