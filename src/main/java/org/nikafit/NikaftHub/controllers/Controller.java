package org.nikafit.NikaftHub.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test-endpoint")
public class Controller {

    @GetMapping
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello World!");
    }

    @GetMapping("/other-endpoint")
    public ResponseEntity<String> howAreYou() {
        return ResponseEntity.ok("How Are You?");
    }
}
