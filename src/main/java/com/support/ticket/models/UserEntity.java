package com.support.ticket.models;

import com.support.ticket.enums.Role;
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
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "auth0id", unique = true)
    private String auth0Id;

    @CreatedDate
    private Instant createdDate;

    private Role role;

    public UserEntity(UUID id, String auth0Id, Instant createdDate, Role role) {
        this.id = id;
        this.auth0Id = auth0Id;
        this.createdDate = createdDate;
        this.role = role;
    }

    public UserEntity() {}

}
