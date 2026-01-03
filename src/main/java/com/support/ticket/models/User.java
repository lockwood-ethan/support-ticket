package com.support.ticket.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Getter
@Setter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Column(unique = true)
    private String email;

    @NonNull
    @Column
    private String password;

    @NonNull
    @Column
    @CreatedDate
    private Instant createdDate;

    @NonNull
    @Column
    @LastModifiedDate
    private Instant lastModifiedDate;

    public User(UUID id, @NonNull String email, @NonNull String password, @NonNull Instant createdDate, @NonNull Instant lastModifiedDate) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public User() {}
}
