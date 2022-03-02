package util;

public class Util {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void printColor(String text) {
        System.out.print("Please enter " + ANSI_CYAN + text + ": " + ANSI_RESET);
    }

    public static void printWhole(String text) {
        System.out.println(ANSI_CYAN + text + ANSI_RESET);
    }
}
