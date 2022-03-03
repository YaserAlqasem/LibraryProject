package controller;

import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.time.LocalDate;
import java.util.List;

public class SystemController {

    private static SystemController instance;
    private DataAccess dataAccess = new DataAccessFacade();

    private SystemController() {
    }

    public static SystemController getInstance() {
        if (instance == null) instance = new SystemController();
        return instance;
    }

    public User login(String id, String password) {
        User user = dataAccess.verifyUser(id, password);
        if (user == null)
            return null;

        return user;
    }

    public String addNewMember(LibraryMember member) {
        dataAccess.addMember(member);

        return "Successfully added";
    }

    public String addBook(String title, String isbn, List<Author> authors) {
        Book book = new Book(title, isbn, 10, authors);
        dataAccess.addNewBook(book);

        return "Book successfully added";
    }

    public String addBookCopy(String isbn, int copyNum) {

        Book book = dataAccess.searchBook(isbn);

        if (book != null) {
            BookCopy bookCopy = new BookCopy(copyNum);
            book.addCopy(bookCopy);
            dataAccess.addNewBookCopy(bookCopy);

            return "Copy added successfully";
        }

        return null;
    }

    public String checkOutBook(String memberId, String isbn) {
        Book book = dataAccess.searchBook(isbn);

        if (dataAccess.searchMember(memberId) && book != null) {

            BookCopy bookCopy = dataAccess.nextAvailableBookCopy(isbn);

            if (bookCopy == null) {
                return "No available copies";
            }

            LocalDate date = LocalDate.now();
            int checkOutLength = dataAccess.getMaximumCheckoutLength(isbn);
            LocalDate dueDate = date.plusDays(checkOutLength);

            CheckOutRecordEntry checkoutRecordEntry = new CheckOutRecordEntry(date, dueDate, bookCopy);
            bookCopy.setAvailability(false);

            dataAccess.saveMemberCheckoutRecord(memberId, checkoutRecordEntry);

            return "Checkout successfully";

        }

        if (!dataAccess.searchMember(memberId)) {
            return "Member not found";
        }

        if (dataAccess.searchBook(isbn) == null) {
            return "Book not found";
        }

        return "Something went wrong";
    }

    public List<CheckOutRecordEntry> searchCheckOutRecords(String memberId) {

        if (dataAccess.searchMember(memberId))
        {
            List<CheckOutRecordEntry> recordEntries = dataAccess.getCheckOutRecord(memberId);

            if (recordEntries != null)
            {
                return recordEntries;
            }
        }

        return null;
    }
}
