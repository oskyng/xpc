package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.domain.MID;
import cl.faceledev.xpc.domain.SID;
import cl.faceledev.xpc.domain.SIDCriteria;
import cl.faceledev.xpc.repository.CriteriaRepository;
import cl.faceledev.xpc.repository.SIDRepository;
import cl.faceledev.xpc.service.SIDService;
import io.facele.facilito.dto.v10.xpc.OutputSIDConsultar;
import io.facele.facilito.dto.v10.xpc.SIDType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SIDServiceImpl implements SIDService {
    @Autowired
    private SIDRepository sidRepository;
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public SID saveSID(SID sid) {
        return sidRepository.save(sid);
    }

    @Override
    public SID getSIDById(long id) {
        Optional<SID> sid = sidRepository.findById(id);
        if (sid.isPresent()) {
            return sid.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public SID updateSID(SID sid, long id) {
        SID foundSid = sidRepository.findById(id).orElseThrow(RuntimeException::new);
        foundSid.setDescripcion(sid.getDescripcion());
        sidRepository.save(foundSid);

        return foundSid;
    }

    @Override
    public void deleteSID(long id) {
        sidRepository.findById(id).orElseThrow(RuntimeException::new);
        sidRepository.deleteById(id);
    }

    @Override
    public OutputSIDConsultar getOutputSIDConsultar(int limit, int offset, String descripcion) {
        SIDCriteria criteria = new SIDCriteria();
        criteria.setLimit(limit);
        criteria.setOffset(offset);
        if (descripcion != null) {
            criteria.setDescripcion(descripcion);
        }

        List<SID> sids = criteriaRepository.findSidByCriteria(criteria);
        OutputSIDConsultar sidConsultar = new OutputSIDConsultar();
        List<SIDType> sidTypes = sidConsultar.getRegistro();

        for (SID sid : sids) {
            SIDType sidType = new SIDType();
            sidType.setID(sid.getId());
            sidType.setDescripcion(descripcion);

            sidTypes.add(sidType);
        }

        return sidConsultar;
    }
}
