package com.anytimers.api.util.authorities;

public class Authorities {
    public static final String IS_USER = "hasAnyAuthority('USER')";
    public static final String IS_ADMIN = "hasAnyAuthority('ADMIN', 'USER')";
}
