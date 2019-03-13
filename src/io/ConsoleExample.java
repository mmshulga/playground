package io;
import java.io.Console;

public class ConsoleExample {
    public static void main(String[] args) {

        // Does not work in the IDE

        Console console = System.console();
        System.out.print("Enter your password: ");
        char[] pArr = console.readPassword();
        String password = new String(pArr);
        System.out.println("password = " + password);

        String line = console.readLine();
        System.out.println("line = " + line);
    }
}