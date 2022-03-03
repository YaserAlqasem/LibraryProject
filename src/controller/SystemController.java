package controller;

import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import util.ControllerResponse;
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

    public ControllerResponse addNewMember(LibraryMember member) {
        dataAccess.addMember(member);

        return new ControllerResponse(true, "Successfully added");
    }

    public ControllerResponse addBook(String title, String isbn, List<Author> authors) {
        Book book = new Book(title, isbn, 10, authors);
        dataAccess.addNewBook(book);

        return new ControllerResponse(true, "Book added successfully");
    }

    public ControllerResponse addBookCopy(String isbn, int copyNum) {
        Book book = dataAccess.searchBook(isbn);

        if (book != null) {
            BookCopy bookCopy = new BookCopy(copyNum);
            dataAccess.addNewBookCopy(bookCopy);

            return new ControllerResponse(true, "Copy added successfully");
        }

        return new ControllerResponse(false, "Book is not found");
    }

    public ControllerResponse checkOutBook(String memberId, String isbn) {
        Book book = dataAccess.searchBook(isbn);

        if (dataAccess.searchMember(memberId) && book != null) {

            BookCopy bookCopy = dataAccess.nextAvailableBookCopy(isbn);

            if (bookCopy == null) {
                return new ControllerResponse(false, "No available copies");
            }

            LocalDate date = LocalDate.now();
            int checkOutLength = dataAccess.getMaximumCheckoutLength(isbn);
            LocalDate dueDate = date.plusDays(checkOutLength);

            CheckOutRecordEntry checkoutRecordEntry = new CheckOutRecordEntry(date, dueDate, bookCopy);
            bookCopy.setAvailability(false);

            dataAccess.saveMemberCheckoutRecord(memberId, checkoutRecordEntry);

            return new ControllerResponse(true, "Checkout successfully");
        }

        if (!dataAccess.searchMember(memberId)) {
            return new ControllerResponse(false, "Member not found");
        }

        if (dataAccess.searchBook(isbn) == null) {
            return new ControllerResponse(false, "Book not found");
        }

        return new ControllerResponse(false, "Something went wrong");
    }

    public List<CheckOutRecordEntry> searchCheckOutRecords(String memberId) {

        if (dataAccess.searchMember(memberId)) {
            List<CheckOutRecordEntry> recordEntries = dataAccess.getCheckOutRecord(memberId);

            if (recordEntries != null) {
                return recordEntries;
            }
        }

        return null;
    }
}
