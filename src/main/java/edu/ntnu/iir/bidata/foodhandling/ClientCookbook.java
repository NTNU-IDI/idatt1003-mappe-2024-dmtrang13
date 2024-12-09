package edu.ntnu.iir.bidata.foodhandling;

import edu.ntnu.iir.bidata.inputvalidator.InputValidator;
import edu.ntnu.iir.bidata.model.Cookbook;
import edu.ntnu.iir.bidata.model.Recipe;
import edu.ntnu.iir.bidata.model.Storage;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * The ClientCookbook class provides functionality for managing a cookbook.
 * It allows initialization, viewing, and manipulation of recipes.
 */
public class ClientCookbook {

    /**
     * Initializes and populates a Cookbook with predefined recipes and ingredients.
     * @return a Cookbook containing predefined recipes.
     */
    public static Cookbook init() {
        Cookbook cookBook = new Cookbook();

        // Predefined recipes and their ingredients
        cookBook.addRecipeToCookbook(new Recipe(2000, "Pancake", "A simple pancake recipe", "Mix all ingredients and cook."), "Breakfast")
                .addIngredient("Eggs", 2, "pcs", LocalDate.of(2023, 12, 1), 20.0)
                .addIngredient("Milk", 0.5, "liter", LocalDate.of(2023, 11, 30), 15.0)
                .addIngredient("Flour", 0.2, "kg", LocalDate.of(2024, 5, 15), 10.0);

        cookBook.addRecipeToCookbook(new Recipe(2001, "Scrambled Eggs", "Quick scrambled eggs.", "Whisk eggs, cook in a pan."), "Breakfast")
                .addIngredient("Eggs", 4, "pcs", LocalDate.of(2023, 12, 1), 40.0)
                .addIngredient("Butter", 20, "g", LocalDate.of(2023, 12, 5), 5.0);

        cookBook.addRecipeToCookbook(new Recipe(0001, "Spaghetti Bolognese", "Classic Italian dish.", "Cook spaghetti, prepare sauce, mix."), "Lunch")
                .addIngredient("Spaghetti", 0.5, "kg", LocalDate.of(2024, 1, 15), 20.0)
                .addIngredient("Ground Beef", 0.4, "kg", LocalDate.of(2023, 12, 20), 60.0)
                .addIngredient("Tomato Sauce", 1, "can", LocalDate.of(2024, 3, 10), 25.0);

        cookBook.addRecipeToCookbook(new Recipe(0002, "Caesar Salad", "A fresh Caesar salad.", "Mix lettuce, croutons, dressing, and chicken."), "Lunch")
                .addIngredient("Lettuce", 1, "head", LocalDate.of(2023, 11, 28), 20.0)
                .addIngredient("Croutons", 50, "g", LocalDate.of(2024, 1, 10), 10.0)
                .addIngredient("Chicken Breast", 0.3, "kg", LocalDate.of(2023, 11, 29), 50.0)
                .addIngredient("Caesar Dressing", 100, "ml", LocalDate.of(2023, 12, 5), 15.0);

        cookBook.addRecipeToCookbook(new Recipe(3000, "Chocolate Cake", "Rich chocolate cake.", "Mix, bake, and frost."), "Dessert")
                .addIngredient("Flour", 250, "g", LocalDate.of(2024, 5, 15), 20.0)
                .addIngredient("Sugar", 200, "g", LocalDate.of(2025, 1, 15), 10.0)
                .addIngredient("Cocoa Powder", 50, "g", LocalDate.of(2024, 6, 10), 15.0)
                .addIngredient("Butter", 100, "g", LocalDate.of(2023, 12, 5), 15.0)
                .addIngredient("Eggs", 3, "pcs", LocalDate.of(2023, 12, 1), 30.0);

        cookBook.addRecipeToCookbook(new Recipe(2002, "French Toast", "Classic French toast with a sweet twist.", "Whisk eggs, dip bread, and cook on a pan."), "Breakfast")
                .addIngredient("Eggs", 2, "pcs", LocalDate.of(2023, 12, 1), 20.0)
                .addIngredient("Milk", 0.2, "liter", LocalDate.of(2023, 11, 30), 10.0)
                .addIngredient("Bread", 4, "slices", LocalDate.of(2023, 12, 15), 15.0)
                .addIngredient("Cinnamon", 5, "g", LocalDate.of(2025, 1, 10), 5.0)
                .addIngredient("Butter", 10, "g", LocalDate.of(2023, 12, 5), 3.0);

        cookBook.addRecipeToCookbook(new Recipe(2003, "Oatmeal", "Healthy and filling breakfast.", "Cook oats with milk and top with fruits."), "Breakfast")
                .addIngredient("Oats", 50, "g", LocalDate.of(2024, 3, 15), 15.0)
                .addIngredient("Milk", 0.3, "liter", LocalDate.of(2023, 11, 30), 12.0)
                .addIngredient("Banana", 1, "pcs", LocalDate.of(2023, 11, 29), 5.0)
                .addIngredient("Honey", 10, "ml", LocalDate.of(2025, 6, 15), 3.0);

        cookBook.addRecipeToCookbook(new Recipe(3001, "Brownies", "Chewy and fudgy brownies.", "Mix ingredients, bake, and cool."), "Dessert")
                .addIngredient("Flour", 150, "g", LocalDate.of(2024, 5, 15), 12.0)
                .addIngredient("Sugar", 100, "g", LocalDate.of(2025, 1, 15), 6.0)
                .addIngredient("Cocoa Powder", 30, "g", LocalDate.of(2024, 6, 10), 10.0)
                .addIngredient("Butter", 80, "g", LocalDate.of(2023, 12, 5), 10.0)
                .addIngredient("Eggs", 2, "pcs", LocalDate.of(2023, 12, 1), 20.0);

        cookBook.addRecipeToCookbook(new Recipe(3002, "Apple Pie", "Traditional apple pie with a flaky crust.", "Prepare crust, fill with apples, and bake."), "Dessert")
                .addIngredient("Flour", 300, "g", LocalDate.of(2024, 5, 15), 15.0)
                .addIngredient("Sugar", 150, "g", LocalDate.of(2025, 1, 15), 9.0)
                .addIngredient("Apples", 3, "pcs", LocalDate.of(2023, 12, 3), 20.0)
                .addIngredient("Butter", 120, "g", LocalDate.of(2023, 12, 5), 15.0)
                .addIngredient("Cinnamon", 10, "g", LocalDate.of(2025, 1, 10), 6.0);

        cookBook.addRecipeToCookbook(new Recipe(1001, "Grilled Chicken", "Juicy grilled chicken with herbs.", "Season chicken and grill until done."), "Dinner")
                .addIngredient("Chicken Breast", 2, "pcs", LocalDate.of(2023, 12, 10), 80.0)
                .addIngredient("Olive Oil", 20, "ml", LocalDate.of(2024, 6, 15), 15.0)
                .addIngredient("Garlic", 2, "cloves", LocalDate.of(2023, 12, 20), 5.0)
                .addIngredient("Rosemary", 5, "g", LocalDate.of(2024, 2, 15), 5.0)
                .addIngredient("Salt", 2, "g", LocalDate.of(2025, 1, 15), 2.0);

        cookBook.addRecipeToCookbook(new Recipe(1002, "Lasagna", "Classic Italian lasagna with meat sauce.", "Layer pasta, sauce, and cheese, then bake."), "Dinner")
                .addIngredient("Lasagna Sheets", 12, "pcs", LocalDate.of(2024, 3, 20), 25.0)
                .addIngredient("Ground Beef", 500, "g", LocalDate.of(2023, 12, 10), 100.0)
                .addIngredient("Tomato Sauce", 300, "ml", LocalDate.of(2024, 1, 15), 30.0)
                .addIngredient("Mozzarella", 200, "g", LocalDate.of(2023, 12, 5), 50.0)
                .addIngredient("Parmesan", 50, "g", LocalDate.of(2024, 2, 10), 25.0);

        cookBook.addRecipeToCookbook(new Recipe(1003, "Beef Stew", "Hearty stew with tender beef and vegetables.", "Simmer beef and vegetables in broth."), "Dinner")
                .addIngredient("Beef Chuck", 500, "g", LocalDate.of(2023, 12, 15), 120.0)
                .addIngredient("Carrots", 2, "pcs", LocalDate.of(2023, 12, 2), 8.0)
                .addIngredient("Potatoes", 3, "pcs", LocalDate.of(2023, 12, 5), 15.0)
                .addIngredient("Onions", 1, "pcs", LocalDate.of(2023, 12, 10), 6.0)
                .addIngredient("Beef Broth", 500, "ml", LocalDate.of(2024, 2, 10), 20.0);

        cookBook.addRecipeToCookbook(new Recipe(1004, "Vegetable Curry", "Aromatic curry with fresh vegetables.", "Cook vegetables in a spicy curry sauce."), "Dinner")
                .addIngredient("Bell Pepper", 2, "pcs", LocalDate.of(2023, 12, 3), 12.0)
                .addIngredient("Carrots", 3, "pcs", LocalDate.of(2023, 12, 2), 12.0)
                .addIngredient("Coconut Milk", 400, "ml", LocalDate.of(2024, 1, 20), 30.0)
                .addIngredient("Curry Paste", 50, "g", LocalDate.of(2024, 6, 10), 15.0)
                .addIngredient("Rice", 200, "g", LocalDate.of(2025, 1, 15), 20.0);

        cookBook.addRecipeToCookbook(new Recipe(0003, "Chicken Wrap", "Healthy chicken wrap with veggies.", "Wrap chicken and vegetables in flatbread."), "Lunch")
                .addIngredient("Flatbread", 2, "pcs", LocalDate.of(2023, 12, 15), 10.0)
                .addIngredient("Chicken Breast", 1, "pcs", LocalDate.of(2023, 12, 10), 40.0)
                .addIngredient("Lettuce", 2, "leaves", LocalDate.of(2023, 11, 28), 5.0)
                .addIngredient("Tomato", 1, "pcs", LocalDate.of(2023, 12, 1), 8.0)
                .addIngredient("Yogurt Dressing", 20, "ml", LocalDate.of(2023, 12, 5), 8.0);


        return cookBook;
    }

