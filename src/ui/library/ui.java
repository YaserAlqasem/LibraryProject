package ui.library;

import java.util.Scanner;

public class ui {
    public static void main(String[] args) {
        Scanner in  = new Scanner(System.in);
        System.out.println("Welcome \nPlease enter ");

        while(true){
            System.out.println(in.nextInt());
        }
    }
}
