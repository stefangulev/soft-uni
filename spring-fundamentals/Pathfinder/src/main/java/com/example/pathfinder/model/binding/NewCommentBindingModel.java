package com.example.pathfinder.model.binding;

import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewCommentBindingModel {
    private String message;

    @NotBlank
    @Size(min = 10)
    public String getMessage() {
        return message;
    }

    public NewCommentBindingModel setMessage(String message) {
        this.message = message;
        return this;
    }
}
