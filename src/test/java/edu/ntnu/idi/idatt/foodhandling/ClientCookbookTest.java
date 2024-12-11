package edu.ntnu.idi.idatt.foodhandling;
import edu.ntnu.idi.idatt.inputvalidator.InputValidator;
import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.Recipe;
import edu.ntnu.idi.idatt.model.Storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;


@ExtendWith(MockitoExtension.class)
class ClientCookbookTest {

    @Mock
    private Cookbook mockCookbook;
    @Mock
    private Recipe mockRecipe;
    @Mock
    private Storage mockStorage;
    @Mock
    private InputValidator mockInputValidator;

    @Test
    void testInit() {
        // Arrange
        // No setup is required because this is a static method.

        // Act
        Cookbook cookbook = ClientCookbook.init();

        // Assert
        assertNotNull(cookbook, "Cookbook should not be null after initialization.");
        assertFalse(cookbook.getCookbook().isEmpty(), "Cookbook should not be empty after initialization.");
    }

    @Test
    void testViewAllRecipes() {
        // Arrange
        when(mockCookbook.getCookbook()).thenReturn(new HashMap<>());

        // Act
        ClientCookbook.viewAllRecipes(mockCookbook);

        // Assert
        verify(mockCookbook, times(1)).getCookbook();
    }

    @Test
    void testViewRecipesByCategory() {
        // Arrange
        String category = "Lunch";
        ArrayList<Recipe> mockRecipes = new ArrayList<>();
        mockRecipes.add(new Recipe(1, "Recipe1", "Desc1", "Instruction1"));

        when(mockInputValidator.getNonEmptyString(anyString())).thenReturn(category);
        when(mockCookbook.viewRecipesByCategory(category)).thenReturn(mockRecipes);

        // Act
        ClientCookbook.viewRecipesByCategory(mockCookbook, mockInputValidator);

        // Assert
        verify(mockInputValidator, times(1)).getNonEmptyString(anyString());
        verify(mockCookbook, times(1)).viewRecipesByCategory(category);
    }

    @Test
    void testAddRecipeToCookbook() {
        // Arrange
        String category = "Dinner";
        String recipeName = "Grilled Chicken";
        String description = "Juicy grilled chicken.";
        String instructions = "Season and grill.";
        String ingredientName = "Chicken Breast";

        when(mockInputValidator.getNonEmptyString(anyString()))
                .thenReturn(category)         // First call for category
                .thenReturn(recipeName)       // Second call for recipe name
                .thenReturn(description)      // Third call for description
                .thenReturn(instructions)     // Fourth call for instructions
                .thenReturn(ingredientName)   // Ingredient name
                .thenReturn("done");          // To stop ingredient addition
        when(mockInputValidator.getValidDouble(anyString())).thenReturn(1.0); // Mock ingredient amount and price

        // Mock recipe ID generation
        when(mockCookbook.generateRecipeID(category)).thenReturn(1234);

        // Act
        ClientCookbook.addRecipeToCookbook(mockCookbook, mockInputValidator);

        // Assert
        verify(mockInputValidator, atLeast(4)).getNonEmptyString(anyString());
        verify(mockCookbook, times(1)).generateRecipeID(category);
        verify(mockCookbook, times(1)).addRecipeToCookbook(any(Recipe.class), eq(category));
    }

    @Test
    void testFindRecipeByName() {
        // Arrange
        String recipeName = "Pancake";
        Recipe mockRecipe = new Recipe(1, recipeName, "Description", "Instruction");

        when(mockInputValidator.getNonEmptyString(anyString())).thenReturn(recipeName);
        when(mockCookbook.findRecipeByName(recipeName)).thenReturn(mockRecipe);

        // Act
        ClientCookbook.findRecipeByName(mockCookbook, mockInputValidator);

        // Assert
        verify(mockInputValidator, times(1)).getNonEmptyString(anyString());
        verify(mockCookbook, times(1)).findRecipeByName(recipeName);
    }

    @Test
    void testCanMakeRecipe() {
        // Arrange
        String recipeName = "Oatmeal";
        Recipe mockRecipe = mock(Recipe.class); // Mock the Recipe itself
        when(mockInputValidator.getNonEmptyString(anyString())).thenReturn(recipeName);
        when(mockCookbook.findRecipeByName(recipeName)).thenReturn(mockRecipe);

        // Act
        ClientCookbook.canMakeRecipe(mockCookbook, mockStorage, mockInputValidator);

        // Assert
        verify(mockInputValidator, times(1)).getNonEmptyString(anyString());
        verify(mockCookbook, times(1)).findRecipeByName(recipeName);
        verify(mockRecipe, times(1)).canMakeRecipe(mockStorage);
    }

    @Test
    void testSuggestRecipe() {
        // Arrange
        ArrayList<Recipe> suggestedRecipes = new ArrayList<>();
        when(mockCookbook.suggestRecipe(mockStorage)).thenReturn(suggestedRecipes);

        // Act
        ClientCookbook.suggestRecipe(mockCookbook, mockStorage);

        // Assert
        verify(mockCookbook, times(1)).suggestRecipe(mockStorage);
    }

}
