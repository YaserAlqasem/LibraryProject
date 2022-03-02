package business;

public class BookCopy {
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
