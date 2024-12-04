package cl.faceledev.xpc.service.impl;

import cl.faceledev.xpc.domain.*;
import cl.faceledev.xpc.repository.*;
import cl.faceledev.xpc.service.ExcepcionService;
import io.facele.facilito.dto.v10.xpc.*;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.namespace.QName;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExcepcionServiceImpl implements ExcepcionService {
    @Autowired
    private ExcepcionRepository excepcionRepository;
    @Autowired
    private MIDRepository midRepository;
    @Autowired
    private SIDRepository sidRepository;
    @Autowired
    private UIDRepository uidRepository;
    @Autowired
    private AbonadoRepository abonadoRepository;
    @Autowired
    private MotivoRepository motivoRepository;
    @Autowired
    private LicenciaRepository licenciaRepository;
    @Autowired
    private ArchivoRepository archivoRepository;
    @Autowired
    private CriteriaRepository criteriaRepository;

    @Value("${file.directory.path}")
    private String filePath;
    @Value("${file.name}")
    private String fileName;

    @Override
    public OutputExceptionObtener obtenerExcepcion(Long id) {
        OutputExceptionObtener outputExceptionObtener = new OutputExceptionObtener();
        Optional<Excepcion> excepcion = this.excepcionRepository.findById(id);
        Excepcion excepcionFound = null;
        if (excepcion.isPresent()) {
            excepcionFound = excepcion.get();
        } else {
            return null;
        }

        NodoType nodoType = new NodoType();
        nodoType.setMID(excepcionFound.getMid().getDescripcion());
        nodoType.setSID(excepcionFound.getSid().getDescripcion());
        nodoType.setUID(excepcionFound.getUid().getDescripcion());
        outputExceptionObtener.setNodo(nodoType);
        outputExceptionObtener.setLicenciaIdentificacion(excepcionFound.getLicencia().getIdentificacion());
        outputExceptionObtener.setUser(excepcionFound.getUid().getDescripcion());
        outputExceptionObtener.setFechaEvento(excepcionFound.getFechaEvento().atStartOfDay());
        outputExceptionObtener.setRequestURL("");
        outputExceptionObtener.setAbonadoIdentificacion(excepcionFound.getAbonado().getIdentificacion());

        MotivoType motivoType = new MotivoType();
        motivoType.setID(excepcionFound.getMotivo().getId());
        motivoType.setDescripcion(excepcionFound.getMotivo().getDescripcion());
        outputExceptionObtener.setMotivo(motivoType);

        outputExceptionObtener.setInput(null);
        outputExceptionObtener.setThrowable(null);


        return outputExceptionObtener;
    }

    @Override
    public OutputExcepcionCrear crearExcepcion(InputExcepcionCrear inputExcepcionCrear) {
        OutputExcepcionCrear outputExcepcionCrear;
        Excepcion excepcion = null;
        Optional<MID> midFound = midRepository.findByDescripcion(inputExcepcionCrear.getNodo().getMID());
        Optional<SID> sidFound = sidRepository.findByDescripcion(inputExcepcionCrear.getNodo().getSID());
        Optional<UID> uidFound = uidRepository.findByDescripcion(inputExcepcionCrear.getNodo().getUID());

        if (midFound.isPresent() && sidFound.isPresent() && uidFound.isPresent()) {
            Excepcion excepcionFound = excepcionRepository.findByMidAndSidAndUid(midFound.get(), sidFound.get(), uidFound.get());
            if (excepcionFound == null) {
                excepcion = this.saveExcepcion(midFound.get(), sidFound.get(), uidFound.get(), inputExcepcionCrear);
                outputExcepcionCrear = this.mapOutputExcepcionCrear(excepcion);
                this.saveArchivo(fileName, excepcion, inputExcepcionCrear.getRequestURL(), filePath);
            } else {
                excepcionFound.setCantidadEventos(excepcionFound.getCantidadEventos()+1);
                excepcionRepository.save(excepcionFound);
                outputExcepcionCrear = this.mapOutputExcepcionCrear(excepcionFound);
                this.saveArchivo(fileName, excepcionFound, inputExcepcionCrear.getRequestURL(), filePath);
            }
        } else {
            excepcion = this.saveExcepcion(
                    midFound.orElseGet(() -> this.saveMid(inputExcepcionCrear.getNodo().getMID())),
                    sidFound.orElseGet(() -> this.saveSid(inputExcepcionCrear.getNodo().getSID())),
                    uidFound.orElseGet(() -> this.saveUid(inputExcepcionCrear.getNodo().getUID())),
                    inputExcepcionCrear);
            outputExcepcionCrear = this.mapOutputExcepcionCrear(excepcion);
            this.saveArchivo(fileName, excepcion, inputExcepcionCrear.getRequestURL(), filePath);
        }

        try {
            File file = new File(filePath+"testInputExcepcion.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(OutputExcepcionCrear.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            JAXBElement<OutputExcepcionCrear> jaxbElement = new JAXBElement<>( new QName("", "OutputExcepcionCrear"), OutputExcepcionCrear.class, outputExcepcionCrear);

            jaxbMarshaller.marshal(jaxbElement, file);

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }

        return outputExcepcionCrear;
    }

    @Override
    public OutputExcepcionConsultar consultarExcepcion(Long motivoId, Long midId, Long sidId, Long abonadoId, Long licenciaId, String fechaEventoDesde, String fechaEventoHasta, int limit, int offset) {
        ExcepcionCriteria excepcionCriteria = new ExcepcionCriteria();
        if (motivoId != null) {
            excepcionCriteria.setMotivoId(motivoId);
        }
        if (midId != null) {
            excepcionCriteria.setMidId(midId);
        }
        if (sidId != null) {
            excepcionCriteria.setSidId(sidId);
        }
        if (abonadoId != null) {
            excepcionCriteria.setAbonadoId(abonadoId);
        }
        if (licenciaId != null) {
            excepcionCriteria.setLicenciaId(licenciaId);
        }
        if (fechaEventoDesde != null) {
            excepcionCriteria.setFechaEventoDesde(fechaEventoDesde);
        }
        if (fechaEventoHasta != null) {
            excepcionCriteria.setFechaEventoHasta(fechaEventoHasta);
        }
        excepcionCriteria.setLimit(limit);
        excepcionCriteria.setOffset(offset);

        List<Excepcion> excepcions = criteriaRepository.findExcepcionByCriteria(excepcionCriteria);
        OutputExcepcionConsultar outputExcepcionConsultar = new OutputExcepcionConsultar();
        List<RegistroExcepcionType> registroExcepcionTypes = outputExcepcionConsultar.getRegistro();
        for (Excepcion excepcion : excepcions) {
            RegistroExcepcionType registroExcepcionType = new RegistroExcepcionType();
            registroExcepcionType.setID(excepcion.getId());
            registroExcepcionType.setEstado(String.valueOf(excepcion.getEstado()));
            registroExcepcionType.setCantidadEventos(excepcion.getCantidadEventos());
            registroExcepcionType.setFechaEvento(String.valueOf(excepcion.getFechaEvento()));

            AbonadoType abonadoType = new AbonadoType();
            abonadoType.setID(excepcion.getAbonado().getId());
            abonadoType.setNombre(excepcion.getAbonado().getNombre());
            abonadoType.setIdentificacion(excepcion.getAbonado().getIdentificacion());
            abonadoType.setEstado(EstadoAbonado.POR_ACTUALIZAR);
            registroExcepcionType.setAbonado(abonadoType);

            MotivoType motivoType = new MotivoType();
            motivoType.setID(excepcion.getMotivo().getId());
            motivoType.setDescripcion(excepcion.getMotivo().getDescripcion());
            registroExcepcionType.setMotivo(motivoType);

            SIDType sidType = new SIDType();
            sidType.setID(excepcion.getSid().getId());
            sidType.setDescripcion(excepcion.getSid().getDescripcion());
            registroExcepcionType.setSID(sidType);

            UIDType uidType = new UIDType();
            uidType.setID(excepcion.getUid().getId());
            uidType.setDescripcion(excepcion.getUid().getDescripcion());
            registroExcepcionType.setUID(uidType);

            MIDType midType = new MIDType();
            midType.setID(excepcion.getMid().getId());
            midType.setDescripcion(excepcion.getMid().getDescripcion());
            registroExcepcionType.setMID(midType);

            RegistroDocumentoType registroDocumentoType = new RegistroDocumentoType();
            registroDocumentoType.setFechaRegistro(LocalDateTime.ofInstant(excepcion.getFechaRegistro(), ZoneOffset.UTC).toLocalDate().atStartOfDay());
            registroDocumentoType.setID(excepcion.getId());

            registroExcepcionTypes.add(registroExcepcionType);
        }

        return outputExcepcionConsultar;
    }

    private MID saveMid(String descripcion) {
        MID mid = new MID();
        mid.setDescripcion(descripcion);
        return midRepository.save(mid);
    }

    private SID saveSid(String descripcion) {
        SID sid = new SID();
        sid.setDescripcion(descripcion);
        return sidRepository.save(sid);
    }

    private UID saveUid(String descripcion) {
        UID uid = new UID();
        uid.setDescripcion(descripcion);
        return uidRepository.save(uid);
    }

    private Motivo saveMotivo(String descripcion) {
        Motivo motivo = new Motivo();
        motivo.setDescripcion(descripcion);
        return motivoRepository.save(motivo);
    }

    private Licencia saveLicencia(String descripcion) {
        Licencia licencia = new Licencia();
        licencia.setIdentificacion(descripcion);
        return licenciaRepository.save(licencia);
    }

    private Abonado saveAbonado(String descripcion) {
        Abonado abonado = new Abonado();
        abonado.setIdentificacion(descripcion);
        abonado.setNombre("test");
        abonado.setEstado(1);
        return abonadoRepository.save(abonado);
    }

    private Excepcion saveExcepcion(MID mid, SID sid, UID uid, InputExcepcionCrear inputExcepcionCrear) {
        Excepcion excepcion = new Excepcion();
        excepcion.setMid(mid);
        excepcion.setSid(sid);
        excepcion.setUid(uid);

        Abonado abonado = abonadoRepository.findByIdentificacion(inputExcepcionCrear.getAbonadoIdentificacion());
        if (abonado != null) {
            excepcion.setAbonado(abonado);
        } else {
            excepcion.setAbonado(this.saveAbonado(inputExcepcionCrear.getAbonadoIdentificacion()));
        }

        Licencia licencia = licenciaRepository.findByIdentificacion(inputExcepcionCrear.getLicenciaIdentificacion());
        if (licencia != null) {
            excepcion.setLicencia(licencia);
        } else {
            excepcion.setLicencia(this.saveLicencia(inputExcepcionCrear.getLicenciaIdentificacion()));
        }

        Motivo motivo = motivoRepository.findByDescripcion(inputExcepcionCrear.getMotivo().getDescripcion());
        excepcion.setMotivo((motivo == null)?this.saveMotivo(inputExcepcionCrear.getMotivo().getDescripcion()) : motivo);
        excepcion.setFechaEvento(this.convertToLocalDateViaMilisecond(new Date()));
        excepcion.setEstado(1);
        excepcion.setCantidadEventos(1);
        excepcion.setFechaRegistro(new Date().toInstant());


        return excepcionRepository.save(excepcion);
    }

    private OutputExcepcionCrear mapOutputExcepcionCrear(Excepcion savedExcepcion) {
        OutputExcepcionCrear outputExcepcionCrear = new OutputExcepcionCrear();

        AbonadoType abonadoType = new AbonadoType();
        abonadoType.setID(savedExcepcion.getAbonado().getId());
        abonadoType.setNombre(savedExcepcion.getAbonado().getNombre());
        abonadoType.setIdentificacion(savedExcepcion.getAbonado().getIdentificacion());
        abonadoType.setEstado(EstadoAbonado.POR_ACTUALIZAR);
        outputExcepcionCrear.setAbonado(abonadoType);

        MotivoType motivoType = new MotivoType();
        motivoType.setID(savedExcepcion.getMotivo().getId());
        motivoType.setDescripcion(savedExcepcion.getMotivo().getDescripcion());
        outputExcepcionCrear.setMotivo(motivoType);

        SIDType sidType = new SIDType();
        sidType.setID(savedExcepcion.getSid().getId());
        sidType.setDescripcion(savedExcepcion.getSid().getDescripcion());
        outputExcepcionCrear.setSID(sidType);

        UIDType uidType = new UIDType();
        uidType.setID(savedExcepcion.getUid().getId());
        uidType.setDescripcion(savedExcepcion.getUid().getDescripcion());
        outputExcepcionCrear.setUID(uidType);

        MIDType midType = new MIDType();
        midType.setID(savedExcepcion.getMid().getId());
        midType.setDescripcion(savedExcepcion.getMid().getDescripcion());
        outputExcepcionCrear.setMID(midType);

        RegistroDocumentoType registroDocumentoType = new RegistroDocumentoType();
        registroDocumentoType.setFechaRegistro(LocalDateTime.ofInstant(savedExcepcion.getFechaRegistro(), ZoneOffset.UTC).toLocalDate().atStartOfDay());
        registroDocumentoType.setID(savedExcepcion.getId()); //Revisar

        outputExcepcionCrear.setEstado(savedExcepcion.getEstado().toString());
        outputExcepcionCrear.setID(savedExcepcion.getId());
        outputExcepcionCrear.setCantidadEventos(savedExcepcion.getCantidadEventos());

        return outputExcepcionCrear;
    }

    private Archivo saveArchivo(String nombreArchivo, Excepcion savedExcepcion, String url, String path) {
        Charset charset = StandardCharsets.US_ASCII;
        byte[] byteUrl = charset.encode(url).array();
        byte[] bytePath = charset.encode(path).array();

        Archivo archivo = new Archivo();
        archivo.setNombre(nombreArchivo);
        archivo.setExcepcion(savedExcepcion);
        archivo.setFechaRegistro(savedExcepcion.getFechaRegistro());
        archivo.setEstado(savedExcepcion.getEstado());
        archivo.setUrl(byteUrl);
        archivo.setPath(bytePath);

        return archivoRepository.save(archivo);
    }

    private LocalDate convertToLocalDateViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}
