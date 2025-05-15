package com.anytimers.api.util;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.anytimers.api.domain.auth.exception.UserNotFoundException;
import com.anytimers.api.domain.auth.userdetails.CustomUserDetails;
import com.anytimers.api.domain.user.data.User;
import com.anytimers.api.domain.user.data.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Component
public class PrincipalUtil {

    private final UserRepository userRepository;

    public PrincipalUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new UserNotFoundException("Not found");
        }

        Object principal = authentication.getPrincipal();

        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            Integer userId = userDetails.getId();

            return userRepository.findById(userId)
            .orElseThrow(() -> new EntityNotFoundException("Not found"));
        }

        throw new AuthenticationCredentialsNotFoundException("User ID not available");
    }
}
