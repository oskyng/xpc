package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.domain.Abonado;
import cl.faceledev.xpc.domain.AbonadoCriteria;
import cl.faceledev.xpc.repository.CriteriaRepository;
import io.facele.facilito.dto.v10.xpc.*;

import cl.faceledev.xpc.repository.AbonadoRepository;
import cl.faceledev.xpc.service.AbonadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbonadoServiceImpl implements AbonadoService {
    @Autowired
    AbonadoRepository abonadoRepository;
    @Autowired
    CriteriaRepository criteriaRepository;

    @Override
    public Abonado save(Abonado abonado) {
        return abonadoRepository.save(abonado);
    }

    @Override
    public OutputAbonadoConsultar findAll(int limit, int offset, Integer estado, String nombre, String abonadoIdentificacion) {
        AbonadoCriteria abonadoCriteria = new AbonadoCriteria();
        if (estado != null) {
            abonadoCriteria.setEstado(estado);
        }
        if (nombre != null) {
            abonadoCriteria.setNombre(nombre);
        }
        if (abonadoIdentificacion != null) {
            abonadoCriteria.setAbonadoIdentificacion(abonadoIdentificacion);
        }
        abonadoCriteria.setLimit(limit);
        abonadoCriteria.setOffset(offset);

        List<Abonado> abonados = criteriaRepository.findAbonadosByCriteria(abonadoCriteria);
        OutputAbonadoConsultar outputAbonadoConsultar = new OutputAbonadoConsultar();
        List<AbonadoType> abonadoTypes = outputAbonadoConsultar.getRegistro();
        for (Abonado abonado : abonados) {
            AbonadoType abonadoType = new AbonadoType();
            abonadoType.setID(abonado.getId());
            abonadoType.setNombre(abonado.getNombre());
            abonadoType.setIdentificacion(abonado.getIdentificacion());
            abonadoType.setEstado(EstadoAbonado.POR_ACTUALIZAR);

            abonadoTypes.add(abonadoType);
        }

        return outputAbonadoConsultar;
    }
}
