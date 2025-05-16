package com.anytimers.api.domain.auth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anytimers.api.domain.auth.controller.dto.AuthReadDto;
import com.anytimers.api.domain.auth.controller.dto.AuthWriteDto;
import com.anytimers.api.domain.auth.service.AuthService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(
    value = "/v1/auth",
    produces = MediaType.APPLICATION_JSON_VALUE
)
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
    
    @PostMapping("/authenticate")
    public AuthReadDto authenticate(@Valid @RequestBody AuthWriteDto dto) {
        return authService.authenticate(dto);
    }

}
