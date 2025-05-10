package com.anytimers.api.domain.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anytimers.api.domain.auth.mapper.CustomUserDetailsMapper;
import com.anytimers.api.domain.user.data.User;
import com.anytimers.api.domain.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    private final CustomUserDetailsMapper mapper;

    public CustomUserDetailsService(UserService userService, CustomUserDetailsMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = userService.getUserByNameOrEmail(username);
            return mapper.toCustomUserDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    public UserDetails loadUserById(Integer userId) throws UsernameNotFoundException {
        System.out.println(userId + " User: " + userService.getUserById(userId));
        try {
            User user = userService.getUserById(userId);
            return mapper.toCustomUserDetails(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found with ID: " + userId);
        }
    }
}
