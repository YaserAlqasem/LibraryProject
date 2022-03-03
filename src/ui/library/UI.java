package ui.library;

import business.Address;
import business.Author;
import business.LibraryMember;
import business.User;
import controller.SystemController;
import util.Util;

import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        User user = null;

        SystemController controller = SystemController.getInstance();

        while (true) {
            System.out.println(" Please select one of the options ");
            if (user == null) {
                Util.printWhole("     1 - login");
            } else {
                Util.printWhole("     2 - add new library member");
                Util.printWhole("     3 - add new book");
                Util.printWhole("     4 - check out book");
                Util.printWhole("     5 - add book copy");
                Util.printWhole("     6 - logout");
            }
            Util.printColor("Your option");
            int option = in.nextInt();
            System.out.println();
            switch (option) {
                case 1 -> {
                    Util.printColor("User ID");
                    String userId = in.next();
                    Util.printColor("Password");
                    String userPassword = in.next();
                    user = controller.login(userId, userPassword);
                    if (user != null) {
                        Util.printSuccess(" User Logged in Successfully ");
                    } else {
                        Util.printError(" Please Enter A Valid Credentials ");
                    }
                }
                case 2 -> {
                    if (!isLoggedIn(user)) continue;
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
                    controller.addNewMember(member);
                    Util.printSuccess(" New Member Added Successfully ");
                }
                case 3 -> {
                    if (!isLoggedIn(user)) continue;
                    Util.printColor("Book's Title");
                    String title = in.next();
                    Util.printColor("Book's Isbn");
                    String isbn = in.next();
                    Util.printColor(" Specify Number of Authors ");
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
                    controller.addBook(title, isbn, authors);
                    Util.printSuccess(" New Book Added Successfully ");
                }
                case 4 -> {
                    if (!isLoggedIn(user)) continue;
                    Util.printColor("memberId");
                    String memberId = in.next();
                    Util.printColor("isbn");
                    String isbn = in.next();
                    controller.checkOutBook(memberId, isbn);
                    Util.printSuccess(" Book Checked Out Successfully ");
                }
                case 5 -> {
                    if (!isLoggedIn(user)) continue;
                    Util.printColor("Book's Isbn");
                    String isbn = in.next();
                    Util.printColor("Copies Number");
                    int copiesNumber = in.nextInt();

                    controller.addBookCopy(isbn, copiesNumber);
                    Util.printSuccess(" Book Copy Updated Successfully ");
                }
                case 6 -> {
                    if (!isLoggedIn(user)) continue;
                    user = null;
                    Util.printSuccess(" User Logged Out Successfully ");
                }
                default -> {
                    Util.printError(" Please Enter a Valid Selection ");
                }
            }
            System.out.println();
        }
    }

    private static boolean isLoggedIn(User user) {
        if (user == null) {
            Util.printError(" Please Enter a Valid Selection ");
            return false;
        }
        return true;
    }
}
