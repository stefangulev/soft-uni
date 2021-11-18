package com.example.manchesterunitedfan.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "News article not found!")
public class NewsArticleNotFoundException extends RuntimeException{
    public NewsArticleNotFoundException(String message) {
        super(message);
    }
}
