package dataaccess;

import business.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class DataAccessFacade implements DataAccess {

    TestData testData = new TestData();

    enum StorageType {
        BOOKS, MEMBERS, USERS;
    }

    public static final String OUTPUT_DIR = "/Users/abdullahfoqha/Documents/LibraryProject/src/dataaccess/storage";
    public static final String DATE_PATTERN = "MM/dd/yyyy";

    //implement: other save operations
    public void saveNewMember(LibraryMember member) {
        HashMap<String, LibraryMember> mems = readMemberMap();
        String memberId = member.getMemberId();
        mems.put(memberId, member);
        saveToStorage(StorageType.MEMBERS, mems);
    }

    @Override
    public User verifyUser(String id, String password) {
        HashMap<String, User> users = readUserMap();

        User user = users.get(id);

        if (user == null)
            return null;

        if (user.getPassword().equals(password)) {
            return user;
        }

        return null;
    }

    @Override
    public void addMember(LibraryMember member) {
        saveToStorage(StorageType.MEMBERS, member);
    }

    @Override
    public void addNewBook(Book book) {
        saveToStorage(StorageType.BOOKS, book);
    }

    @Override
    public void addNewBookCopy(BookCopy bookCopy) {
        Book book = bookCopy.getBook();

        book.addCopy(bookCopy);
    }

    @Override
    public Book searchBook(String isbn) {

        HashMap<String, Book> books = readBooksMap();

        Book book = books.get(isbn);

        if(book != null)
            return book;

        return null;
    }

    @Override
    public boolean searchMember(String memberId) {

        HashMap<String, LibraryMember> members = readMemberMap();

        LibraryMember member = members.get(memberId);

        if(member != null)
            return true;

        return false;
    }

    @Override
    public int getMaximumCheckoutLength(String isbn) {

        HashMap<String, Book> books = readBooksMap();

        return books.get(isbn).getMaxCheckOutLength();
    }

    @Override
    public BookCopy nextAvailableBookCopy(String isbn) {

        HashMap<String, Book> books = readBooksMap();

        Book book = books.get(isbn);

//        for(BookCopy bookCopy: book.getBookCopies()) {
//            if(bookCopy.getAvailability()) {
//                return bookCopy;
//            }
//        }

        Optional<BookCopy> matchingObject = book.getBookCopies().stream()
                .filter(x -> x.getAvailability() == true).findFirst();

        BookCopy bookCopy = matchingObject.get();

        if(bookCopy != null)
        {
            return bookCopy;
        }

        return null;
    }

    @Override
    public void saveMemberCheckoutRecord(String memberId, CheckOutRecordEntry entry) {

        HashMap<String, LibraryMember> members = readMemberMap();

        LibraryMember member = members.get(memberId);

        member.addCheckOutRecordEntry(entry);
    }

    @Override
    public List<CheckOutRecordEntry> getCheckOutRecord(String memberId) {

        HashMap<String, LibraryMember> members = readMemberMap();

        LibraryMember member = members.get(memberId);

        return  member.getCheckOutRecord().getCheckOutRecordEntries();
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, Book> readBooksMap() {
        //Returns a Map with name/value pairs being
        //   isbn -> Book
        return (HashMap<String, Book>) readFromStorage(StorageType.BOOKS);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, LibraryMember> readMemberMap() {
        //Returns a Map with name/value pairs being
        //   memberId -> LibraryMember
        return (HashMap<String, LibraryMember>) readFromStorage(
                StorageType.MEMBERS);
    }

    @SuppressWarnings("unchecked")
    public HashMap<String, User> readUserMap() {
        //Returns a Map with name/value pairs being
        //   userId -> User
        return (HashMap<String, User>) readFromStorage(StorageType.USERS);
    }

    static void loadBookMap(List<Book> bookList) {
        HashMap<String, Book> books = new HashMap<String, Book>();
        bookList.forEach(book -> books.put(book.getIsbn(), book));
        saveToStorage(StorageType.BOOKS, books);
    }

    static void loadUserMap(List<User> userList) {
        HashMap<String, User> users = new HashMap<String, User>();
        userList.forEach(user -> users.put(user.getId(), user));
        saveToStorage(StorageType.USERS, users);
    }

    static void loadMemberMap(List<LibraryMember> memberList) {
        HashMap<String, LibraryMember> members = new HashMap<String, LibraryMember>();
        memberList.forEach(member -> members.put(member.getMemberId(), member));
        saveToStorage(StorageType.MEMBERS, members);
    }

    static void saveToStorage(StorageType type, Object ob) {
        ObjectOutputStream out = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            out = new ObjectOutputStream(Files.newOutputStream(path));
            out.writeObject(ob);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                }
            }
        }
    }

    static Object readFromStorage(StorageType type) {
        ObjectInputStream in = null;
        Object retVal = null;
        try {
            Path path = FileSystems.getDefault().getPath(OUTPUT_DIR, type.toString());
            in = new ObjectInputStream(Files.newInputStream(path));
            retVal = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                }
            }
        }
        return retVal;
    }

    final static class Pair<S, T> implements Serializable {

        S first;
        T second;

        Pair(S s, T t) {
            first = s;
            second = t;
        }

        @Override
        public boolean equals(Object ob) {
            if (ob == null) return false;
            if (this == ob) return true;
            if (ob.getClass() != getClass()) return false;
            @SuppressWarnings("unchecked")
            Pair<S, T> p = (Pair<S, T>) ob;
            return p.first.equals(first) && p.second.equals(second);
        }

        @Override
        public int hashCode() {
            return first.hashCode() + 5 * second.hashCode();
        }

        @Override
        public String toString() {
            return "(" + first.toString() + ", " + second.toString() + ")";
        }

        private static final long serialVersionUID = 5399827794066637059L;
    }
}
