package com.support.ticket.payload;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationRequest {
    private String email;
    private String password;

    public RegistrationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
