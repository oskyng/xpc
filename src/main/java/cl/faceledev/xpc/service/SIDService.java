package cl.faceledev.xpc.service;

import cl.faceledev.xpc.domain.SID;
import io.facele.facilito.dto.v10.xpc.OutputSIDConsultar;

public interface SIDService {
    SID saveSID(SID sid);
    SID getSIDById(long id);
    SID updateSID(SID sid, long id);
    void deleteSID(long id);
    OutputSIDConsultar getOutputSIDConsultar(int limit, int offset, String descripcion);
}
