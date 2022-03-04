package business;

import java.io.Serial;
import java.io.Serializable;

public class BookCopy implements Serializable {
    @Serial
    private static final long serialVersionUID = 5368582527605103286L;
    private String copyNumber;
    private boolean availability;
    private Book book;

    public BookCopy(String copyNum) {
        this.copyNumber = copyNum;
        this.availability = true;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book =  book;
    }

    public String getCopyNumber() {
        return copyNumber;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public boolean getAvailability() {
        return availability;
    }
}
