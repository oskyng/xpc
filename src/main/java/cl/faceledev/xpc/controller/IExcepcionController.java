package cl.faceledev.xpc.controller;

import io.facele.facilito.dto.v10.xpc.InputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionConsultar;
import io.facele.facilito.dto.v10.xpc.OutputExcepcionCrear;
import io.facele.facilito.dto.v10.xpc.OutputExceptionObtener;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Excepcion")
public interface IExcepcionController {
    @Operation(summary = "Api get excepcion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @GetMapping("/xpc/sa/excepcion/v10")
    OutputExcepcionConsultar getExcepcion(
            @RequestParam(required = false) Long motivoId,
            @RequestParam(required = false) Long midId,
            @RequestParam(required = false) Long sidId,
            @RequestParam(required = false) Long abonadoId,
            @RequestParam(required = false) Long licenciaId,
            @RequestParam(required = false) String fechaEventoDesde,
            @RequestParam(required = false) String fechaEventoHasta,
            @RequestParam(required = true) int limit,
            @RequestParam(required = true) int offset);

    @Operation(summary = "Api create data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @PostMapping("/xpc/sa/excepcion/v10")
    OutputExcepcionCrear createExcepcion(@RequestBody @Valid InputExcepcionCrear inputExcepcionCrear);

    @Operation(summary = "Api get data by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @GetMapping("/xpc/sa/excepcion/v10/{id}")
    OutputExceptionObtener obtenerExcepcion(@PathVariable("id") Long id);
}
