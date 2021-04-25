package com.tyr.controller;

import com.tyr.autentication.service.token.security.dto.AuthenticationRequest;
import com.tyr.autentication.service.token.security.dto.AuthenticationResponse;
import com.tyr.autentication.service.token.security.dto.TokenRequest;
import com.tyr.autentication.service.token.service.ServiceToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    ServiceToken serviceToken;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> generateToken(@RequestBody AuthenticationRequest request){

        return serviceToken.tokenServiceGenerate(request);
    }

    @PostMapping("/validate")
    public ResponseEntity<Object> validateToken(@RequestBody TokenRequest request){
        return  new ResponseEntity<> (serviceToken.tokenServiceValidate(request), HttpStatus.OK);
    }

}
