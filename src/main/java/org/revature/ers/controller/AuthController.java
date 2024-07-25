package org.revature.ers.controller;

import org.revature.ers.dto.auth.LoginDto;
import org.revature.ers.dto.auth.RegistrationDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDto registrationDto) {
        try {
            String createdUser = authService.registerUser(registrationDto);
            return ResponseEntity.ok("Successfully created user: " + createdUser);
        } catch (CustomException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) {
        try {
            authService.loginUser(loginDto);
        } catch (CustomException e) {
            ResponseEntity.status(400).body(e.getMessage());
        }
        return ResponseEntity.ok("Login Success TEMP");
    }
}
