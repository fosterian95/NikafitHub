package org.nikafit.NikaftHub.controllers;

import lombok.RequiredArgsConstructor;
import org.nikafit.NikaftHub.model.Response;
import org.nikafit.NikaftHub.service.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static java.time.LocalDateTime.now;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @GetMapping("/users")
    public ResponseEntity<Response> getUsers() {
        return ResponseEntity.ok(
                Response.builder().
                        timeStamp(now())
                        .data(Map.of("users", userService.list(10)))
                        .message("Users retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
