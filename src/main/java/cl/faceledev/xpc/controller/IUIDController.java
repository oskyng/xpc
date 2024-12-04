package cl.faceledev.xpc.controller;

import io.facele.facilito.dto.v10.xpc.OutputUIDConsultar;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "UID")
public interface IUIDController {
    @Operation(summary = "Api get data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", description = "Data not found", content = @Content)
    })
    @GetMapping("/xpc/sa/uid/v10")
    OutputUIDConsultar getUIDConsultar(
            @RequestParam int limit,
            @RequestParam int offset,
            @RequestParam(required = false) String descripcion
    );
}
