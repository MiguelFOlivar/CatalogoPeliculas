package com.mf.JSP_Movie.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private boolean enabled;
    private String role;
    private int failedAttempts;

    public Usuario(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.failedAttempts = 0;
    }

}
