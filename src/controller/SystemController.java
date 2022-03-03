package controller;

import business.*;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;

import java.util.List;

public class SystemController {

    private static SystemController instance;
    private DataAccess dataAccess = new DataAccessFacade();
    private List<Author> authorsList;

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

    public void addNewMember(LibraryMember member) {
//        dataAccess.addMember(member);
    }

    public Book addBook(String title, String isbn, List<Author> authors) {
        Book book = new Book(title, isbn, 10, authors);
//        dataAccess.saveNewBook(book);

        return book;
    }

    public BookCopy addBookCopy(String isbn, int copyNum) {

//        Book book = dataAccess.searchBook(isbn);
//
//        if (book != null)
//        {
//            book.addCopy();
//        }
//
        return null;
    }

    public void checkOutBook(String memberId, String isbn) {
    }

    public void searchCheckOutRecords(String memberId) {
    }
}
