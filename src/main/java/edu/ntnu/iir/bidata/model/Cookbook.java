package edu.ntnu.iir.bidata.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a cookbook containing recipes organized by categories.
 * Provides functionality to add, retrieve, and suggest recipes based on available ingredients.
 */
public class Cookbook {
    private final HashMap<Integer, Recipe> cookbook;
    private final HashMap<String, ArrayList<Recipe>> categorizedRecipes;
    private final HashMap<String, Integer> categoryCounters;

    /**
     * Constructs an empty {@code Cookbook} and initializes category counters.
     */
    public Cookbook() {
        this.cookbook = new HashMap<>();
        this.categorizedRecipes = new HashMap<>();
        this.categoryCounters = new HashMap<>();

        categoryCounters.put("Lunch", 0);
        categoryCounters.put("Dinner", 1);
        categoryCounters.put("Breakfast", 2);
        categoryCounters.put("Dessert", 3);
    }

    /**
     * Retrieves the cookbook containing all recipes.
     *
     * @return a {@code HashMap} where the keys are recipe IDs and the values are {@code Recipe} objects.
     */
    public HashMap<Integer, Recipe> getCookbook() {
        return cookbook;
    }

    /**
     * Generates a unique recipe ID based on the category.
     *
     * @param category the category of the recipe.
     * @return a unique recipe ID.
     * @throws IllegalArgumentException if the category is invalid.
     */
    public int generateRecipeID(String category) {
        if (!categoryCounters.containsKey(category)) {
            throw new IllegalArgumentException("Invalid category: " + category);
        }
        int prefix = categoryCounters.get(category);
        int counter = prefix * 1000 + categorizedRecipes.getOrDefault(category.toLowerCase(), new ArrayList<>()).size() + 1;
        return counter;
    }

    /**
     * Retrieves and displays recipes by a specified category.
     *
     * @param category the category to filter recipes by.
     * @return a list of recipes in the specified category.
     */
    public ArrayList<Recipe> viewRecipesByCategory(String category) {
        ArrayList<Recipe> recipes = categorizedRecipes.getOrDefault(category.toLowerCase(), new ArrayList<>());
        if (recipes.isEmpty()) {
            System.out.println("No recipes found under category: " + category);
        } else {
            System.out.println("\nRecipes under category: " + category);
            recipes.forEach(recipe -> System.out.println("ID: " + recipe.getRecipeID() + " | " + recipe.getRecipeName()));
        }
        return recipes;
    }

    /**
     * Adds a new recipe to the cookbook under the specified category.
     *
     * @param recipe   the recipe to add.
     * @param category the category to associate the recipe with.
     * @return the added recipe with an assigned recipe ID.
     */
    public Recipe addRecipeToCookbook(Recipe recipe, String category) {
        int recipeID = generateRecipeID(category);
        recipe.setRecipeID(recipeID);
        cookbook.put(recipeID, recipe);

        categorizedRecipes.putIfAbsent(category.toLowerCase(), new ArrayList<>());
        categorizedRecipes.get(category.toLowerCase()).add(recipe);

        System.out.println("Recipe added: " + recipe.getRecipeName() + " with ID: " + recipeID);
        return recipe;
    }

    /**
     * Finds a recipe by its name.
     *
     * @param recipeName the name of the recipe to find.
     * @return the recipe if found; {@code null} otherwise.
     * @throws IllegalArgumentException if the recipe name is null or empty.
     */
    public Recipe findRecipeByName(String recipeName) {
        if (recipeName == null || recipeName.isEmpty()) {
            throw new IllegalArgumentException("Recipe name cannot be null or empty.");
        }
        return cookbook.values().stream()
                .filter(recipe -> recipe.getRecipeName().equals(recipeName))
                .findFirst().orElse(null);
    }

    /**
     * Suggests recipes that can be made with the available ingredients in the storage.
     *
     * @param storage the storage containing the available ingredients.
     * @return a list of recipes that can be made with the available ingredients.
     */
    public ArrayList<Recipe> suggestRecipe(Storage storage) {
        Map<String, Ingredient> ingredientMap = storage.getIngredients().stream()
                .collect(Collectors.toMap(ingredient ->
                        ingredient.getIngredientName().toLowerCase(), ingredient -> ingredient));

        return cookbook.values().stream()
                .filter(recipe -> recipe.getRecipe().stream().allMatch(recipeIngredient -> {
                    Ingredient available = ingredientMap.get(recipeIngredient.getIngredientName().toLowerCase());
                    return available != null && available.getIngredientAmount() >= recipeIngredient.getIngredientAmount();
                }))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
