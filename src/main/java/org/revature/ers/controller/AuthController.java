package org.revature.ers.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.revature.ers.dto.auth.LoginDto;
import org.revature.ers.dto.auth.LoginResponse;
import org.revature.ers.dto.auth.RegistrationDto;
import org.revature.ers.exception.CustomException;
import org.revature.ers.exception.user.InvalidUsernamePasswordException;
import org.revature.ers.service.AuthService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins="http://localhost:3000", allowCredentials = "true")
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;



    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegistrationDto registrationDto) {
        try {
            String createdUser = authService.registerUser(registrationDto);
            return ResponseEntity.ok("Successfully created user: " + createdUser);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody LoginDto loginDto, HttpSession httpSession) {
        System.out.println("LOGIN HIT");
        try {
            LoginResponse userResponse = authService.loginUser(loginDto);
            if (userResponse != null) {
                httpSession.setAttribute("USER", userResponse);
                httpSession.setAttribute("userId", userResponse.getUserId());
                return ResponseEntity.ok(userResponse);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
            }
        } catch (InvalidUsernamePasswordException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession httpSession) {
        System.out.println("LOGOUT HIT");
        try {
            httpSession.invalidate();
            return ResponseEntity.ok("Logout Successful!");
        } catch (CustomException e) {
            ResponseEntity.status(404).body(e.getMessage());
        }
        return null;
    }

    @GetMapping("/checkSession")
    public ResponseEntity<Boolean> checkSession(HttpSession httpSession) {
        Object user = httpSession.getAttribute("USER");
        boolean isSessionValid = user != null;
        System.out.println("IS SESSION VALID?: " + isSessionValid);
        return ResponseEntity.ok(isSessionValid);
    }
}