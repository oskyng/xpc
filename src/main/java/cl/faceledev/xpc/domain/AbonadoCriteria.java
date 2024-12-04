package cl.faceledev.xpc.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AbonadoCriteria {
    private int limit;
    private int offset;
    private Integer estado;
    private String nombre;
    private String abonadoIdentificacion;
}
