package controller;

import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import util.ControllerResponse;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public ControllerResponse addBook(String title, String isbn, int maxCheckoutLength, int numCopies, List<Author> authors) {
        Book book = new Book(title, isbn, maxCheckoutLength, authors);
        dataAccess.addNewBook(book);

        for(int i=1; i<=numCopies; i++) dataAccess.addNewBookCopy(book);

        return new ControllerResponse(true, "Book added successfully");
    }

    public ControllerResponse addBookCopy(String isbn) {
        Book book = dataAccess.searchBook(isbn);

        if (book != null) {
//            BookCopy bookCopy = new BookCopy("");
//            bookCopy.setBook(book);
            dataAccess.addNewBookCopy(book);

            return new ControllerResponse(true, "Copy added successfully");
        }

        return new ControllerResponse(false, "Book is not found");
    }

    public ControllerResponse checkOutBook(String memberId, String isbn) {
        Book book = dataAccess.searchBook(isbn);
        boolean isExist = dataAccess.searchMember(memberId);

        if (!isExist) {
            return new ControllerResponse(false, "Member not found");
        }

        if (book == null) {
            return new ControllerResponse(false, "Book not found");
        }

        if (isExist && book != null) {

            BookCopy bookCopy = dataAccess.nextAvailableBookCopy(isbn);

            if (bookCopy == null) {
                return new ControllerResponse(false, "No available copies");
            }

            LocalDate date = LocalDate.now();
            int checkOutLength = dataAccess.getMaximumCheckoutLength(isbn);
            LocalDate dueDate = date.plusDays(checkOutLength);

            CheckOutRecordEntry checkoutRecordEntry = new CheckOutRecordEntry(date, dueDate, bookCopy);

            dataAccess.saveMemberCheckoutRecord(memberId, checkoutRecordEntry, book);

            return new ControllerResponse(true, "Checkout successfully");
        }

        return new ControllerResponse(false, "Something went wrong");
    }

    public List<CheckOutRecordEntry> searchCheckOutRecords(String memberId) {

        if (dataAccess.searchMember(memberId))
        {
            List<CheckOutRecordEntry> recordEntries = dataAccess.getCheckOutRecord(memberId);

            if (recordEntries != null)
                return recordEntries;
        }

        return null;
    }

    public boolean searchMember(String memberId)
    {
        return dataAccess.searchMember(memberId);
    }
}
