package com.support.ticket.repositories;

import com.support.ticket.enums.TicketStatus;
import com.support.ticket.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    List<Ticket> findAllByAuthorId(UUID authorId);
    List<Ticket> findAllByTicketStatus(TicketStatus ticketStatus);
    List<Ticket> findAllByAssignedTo(UUID assignedTo);
}
