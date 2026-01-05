package com.support.ticket.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "ticket_number" ,unique = true, nullable = false, updatable = false)
    private Long ticketNumber;

    private UUID authorId;

    @Column(columnDefinition = "TEXT")
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    @CreatedDate
    private Instant createdDate;

    public Ticket() {}

    public Ticket(UUID id, Long ticketNumber, UUID authorId, String subject, String body, Instant createdDate) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.authorId = authorId;
        this.subject = subject;
        this.body = body;
        this.createdDate = createdDate;
    }
}
