package com.example.booksrestdemo.service.impl;

import com.example.booksrestdemo.entity.Author;
import com.example.booksrestdemo.entity.Book;
import com.example.booksrestdemo.entity.dto.AuthorDto;
import com.example.booksrestdemo.entity.dto.BookDto;
import com.example.booksrestdemo.repository.AuthorRepository;
import com.example.booksrestdemo.repository.BookRepository;
import com.example.booksrestdemo.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, ModelMapper modelMapper, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<BookDto> findAllBooks() {
        return bookRepository.findAll().stream().map(this::asBook).collect(Collectors.toList());
    }

    public Optional<BookDto> findBookById(Long id) {
        return bookRepository.findById(id).map(this::asBook);
    }

    @Override
    public boolean deleteBook(Long id) {
        Optional<Book> byId = bookRepository.findById(id);
        if(byId.isEmpty()) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public long createBook(BookDto bookDto) {
        Author author = authorRepository.findByName(bookDto.getAuthor().getName())
                .orElseGet(() -> authorRepository.save(new Author().setName(bookDto.getAuthor().getName())));
        Book book = new Book()
                .setAuthor(author)
                .setIsBn(bookDto.getIsBn())
                .setTitle(bookDto.getTitle());

        return bookRepository.save(book).getId();
    }

    @Override
    public Long updateBook(BookDto bookDto, Long id) {
        Optional<Book> byId = bookRepository.findById(id);
        if(byId.isEmpty()) {
            return null;
        } else {
           return bookRepository.save(byId.get()
                    .setAuthor(authorRepository.findByName(bookDto.getAuthor().getName()).orElseGet(() -> authorRepository.save(new Author().setName(bookDto.getAuthor().getName()))))
                    .setIsBn(bookDto.getIsBn())
                    .setTitle(bookDto.getTitle())).getId();

        }
    }

    @Override
    public Page<BookDto> getAllBooks(Integer pageNo, Integer pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return bookRepository.findAll(pageable).map(this::asBook);
    }

    private BookDto asBook(Book book) {
        BookDto bookDTO = modelMapper.map(book, BookDto.class);
        AuthorDto authorDTO = modelMapper.map(book.getAuthor(), AuthorDto.class);
        bookDTO.setAuthor(authorDTO);
        return bookDTO;
    }
}
