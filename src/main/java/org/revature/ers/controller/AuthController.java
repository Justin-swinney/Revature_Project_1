package org.revature.ers.controller;

import org.revature.ers.dto.UserRegistrationDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        try {
            String createdUser = authService.registerUser(userRegistrationDto);
            return ResponseEntity.ok("Successfully created user: " + createdUser);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
