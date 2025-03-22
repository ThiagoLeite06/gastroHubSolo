package com.thiagoleite.GastroHubSolo.entrypoint.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("O servidor está funcionando!");
    }

    @PostMapping("/test")
    public ResponseEntity<String> testPost(@RequestBody(required = false) String body) {
        return ResponseEntity.ok("POST recebido. Body: " + (body != null ? body : "vazio"));
    }
}