package cl.faceledev.xpc.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UIDCriteria {
    private int limit;
    private int offset;
    private String descripcion;
}
