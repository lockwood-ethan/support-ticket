package com.support.ticket.controllers;

import com.support.ticket.models.UserEntity;
import com.support.ticket.repositories.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/me")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public Optional<UserEntity> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        return userRepository
                .findByAuth0Id(jwt.getSubject());
    }
}
