package edu.ntnu.idi.idatt.ui;

import edu.ntnu.idi.idatt.foodhandling.ClientCookbook;
import edu.ntnu.idi.idatt.foodhandling.ClientStorage;
import edu.ntnu.idi.idatt.inputvalidator.InputValidator;
import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.Storage;

import java.util.stream.Collectors;

/**
 * Represents the user interface for interacting with the Recipe & Storage application.
 * Provides menus for managing storage and cookbook data.
 */
public class UserInterface {
    private final ClientStorage clientStorage;
    private final ClientCookbook clientCookbook;
    private final Storage storage;
    private final Cookbook cookbook;
    private final InputValidator inputValidator;

    /**
     * Constructs a new UserInterface instance with the specified storage and cookbook clients.
     *
     * @param clientStorage  the client handling storage operations.
     * @param clientCookbook the client handling cookbook operations.
     * @param storage        the storage system to be managed.
     * @param cookbook       the cookbook to be managed.
     */
    public UserInterface(ClientStorage clientStorage, ClientCookbook clientCookbook, Storage storage, Cookbook cookbook, InputValidator inputValidator) {
        this.clientStorage = clientStorage;
        this.clientCookbook = clientCookbook;
        this.storage = storage;
        this.cookbook = cookbook;
        this.inputValidator = inputValidator;
    }

    /**
     * Starts the user interface, displaying the main menu and processing user input.
     */
    public void start() {
        System.out.println("Welcome to the Recipe & Storage App!");
        boolean running = true;
        while (running) {
            int mainOption = displayMainMenu();
            switch (mainOption) {
                case 1 -> manageStorage();
                case 2 -> manageCookbook();
                case 3 -> {
                    System.out.println("Exiting application... Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Manages the storage system by displaying the storage menu and processing user input.
     */
    private void manageStorage() {
        boolean running = true;
        while (running) {
            int storageOption = displayStorageMenu();
            switch (storageOption) {
                case 1 -> clientStorage.viewAllIngredients(storage);
                case 2 -> clientStorage.getIngredientsByName(storage, inputValidator);
                case 3 -> clientStorage.addIngredient(storage, inputValidator);
                case 4 -> clientStorage.removeIngredientByNameAndAmount(storage, inputValidator);
                case 5 -> {
                    System.out.printf("Expired ingredients: %s%nTotal price: %.2f kr%n",
                            clientStorage.getExpiredIngredients(storage).stream()
                                    .map(Object::toString).collect(Collectors.joining("\n")),
                            clientStorage.getExpiredPrice(storage));
                }
                case 6 -> clientStorage.getTotalPrice(storage);
                case 7 -> {
                    var ingredientsInRange = clientStorage.getIngredientsInDateInterval(storage, inputValidator);
                    if (ingredientsInRange.isEmpty()) {
                        System.out.println("No ingredients found in the specified date range.");
                    } else {
                        ingredientsInRange.forEach(System.out::println);
                    }
                }
                case 8 -> {
                    System.out.println("Returning to Main Menu...");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Manages the cookbook system by displaying the cookbook menu and processing user input.
     */
    private void manageCookbook() {
        boolean running = true;
        while (running) {
            int cookbookOption = displayCookbookMenu();
            switch (cookbookOption) {
                case 1 -> clientCookbook.viewAllRecipes(cookbook);
                case 2 -> clientCookbook.viewRecipesByCategory(cookbook, inputValidator);
                case 3 -> clientCookbook.addRecipeToCookbook(cookbook, inputValidator);
                case 4 -> clientCookbook.findRecipeByName(cookbook, inputValidator);
                case 5 -> clientCookbook.canMakeRecipe(cookbook, storage, inputValidator);
                case 6 -> clientCookbook.suggestRecipe(cookbook, storage);
                case 7 -> {
                    System.out.println("Returning to Main Menu...");
                    running = false;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private int displayMainMenu() {
        System.out.println("\n==============================");
        System.out.println("     Recipe & Storage App     ");
        System.out.println("==============================");
        System.out.println("1. Manage Storage");
        System.out.println("2. Manage Cookbook");
        System.out.println("3. Exit Application");
        System.out.println("==============================");
        return inputValidator.getValidInt("Please select an option (1-3): ");
    }

    private int displayStorageMenu() {
        System.out.println("\n==============================");
        System.out.println("        Storage Menu          ");
        System.out.println("==============================");
        System.out.println("1. View All Ingredients");
        System.out.println("2. Find Ingredient by Name");
        System.out.println("3. Add New Ingredient");
        System.out.println("4. Remove Ingredient by Name and Amount");
        System.out.println("5. View Expired Ingredients and Total Value");
        System.out.println("6. Calculate Total Value of Storage");
        System.out.println("7. Get Ingredients in Date Range");
        System.out.println("8. Return to Main Menu");
        System.out.println("==============================");
        return inputValidator.getValidInt("Please select an option (1-8): ");
    }

    private int displayCookbookMenu() {
        System.out.println("\n==============================");
        System.out.println("       Cookbook Menu          ");
        System.out.println("==============================");
        System.out.println("1. View All Recipes");
        System.out.println("2. View Recipes by Category");
        System.out.println("3. Add a New Recipe");
        System.out.println("4. Find a Recipe by Name");
        System.out.println("5. Check if a Recipe Can Be Made");
        System.out.println("6. Suggest Recipes Based on Ingredients");
        System.out.println("7. Return to Main Menu");
        System.out.println("==============================");
        return inputValidator.getValidInt("Please select an option (1-7): ");
    }
}