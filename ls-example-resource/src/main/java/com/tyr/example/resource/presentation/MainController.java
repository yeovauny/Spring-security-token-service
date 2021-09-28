package com.tyr.example.resource.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/resource")
    public String methodExamplevalidation(){

        return "hola mundo, ya estas validado!";
    }

}
