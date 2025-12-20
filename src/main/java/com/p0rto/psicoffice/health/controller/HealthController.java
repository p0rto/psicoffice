package com.p0rto.psicoffice.health.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HealthController {
    @GetMapping("/health")
    public String healthcheck() {
        return "API ok.";
    }
    
}
