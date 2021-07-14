import java.util.Arrays;
import java.util.Iterator;

public class Library implements Iterable<Book> {

    private Book[] books;

    public Library(Book... books) {
        this.books = Arrays.copyOf(books, books.length);
    }


    private class LibIterator implements Iterator<Book> {

        int index = 0;


        @Override
        public boolean hasNext() {
            return this.index < books.length;

        }

        @Override
        public Book next() {
            return books[this.index++];
        }
    }



    @Override
    public Iterator<Book> iterator() {
        return new LibIterator();
    }
}
