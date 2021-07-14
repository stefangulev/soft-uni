package com.example.springdataintroexcercise.service;

import com.example.springdataintroexcercise.models.Book;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<Book> booksAfterYear(int year);
    void seedBooks() throws IOException;
    List<String> findAllAuthorsWithBooksBefore(int i);
    List<String> printAllByAuthorNameOrderByReleaseDateDESCBookTitleASC(String firname, String lastname);
}
