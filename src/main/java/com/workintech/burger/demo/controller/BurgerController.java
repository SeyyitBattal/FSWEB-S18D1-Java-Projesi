package com.workintech.burger.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hi")
public class BurgerController {

    @GetMapping("/")
    public String sayHi(){
        return "Hello";
    }

}
