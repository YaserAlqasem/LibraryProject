package ui.library;

import business.*;
import controller.SystemController;
import util.ControllerResponse;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {
    static Scanner in = new Scanner(System.in);
    static User user = null;

    static SystemController controller = SystemController.getInstance();

    public static void main(String[] args) {
        while (true) {
            System.out.println(" Please select one of the options ");
            if (user == null) {
                loginUI();
            } else if (user.getRole() == Role.ADMIN) {
                adminUI();
            } else if (user.getRole() == Role.LIBRARIAN) {
                librarianUI();
            } else {
                Util.printWhole("     1 - add new library member");
                Util.printWhole("     2 - add new book");
                Util.printWhole("     3 - add book copy");
                Util.printWhole("     4 - check out book");
                Util.printWhole("     6 - logout");
            }
            System.out.println();
        }
    }

    public static void loginUI() {
        Util.printWhole("     1 - login");
        if (getOption() != 1) return;
        Util.printColor("User ID");
        String userId = in.next();
        Util.printColor("Password");
        String userPassword = in.next();
        user = controller.login(userId, userPassword);
        if (user != null) {
            Util.printSuccess(" User Logged in Successfully ");
        } else {
            Util.printError(" Username Or Password incorrect ");
        }
    }

    private static void librarianUI() {
        Util.printWhole("     1 - Checkout a book for a library member");
        Util.printWhole("     2 - Print checkout records of the library member");
        Util.printWhole("     3 - logout");

        switch (getOption()) {
            case 1 -> checkoutBook();
            case 2 -> searchCheckOutRecords();
            case 3 -> logout();
            default -> Util.printError(" Please Enter a Valid Selection ");
        }
    }

    private static void adminUI() {
        Util.printWhole("     1 - add new library member");
        Util.printWhole("     2 - add new book");
        Util.printWhole("     3 - add book copy");
        Util.printWhole("     4 - logout");

        switch (getOption()) {
            case 1 -> addNewLibraryMember();
            case 2 -> addNewBook();
            case 3 -> addBookCopy();
            case 4 -> logout();
            default -> Util.printError(" Please Enter a Valid Selection ");
        }
    }

    //<editor-fold desc ="Admin UI">
    private static void addNewLibraryMember() {
        Util.printColor("Member's First Name");
        String firstName = in.next();
        Util.printColor("Member's Last Name");
        String lastName = in.next();
        Util.printColor("Member's Phone");
        String phoneNumber = in.next();
        Util.printColor("Member's City");
        String city = in.next();
        Util.printColor("Member's State");
        String state = in.next();
        Util.printColor("Member's Street");
        String street = in.next();
        Util.printColor("Member's ZipCode");
        String zipCode = in.next();
        LibraryMember member = new LibraryMember("", firstName, lastName, phoneNumber, new Address(state, street, zipCode, city));
        Util.printResponse(controller.addNewMember(member));
    }

    private static void addNewBook() {
        Util.printColor("Book's Title");
        String title = in.next();
        Util.printColor("Book's Isbn");
        String isbn = in.next();
        Util.printColor("Specify Number of Authors");
        int authorsNumber = in.nextInt();
        ArrayList<Author> authors = new ArrayList<>();
        for (int i = 1; i <= authorsNumber; i++) {
            Util.printColor("Author's #" + i + " First Name");
            String firstName = in.next();
            Util.printColor("Author's #" + i + " Last Name");
            String lastName = in.next();
            Util.printColor("Author's #" + i + " Phone Number");
            String phoneNumber = in.next();
            Util.printColor("Author's #" + i + " shortBio");
            String shortBio = in.next();
            Util.printColor("Author's #" + i + " City");
            String city = in.next();
            Util.printColor("Author's #" + i + " State");
            String state = in.next();
            Util.printColor("Author's #" + i + " Street");
            String street = in.next();
            Util.printColor("Author's #" + i + " ZipCode");
            String zipCode = in.next();
            authors.add(new Author(firstName, lastName, phoneNumber, new Address(state, street, zipCode, city), shortBio));
        }
        Util.printResponse(controller.addBook(title, isbn, authors));
    }

    private static void addBookCopy() {
        Util.printColor("Book's Isbn");
        String isbn = in.next();
        Util.printColor("Copies Number");
        int copiesNumber = in.nextInt();
        Util.printResponse(controller.addBookCopy(isbn, copiesNumber));
    }
    //</editor-fold>

    //<editor-fold desc ="Common UI">
    private static void logout() {
        user = null;
        Util.printSuccess(" User Logged Out Successfully ");
    }

    private static int getOption() {
        Util.printColor("Your option");
        String textEntered = in.next();
        int option = -1;
        try {
            option = Integer.parseInt(textEntered);
        } catch (Exception e) {
            Util.printError(" Please Enter a Valid Selection ");
            return -1;
        }
        System.out.println();
        return option;
    }
    //</editor-fold>

    //<editor-fold desc ="Librarian UI">
    private static void checkoutBook() {
        Util.printColor("memberId");
        String memberId = in.next();
        Util.printColor("isbn");
        String isbn = in.next();
        Util.printResponse(controller.checkOutBook(memberId, isbn));
    }

    private static void searchCheckOutRecords() {
        Util.printColor("memberId");
        String memberId = in.next();
        List<CheckOutRecordEntry> res = controller.searchCheckOutRecords(memberId);
        for (CheckOutRecordEntry item : res) {
            Util.printWhole("" + item.getDueDate() + item.getBookCopy().getBook().getTitle());
        }
    }
    // </editor-fold>
}
