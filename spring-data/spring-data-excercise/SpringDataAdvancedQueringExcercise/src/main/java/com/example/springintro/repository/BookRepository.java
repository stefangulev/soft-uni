package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

    List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

    List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName, String author_lastName);

    List<Book> findBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType type, int number);
    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal priceLessThan, BigDecimal priceHigherThan);
    List<Book> findAllByReleaseDateBeforeOrReleaseDateAfter(LocalDate lowerBorder, LocalDate upperBorder);
    List<Book> findAllByTitleContains(String pattern);
    List<Book> findAllByAuthor_LastNameStartsWith(String pattern);
    @Query("SELECT COUNT(b.id) FROM Book b WHERE length(b.title) > :num")
    int findCountOfBooksWithTitlesLongerThan(int num);
    Book findBooksByTitle(String title);
    @Query("UPDATE Book b SET b.copies = b.copies + :newCopies WHERE b IN :collection")
    @Modifying
    int increaseCopiesOfBooksInCollection(int newCopies, List<Book> collection);
    @Modifying
    int deleteAllByCopiesIsLessThan(int num);


}
