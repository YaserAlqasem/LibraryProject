package util;

public class Util {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_BLUE = "\u001B[34m";


    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printColor(String text) {
        System.out.print("Please enter " + ANSI_CYAN + text + ": " + ANSI_RESET);
    }

    public static void printWhole(String text) {
        System.out.println(ANSI_PURPLE + text + ANSI_RESET);
    }

    public static void printSuccess(String text) {
        System.out.println(ANSI_GREEN_BACKGROUND + text + ANSI_RESET);
    }

    public static void printError(String text) {
        System.out.println(ANSI_RED_BACKGROUND + text + ANSI_RESET);
    }
}
