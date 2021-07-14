package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;
    private final BufferedReader bufferedReader;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService, BufferedReader bufferedReader) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
        this.bufferedReader = bufferedReader;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();
//        //printAllBooksAfterYear(2000);
////        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
//     //   printAllAuthorsAndNumberOfTheirBooks();
//        pritnALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");
        try {
            int problemNum = parseNumberWithMessage("Please enter problem number:");
            switch (problemNum) {
                case 1:
                    problemOne();
                    break;
                case 2:
                    problemTwo();
                    break;
                case 3:
                    problemThree();
                    break;
                case 4:
                    problemFour();
                    break;
                case 5:
                    problemFive();
                    break;
                case 6:
                    problemSix();
                    break;
                case 7:
                    problemSeven();
                    break;
                case 8:
                    problemEight();
                    break;
                case 9:
                    problemNine();
                    break;
                case 10:
                    problemTen();
                    break;
                case 11:
                    problemEleven();
                    break;
                case 12:
                    problemTwelve();
                    break;
                case 13:
                    problemThirteen();
                    break;
                default:
                    System.out.println("Invalid problem number");
            }
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input");
        }

    }



    private void problemThirteen() throws IOException {
        int minNumberOfCopies = parseNumberWithMessage("Please enter the minimum number of copies. All books below this number will be deleted from the database:");
        System.out.println(bookService.removeAllBooksWithLessCopiesThan(minNumberOfCopies));

    }

    private void problemTwelve() {
        try {
            LocalDate date = parseLocalDate();
            List<Book> allPublishedAfterDate = bookService.findAllPublishedAfterDate(date);
            int newCopies = parseNumberWithMessage("Please enter number of the copies to be added to each book");
            int totalCopiesAdded = bookService.increaseCopiesOfBooksInCollection(newCopies, allPublishedAfterDate);
            System.out.printf("%d books are released after %d %s %d, so total of %d book copies were added%n", allPublishedAfterDate.size(), date.getDayOfMonth(), date.getMonth(), date.getYear(), totalCopiesAdded);
        } catch (Exception ex) {
            System.out.println("Invalid input");
        }
    }

    private void problemEleven() throws IOException {
        try {
            String title = parseStringWithMessage("Please enter book title to display title, edition type, age restriction and price:");
            System.out.println(bookService.findBookByTitleAndPresentReducedInfo(title));
        } catch (NullPointerException ex) {
            System.out.println("No such book exists!");
        }
    }

    private void problemTen() {
        authorService.findSumOfCopiesPerAuthor().forEach(System.out::println);
        System.out.println("Above you can see all authors listed, along with the total copies of their books.");
    }

    private void problemNine() throws IOException {
        try {
            int num = parseNumberWithMessage("Please enter min. book title length to present the count of books with longer titles");
            System.out.println(bookService.findCountOfBooksWithTitlesLongerThan(num));
        } catch(NumberFormatException ex) {
            System.out.println("Invalid input!");
        }
    }

    private void problemEight() throws IOException {
        String pattern = parseStringWithMessage("Please enter desired pattern for the start of an author's last name to display books created by the relevant authors:");
        bookService.findAllBooksWhereAuthorLastNameStartsWithPattern(pattern).forEach(System.out::println);
    }

    private void problemSeven() throws IOException {
        String pattern = parseStringWithMessage("Please enter desired pattern to be contained in the book title to display relevant books:");
        bookService.findAllByTitleContains(pattern).forEach(System.out::println);
    }

    private void problemSix() throws IOException {
        String pattern = parseStringWithMessage("Enter desired pattern for the end of the author's first name to display relevant authors:");
        authorService.findAllByFirstNameEndingWith(pattern).forEach(System.out::println);

    }

    private void problemFive() throws IOException {
        try {
            LocalDate date = parseLocalDate();
            bookService.findAllReleasedBefore(date).forEach(System.out::println);
        }catch (DateTimeParseException ex) {
            System.out.println("Invalid input format");
        }



    }

    private void problemFour() throws IOException {
        try {
           int year = parseNumberWithMessage("Please enter release year for excluded books:");
           bookService.findAllByReleaseDateAfterOrReleaseDateBefore
                   (LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31)).forEach(System.out::println);
       } catch (NumberFormatException ex) {
           System.out.println("Invalid input");
       }

    }

    private void problemThree() {
        bookService.findAllByPriceLessThanAndPriceGreaterThan(new BigDecimal(5), new BigDecimal(40)).forEach(System.out::println);
        System.out.println("What you see above are the books with price less than 5 and more than 40");
    }

    private void problemTwo() {
        bookService.findAllByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000).forEach(System.out::println);
        System.out.println("What you see above are the gold edition books with less than 5000 copies:");
    }

    private void problemOne() throws IOException {
        try {
            AgeRestriction ageRestriction = AgeRestriction.valueOf(parseStringWithMessage("Please enter age restriction type to display relevant books:").toUpperCase());
            bookService.getTitlesPerAgeRestrictionType(ageRestriction).forEach(System.out::println);
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid age restriction type");
        }
    }

    private void pritnALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
        bookService
                .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
                .forEach(System.out::println);
    }

    private void printAllAuthorsAndNumberOfTheirBooks() {
        authorService
                .getAllAuthorsOrderByCountOfTheirBooks()
                .forEach(System.out::println);
    }

    private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
        bookService
                .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
                .forEach(System.out::println);
    }

    private void printAllBooksAfterYear(int year) {
        bookService
                .findAllBooksAfterYear(year)
                .stream()
                .map(Book::getTitle)
                .forEach(System.out::println);
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }

    private LocalDate parseLocalDate() throws IOException {
        System.out.println("Please enter release date to get books released before this date");
            String dateInput = bufferedReader.readLine();
            return LocalDate.parse(dateInput,  dateInput.contains("-") ? DateTimeFormatter.ofPattern("dd-MM-yyyy") : DateTimeFormatter.ofPattern("dd MMM yyyy") );
    }
    public int parseNumberWithMessage(String message) throws IOException {
        System.out.println(message);
            return Integer.parseInt(bufferedReader.readLine()); }

    public String parseStringWithMessage(String message) throws IOException {
        System.out.println(message);
        return bufferedReader.readLine();
    }
}

