package com.entechilavu.backend.security;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, name = "username")
    private String username;

    @Column(nullable = false)
    private String password; // Must be stored encrypted

    private String role; // e.g., "ROLE_USER", "ROLE_ADMIN"

    @Column(name = "created")
    private LocalDate created;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }



    @Override
    public String getUsername() {
        return "";
    }
}
