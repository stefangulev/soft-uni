package com.example.booksrestdemo.entity.dto;

import com.example.booksrestdemo.entity.Author;

public class BookDto {
    private Long id;
    private String title;
    private String isBn;
    private AuthorDto author;

    public String getTitle() {
        return title;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getIsBn() {
        return isBn;
    }

    public BookDto setIsBn(String isBn) {
        this.isBn = isBn;
        return this;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public BookDto setAuthor(AuthorDto author) {
        this.author = author;
        return this;
    }

    public Long getId() {
        return id;
    }

    public BookDto setId(Long id) {
        this.id = id;
        return this;
    }
}
