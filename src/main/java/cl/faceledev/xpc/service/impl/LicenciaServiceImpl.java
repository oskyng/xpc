package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.domain.Licencia;
import cl.faceledev.xpc.domain.LicenciaCriteria;
import cl.faceledev.xpc.repository.CriteriaRepository;
import cl.faceledev.xpc.repository.LicenciaRepository;
import cl.faceledev.xpc.service.LicenciaService;
import io.facele.facilito.dto.v10.xpc.LicenciaType;
import io.facele.facilito.dto.v10.xpc.OutputLicenciaConsultar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LicenciaServiceImpl implements LicenciaService {
    @Autowired
    private LicenciaRepository licenciaRepository;
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Override
    public Licencia getLicenciaById(Long id) {
        return null;
    }

    @Override
    public Licencia saveLicencia(Licencia licencia) {
        return licenciaRepository.save(licencia);
    }

    @Override
    public Licencia updateLicencia(Licencia licencia, Long id) {
        return null;
    }

    @Override
    public Void deleteLicencia(Long id) {
        return null;
    }

    @Override
    public OutputLicenciaConsultar getOutputLicenciaConsultar(int limit, int offset, String descripcion) {
        LicenciaCriteria criteria = new LicenciaCriteria();
        if (descripcion != null) {
            criteria.setDescripcion(descripcion);
        }
        criteria.setLimit(limit);
        criteria.setOffset(offset);

        List<Licencia> licencias = criteriaRepository.findLicenciaByCriteria(criteria);
        OutputLicenciaConsultar outputLicenciaConsultar = new OutputLicenciaConsultar();
        List<LicenciaType> licenciaTypes = outputLicenciaConsultar.getRegistro();

        for (Licencia licencia: licencias) {
            LicenciaType licenciaType = new LicenciaType();
            licenciaType.setID(licencia.getId());
            licenciaType.setIdentificacion(licencia.getIdentificacion());

            licenciaTypes.add(licenciaType);
        }

        return outputLicenciaConsultar;
    }
}
