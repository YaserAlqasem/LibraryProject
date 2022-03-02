package ui.library;

import business.LibraryMember;
import business.User;
import controller.SystemController;
import util.Util;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        User user = null;

        SystemController controller = SystemController.getInstance();

        while (true) {
            System.out.println("Please select one of the options");
            if (user == null) {
                Util.printWhole("     1 - login");
            } else {
                Util.printWhole("     2 - add new library member");
                Util.printWhole("     3 - add new book");
                Util.printWhole("     4 - check out book");
                Util.printWhole("     5 - add book copy");
                Util.printWhole("     6 - logout");
            }
            int option = in.nextInt();
            switch (option) {
                case 1 -> {
                    Util.printColor("User ID");
                    String userId = in.next();
                    Util.printColor("Password");
                    String userPassword = in.next();
                    user = controller.login(userId, userPassword);
                }
                case 2 -> {
                    System.out.println("Please Enter new Member First Name");
//                    LibraryMember member = new LibraryMember();

                }
                case 6 -> {
                    user = null;
                }
            }
            System.out.println();
        }
    }
}
