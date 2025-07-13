package com.anytimers.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.anytimers.api.util.bcrypt.BCryptUtil;

@Configuration
@Profile("dev")
public class DevSecurityConfig {

    private final BCryptUtil bCryptUtil;

    public DevSecurityConfig(BCryptUtil bCryptUtil) {
        this.bCryptUtil = bCryptUtil;
    }
}
