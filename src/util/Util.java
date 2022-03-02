package util;

public class Util {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void print(String text) {
        System.out.println("Please enter " + ANSI_CYAN + text + ": " + ANSI_RESET);
    }
}
