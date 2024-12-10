package edu.ntnu.iir.bidata.foodhandling;

import edu.ntnu.iir.bidata.inputvalidator.InputValidator;
import edu.ntnu.iir.bidata.model.Ingredient;
import edu.ntnu.iir.bidata.model.Storage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ClientStorage class provides functionality for managing and interacting with a storage system.
 * It supports initializing storage with predefined ingredients, viewing, adding, and removing ingredients,
 * and calculating storage-related metrics.
 */
public class ClientStorage {

    /**
     * Initializes and populates a Storage with predefined ingredients.
     *
     * @return a Storage instance containing predefined ingredients.
     */
    public static Storage init() {
        Storage storage = new Storage();

        // Populate the storage with predefined ingredients
        storage.addIngredient("Eggs", 10, "pcs", LocalDate.of(2023, 12, 1), 50.0);
        storage.addIngredient("Milk", 2, "liter", LocalDate.of(2023, 11, 30), 60.0);
        storage.addIngredient("Flour", 1, "kg", LocalDate.of(2024, 5, 15), 25.0);
        storage.addIngredient("Butter", 0.2, "kg", LocalDate.of(2023, 12, 5), 20.0);
        storage.addIngredient("Tomato Sauce", 2, "can", LocalDate.of(2024, 3, 10), 50.0);
        storage.addIngredient("Ground Beef", 0.5, "kg", LocalDate.of(2023, 12, 20), 100.0);
        storage.addIngredient("Lettuce", 2, "head", LocalDate.of(2023, 11, 28), 40.0);
        storage.addIngredient("Croutons", 0.1, "kg", LocalDate.of(2024, 1, 10), 15.0);
        storage.addIngredient("Chicken Breast", 1, "kg", LocalDate.of(2023, 11, 29), 150.0);
        storage.addIngredient("Mozzarella", 0.5, "kg", LocalDate.of(2023, 12, 5), 100.0);
        storage.addIngredient("Broccoli", 2, "head", LocalDate.of(2023, 11, 28), 30.0);
        storage.addIngredient("Carrots", 5, "pcs", LocalDate.of(2023, 12, 2), 20.0);
        storage.addIngredient("Bell Pepper", 3, "pcs", LocalDate.of(2023, 12, 3), 36.0);
        storage.addIngredient("Soy Sauce", 1, "bottle", LocalDate.of(2024, 1, 15), 40.0);
        storage.addIngredient("Cucumber", 2, "pcs", LocalDate.of(2023, 11, 30), 20.0);
        storage.addIngredient("Sugar", 1, "kg", LocalDate.of(2025, 1, 15), 40.0);
        storage.addIngredient("Cocoa Powder", 0.5, "kg", LocalDate.of(2024, 6, 10), 75.0);
        storage.addIngredient("Pizza Dough", 3, "pcs", LocalDate.of(2023, 12, 15), 90.0);
        storage.addIngredient("Apple", 6, "pcs", LocalDate.of(2023, 12, 5), 30.0);
        storage.addIngredient("Banana", 8, "pcs", LocalDate.of(2023, 12, 3), 24.0);
        storage.addIngredient("Oats", 1, "kg", LocalDate.of(2024, 4, 15), 40.0);
        storage.addIngredient("Honey", 0.5, "jar", LocalDate.of(2025, 6, 15), 50.0);
        storage.addIngredient("Cheddar Cheese", 0.5, "kg", LocalDate.of(2023, 12, 10), 120.0);
        storage.addIngredient("Parmesan Cheese", 0.2, "kg", LocalDate.of(2024, 2, 10), 80.0);
        storage.addIngredient("Basil", 20, "g", LocalDate.of(2024, 1, 20), 15.0);
        storage.addIngredient("Rosemary", 15, "g", LocalDate.of(2024, 1, 25), 20.0);
        storage.addIngredient("Cinnamon", 30, "g", LocalDate.of(2025, 3, 15), 25.0);
        storage.addIngredient("Vanilla Extract", 1, "bottle", LocalDate.of(2025, 8, 10), 75.0);
        storage.addIngredient("Almond Milk", 1, "liter", LocalDate.of(2024, 3, 10), 40.0);
        storage.addIngredient("Coconut Milk", 2, "cans", LocalDate.of(2024, 4, 5), 70.0);
        storage.addIngredient("Rice", 2, "kg", LocalDate.of(2025, 7, 15), 90.0);
        storage.addIngredient("Pasta", 1.5, "kg", LocalDate.of(2024, 2, 20), 50.0);
        storage.addIngredient("Tomato", 4, "pcs", LocalDate.of(2023, 12, 2), 20.0);
        storage.addIngredient("Potato", 5, "kg", LocalDate.of(2023, 12, 10), 50.0);
        storage.addIngredient("Onion", 3, "kg", LocalDate.of(2023, 12, 12), 40.0);
        storage.addIngredient("Garlic", 0.5, "kg", LocalDate.of(2023, 12, 18), 30.0);
        storage.addIngredient("Vegetable Broth", 1, "liter", LocalDate.of(2024, 6, 10), 50.0);
        storage.addIngredient("Chili Powder", 50, "g", LocalDate.of(2025, 4, 10), 30.0);
        storage.addIngredient("Curry Paste", 1, "bottle", LocalDate.of(2024, 7, 10), 60.0);
        storage.addIngredient("Ground Chicken", 1, "kg", LocalDate.of(2023, 12, 10), 120.0);
        storage.addIngredient("Shrimp", 0.5, "kg", LocalDate.of(2023, 12, 8), 180.0);
        storage.addIngredient("Salmon", 0.6, "kg", LocalDate.of(2023, 12, 12), 200.0);
        storage.addIngredient("Bread", 2, "loaves", LocalDate.of(2023, 12, 2), 40.0);
        storage.addIngredient("Dark Chocolate", 200, "g", LocalDate.of(2024, 9, 15), 70.0);
        storage.addIngredient("Walnuts", 150, "g", LocalDate.of(2024, 6, 10), 50.0);
        storage.addIngredient("Almonds", 100, "g", LocalDate.of(2024, 5, 10), 45.0);
        storage.addIngredient("Mushrooms", 500, "g", LocalDate.of(2023, 12, 3), 60.0);
        storage.addIngredient("Spinach", 250, "g", LocalDate.of(2023, 12, 4), 30.0);
        storage.addIngredient("Zucchini", 2, "pcs", LocalDate.of(2023, 12, 6), 40.0);

        return storage;
    }

