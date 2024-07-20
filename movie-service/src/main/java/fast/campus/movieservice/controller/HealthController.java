package fast.campus.movieservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping("/api/v1/healthcheck")
    public String healthCheck() {
        return "200 OK";
    }
}
