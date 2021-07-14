package com.example.springdataintroexcercise.repositories;

import com.example.springdataintroexcercise.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    @Query("SELECT a FROM Author a ORDER BY a.books.size DESC ")
    List<Author> findAllAuthorsOrderedByBookCountDESC();
}
