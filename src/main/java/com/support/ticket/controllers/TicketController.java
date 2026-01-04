package com.support.ticket.controllers;

import com.support.ticket.models.Ticket;
import com.support.ticket.models.UserEntity;
import com.support.ticket.payload.TicketRequest;
import com.support.ticket.repositories.TicketRepository;
import com.support.ticket.repositories.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketController(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllUserTickets(@AuthenticationPrincipal Jwt jwt) {
        UserEntity userEntity = getOrCreateUser(jwt);
        List<Ticket> tickets = ticketRepository.findAllByAuthorId(userEntity.getId());
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createTicket(@AuthenticationPrincipal Jwt jwt, @RequestBody TicketRequest ticketRequest) {
        UserEntity userEntity = getOrCreateUser(jwt);
        Ticket ticket = new Ticket(null, userEntity.getId(), ticketRequest.getTitle(), ticketRequest.getBody(), Instant.now());
        ticketRepository.save(ticket);
        return ResponseEntity.ok(ticket);
    }

    private UserEntity getOrCreateUser(Jwt jwt) {
        String auth0Id = jwt.getSubject();
        return userRepository.findByAuth0Id(auth0Id)
                .orElseGet(() -> {
                    UserEntity newUserEntity = new UserEntity(null, auth0Id, Instant.now());
                    return userRepository.save(newUserEntity);
                });
    }
}
