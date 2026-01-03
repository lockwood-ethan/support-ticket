package com.support.ticket.controllers;

import com.support.ticket.models.User;
import com.support.ticket.payload.LoginRequest;
import com.support.ticket.payload.RegistrationRequest;
import com.support.ticket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDateTime;

@RestController
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        User newUser = new User(null, registrationRequest.getEmail(), registrationRequest.getPassword(), Instant.now(), Instant.now());
        User savedUser = userRepository.save(newUser);
        return ResponseEntity.ok().body(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.notFound().build();
    }
}
