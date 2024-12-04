package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.domain.Motivo;
import cl.faceledev.xpc.domain.MotivoCriteria;
import cl.faceledev.xpc.repository.CriteriaRepository;
import cl.faceledev.xpc.repository.MotivoRepository;
import cl.faceledev.xpc.service.MotivoService;
import io.facele.facilito.dto.v10.xpc.MotivoType;
import io.facele.facilito.dto.v10.xpc.OutputMotivoConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MotivoServiceImpl implements MotivoService {
    @Autowired
    private MotivoRepository motivoRepository;
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public Motivo saveMotivo(Motivo motivo) {
        return motivoRepository.save(motivo);
    }

    @Override
    public Motivo getMotivoById(long id) {
        return null;
    }

    @Override
    public Motivo updateMotivo(Motivo motivo, long id) {
        return null;
    }

    @Override
    public void deleteMotivo(long id) {

    }

    @Override
    public OutputMotivoConsultar getOutputMotivoConsultar(int limit, int offset, String descripcion) {
        MotivoCriteria criteria = new MotivoCriteria();
        criteria.setLimit(limit);
        criteria.setOffset(offset);

        if (descripcion != null) {
            criteria.setDescripcion(descripcion);
        }
        List<Motivo> motivos = criteriaRepository.findMotivoByCriteria(criteria);
        OutputMotivoConsultar outputMotivoConsultar = new OutputMotivoConsultar();
        List<MotivoType> motivoTypes = outputMotivoConsultar.getRegistro();

        for (Motivo motivo : motivos) {
            MotivoType motivoType = new MotivoType();
            motivoType.setID(motivo.getId());
            motivoType.setDescripcion(motivo.getDescripcion());

            motivoTypes.add(motivoType);
        }

        return outputMotivoConsultar;
    }
}
