package com.tyr.autentication.service.token.service;


import com.tyr.autentication.service.token.security.JWTUtil;
import com.tyr.autentication.service.token.security.dto.AuthenticationRequest;
import com.tyr.autentication.service.token.security.dto.AuthenticationResponse;
import com.tyr.autentication.service.token.security.dto.TokenRequest;
import com.tyr.domain.response.ValidateResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ServiceToken {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenUserDetailsService tokenUserDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    public ResponseEntity<AuthenticationResponse> tokenServiceGenerate(AuthenticationRequest request){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = tokenUserDetailsService.loadUserByUsername(request.getUsername());

            String jwt = jwtUtil.generateToken(userDetails);
            return new ResponseEntity<>(new AuthenticationResponse(jwt), HttpStatus.OK);
        }catch (BadCredentialsException e){
            return new ResponseEntity<>(
                    HttpStatus.FORBIDDEN);
        }

    }


    public Object tokenServiceValidate(TokenRequest tokenRequest){

        String username = jwtUtil.extractUsername(tokenRequest.getJwt());

        // the security context holder is out of here because is autenticate by springSecurity url.
        //if(username !=null && SecurityContextHolder.getContext().getAuthentication() == null){
        if(username != null){
            UserDetails userDetails = tokenUserDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(tokenRequest.getJwt(),userDetails)){
                return  new ValidateResponse(true, new Date(),"this token is valid");
                //UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                //authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails());
            }

            return new ValidateResponse(false, new Date(),"this token is  not valid");

        }
    return new ValidateResponse(false, new Date(),"this token is  not valid");
    }



}
