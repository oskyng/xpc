package cl.faceledev.xpc.domain;

import lombok.*;
import org.springframework.stereotype.Repository;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExcepcionCriteria {
    private Long motivoId;
    private Long midId;
    private Long sidId;
    private Long abonadoId;
    private Long licenciaId;
    private String fechaEventoDesde;
    private String fechaEventoHasta;
    private int limit;
    private int offset;
}
