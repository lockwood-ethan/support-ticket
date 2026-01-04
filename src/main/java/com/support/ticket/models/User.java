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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String auth0Id;

    @CreatedDate
    private Instant createdDate;

    public User(UUID id, String auth0Id, Instant createdDate) {
        this.id = id;
        this.auth0Id = auth0Id;
        this.createdDate = createdDate;
    }

    public User() {}

    public User orElseGet(Object o) {
        return null;
    }
}
