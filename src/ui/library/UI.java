package ui.library;

import business.User;
import util.Util;

import java.util.Scanner;

public class UI {


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        User user = null;

        while (true) {
            System.out.println("Please select one of the options");
            if (user == null) {
                System.out.println("1 - login");
            } else {
                System.out.println("2 - add new library member");
                System.out.println("3 - add new book");
                System.out.println("4 - check out book");
                System.out.println("5 - add book copy");
                System.out.println("6 - logout");
            }
            int option = in.nextInt();
            switch (option) {
                case 1 -> {
                    Util.print("User ID");
                    String userId = in.next();
                    Util.print("Password");
                    String userPassword = in.next();
                    //TODO Controller Call To get User (LOGIN)
                }
                case 2 -> {
                    System.out.println("Please Enter new Member First Name");
                }
            }
        }
    }
}
