package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.domain.MID;
import cl.faceledev.xpc.domain.UID;
import cl.faceledev.xpc.domain.UIDCriteria;
import cl.faceledev.xpc.repository.CriteriaRepository;
import cl.faceledev.xpc.repository.UIDRepository;
import cl.faceledev.xpc.service.UIDService;
import io.facele.facilito.dto.v10.xpc.OutputUIDConsultar;
import io.facele.facilito.dto.v10.xpc.UIDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UIDServiceImpl implements UIDService {
    @Autowired
    private UIDRepository uidRepository;
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public UID saveUID(UID uid) {
        return uidRepository.save(uid);
    }

    @Override
    public UID getUIDById(long id) {
        Optional<UID> uid = uidRepository.findById(id);
        if (uid.isPresent()) {
            return uid.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public UID updateUID(UID uid, long id) {
        UID foundUID = uidRepository.findById(id).orElseThrow(RuntimeException::new);
        foundUID.setDescripcion(uid.getDescripcion());

        uidRepository.save(foundUID);
        return foundUID;
    }

    @Override
    public void deleteUID(long id) {
        uidRepository.findById(id).orElseThrow(RuntimeException::new);
        uidRepository.deleteById(id);
    }

    @Override
    public OutputUIDConsultar getOutputUIDConsultar(int limit, int offset, String descripcion) {
        UIDCriteria criteria = new UIDCriteria();
        criteria.setLimit(limit);
        criteria.setOffset(offset);
        if (descripcion != null) {
            criteria.setDescripcion(descripcion);
        }

        List<UID> uids = criteriaRepository.findUidByCriteria(criteria);
        OutputUIDConsultar outputUIDConsultar = new OutputUIDConsultar();
        List<UIDType> uidTypes = outputUIDConsultar.getRegistro();

        for (UID uid : uids) {
            UIDType uidType = new UIDType();
            uidType.setDescripcion(uid.getDescripcion());
            uidType.setID(uid.getId());

            uidTypes.add(uidType);
        }

        return outputUIDConsultar;
    }

}
