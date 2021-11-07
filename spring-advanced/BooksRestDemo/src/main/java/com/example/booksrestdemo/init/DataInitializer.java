package com.example.booksrestdemo.init;

import com.example.booksrestdemo.entity.Author;
import com.example.booksrestdemo.entity.Book;
import com.example.booksrestdemo.repository.AuthorRepository;
import com.example.booksrestdemo.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public DataInitializer(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

     if (bookRepository.count() == 0 && authorRepository.count() == 0) {
        initJovkov();
        initNikolaiHaitov();
        initDimitarTalev();
        initElinPelin();
        initVazov();
    }
}

    private void initNikolaiHaitov() {
        initAuthor("Николай Хайтов",
                "Диви Разкази"
        );
    }

    private void initDimitarTalev() {
        initAuthor("Димитър Димов",
                "Тютюн"
        );
    }

    private void initElinPelin() {
        initAuthor("Елин Пелин",
                "Пижо и Пендо",
                "Ян Бибиян на луната",
                "Под манастирската лоза"
        );
    }

    private void initVazov() {
        initAuthor("Иван Вазов",
                "Пряпорец и Гусла",
                "Под Игото",
                "Тъгите на България"
        );
    }

    private void initJovkov() {

        initAuthor("Йордан Йовков",
                "Старопланински легенди",
                "Чифликът край границата");
    }

    private void initAuthor(String authorName, String... books) {
        Author author = new Author();
        author.setName(authorName);
        author = authorRepository.save(author);

        Set<Book> allBooks = new HashSet<>();

        for (String book: books) {
            Book aBook = new Book();
            aBook.setAuthor(author);
            aBook.setTitle(book);
            aBook.setIsBn(UUID.randomUUID().toString());
            allBooks.add(aBook);
        }

        author.setBooks(allBooks);

        bookRepository.saveAll(allBooks);
}
}

