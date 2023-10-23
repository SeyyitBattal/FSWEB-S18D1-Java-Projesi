package com.workintech.burger.demo.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BurgerExcetion extends RuntimeException {
    private HttpStatus status;

    public BurgerExcetion(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
