package com.tyr.autentication.service.token.security.dto;

public class TokenRequest {

    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
