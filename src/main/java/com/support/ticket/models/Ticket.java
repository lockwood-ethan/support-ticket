package com.support.ticket.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    private UUID authorId;

    private String subject;

    private String body;

    @CreatedDate
    private Instant createdDate;

    public Ticket() {}

    public Ticket(UUID id, UUID authorId, String subject, String body, Instant createdDate) {
        this.id = id;
        this.authorId = authorId;
        this.subject = subject;
        this.body = body;
        this.createdDate = createdDate;
    }
}
