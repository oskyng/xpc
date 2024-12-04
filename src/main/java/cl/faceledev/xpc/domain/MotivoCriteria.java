package cl.faceledev.xpc.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MotivoCriteria {
    private int limit;
    private int offset;
    private String descripcion;
}
