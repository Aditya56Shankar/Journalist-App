package com.aditya_shankar.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {
    @GetMapping("/check") public String healthCheck() {
        return "OK";
    }
}
