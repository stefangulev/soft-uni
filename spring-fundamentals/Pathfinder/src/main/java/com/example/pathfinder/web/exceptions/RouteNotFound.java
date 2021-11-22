package com.example.pathfinder.web.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RouteNotFound extends RuntimeException{
    public RouteNotFound(String message) {
        super(message);
    }
}
