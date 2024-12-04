package cl.faceledev.xpc.controller;

import io.facele.facilito.dto.v10.xpc.OutputAbonadoConsultar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Abonados")
public interface IAbonadoController {
    @Operation(summary = "Api get data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @GetMapping("/xpc/sa/abonado/v10")
    OutputAbonadoConsultar getAbonadoConsultar(
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam(required = false) Integer estado,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String abonadoIdentificacion
    );
}
