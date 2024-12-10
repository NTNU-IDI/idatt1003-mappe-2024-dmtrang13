package edu.ntnu.iir.bidata.inputvalidator;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * Provides utility methods for validating and retrieving user input.
 * Supports input validation for integers, doubles, non-empty strings, and dates.
 */
public class InputValidator {
    public final Scanner scanner;

    /**
     * Constructs an InputValidator instance with a specified Scanner.
     *
     * @param scanner the Scanner to use for input.
     */
    public InputValidator(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Prompts the user with a message and retrieves a valid integer input.
     * If the input is not a valid integer, the user is re-prompted until a valid integer is provided.
     *
     * @param message the prompt message to display to the user.
     * @return a valid integer input from the user.
     */
    public int getValidInt(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer");
            }
        }
    }

    /**
     * Prompts the user with a message and retrieves a valid double input.
     * If the input is not a valid double, the user is re-prompted until a valid double is provided.
     *
     * @param message the prompt message to display to the user.
     * @return a valid double input from the user.
     */
    public double getValidDouble(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid double");
            }
        }
    }

    /**
     * Prompts the user with a message and retrieves a non-empty string input.
     * If the input is empty, the user is re-prompted until a non-empty string is provided.
     *
     * @param message the prompt message to display to the user.
     * @return a non-empty string input from the user.
     */
    public String getNonEmptyString(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Please enter a valid string");
            } else {
                return input;
            }
        }
    }

    /**
     * Prompts the user with a message and retrieves a valid expiration date.
     * If the input does not form a valid date, the user is re-prompted until a valid date is provided.
     * Ensures that the month is between 1 and 12 and the date is between 1 and 31.
     *
     * @param message the prompt message to display to the user.
     * @return a valid LocalDate object representing the expiration date.
     */
    public LocalDate getValidExpirationDate(String message) {
        while (true) try {
            System.out.println(message);

            int year = getValidInt("Please enter the expiration year (yyyy) : ");
            int month = getValidInt("Please enter the expiration month (mm) :");
            int date = getValidInt("Please enter the expiration date (dd) :");

            if (month < 1 || month > 12) {
                System.out.println("Invalid month. Please enter a month between 1 and 12.");
                return null;
            }
            if (date < 1 || date > 31) {
                System.out.println("Invalid date. Please enter a month between 1 and 31.");
                return null;
            }

            return LocalDate.of(year, month, date);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            return null;
        }
    }
}