    /**
     * Displays all the ingredients in the given Storage.
     *
     * @param storage the Storage to display ingredients from.
     */
    public static void viewAllIngredients(Storage storage) {
        if (storage.getIngredients().isEmpty()) {
            System.out.println("Storage is empty.");
        } else {
            System.out.println("All the ingredients in storage: ");
            storage.getIngredients().forEach(System.out::println);
        }
    }

    /**
     * Displays ingredients in the Storage filtered by their name.
     *
     * @param storage the Storage to search for ingredients.
     */
    public static void getIngredientsByName(Storage storage, InputValidator inputValidator) {
        String ingredientName = inputValidator.getNonEmptyString("Please enter ingredient's name: ");
        storage.getIngredientsByName(ingredientName).stream().sorted()
                .forEach(ingredient -> System.out.println(ingredient + "\n"));
    }

    /**
     * Allows the user to add a new ingredient to the Storage.
     *
     * @param storage the Storage where the ingredient will be added.
     */
    public static void addIngredient(Storage storage, InputValidator inputValidator) {
        String name = inputValidator.getNonEmptyString("Please enter ingredient's name: ");
        int amount = inputValidator.getValidInt("Please enter ingredient's amount: ");
        String measurement = inputValidator.getNonEmptyString("Please enter ingredient's measurement: ");
        LocalDate expireDate = inputValidator.getValidExpirationDate("Please enter ingredient's expiration date: ");
        double price = inputValidator.getValidDouble("Please enter ingredient's price: ");
        storage.addIngredient(name, amount, measurement, expireDate, price);
    }

    /**
     * Removes a specified amount of an ingredient from the Storage.
     *
     * @param storage the Storage where the ingredient will be removed.
     */
    public static void removeIngredientByNameAndAmount(Storage storage, InputValidator inputValidator) {
        String ingredientName = inputValidator.getNonEmptyString("Please enter ingredient's name: ");
        double ingredientAmount = inputValidator.getValidDouble("Please enter the amount to remove: ");
        storage.removeIngredientByNameAndAmount(ingredientName, ingredientAmount);
        System.out.println("Updated storage: ");
        storage.getIngredients().forEach(ingredient -> System.out.println(ingredient + "\n"));
    }

    /**
     * Calculates and displays the total price of all ingredients in the Storage.
     *
     * @param storage the Storage to calculate the total price from.
     */
    public static void getTotalPrice(Storage storage) {
        if (storage.getIngredients().isEmpty()) {
            System.out.println("No ingredients registered.");
            return;
        }
        double totalPrice = storage.getIngredients().stream()
                .mapToDouble(Ingredient::getIngredientPrice)
                .sum();
        System.out.println("Total value of storage: " + totalPrice + " kr.");
    }

    /**
     * Retrieves a list of expired ingredients from the Storage.
     *
     * @param storage the Storage to check for expired ingredients.
     * @return a list of expired Ingredients.
     */
    public static ArrayList<Ingredient> getExpiredIngredients (Storage storage) {
        LocalDate today = LocalDate.now();
        return storage.getIngredients().stream()
                .filter(ingredient -> ingredient.getExpireDate().isBefore(today))
                .sorted(Comparator.comparing(Ingredient::getExpireDate))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Calculates and displays the total price of expired ingredients in the Storage.
     *
     * @param storage the Storage to calculate the price of expired ingredients.
     * @return the total price of expired ingredients.
     */
    public static double getExpiredPrice(Storage storage) {
        List<Ingredient> expiredIngredients = getExpiredIngredients(storage); // Only call once
        double expiredPrice = expiredIngredients.stream()
                .mapToDouble(Ingredient::getIngredientPrice)
                .sum();

        if (expiredIngredients.isEmpty()) {
            System.out.println("No expired ingredients found.");
        } else {
            System.out.println("Total price of expired ingredients: +" + expiredPrice + " kr.");
        }
        return expiredPrice;
    }

    /**
     * Retrieves a list of ingredients in the Storage that expire within a specified date interval.
     *
     * @param storage the Storage to search for ingredients.
     * @return a list of Ingredients within the specified date interval.
     */
    public static ArrayList<Ingredient> getIngredientsInDateInterval(Storage storage, InputValidator inputValidator) {
        LocalDate lower = inputValidator.getValidExpirationDate("Please enter lower date: ");
        LocalDate upper = inputValidator.getValidExpirationDate("Please enter upper date: ");
        return storage.getIngredientsInDateInterval(lower, upper);
    }
}
