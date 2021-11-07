package com.example.booksrestdemo.entity.dto;

import java.util.List;

public class AuthorDto {
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public AuthorDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AuthorDto setName(String name) {
        this.name = name;
        return this;
    }

}