    /**
     * Displays all recipes in the given Cookbook.
     * @param cookbook the Cookbook whose recipes will be displayed.
     */
    public static void viewAllRecipes(Cookbook cookbook) {
        if (cookbook.getCookbook().isEmpty()) {
            System.out.println("No recipes found in the cookbook.");
        } else {
            cookbook.getCookbook().values().forEach(recipe -> {
                System.out.println("\n======================");
                System.out.println(recipe);
                System.out.println("======================");
            });
        }
    }

    /**
     * Displays recipes in the given Cookbook by their category.
     * @param cookbook the Cookbook whose recipes will be filtered by category.
     */
    public static void viewRecipesByCategory(Cookbook cookbook, InputValidator inputValidator) {
        String category = inputValidator.getNonEmptyString("Please enter cookbook's category: ");
        ArrayList<Recipe> recipes = cookbook.viewRecipesByCategory(category);
        if (recipes.isEmpty()) {
            System.out.println("No recipes found under category: " + category);
        } else {
            System.out.println("Recipes under category: " + category);
            recipes.forEach(System.out::println);
        }
    }

    /**
     * Allows the user to add a new recipe to the given Cookbook.
     * @param cookbook the Cookbook to which the new recipe will be added.
     */
    public static void addRecipeToCookbook(Cookbook cookbook, InputValidator inputValidator) {
        String category = inputValidator.getNonEmptyString("Enter the category for the recipe (Lunch / Dinner / Breakfast / Dessert): ");
        int recipeID = cookbook.generateRecipeID(category);
        String recipeName = inputValidator.getNonEmptyString("Please enter recipe's name: ");
        String recipeDescription = inputValidator.getNonEmptyString("Please enter recipe's description: ");
        String recipeInstruction = inputValidator.getNonEmptyString("Please enter recipe's instruction: ");

        Recipe recipe = new Recipe(recipeID, recipeName, recipeDescription, recipeInstruction);
        System.out.println("Adding ingredients to recipe: ");

        String ingredientName;
        do {
            ingredientName = inputValidator.getNonEmptyString("Enter ingredient name / done to finish: ");
            if (!ingredientName.equalsIgnoreCase("done")) {
                double amount = inputValidator.getValidDouble("Enter ingredient amount: ");
                String measurement = inputValidator.getNonEmptyString("Enter measurement: ");
                double price = inputValidator.getValidDouble("Enter price per unit: ");
                LocalDate expireDate = LocalDate.now();

                recipe.addIngredient(ingredientName, amount, measurement, expireDate, price);
            }
        } while (!ingredientName.equalsIgnoreCase("done"));

        cookbook.addRecipeToCookbook(recipe, category);
        System.out.println("Recipe added successfully.");
    }

