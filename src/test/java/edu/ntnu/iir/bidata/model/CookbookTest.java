package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class CookbookTest {

    private Cookbook cookbook;

    @Mock
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        cookbook = new Cookbook(); // Only initialize your test object
    }

    @Test
    void testGetCookbook() {
        // Arrange & Act
        HashMap<Integer, Recipe> recipes = cookbook.getCookbook();

        // Assert
        assertNotNull(recipes, "Cookbook should return a non-null map of recipes.");
        assertTrue(recipes.isEmpty(), "Cookbook should initially be empty.");
    }

    @Test
    void testGenerateRecipeID_ValidCategory() {
        // Arrange
        String category = "Lunch";

        // Act
        int recipeID = cookbook.generateRecipeID(category);

        // Assert
        assertEquals(1, recipeID % 1000, "The generated recipe ID should start with 1 for a new category.");
    }

    @Test
    void testGenerateRecipeID_InvalidCategory() {
        // Arrange
        String invalidCategory = "InvalidCategory";

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                cookbook.generateRecipeID(invalidCategory));
        assertEquals("Invalid category: " + invalidCategory, exception.getMessage(),
                "The exception message should indicate the invalid category.");
    }

    @Test
    void testViewRecipesByCategory_EmptyCategory() {
        // Arrange
        String category = "Lunch";

        // Act
        List<Recipe> recipes = cookbook.viewRecipesByCategory(category);

        // Assert
        assertTrue(recipes.isEmpty(), "No recipes should be returned for an empty category.");
    }

    @Test
    void testAddRecipeToCookbook() {
        // Arrange
        Recipe recipe = new Recipe(0, "Spaghetti Bolognese", "Delicious spaghetti", "Cook and serve.");
        String category = "Lunch";

        // Act
        Recipe addedRecipe = cookbook.addRecipeToCookbook(recipe, category);

        // Assert
        assertNotNull(addedRecipe, "The added recipe should not be null.");
        assertEquals(recipe, addedRecipe, "The returned recipe should match the one added.");
        assertEquals(1, cookbook.getCookbook().size(), "The cookbook should contain one recipe.");

        // Assert the recipe is under the correct category
        assertTrue(
                cookbook.viewRecipesByCategory(category).contains(recipe),
                "The recipe should be associated with the correct category."
        );
    }

    @Test
    void testFindRecipeByName_Found() {
        // Arrange
        Recipe recipe = new Recipe(0, "Spaghetti Bolognese", "Delicious spaghetti", "Cook and serve.");
        cookbook.addRecipeToCookbook(recipe, "Lunch");

        // Act
        Recipe foundRecipe = cookbook.findRecipeByName("Spaghetti Bolognese");

        // Assert
        assertNotNull(foundRecipe, "The recipe should be found.");
        assertEquals(recipe, foundRecipe, "The found recipe should match the expected recipe.");
    }

    @Test
    void testFindRecipeByName_NotFound() {
        // Act
        Recipe foundRecipe = cookbook.findRecipeByName("Nonexistent Recipe");

        // Assert
        assertNull(foundRecipe, "The recipe should not be found and should return null.");
    }

    @Test
    void testFindRecipeByName_InvalidInput() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                cookbook.findRecipeByName(null));
        assertEquals("Recipe name cannot be null or empty.", exception.getMessage(),
                "The exception message should indicate that the recipe name cannot be null or empty.");
    }

    @Test
    void testSuggestRecipe_Success() {
        // Arrange
        Recipe recipe = new Recipe(0, "Spaghetti Bolognese", "Delicious spaghetti", "Cook and serve.");
        recipe.addIngredient("Spaghetti", 0.5, "kg", null, 0);
        cookbook.addRecipeToCookbook(recipe, "Lunch");

        Ingredient ingredient = new Ingredient("Spaghetti", 1.0, "kg", null, 0);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        when(mockStorage.getIngredients()).thenReturn(ingredients);

        // Act
        List<Recipe> suggestedRecipes = cookbook.suggestRecipe(mockStorage);

        // Assert
        assertEquals(1, suggestedRecipes.size(), "One recipe should be suggested.");
        assertEquals(recipe, suggestedRecipes.get(0), "The suggested recipe should match the expected recipe.");
    }

    @Test
    void testSuggestRecipe_NoSuggestions() {
        // Arrange
        Recipe recipe = new Recipe(0, "Spaghetti Bolognese", "Delicious spaghetti", "Cook and serve.");
        recipe.addIngredient("Spaghetti", 0.5, "kg", null, 0);
        cookbook.addRecipeToCookbook(recipe, "Lunch");

        Ingredient ingredient = new Ingredient("Rice", 1.0, "kg", null, 0);
        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient);

        when(mockStorage.getIngredients()).thenReturn(ingredients);

        // Act
        List<Recipe> suggestedRecipes = cookbook.suggestRecipe(mockStorage);

        // Assert
        assertTrue(suggestedRecipes.isEmpty(), "No recipes should be suggested when ingredients are insufficient.");
    }
}