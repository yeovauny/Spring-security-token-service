package com.tyr.security.authorization.token.validator.service.presentation;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.tyr.security.authorization.token.validator.service.domain.dto.LoginDto;
import com.tyr.security.authorization.token.validator.service.domain.service.JwtHelperService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = {"${app.security.cors.origin}"})
@RestController

public class AuthController {

    private final JwtHelperService jwtHelperService;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtHelperService jwtHelperService, UserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.jwtHelperService = jwtHelperService;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(path = "login", consumes = { MediaType.APPLICATION_FORM_URLENCODED_VALUE })
    public LoginDto login(
            @RequestParam String username,
            @RequestParam String password) {

        UserDetails userDetails;
        try {
            userDetails = userDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found");
        }

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            Map<String, String> claims = new HashMap<>();
            claims.put("username", username);

            String authorities = userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.joining(" "));
            claims.put("authorities", authorities);
            claims.put("userId", String.valueOf(1));

            String jwt = jwtHelperService.createJwtForClaims(username, claims);
            return new LoginDto(jwt);
        }

        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not authenticated");
    }
}
