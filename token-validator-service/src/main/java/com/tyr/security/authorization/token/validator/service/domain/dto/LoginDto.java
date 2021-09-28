package com.tyr.security.authorization.token.validator.service.domain.dto;

import org.springframework.lang.NonNull;


public class LoginDto {

    @NonNull
    private String jwt;

    public LoginDto(String jwt) {
        this.jwt =jwt;
    }

    @NonNull
    public String getJwt() {
        return jwt;
    }

    public void setJwt(@NonNull String jwt) {
        this.jwt = jwt;
    }
}
