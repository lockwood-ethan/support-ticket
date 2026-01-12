package com.support.ticket.models;

import com.support.ticket.enums.TicketPriority;
import com.support.ticket.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;
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

    @Column(nullable = false, unique = true, updatable = false, insertable = false)
    private Long ticketNumber;

    private UUID authorId;

    @Column(columnDefinition = "TEXT")
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String body;

    private TicketStatus ticketStatus;

    private TicketPriority ticketPriority;

    @CreatedDate
    private Instant createdDate;

    @UpdateTimestamp
    private Instant updatedDate;

    public Ticket() {}

    public Ticket(UUID id, UUID authorId, String subject, String body, TicketStatus ticketStatus, TicketPriority ticketPriority, Instant createdDate, Instant updatedDate) {
        this.id = id;
        this.authorId = authorId;
        this.subject = subject;
        this.body = body;
        this.ticketStatus = ticketStatus;
        this.ticketPriority = ticketPriority;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
