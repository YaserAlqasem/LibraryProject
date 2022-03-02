package business;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private String title;
    private String isbn;
    private int availability;
    private List<Author> authors;
    private List<BookCopy>  bookCopies = new ArrayList<>();

    public Book(String title, String isbn, int availability, List<Author> authors) {
        this.title = title;
        this.isbn = isbn;
        this.availability = availability;
        this.authors = authors;
        bookCopies.add(new BookCopy(++availability, this));
    }

    public void addCopy(){
        bookCopies.add(new BookCopy(++availability, this));
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

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }
}