    /**
     * Finds and displays a recipe by its name from the given Cookbook.
     * @param cookbook the Cookbook to search for the recipe.
     */
    public static void findRecipeByName(Cookbook cookbook, InputValidator inputValidator) {
        String recipeName = inputValidator.getNonEmptyString("PLease enter recipe's name: ");
        Recipe recipe = cookbook.findRecipeByName(recipeName);
        if (recipe != null) {
            System.out.println("Recipe found: \n" + recipe);
        } else {
            System.out.println(" Recipe " + recipeName + " not found.");
        }

    }

    /**
     * Checks if a recipe can be made with the current ingredients in the given Storage.
     * @param cookbook the Cookbook containing the recipes.
     * @param storage  the Storage containing the available ingredients.
     */
    public static void canMakeRecipe(Cookbook cookbook, Storage storage, InputValidator inputValidator) {
        String recipeName = inputValidator.getNonEmptyString("Please enter recipe's name: ");
        Recipe recipe = cookbook.findRecipeByName(recipeName);
        recipe.canMakeRecipe(storage);
    }

    /**
     * Suggests recipes that can be made with the available ingredients in the given Storage.
     * @param cookbook the Cookbook containing the recipes.
     * @param storage  the Storage containing the available ingredients.
     */
    public static void suggestRecipe(Cookbook cookbook, Storage storage) {
        ArrayList<Recipe> suggestedRecipes = cookbook.suggestRecipe(storage);
        if (suggestedRecipes.isEmpty()) {
            System.out.println("No recipes can be made with the current ingredients.");
        } else {
            System.out.println("You can make the following recipes:");
            suggestedRecipes.forEach(System.out::println);
        }
    }
}
