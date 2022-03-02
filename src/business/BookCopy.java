package business;

import java.io.Serial;
import java.io.Serializable;

public class BookCopy implements Serializable {
    @Serial
    private static final long serialVersionUID = 5368582527605103286L;
    private int copyNumber;
    private Book book;

    public BookCopy(int copyNumber, Book book) {
        this.copyNumber = copyNumber;
        this.book = book;
    }

    public int getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
    }
}
