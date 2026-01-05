package com.support.ticket.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TicketRequest {
    String subject;
    String body;

    public TicketRequest(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
