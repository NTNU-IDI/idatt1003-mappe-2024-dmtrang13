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
     * Ensures the date is valid for the specified month and year (handles leap years).
     *
     * @param message the prompt message to display to the user.
     * @return a valid LocalDate object representing the expiration date.
     */
    public LocalDate getValidExpirationDate(String message) {
        System.out.printf("%s (type 'cancel' to exit)%n", message);

        Integer year = getValidYear();
        if (year == null) return null;

        Integer month = getValidMonth();
        if (month == null) return null;

        Integer day = getValidDay(year, month);
        if (day == null) return null;

        return LocalDate.of(year, month, day);
    }

    private Integer getValidYear() {
        System.out.print("Year: ");
        String yearInput = scanner.nextLine();
        if (yearInput.equalsIgnoreCase("cancel")) {
            System.out.println("Input canceled by user.");
            return null;
        }
        try {
            return Integer.parseInt(yearInput);
        } catch (NumberFormatException e) {
            System.out.println("Invalid year. Please enter a numeric value.");
            return null;
        }
    }

    private Integer getValidMonth() {
        System.out.print("Month (1-12): ");
        String monthInput = scanner.nextLine();
        if (monthInput.equalsIgnoreCase("cancel")) {
            System.out.println("Input canceled by user.");
            return null;
        }
        try {
            int month = Integer.parseInt(monthInput);
            if (month < 1 || month > 12) {
                System.out.println("Invalid month. Must be between 1 and 12.");
                return null;
            }
            return month;
        } catch (NumberFormatException e) {
            System.out.println("Invalid month. Please enter a numeric value.");
            return null;
        }
    }

    /**
     * Retrieves a valid day input from the user for a specific month and year.
     * Validates that the day is within the appropriate range for the given month and year.
     * Prompts the user repeatedly until valid input is entered or the user cancels the input.
     *
     * @param year the year to determine the range of valid days (handles leap years).
     * @param month the month to determine the range of valid days (1-12).
     * @return a valid day as an integer, or null if the user cancels the input.
     */
    private Integer getValidDay(int year, int month) {
        int maxDays = daysInMonth(year, month);
        while (true) {
            System.out.print("Day: ");
            String dayInput = scanner.nextLine();
            if (dayInput.equalsIgnoreCase("cancel")) {
                System.out.println("Input canceled by user.");
                return null;
            }
            try {
                int day = Integer.parseInt(dayInput);
                if (day < 1 || day > maxDays) {
                    System.out.printf("Invalid day. Must be between 1 and %d.%n", maxDays);
                } else {
                    return day;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid day. Please enter a numeric value.");
            }
        }
    }

    /**
     * Determines the number of days in a given month and year.
     *
     * @param year  the year.
     * @param month the month (1-12).
     * @return the number of days in the specified month.
     */
    private int daysInMonth(int year, int month) {
        return switch (month) {
            case 2 -> (isLeapYear(year) ? 29 : 28); // February
            case 4, 6, 9, 11 -> 30; // Months with 30 days
            default -> 31; // All other months
        };
    }

    /**
     * Checks if a given year is a leap year.
     *
     * @param year the year to check.
     * @return true if the year is a leap year, false otherwise.
     */
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
