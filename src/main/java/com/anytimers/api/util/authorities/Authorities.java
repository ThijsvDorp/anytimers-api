package com.anytimers.api.util.authorities;

public final class Authorities {
    public static final String IS_USER = "hasAnyAuthority('USER','ADMIN')";
    public static final String IS_ADMIN = "hasAnyAuthority('ADMIN')";
}
