package com.example.booksrestdemo.web;

import com.example.booksrestdemo.entity.dto.BookDto;
import com.example.booksrestdemo.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<BookDto>> getAllBooks(
            @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
            @RequestParam(name = "pageSize", defaultValue = "3" ) Integer pageSize,
            @RequestParam(name = "sortBy", defaultValue = "id") String sortBy
    ) {

        return ResponseEntity.ok(bookService.getAllBooks(pageNo, pageSize,sortBy));
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> allBooks = bookService.findAllBooks();
        return ResponseEntity.ok(allBooks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Long id) {
        Optional<BookDto> bookById = bookService.findBookById(id);
        if(bookById.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(bookById.get());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable Long id) {
       if(!bookService.deleteBook(id)) {
           return ResponseEntity.notFound().build();
       }
        return ResponseEntity.noContent().build();
    }
    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto, UriComponentsBuilder uriComponentsBuilder) {
        long bookId = bookService.createBook(bookDto);
        URI uri = uriComponentsBuilder.path("/books/{id}").buildAndExpand(bookId).toUri();
        return ResponseEntity.created(uri).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable Long id, UriComponentsBuilder uriComponentsBuilder) {
        Long updatedBookId = bookService.updateBook(bookDto, id);
        URI uri = uriComponentsBuilder.path("/books/{id}").buildAndExpand(updatedBookId).toUri();
        return updatedBookId != null ? ResponseEntity.created(uri).build() : ResponseEntity.notFound().build();
    }
}
