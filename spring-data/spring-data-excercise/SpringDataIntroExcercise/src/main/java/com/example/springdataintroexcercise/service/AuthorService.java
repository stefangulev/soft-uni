package com.example.springdataintroexcercise.service;

import com.example.springdataintroexcercise.models.Author;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;
    Author getRandomAuthor();

    List<String> findAllAuthorsOrdereredByBookCount();
}
