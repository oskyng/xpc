package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.domain.MID;
import cl.faceledev.xpc.domain.MIDCriteria;
import cl.faceledev.xpc.repository.CriteriaRepository;
import cl.faceledev.xpc.repository.MIDRepository;
import cl.faceledev.xpc.service.MIDService;
import io.facele.facilito.dto.v10.xpc.MIDType;
import io.facele.facilito.dto.v10.xpc.OutputMIDConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MIDServiceImpl implements MIDService {
    @Autowired
    private MIDRepository midRepository;
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public MID saveMID(MID mid) {
        return midRepository.save(mid);
    }

    @Override
    public MID getMIDById(long id) {
        Optional<MID> mid = midRepository.findById(id);
        if (mid.isPresent()) {
            return mid.get();
        } else {
            throw new RuntimeException();
        }
    }

    @Override
    public MID updateMID(MID mid, long id) {
        MID foundMID = midRepository.findById(id).orElseThrow(RuntimeException::new);
        foundMID.setDescripcion(mid.getDescripcion());

        midRepository.save(foundMID);
        return foundMID;
    }

    @Override
    public void deleteMID(long id) {
        midRepository.findById(id).orElseThrow(RuntimeException::new);
        midRepository.deleteById(id);
    }

    @Override
    public OutputMIDConsultar getOutputMIDConsultar(int limit, int offset, String descripcion) {
        MIDCriteria criteria = new MIDCriteria();
        if (descripcion != null) {
            criteria.setDescripcion(descripcion);
        }
        criteria.setLimit(limit);
        criteria.setOffset(offset);

        List<MID> mids = criteriaRepository.findMidByCriteria(criteria);

        OutputMIDConsultar midConsultar = new OutputMIDConsultar();
        List<MIDType> midTypes = midConsultar.getRegistro();

        for (MID mid : mids) {
            MIDType midType = new MIDType();
            midType.setID(mid.getId());
            midType.setDescripcion(mid.getDescripcion());

            midTypes.add(midType);
        }

        return midConsultar;
    }
}
