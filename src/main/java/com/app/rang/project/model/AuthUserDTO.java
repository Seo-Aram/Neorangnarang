package com.app.rang.project.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDate;
import java.util.Collection;

public class AuthUserDTO extends User {
    private String nickname;
    private String location;
    private LocalDate joindate;

    public AuthUserDTO(String username,
                       String password,
                       Collection<? extends GrantedAuthority> authorities,
                       String name,
                       String location,
                       LocalDate joindate) {
        super(username, password, authorities);
        this.nickname = name;
        this.location = location;
        this.joindate = joindate;
    }
}