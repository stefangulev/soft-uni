import java.util.Comparator;

public class BookComparator implements Comparator<Book> {

    @Override
    public int compare(Book book, Book t1) {
        int result = book.getTitle().compareTo(t1.getTitle());

        if (result ==0) {
            result = Integer.compare(book.getYear(), t1.getYear());
        }
        return result;
    }
}
