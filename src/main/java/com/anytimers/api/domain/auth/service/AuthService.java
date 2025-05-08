package com.anytimers.api.domain.auth.service;

import java.time.Duration;
import java.time.Instant;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.anytimers.api.domain.auth.controller.dto.AuthReadDto;
import com.anytimers.api.domain.auth.controller.dto.AuthWriteDto;
import com.anytimers.api.domain.auth.data.RefreshToken;
import com.anytimers.api.domain.auth.data.RefreshTokenRepository;
import com.anytimers.api.domain.auth.exception.InvalidCredentialsException;
import com.anytimers.api.domain.auth.jwt.JwtUtil;
import com.anytimers.api.domain.auth.mapper.CustomUserDetailsMapper;
import com.anytimers.api.domain.auth.userdetails.CustomUserDetails;
import com.anytimers.api.domain.user.data.User;
import com.anytimers.api.domain.user.service.UserService;
import com.anytimers.api.util.service.EntityService;

@Service
public class AuthService extends EntityService<RefreshToken, RefreshTokenRepository> {

    private final UserService userService;

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsMapper mapper;

    private BCryptPasswordEncoder passwordEncoder;

    public AuthService(RefreshTokenRepository repository, UserService userService, JwtUtil jwtUtil, CustomUserDetailsMapper mapper, BCryptPasswordEncoder encoder) {
        super("refreshToken", repository);
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.mapper = mapper;
        this.passwordEncoder = encoder;
    }

    /**
     * Authenticated the user by email or username and password
     * 
     * @param dto containing the email or username and password
     * @return the accessToken and refreshToken for the given user
     */
    public AuthReadDto authenticate(AuthWriteDto dto) { //TODO: Think about when a user already has a valid refreshToken, how we redirect them to /refresh instead
        User user = getUserAndValidateCredentials(dto.getIdentifier(), dto.getPassword());
        CustomUserDetails userDetails = mapper.toCustomUserDetails(user);
        String accessToken = jwtUtil.generateToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());

        Instant expiresAt = Instant.now().plus(Duration.ofDays(1));
        RefreshToken token = new RefreshToken(refreshToken, user.getId(), expiresAt);
        repository.save(token);

        return new AuthReadDto(accessToken, refreshToken);
    }

    /**
     * Validates user credentials
     * @param identifier the email or username
     * @param password the password authenticating the user
     * @return {@link User} object
     */
    private User getUserAndValidateCredentials(String identifier, String password) {

        User user = userService.getUserByNameOrEmail(identifier);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Wrong credentials");
        }

        return user;
    }

}
