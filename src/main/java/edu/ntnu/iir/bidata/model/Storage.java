package edu.ntnu.iir.bidata.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Represents a storage system for ingredients. Provides functionality to manage, update, and query stored ingredients.
 */
public class Storage {
    private static ArrayList<Ingredient> storage;
    private Scanner scanner;

    /**
     * Constructs an empty Storage instance.
     */
    public Storage() {
        storage = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Sets the Scanner instance to be used for user input in the Storage class.
     * This method allows overriding the default Scanner for testing or alternative input sources.
     *
     * @param scanner the Scanner instance to use for user input
     */
    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Retrieves all ingredients stored in the system.
     *
     * @return a list of ingredients in storage.
     */
    public ArrayList<Ingredient> getIngredients() {
        return storage;
    }

    /**
     * Prompts the user for confirmation to update a specific field to a new value.
     *
     * @param scanner the Scanner instance for user input.
     * @param field the name of the field being updated.
     * @param newValue the proposed new value for the field.
     * @return true if the user confirms; false otherwise.
     */
    private boolean confirmUpdate(Scanner scanner, String field, Object newValue) {
        System.out.print("Update " + field + " to '" + newValue + "'? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    /**
     * Prompts the user to confirm an action described by the given message.
     *
     * @param scanner the Scanner instance for user input.
     * @param actionDescription a description of the action being confirmed.
     * @return true if the user confirms; false otherwise.
     */
    private boolean confirmAction(Scanner scanner, String actionDescription) {
        System.out.print("Do you want to " + actionDescription + "? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("y");
    }

    /**
     * Adds an ingredient to the storage. If the ingredient already exists, its amount is updated,
     * and mismatched details can optionally be modified.
     *
     * @param ingredientName       the name of the ingredient.
     * @param ingredientAmount     the amount of the ingredient.
     * @param ingredientMeasurement the measurement unit of the ingredient.
     * @param expireDate           the expiration date of the ingredient.
     * @param ingredientPrice      the price of the ingredient.
     */
    public void addIngredient(String ingredientName, double ingredientAmount, String ingredientMeasurement, LocalDate expireDate, double ingredientPrice) {
        Ingredient existingIngredient = storage.stream()
                .filter(ingredient -> ingredient.getIngredientName().equalsIgnoreCase(ingredientName))
                .findFirst()
                .orElse(null);

        if (existingIngredient != null) {
            System.out.println("Ingredient '" + ingredientName + "' already exists:");
            System.out.println(" - Current Details: " + existingIngredient);

            boolean measurementMismatch = !existingIngredient.getIngredientMeasurement().equalsIgnoreCase(ingredientMeasurement);
            boolean expireDateMismatch = !existingIngredient.getExpireDate().equals(expireDate);
            boolean priceMismatch = existingIngredient.getIngredientPrice() != ingredientPrice;

            if (measurementMismatch || expireDateMismatch || priceMismatch) {
                System.out.println("Mismatched details found:");
                if (measurementMismatch) {
                    System.out.println(" - Measurement: Existing = " + existingIngredient.getIngredientMeasurement() + ", New = " + ingredientMeasurement);
                }
                if (expireDateMismatch) {
                    System.out.println(" - Expiration Date: Existing = " + existingIngredient.getExpireDate() + ", New = " + expireDate);
                }
                if (priceMismatch) {
                    System.out.println(" - Price: Existing = " + existingIngredient.getIngredientPrice() + ", New = " + ingredientPrice);
                }
            }

            System.out.print("Do you want to update the existing ingredient? (y/n): ");
            if (scanner.nextLine().trim().equalsIgnoreCase("y")) {
                existingIngredient.setIngredientAmount(existingIngredient.getIngredientAmount() + ingredientAmount);

                if (measurementMismatch && confirmUpdate(scanner, "measurement", ingredientMeasurement)) {
                    existingIngredient.setIngredientMeasurement(ingredientMeasurement);
                }
                if (expireDateMismatch && confirmUpdate(scanner, "expiration date", expireDate)) {
                    existingIngredient.setExpireDate(expireDate);
                }
                if (priceMismatch && confirmUpdate(scanner, "price", ingredientPrice)) {
                    existingIngredient.setIngredientPrice(ingredientPrice);
                }

                System.out.println("Ingredient '" + ingredientName + "' updated successfully.");
            } else if (confirmAction(scanner, "add a new ingredient entry with the provided details")) {
                storage.add(new Ingredient(ingredientName, ingredientAmount, ingredientMeasurement, expireDate, ingredientPrice));
                System.out.println("Added new ingredient entry: " + ingredientName + " (" + ingredientAmount + " " + ingredientMeasurement + ")");
            } else {
                System.out.println("No changes were made.");
            }
        } else {
            storage.add(new Ingredient(ingredientName, ingredientAmount, ingredientMeasurement, expireDate, ingredientPrice));
            System.out.println("Added new ingredient: " + ingredientName + " (" + ingredientAmount + " " + ingredientMeasurement + ")");
        }
    }

    /**
     * Retrieves a list of ingredients by their name.
     *
     * @param ingredientName the name of the ingredient to search for.
     * @return a list of matching ingredients.
     */
    public ArrayList<Ingredient> getIngredientsByName(String ingredientName) {
        return storage.stream().filter(ingredient -> ingredient.getIngredientName().equalsIgnoreCase(ingredientName))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Removes a specified amount of an ingredient from storage. If the remaining amount is zero or less, the ingredient is removed entirely.
     *
     * @param ingredientName the name of the ingredient to remove.
     * @param amountToRemove the amount of the ingredient to remove.
     */
    public void removeIngredientByNameAndAmount(String ingredientName, double amountToRemove) {
        for (Ingredient ingredient : new ArrayList<>(storage)) {
            if (ingredient.getIngredientName().equalsIgnoreCase(ingredientName)) {
                if (ingredient.getIngredientAmount() > amountToRemove) {
                    ingredient.setIngredientAmount(ingredient.getIngredientAmount() - amountToRemove);
                    System.out.println(ingredientName + ". Remaining amount: " + ingredient.getIngredientAmount() + " " + ingredient.getIngredientMeasurement());
                    return;
                } else {
                    storage.remove(ingredient);
                    System.out.println("Removed " + ingredientName + " from storage");
                    return;
                }
            }
        }
        System.out.println("Ingredient " + ingredientName + " not found in storage.");
    }

    /**
     * Retrieves ingredients within a specified date range.
     *
     * @param lower the start of the date range.
     * @param upper the end of the date range.
     * @return a list of ingredients that fall within the date range.
     * @throws IllegalArgumentException if the date range is invalid.
     */
    public ArrayList<Ingredient> getIngredientsInDateInterval(LocalDate lower, LocalDate upper) {
        if (lower == null || upper == null) {
            throw new IllegalArgumentException("Date range cannot be null.");
        }
        if (lower.isAfter(upper)) {
            throw new IllegalArgumentException("Lower date cannot be after upper date.");
        }
        return storage.stream()
                .filter(ingredient -> !ingredient.getExpireDate().isBefore(lower) && !ingredient.getExpireDate().isAfter(upper))
                .sorted(Comparator.comparing(Ingredient::getExpireDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}

