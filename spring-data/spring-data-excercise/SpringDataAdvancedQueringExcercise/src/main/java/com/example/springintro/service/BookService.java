package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllBooksAfterYear(int year);

    List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

    List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

    List<String> getTitlesPerAgeRestrictionType(AgeRestriction ageRestriction);
    List<String> findAllByEditionTypeAndCopiesLessThan(EditionType type, int number);
    List<String> findAllByPriceLessThanAndPriceGreaterThan(BigDecimal priceLessThan, BigDecimal priceHigherThan);
    List<String>findAllByReleaseDateAfterOrReleaseDateBefore(LocalDate lowerBorder, LocalDate upperBorder);
    List<String> findAllReleasedBefore(LocalDate date);
    List<String> findAllByTitleContains(String pattern);

    List<String> findAllBooksWhereAuthorLastNameStartsWithPattern(String pattern);
    String findCountOfBooksWithTitlesLongerThan(int num);
    String findBookByTitleAndPresentReducedInfo(String title);
    int increaseCopiesOfBooksInCollection(int newCopies, List<Book> collection);
    List<Book> findAllPublishedAfterDate(LocalDate date);

    int removeAllBooksWithLessCopiesThan(int minNumberOfCopies);
}
