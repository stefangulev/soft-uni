package com.example.springdataintroexcercise.service;

import com.example.springdataintroexcercise.enums.AgeRestriction;
import com.example.springdataintroexcercise.enums.EditionType;
import com.example.springdataintroexcercise.models.Author;
import com.example.springdataintroexcercise.models.Book;
import com.example.springdataintroexcercise.models.Category;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConsoleRunner implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public ConsoleRunner(BookService bookService, AuthorService authorService, CategoryService categoryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }


    @Override
    public void run(String... args) throws Exception {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
        printAllBooksAfter2000(2000);
        printAllAuthorsNamesWithBooksAfterYear(1990);
        printAllAuthorsOrderedByNumberOfBooks();
        printAllByAuthorNameOrderByReleaseDateDESCBookTitleASC("George","Powell");

    }

    private void printAllByAuthorNameOrderByReleaseDateDESCBookTitleASC(String firstname, String lastname) {
        bookService.printAllByAuthorNameOrderByReleaseDateDESCBookTitleASC(firstname, lastname).forEach(System.out::println);
    }

    private void printAllAuthorsOrderedByNumberOfBooks() {
     authorService.findAllAuthorsOrdereredByBookCount().forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksAfterYear(int i) {
        bookService.findAllAuthorsWithBooksBefore(i).forEach(System.out::println);
    }

    private void printAllBooksAfter2000(int year) {
        bookService.booksAfterYear(year).forEach(b -> System.out.println(b.getTitle()));
    }
}
