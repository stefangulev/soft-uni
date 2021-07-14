import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Book implements Comparable<Book> {
    private String title;
    private int year;
    private List<String> authors;

    public Book(String title, int year, String... authors) {
        this.title = setTitle(title);
        this.year = setYear(year);
        this.authors = setAuthors();
    }

    private List<String> setAuthors(String... authors) {
        if (authors.length == 0 ) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(Arrays.asList(authors));
        }
    }

    private int setYear(int year) {
        return year;
    }

    private String setTitle(String title) {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public List<String> getAuthors() {
        return authors;
    }

    @Override
    public int compareTo(Book other) {
       int result = this.getTitle().compareTo(other.getTitle());

       if (result == 0 ) {
           result = Integer.compare(this.getYear(), other.getYear());
       }

       return result;
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
