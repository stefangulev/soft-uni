package com.example.springdataintroexcercise.service;

import com.example.springdataintroexcercise.enums.AgeRestriction;
import com.example.springdataintroexcercise.enums.EditionType;
import com.example.springdataintroexcercise.models.Author;
import com.example.springdataintroexcercise.models.Book;
import com.example.springdataintroexcercise.models.Category;
import com.example.springdataintroexcercise.repositories.AuthorRepository;
import com.example.springdataintroexcercise.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookImpl implements BookService{

    private final String FILE_PATH = "src/main/resources/files/books.txt";
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final AuthorService authorService;
  private final CategoryService categoryService;

    public BookImpl(BookRepository bookRepository, AuthorRepository authorRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public List<Book> booksAfterYear(int year) {
       return bookRepository.getAllByReleaseDateIsAfter(LocalDate.of(year, 12, 31));

    }

    @Override
    public void seedBooks() throws IOException {
        if(bookRepository.count() == 0) {
            Files.readAllLines(Path.of(FILE_PATH)).stream().map(row -> row.split(" ")).forEach(s -> {
                String title = Arrays.stream(s).skip(5).collect(Collectors.joining(" "));
                EditionType type = EditionType.values()[Integer.parseInt(s[0])];
                BigDecimal price = new BigDecimal(s[3]);
                LocalDate date = LocalDate.parse(s[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
                AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(s[4])];
                int copies = Integer.parseInt(s[2]);
                Book book = new Book(title, type, price, date, ageRestriction, copies);
                Author author = authorService.getRandomAuthor();
                categoryService.getRandomCategory().forEach(c -> book.getCategories().add(c));
                book.setAuthor(author);
                bookRepository.save(book);
            });

        }
    }

    @Override
    public List<String> findAllAuthorsWithBooksBefore(int year) {
       return bookRepository.getAllByReleaseDateBefore(LocalDate.of(year, 1, 1 )).stream().map(b -> String.format("%s %s", b.getAuthor().getFirstName(), b.getAuthor().getLastName())).distinct().collect(Collectors.toList());
    }

    @Override
    public List<String> printAllByAuthorNameOrderByReleaseDateDESCBookTitleASC(String firstname, String lastname) {
        return bookRepository.getAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitleAsc(firstname,lastname).stream().map(e -> String.format("%s %s %d",e.getTitle(), e.getReleaseDate().toString(), e.getCopies())).collect(Collectors.toList());
    }
}
