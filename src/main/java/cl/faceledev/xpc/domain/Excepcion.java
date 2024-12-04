package cl.faceledev.xpc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "excepcion")
public class Excepcion {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "fecha_evento", nullable = false)
    private LocalDate fechaEvento;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "motivo_id", nullable = false)
    private Motivo motivo;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "mid_id", nullable = false)
    private MID mid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sid_id", nullable = false)
    private SID sid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "uid_id", nullable = false)
    private UID uid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abonado_id")
    private Abonado abonado;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "licencia_id", nullable = false)
    private cl.faceledev.xpc.domain.Licencia licencia;

    @Column(name = "cantidad_eventos", nullable = false)
    private Integer cantidadEventos;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}