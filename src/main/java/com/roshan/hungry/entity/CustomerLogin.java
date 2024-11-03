package com.roshan.hungry.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class CustomerLogin {
    @Id
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name="password", nullable = false)
    private String password;
}
