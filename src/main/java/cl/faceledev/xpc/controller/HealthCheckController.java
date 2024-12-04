package cl.faceledev.xpc.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController implements IHealthCheckController {
    public ResponseEntity<?> health() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
