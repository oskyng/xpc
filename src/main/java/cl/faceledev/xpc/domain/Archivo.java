package cl.faceledev.xpc.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "archivo")
public class Archivo {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "excepcion_id", nullable = false)
    private cl.faceledev.xpc.domain.Excepcion excepcion;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "path")
    private byte[] path;

    @Column(name = "url")
    private byte[] url;

    @Column(name = "estado", nullable = false)
    private Integer estado;

    @Column(name = "fecha_registro", nullable = false)
    private Instant fechaRegistro;
}