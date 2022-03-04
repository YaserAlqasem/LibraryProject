package ui.library;

import business.*;
import controller.SystemController;
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
            System.out.println("Please select one of the options");
            if (user == null) {
                loginUI();
            } else if (user.getRole() == Role.ADMIN) {
                adminUI();
            } else if (user.getRole() == Role.LIBRARIAN) {
                librarianUI();
            } else {
                bothUI();
            }
            System.out.println();
        }
    }

    public static void loginUI() {
        Util.printWhole("     1 - Login");
        if (getOption() != 1) {
            Util.printError(" Please Enter a Valid Selection ");
            return;
        }
        System.out.println();
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
        Util.printWhole("     3 - Logout");

        switch (getOption()) {
            case 1 -> checkoutBook();
            case 2 -> searchCheckOutRecords();
            case 3 -> logout();
            default -> Util.printError(" Please Enter a Valid Selection ");
        }
    }

    private static void adminUI() {
        Util.printWhole("     1 - Add new library member");
        Util.printWhole("     2 - Add new book");
        Util.printWhole("     3 - Add book copy");
        Util.printWhole("     4 - Logout");

        switch (getOption()) {
            case 1 -> addNewLibraryMember();
            case 2 -> addNewBook();
            case 3 -> addBookCopy();
            case 4 -> logout();
            default -> Util.printError(" Please Enter a Valid Selection ");
        }
    }

    private static void bothUI() {
        Util.printWhole("     1 - Add new library member");
        Util.printWhole("     2 - Add new book");
        Util.printWhole("     3 - Add book copy");
        Util.printWhole("     4 - Checkout a book for a library member");
        Util.printWhole("     5 - Print checkout records of the library member");
        Util.printWhole("     6 - Logout");

        switch (getOption()) {
            case 1 -> addNewLibraryMember();
            case 2 -> addNewBook();
            case 3 -> addBookCopy();
            case 4 -> checkoutBook();
            case 5 -> searchCheckOutRecords();
            case 6 -> logout();
            default -> Util.printError(" Please Enter a Valid Selection ");
        }
    }

    //<editor-fold desc ="Admin UI">
    private static void addNewLibraryMember() {
        System.out.println();
        Util.printColor("Member's Number");
        String memberNumber = in.next();
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
        LibraryMember member = new LibraryMember(memberNumber, firstName, lastName, phoneNumber, new Address(state, street, zipCode, city));
        Util.printResponse(controller.addNewMember(member));
    }

    private static void addNewBook() {
        System.out.println();
        Util.printColor("Book's Title");
        String title = in.next();
        Util.printColor("Book's Isbn");
        String isbn = in.next();
        Util.printColor("Max Checkout length");
        int maxCheckoutLength = Integer.parseInt(in.next());
        Util.printColor("Number of copies");
        int numCopies = Integer.parseInt(in.next());
        boolean isNotDig = true;
        int authorsNumber = -1;
        while (isNotDig) {
            Util.printColor("Specify Number of Authors");
            String textEntered = in.next();
            try {
                authorsNumber = Integer.parseInt(textEntered);
                if (authorsNumber > 0)
                    isNotDig = false;
                else
                    Util.printError(" Please Enter a Valid Number ");
            } catch (Exception e) {
                Util.printError(" Please Enter a Valid Number ");
            }
            System.out.println();
        }


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
            if (i < authorsNumber)
                System.out.println();
        }
        Util.printResponse(controller.addBook(title, isbn, maxCheckoutLength, numCopies, authors));
    }

    private static void addBookCopy() {
        System.out.println();
        Util.printColor("Book's Isbn");
        String isbn = in.next();

        Util.printResponse(controller.addBookCopy(isbn));
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
            return -1;
        }
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

        if(controller.searchMember(memberId))
        {
            List<CheckOutRecordEntry> res = controller.searchCheckOutRecords(memberId);

            if(res != null && res.size() > 0) {
                for (CheckOutRecordEntry item : res) {
                    Util.printWhole("Book Date: " + item.getDueDate() + " \nBook Title: " + item.getBookCopy().getBook().getTitle() + "\n");
                }
            }
            else
                Util.printError("No entries found");
        }
        else
            Util.printError("Member not found");
    }
    // </editor-fold>
}
