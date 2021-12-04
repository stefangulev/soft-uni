package com.example.manchesterunitedfan.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Visit not found!")
public class VisitNotFoundException extends RuntimeException{
    public VisitNotFoundException(String message) {
        super(message);
    }
}
