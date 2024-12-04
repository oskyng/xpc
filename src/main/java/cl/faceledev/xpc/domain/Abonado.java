package cl.faceledev.xpc.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "abonado")
public class Abonado {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 20, nullable = false, unique = true)
    private String identificacion;
    @Column(length = 255, nullable = false)
    private String nombre;
    @Column(nullable = false)
    private Integer estado;
}
