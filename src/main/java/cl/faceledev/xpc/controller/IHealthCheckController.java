package cl.faceledev.xpc.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Health")
public interface IHealthCheckController {
    ResponseEntity<?> health();
}
