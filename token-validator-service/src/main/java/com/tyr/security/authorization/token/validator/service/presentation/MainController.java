package com.tyr.security.authorization.token.validator.service.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping("HolaMundo")
    public String testingController(){
        return "hola";
    }

}
