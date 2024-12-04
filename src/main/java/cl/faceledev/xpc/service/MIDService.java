package cl.faceledev.xpc.service;

import cl.faceledev.xpc.domain.MID;
import io.facele.facilito.dto.v10.xpc.OutputMIDConsultar;

public interface MIDService {
    MID saveMID(MID mid);
    MID getMIDById(long id);
    MID updateMID(MID mid, long id);
    void deleteMID(long id);
    OutputMIDConsultar getOutputMIDConsultar(int limit, int offset, String descripcion);
}
