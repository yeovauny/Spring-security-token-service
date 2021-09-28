package com.tyr.security.authorization.token.validator.service.domain.service;


import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;


@Service
public class JwtHelperService {

    private final RSAPrivateKey privateKey;
    private final RSAPublicKey publicKey;

    public JwtHelperService(RSAPrivateKey privateKey, RSAPublicKey publicKey) {
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String createJwtForClaims(String subject, Map<String, String> claims) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(Instant.now().toEpochMilli());
        calendar.add(Calendar.DATE, 1);

        JWTCreator.Builder jwtBuilder = JWT.create().withSubject(subject);

        // Add claims
        claims.forEach(jwtBuilder::withClaim);

        // Add expiredAt and etc
        return jwtBuilder
                .withNotBefore(new Date())
                .withClaim("scope", "pruebascope")
                .withExpiresAt(new Date(System.currentTimeMillis() + (1 * 60 * 1000)))
                .sign(Algorithm.RSA256(publicKey, privateKey));
    }
}
