package org.nikafit.NikaftHub.controllers;

import lombok.RequiredArgsConstructor;
import org.nikafit.NikaftHub.model.Response;
import org.nikafit.NikaftHub.model.User;
import org.nikafit.NikaftHub.service.implementation.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PostMapping("/user")
    public ResponseEntity<Response> saveUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("user", userService.create(user)))
                        .message("User " + user.getFirstName() + " " + user.getLastName() + " created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Response> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder().
                        timeStamp(now())
                        .data(Map.of("user", userService.get(id)))
                        .message("User " + id + " retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Response> deleteUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder().
                        timeStamp(now())
                        .data(Map.of("user", userService.delete(id)))
                        .message("User " + id + " deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
