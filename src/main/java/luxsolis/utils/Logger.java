package luxsolis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Logger logger = null;

    private Logger() {}

    public static Logger getInstance() {
        if (logger == null) logger = new Logger();

        return logger;
    }

    public static void info(String message) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.WHITE + message + ConsoleColour.RESET);
    }
    /**
     * Log a message to the console
     * @param message   Message to log to the console
     * @param prefix    Message prefix
     */
    public static void info(String message, String prefix) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.BLUE
                + "[" + prefix + "] \t" + ConsoleColour.WHITE + message + ConsoleColour.RESET);
    }

    public static void warn(String message) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.RED
                + "[WARN] " + ConsoleColour.WHITE + message + ConsoleColour.RESET);
    }

    public static void warn(String message, String prefix) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.RED
                + "[WARN] " + ConsoleColour.BLUE + "[" + prefix + "] \t" + ConsoleColour.WHITE + message + ConsoleColour.RESET);
    }

    /**
     * Log an error to the console
     * @param message   Message to log to the console
     */
    public static void error(String message) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.RED
                + "[ERR] " + message + ConsoleColour.RESET);
    }

    /**
     * Log an error to the console
     * @param message   Message to log to the console
     * @param prefix    Message prefix
     */
    public static void error(String message, String prefix) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.RED
                + "[ERR] " + ConsoleColour.BLUE + "[" + prefix + "] \t" + ConsoleColour.RED + message + ConsoleColour.RESET);
    }

    /**
     * Log a success to the console
     * @param message   Message to log to the console
     */
    public static void success(String message) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.GREEN
                + message + ConsoleColour.RESET);
    }

    /**
     * Log a success to the console
     * @param message   Message to log to the console
     * @param prefix    Message prefix
     */
    public static void success(String message, String prefix) {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SSS");

        System.out.println(ConsoleColour.YELLOW + "[" + dateFormat.format(date) + "] " + ConsoleColour.BLUE
                + "[" + prefix + "] \t" + ConsoleColour.GREEN + message + ConsoleColour.RESET);
    }

    /**
     * Print a colour test to the console
     */
    public static void colourTest() {
        System.out.println("-= Commencing Colour Test =-");
        System.out.println(ConsoleColour.BLACK + "Black Text" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.RED + "Red Text" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.GREEN + "Green Text" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.YELLOW + "Yellow Text" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.BLUE + "Blue Text" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.PURPLE + "Purple Text" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.CYAN + "Cyan Text" + ConsoleColour.RESET);
        System.out.println(ConsoleColour.WHITE + "White Text" + ConsoleColour.RESET);
        System.out.println("  -= End of Colour Test =-  ");
    }
}
