package cl.faceledev.xpc.service;

import cl.faceledev.xpc.domain.Motivo;
import io.facele.facilito.dto.v10.xpc.OutputMotivoConsultar;

public interface MotivoService {
    Motivo saveMotivo(Motivo motivo);
    Motivo getMotivoById(long id);
    Motivo updateMotivo(Motivo motivo, long id);
    void deleteMotivo(long id);
    OutputMotivoConsultar getOutputMotivoConsultar(int limit, int offset, String descripcion);
}
