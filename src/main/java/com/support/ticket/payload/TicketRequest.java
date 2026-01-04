package com.support.ticket.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TicketRequest {
    String title;
    String body;

    TicketRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
