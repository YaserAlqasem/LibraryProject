package dataaccess;

import business.*;

import java.util.HashMap;
import java.util.List;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);

    User verifyUser(String id, String password);

    void addMember(LibraryMember member);

    void addNewBook(Book book);

    void addNewBookCopy(BookCopy bookCopy);

    Book searchBook(String isbn);

    boolean searchMember(String memberId);

    int getMaximumCheckoutLength(String isbn);

    BookCopy nextAvailableBookCopy(String isbn);

    void saveMemberCheckoutRecord(String memberId, CheckOutRecordEntry entry);

    List<CheckOutRecordEntry> getCheckOutRecord(String memberId);
}
