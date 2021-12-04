package com.example.manchesterunitedfan.service.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Invalid role choice!")
public class InvalidRoleChoiceException extends RuntimeException {
    public InvalidRoleChoiceException(String message) {
        super(message);
    }
}
