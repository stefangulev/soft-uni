package com.example.springdataintroexcercise.service;


import com.example.springdataintroexcercise.models.Author;
import com.example.springdataintroexcercise.repositories.AuthorRepository;
import com.example.springdataintroexcercise.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class AuthorImpl implements AuthorService {
    private final String FILE_PATH = "src/main/resources/files/authors.txt";
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorImpl(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    @Override
    public void seedAuthors() throws IOException {
        if(authorRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH)).stream().forEach(row -> {
                String[] s = row.split(" ");
                Author author = new Author(s[0], s[1]);
                authorRepository.save(author);
            });
        }
    }

    @Override
    public Author getRandomAuthor() {
        Random rand = new Random();
        return authorRepository.findById(rand.nextInt((int) authorRepository.count())).orElse(null);
    }

    @Override
    public List<String> findAllAuthorsOrdereredByBookCount() {
        return authorRepository.findAllAuthorsOrderedByBookCountDESC().stream().
                map(a -> String.format("%s %s - %d books", a.getFirstName(), a.getLastName(), a.getBooks().size())).collect(Collectors.toList());
    }


}
