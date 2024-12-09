package edu.ntnu.iir.bidata.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Represents a recipe with a unique ID, name, description, instructions, and a list of ingredients.
 * Provides functionality to manage ingredients and check if the recipe can be made with the available storage.
 */
public class Recipe {
    private int recipeID;
    private final String recipeName;
    private final String recipeDescription;
    private final String recipeInstruction;
    private final ArrayList<Ingredient> recipe;

    /**
     * Constructs a new Recipe with the specified details.
     * @param recipeID          the unique ID of the recipe.
     * @param recipeName        the name of the recipe.
     * @param recipeDescription a brief description of the recipe.
     * @param recipeInstruction instructions on how to prepare the recipe.
     */
    public Recipe(int recipeID, String recipeName, String recipeDescription, String recipeInstruction) {
        this.recipeID = recipeID;
        this.recipeName = recipeName;
        this.recipeDescription = recipeDescription;
        this.recipeInstruction = recipeInstruction;
        this.recipe = new ArrayList<>();
    }

    /**
     * Retrieves the recipe ID.
     * @return the recipe ID.
     */
    public int getRecipeID() {
        return recipeID;
    }

    /**
     * Retrieves the name of the recipe.
     * @return the recipe name.
     */
    public String getRecipeName() {
        return recipeName;
    }

    /**
     * Retrieves the list of ingredients for this recipe.
     * @return a list of Ingredient objects.
     */
    public ArrayList<Ingredient> getRecipe() {
        return this.recipe;
    }

    /**
     * Sets the recipe ID.
     * @param recipeID the new recipe ID.
     */
    public void setRecipeID(int recipeID) {
        this.recipeID = recipeID;
    }

    /**
     * Adds an ingredient to the recipe. If the ingredient already exists, its amount is updated.
     * @param ingredientName       the name of the ingredient.
     * @param ingredientAmount     the amount of the ingredient.
     * @param ingredientMeasurement the measurement unit of the ingredient.
     * @param expireDate           the expiration date of the ingredient.
     * @param ingredientPrice      the price of the ingredient.
     * @return the current Recipe instance.
     */
    public Recipe addIngredient(String ingredientName, double ingredientAmount, String ingredientMeasurement, LocalDate expireDate, double ingredientPrice) {
        for (Ingredient ingredient : this.recipe) {
            if (ingredient.getIngredientName().equalsIgnoreCase(ingredientName)) {
                ingredient.setIngredientAmount(ingredient.getIngredientAmount() + ingredientAmount);
                return this;
            }
        }
        this.recipe.add(new Ingredient(ingredientName, ingredientAmount, ingredientMeasurement, expireDate, ingredientPrice));
        return this;
    }

    /**
     * Checks if the recipe can be made with the available ingredients in the specified Storage.
     * If any ingredient is insufficient, details are printed to the console.
     * @param storage the Storage containing the available ingredients.
     * @return true if the recipe can be made; false otherwise.
     */
    public boolean canMakeRecipe(Storage storage) {
        Map<String, Ingredient> storageIngredients = storage.getIngredients().stream()
                .collect(Collectors.toMap(ingredient -> ingredient.getIngredientName().toLowerCase(),
                        ingredient -> ingredient));

        ArrayList<String> insufficientIngredients = new ArrayList<>();
        boolean canMake = recipe.stream().allMatch(recipeIngredient -> {
            Ingredient storageIngredient = storageIngredients.get(recipeIngredient.getIngredientName().toLowerCase());

            if (storageIngredient == null || storageIngredient.getIngredientAmount() < recipeIngredient.getIngredientAmount()) {
                String reason = storageIngredient == null
                        ? "not available"
                        : "only " + storageIngredient.getIngredientAmount() + " available";
                insufficientIngredients.add(recipeIngredient.getIngredientName() + " (" + reason + ", requires " + recipeIngredient.getIngredientAmount() + " )");
                return false;
            }
            return true;
        });

        if (!canMake) {
            System.out.println("Cannot make this recipe due to insufficient ingredients:");
            insufficientIngredients.forEach(System.out::println);
        }
        return canMake;
    }

    /**
     * Returns a string representation of the recipe, including its name, description, instructions, and ingredients.
     * @return a string representation of the recipe.
     */
    @Override
    public String toString() {
        String result =  "Recipe: " + recipeName
                + "\nDescription: " + recipeDescription
                + "\nInstruction: " + recipeInstruction
                + "\nIngredients:"
                + "\n";

        for (Ingredient ingredient : recipe) {
            result += "- " + ingredient.getIngredientName()
                    + ": " + ingredient.getIngredientAmount()
                    + " " + ingredient.getIngredientMeasurement()
                    + "\n";
        }
        return result;
    }
}
