package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllBooksAfterYear(int year) {
        return bookRepository
                .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
    }

    @Override
    public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
        return bookRepository
                .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
                        book.getAuthor().getLastName()))
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
       return bookRepository
                .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
                .stream()
                .map(book -> String.format("%s %s %d",
                        book.getTitle(),
                        book.getReleaseDate(),
                        book.getCopies()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getTitlesPerAgeRestrictionType(AgeRestriction ageRestriction) {
        return bookRepository.findBooksByAgeRestriction(ageRestriction).stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByEditionTypeAndCopiesLessThan(EditionType type, int number) {
        return bookRepository.findAllByEditionTypeAndCopiesLessThan(type, number).stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByPriceLessThanAndPriceGreaterThan(BigDecimal priceLessThan, BigDecimal priceHigherThan) {
       return bookRepository.findAllByPriceLessThanOrPriceGreaterThan(priceLessThan, priceHigherThan)
               .stream().map(m -> String.format("%s - $ %.2f", m.getTitle(), m.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByReleaseDateAfterOrReleaseDateBefore(LocalDate lowerBorder, LocalDate upperBorder) {
        return bookRepository.findAllByReleaseDateBeforeOrReleaseDateAfter(lowerBorder, upperBorder).stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllReleasedBefore(LocalDate date) {
        return bookRepository.findAllByReleaseDateBefore(date).stream().map(b -> String.format("%s %s %.2f", b.getTitle(), b.getEditionType(),  b.getPrice())).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllByTitleContains(String pattern) {
        String adaptPattern = pattern.toLowerCase();
        return bookRepository.findAllByTitleContains(adaptPattern).stream().map(Book::getTitle).collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksWhereAuthorLastNameStartsWithPattern(String pattern) {
        return bookRepository.findAllByAuthor_LastNameStartsWith(pattern).stream().map(b -> String.format("%s (%s %s)", b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName())).collect(Collectors.toList());
    }

    @Override
    public String findCountOfBooksWithTitlesLongerThan(int num) {
        int result = bookRepository.findCountOfBooksWithTitlesLongerThan(num);
        return String.format("There are %d books with longer title than %d symbols", result, num);
    }

    @Override
    public String findBookByTitleAndPresentReducedInfo(String title) {
        Book booksByTitle = bookRepository.findBooksByTitle(title);
        return String.format("%s %s %s %.2f", booksByTitle.getTitle(), booksByTitle.getEditionType(), booksByTitle.getAgeRestriction(), booksByTitle.getPrice());

    }

    @Override
    @Transactional
    public int increaseCopiesOfBooksInCollection(int newCopies, List<Book> collection) {
       bookRepository.increaseCopiesOfBooksInCollection(newCopies, collection);
       return collection.size() * newCopies;
    }

    @Override
    public List<Book> findAllPublishedAfterDate(LocalDate date) {
        return bookRepository.findAllByReleaseDateAfter(date);
    }

    @Override
    @Transactional
    public int removeAllBooksWithLessCopiesThan(int minNumberOfCopies) {
        return bookRepository.deleteAllByCopiesIsLessThan(minNumberOfCopies);
    }


    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

    }
}
