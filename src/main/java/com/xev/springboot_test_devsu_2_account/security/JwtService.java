package com.xev.springboot_test_devsu_2_account.security;

public interface JwtService {
    Long extractClientId(String token);

    boolean isTokenValid(String token);
}
