package com.example.gr.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    private Integer token_id;

    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private Boolean expired;

    private Boolean revoked;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public boolean isExpired() {
        return expired;
    }

    public boolean isRevoked() {
        return revoked;
    }
}
