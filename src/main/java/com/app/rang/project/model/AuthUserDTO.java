package com.app.rang.project.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AuthUserDTO extends User {
    private String name;

    public AuthUserDTO(String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities,
                         String name) {
        super(username, password, authorities);
        this.name = name;
    }
}