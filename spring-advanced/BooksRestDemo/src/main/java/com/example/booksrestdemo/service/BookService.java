package com.example.booksrestdemo.service;

import com.example.booksrestdemo.entity.dto.BookDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> findAllBooks();

    Optional<BookDto> findBookById(Long id);

    boolean deleteBook(Long id);

    long createBook(BookDto bookDto);

    Long updateBook(BookDto bookDto, Long id);


    Page<BookDto> getAllBooks(Integer pageNo, Integer pageSize, String sortBy);
}
