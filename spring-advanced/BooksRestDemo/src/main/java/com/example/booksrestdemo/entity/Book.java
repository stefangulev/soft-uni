package com.example.booksrestdemo.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Book extends BaseEntity {
    private String title;
    private String isBn;
    private Author author;

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;
    }

    @ManyToOne
    public Author getAuthor() {
        return author;
    }

    public Book setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public String getIsBn() {
        return isBn;
    }

    public Book setIsBn(String isBn) {
        this.isBn = isBn;
        return this;
    }
}
