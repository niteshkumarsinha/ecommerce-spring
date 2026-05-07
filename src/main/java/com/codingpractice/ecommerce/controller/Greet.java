package com.codingpractice.ecommerce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Greet {
    @GetMapping("/api/echo")
    public ResponseEntity<String> echo(@RequestParam(name = "message", defaultValue = "Hi Everyone", required = false) String message){
        return new ResponseEntity<>("Echoed Message: " + message, HttpStatus.OK);
    }

}
