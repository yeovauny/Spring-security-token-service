package com.tyr.autentication.service.token.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JWTUtil {


    @Value("${token.cypher}")
    private String key;

    @Value("${token.expiretime}")
    private int tokenExpireTime;

//firts builder pattern
    // second with the userName and the date of token as created
    // third expiration time token
    // four cypher algorim with the key

    public String generateToken(UserDetails userdetails){
        return Jwts.builder().setSubject(userdetails.getUsername()).setIssuedAt(new Date())
               // .setExpiration(new Date(System.currentTimeMillis() +1000 * 60 * 60 *10))
                .setExpiration(Date.from(ZonedDateTime.now().plusMinutes(tokenExpireTime).toInstant()))
                .signWith(SignatureAlgorithm.HS256, key).compact();

    }


    public boolean validateToken(String token, UserDetails userDetails){
        return userDetails.getUsername().equals(extractUsername(token)) && !isTokenExpired(token);
    }


    public String extractUsername(String token){
        return getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token){
        return getClaims(token).getExpiration().before(new Date());
    }


    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
    }


}
