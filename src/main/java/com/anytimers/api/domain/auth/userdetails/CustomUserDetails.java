package com.anytimers.api.domain.auth.userdetails;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class CustomUserDetails implements UserDetails {

    private Integer id;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String password;

    private List<GrantedAuthority> authorities;
}
