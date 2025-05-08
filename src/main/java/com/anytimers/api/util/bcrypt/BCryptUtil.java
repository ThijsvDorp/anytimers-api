package com.anytimers.api.util.bcrypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {


    private static final BCryptPasswordEncoder INSTANCE = new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);

    private BCryptUtil() {}

    public BCryptPasswordEncoder getInstance() {
        return INSTANCE;
    }
}
