package com.example.springdataintroexcercise.repositories;

import com.example.springdataintroexcercise.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springdataintroexcercise.models.Book;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> getAllByReleaseDateIsAfter(LocalDate date);
    List<Book> getAllByReleaseDateBefore(LocalDate date);
    List<Book> getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(String firstname, String lastName);
}
