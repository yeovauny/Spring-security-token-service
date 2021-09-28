package com.tyr.security.authorization.token.validator.service.domain.dto;

import org.springframework.lang.NonNull;


public class LoginDto {

    @NonNull
    private String acces_token;

    public LoginDto(String jwt) {
        this.acces_token =jwt;
    }

    @NonNull
    public String getJwt() {
        return acces_token;
    }

    public void setJwt(@NonNull String jwt) {
        this.acces_token = jwt;
    }
}
