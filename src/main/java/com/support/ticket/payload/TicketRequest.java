package com.support.ticket.payload;

import com.support.ticket.enums.TicketPriority;
import com.support.ticket.enums.TicketStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TicketRequest {
    String subject;
    String body;
    TicketStatus ticketStatus;
    TicketPriority ticketPriority;

    public TicketRequest(String subject, String body, TicketStatus ticketStatus, TicketPriority ticketPriority) {
        this.subject = subject;
        this.body = body;
        this.ticketStatus = ticketStatus;
        this.ticketPriority = ticketPriority;
    }
}
