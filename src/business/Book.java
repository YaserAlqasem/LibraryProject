package business;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book  implements Serializable {
    @Serial
    private static final long serialVersionUID = 6359507509442080467L;
    private String title;
    private String isbn;
    private int maxCheckOutLength;
    private List<Author> authors;
    private List<BookCopy>  bookCopies;

    public Book(String title, String isbn, int maxCheckOutLength, List<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.maxCheckOutLength = maxCheckOutLength;
        this.authors = authors;
    }

    public void addCopy(BookCopy bookCopy){
        if (bookCopies == null)
            bookCopies = new ArrayList<>();

        this.bookCopies.add(bookCopy);
        bookCopy.setBook(this);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public int getMaxCheckOutLength() {
        return maxCheckOutLength;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<BookCopy> getBookCopies() {
        return bookCopies;
    }
}
