package cl.faceledev.xpc.repository;

import cl.faceledev.xpc.domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CriteriaRepository {
    private final EntityManager entityManager;

    public CriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Abonado> findAbonadosByCriteria(AbonadoCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Abonado> cq = cb.createQuery(Abonado.class);

        Root<Abonado> root = cq.from(Abonado.class);
        List<Predicate> predicates = new ArrayList<>();
        if (criteria.getEstado() != null) {
            predicates.add(cb.equal(root.get("estado"), criteria.getEstado()));
        }
        if (criteria.getNombre() != null) {
            predicates.add(cb.equal(root.get("nombre"), criteria.getNombre()));
        }
        if (criteria.getAbonadoIdentificacion() != null) {
            predicates.add(cb.equal(root.get("abonadoIdentificacion"), criteria.getAbonadoIdentificacion()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).getResultList();
    }

    public List<MID> findMidByCriteria(MIDCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<MID> cq = cb.createQuery(MID.class);
        Root<MID> root = cq.from(MID.class);
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getDescripcion() != null) {
            predicates.add(cb.equal(root.get("descripcion"), criteria.getDescripcion()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).getResultList();
    }

    public List<SID> findSidByCriteria(SIDCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SID> cq = cb.createQuery(SID.class);
        Root<SID> root = cq.from(SID.class);
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getDescripcion() != null) {
            predicates.add(cb.equal(root.get("descripcion"), criteria.getDescripcion()));
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).getResultList();
    }

    public List<UID> findUidByCriteria(UIDCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UID> cq = cb.createQuery(UID.class);
        Root<UID> root = cq.from(UID.class);
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getDescripcion() != null) {
            predicates.add(cb.equal(root.get("descripcion"), criteria.getDescripcion()));
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).getResultList();
    }

    public List<Motivo> findMotivoByCriteria(MotivoCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Motivo> cq = cb.createQuery(Motivo.class);
        Root<Motivo> root = cq.from(Motivo.class);
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getDescripcion() != null) {
            predicates.add(cb.equal(root.get("descripcion"), criteria.getDescripcion()));
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).getResultList();
    }

    public List<Licencia> findLicenciaByCriteria(LicenciaCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Licencia> cq = cb.createQuery(Licencia.class);
        Root<Licencia> root = cq.from(Licencia.class);
        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getDescripcion() != null) {
            predicates.add(cb.equal(root.get("descripcion"), criteria.getDescripcion()));
        }
        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).getResultList();
    }

    public List<Excepcion> findExcepcionByCriteria(ExcepcionCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Excepcion> cq = cb.createQuery(Excepcion.class);

        Root<Excepcion> root = cq.from(Excepcion.class);
        Join<Excepcion, MID> joinMid = root.join("mid", JoinType.INNER);
        Join<Excepcion, Motivo> joinMotivo = root.join("motivo", JoinType.INNER);
        Join<Excepcion, Abonado> joinAbonado = root.join("abonado", JoinType.INNER);
        Join<Excepcion, SID> joinSid = root.join("sid", JoinType.INNER);
        Join<Excepcion, Licencia> joinLicencia = root.join("licencia", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        if (criteria.getMotivoId() != null) {
            predicates.add(cb.equal(joinMotivo.get("id"), criteria.getMotivoId()));
        }
        if (criteria.getMidId() != null) {
            predicates.add(cb.equal(joinMid.get("id"), criteria.getMidId()));
        }
        if (criteria.getSidId() != null) {
            predicates.add(cb.equal(joinSid.get("id"), criteria.getSidId()));
        }
        if (criteria.getAbonadoId() != null) {
            predicates.add(cb.equal(joinAbonado.get("id"), criteria.getAbonadoId()));
        }
        if (criteria.getLicenciaId() != null) {
            predicates.add(cb.equal(joinLicencia.get("id"), criteria.getLicenciaId()));
        }
        if (criteria.getFechaEventoDesde() != null && criteria.getFechaEventoHasta() != null) {
            predicates.add(cb.between(root.get("fecha_evento"), criteria.getFechaEventoDesde(), criteria.getFechaEventoHasta()));
        }

        cq.where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(cq).setFirstResult(criteria.getOffset()).setMaxResults(criteria.getLimit()).getResultList();
    }

}
