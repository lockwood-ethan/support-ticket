package com.support.ticket.controllers;

import com.support.ticket.enums.Role;
import com.support.ticket.enums.TicketStatus;
import com.support.ticket.models.Ticket;
import com.support.ticket.models.UserEntity;
import com.support.ticket.payload.TicketRequest;
import com.support.ticket.repositories.TicketRepository;
import com.support.ticket.repositories.UserEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private final TicketRepository ticketRepository;
    private final UserEntityRepository userEntityRepository;

    public TicketController(TicketRepository ticketRepository, UserEntityRepository userEntityRepository) {
        this.ticketRepository = ticketRepository;
        this.userEntityRepository = userEntityRepository;
    }

    @GetMapping
    public ResponseEntity<?> getAllUserTickets(@AuthenticationPrincipal Jwt jwt) {
        UserEntity userEntity = getOrCreateUser(jwt);
        List<Ticket> tickets = ticketRepository.findAllByAuthorId(userEntity.getId());
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createTicket(@AuthenticationPrincipal Jwt jwt, @RequestBody TicketRequest ticketRequest) {
        UserEntity userEntity = getOrCreateUser(jwt);
        Ticket ticket = new Ticket(null,
                userEntity.getId(),
                null,
                ticketRequest.getSubject(),
                ticketRequest.getBody(),
                ticketRequest.getTicketStatus(),
                ticketRequest.getTicketPriority(),
                Instant.now(),
                Instant.now());
        ticketRepository.save(ticket);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/queue")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<Ticket>> getAllTicketsInOpenQueue() {
        List<Ticket> tickets = ticketRepository.findAllByTicketStatus(TicketStatus.OPEN);
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/assigned")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<List<Ticket>> getAllTicketsInAgentQueue(@AuthenticationPrincipal Jwt jwt) {
        UserEntity userEntity = getOrCreateUser(jwt);
        List<Ticket> agentTickets = ticketRepository.findAllByAssignedTo(userEntity.getId());
        return ResponseEntity.ok(agentTickets);
    }

    @PatchMapping("/{id}/assign")
    @PreAuthorize("hasRole('AGENT')")
    public ResponseEntity<?> assignTicket(@PathVariable UUID id, @AuthenticationPrincipal Jwt jwt) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        if (ticket.isPresent() && ticket.get().getTicketStatus() == TicketStatus.OPEN) {
            ticket.get().setAssignedTo(getOrCreateUser(jwt).getId());
            ticket.get().setTicketStatus(TicketStatus.IN_PROGRESS);
            ticketRepository.save(ticket.get());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Ticket is not found or is already assigned");
        }
        return ResponseEntity.ok().build();
    }

    private UserEntity getOrCreateUser(Jwt jwt) {
        String auth0Id = jwt.getSubject();
        List<String> roles = jwt.getClaimAsStringList("https://support-ticket-app/roles");
        Role jwtRole = roles != null && roles.contains("AGENT") ? Role.AGENT : Role.USER;
        return userEntityRepository.findByAuth0Id(auth0Id)
                .map(existingUser -> {
                    if (existingUser.getRole() != jwtRole) {
                        existingUser.setRole(jwtRole);
                        return userEntityRepository.save(existingUser);
                    }
                    return existingUser;
                })
                .orElseGet(() -> {
                    UserEntity newUserEntity = new UserEntity(null, auth0Id, Instant.now(), jwtRole);
                    return userEntityRepository.save(newUserEntity);
                });
    }
}
