package cl.faceledev.xpc.service;


import cl.faceledev.xpc.domain.UID;
import io.facele.facilito.dto.v10.xpc.OutputUIDConsultar;

public interface UIDService {
    UID saveUID(UID uid);
    UID getUIDById(long id);
    UID updateUID(UID uid, long id);
    void deleteUID(long id);
    OutputUIDConsultar getOutputUIDConsultar(int limit, int offset, String descripcion);
}
