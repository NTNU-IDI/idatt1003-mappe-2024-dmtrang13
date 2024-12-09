package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;


@ExtendWith(MockitoExtension.class)
class RecipeTest {

    private Recipe recipe;

    @Mock
    private Storage mockStorage;

    @BeforeEach
    void setUp() {
        recipe = new Recipe(1000, "Spaghetti Bolognese", "A delicious pasta dish", "Cook spaghetti and mix with sauce.");
    }

    @Test
    void testGetRecipeID() {
        // Act
        int recipeID = recipe.getRecipeID();

        // Assert
        assertEquals(1000, recipeID, "The recipe ID should match the one set during initialization.");
    }

    @Test
    void testGetRecipeName() {
        // Act
        String recipeName = recipe.getRecipeName();

        // Assert
        assertEquals("Spaghetti Bolognese", recipeName, "The recipe name should match the one set during initialization.");
    }

    @Test
    void testAddIngredient_NewIngredient() {
        // Arrange
        String ingredientName = "Spaghetti";
        double amount = 0.5;
        String measurement = "kg";
        LocalDate expiryDate = LocalDate.of(2024, 1, 1);
        double price = 20.0;

        // Act
        recipe.addIngredient(ingredientName, amount, measurement, expiryDate, price);

        // Assert
        assertEquals(1, recipe.getRecipe().size(), "The recipe should contain one ingredient.");
        assertEquals(ingredientName, recipe.getRecipe().get(0).getIngredientName(), "The ingredient name should match.");
    }

    @Test
    void testAddIngredient_ExistingIngredient() {
        // Arrange
        recipe.addIngredient("Spaghetti", 0.5, "kg", LocalDate.of(2024, 1, 1), 20.0);

        // Act
        recipe.addIngredient("Spaghetti", 0.3, "kg", LocalDate.of(2024, 1, 1), 20.0);

        // Assert
        assertEquals(1, recipe.getRecipe().size(), "The recipe should still contain one ingredient.");
        assertEquals(0.8, recipe.getRecipe().get(0).getIngredientAmount(), "The ingredient amount should be updated.");
    }

    @Test
    void testCanMakeRecipe_Success() {
        // Arrange
        recipe.addIngredient("Spaghetti", 0.5, "kg", LocalDate.of(2024, 1, 1), 20.0);
        recipe.addIngredient("Tomato Sauce", 1, "can", LocalDate.of(2024, 1, 1), 10.0);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Spaghetti", 1.0, "kg", LocalDate.of(2024, 1, 1), 20.0));
        ingredients.add(new Ingredient("Tomato Sauce", 2, "can", LocalDate.of(2024, 1, 1), 20.0));

        when(mockStorage.getIngredients()).thenReturn(ingredients);

        // Act
        boolean canMake = recipe.canMakeRecipe(mockStorage);

        // Assert
        assertTrue(canMake, "The recipe should be possible to make with the available ingredients.");
    }

    @Test
    void testCanMakeRecipe_InsufficientIngredient() {
        // Arrange
        recipe.addIngredient("Spaghetti", 0.5, "kg", LocalDate.of(2024, 1, 1), 20.0);
        recipe.addIngredient("Tomato Sauce", 1, "can", LocalDate.of(2024, 1, 1), 10.0);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Spaghetti", 0.4, "kg", LocalDate.of(2024, 1, 1), 20.0)); // Insufficient amount
        ingredients.add(new Ingredient("Tomato Sauce", 1, "can", LocalDate.of(2024, 1, 1), 10.0));

        when(mockStorage.getIngredients()).thenReturn(ingredients);

        // Act
        boolean canMake = recipe.canMakeRecipe(mockStorage);

        // Assert
        assertFalse(canMake, "The recipe should not be possible to make due to insufficient ingredients.");
    }

    @Test
    void testCanMakeRecipe_MissingIngredient() {
        // Arrange
        recipe.addIngredient("Spaghetti", 0.5, "kg", LocalDate.of(2024, 1, 1), 20.0);
        recipe.addIngredient("Tomato Sauce", 1, "can", LocalDate.of(2024, 1, 1), 10.0);

        ArrayList<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Spaghetti", 1.0, "kg", LocalDate.of(2024, 1, 1), 20.0)); // Missing Tomato Sauce

        when(mockStorage.getIngredients()).thenReturn(ingredients);

        // Act
        boolean canMake = recipe.canMakeRecipe(mockStorage);

        // Assert
        assertFalse(canMake, "The recipe should not be possible to make due to missing ingredients.");
    }

    @Test
    void testToString() {
        // Arrange
        recipe.addIngredient("Spaghetti", 0.5, "kg", LocalDate.of(2024, 1, 1), 20.0);
        recipe.addIngredient("Tomato Sauce", 1, "can", LocalDate.of(2024, 1, 1), 10.0);

        // Act
        String recipeString = recipe.toString();

        // Assert
        assertTrue(recipeString.contains("Spaghetti"), "The string representation should include 'Spaghetti'.");
        assertTrue(recipeString.contains("Tomato Sauce"), "The string representation should include 'Tomato Sauce'.");
    }
}