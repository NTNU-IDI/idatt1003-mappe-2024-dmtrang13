package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;


class StorageTest {
    private Storage storage;

    @Mock
    private Scanner mockScanner;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddIngredient_NewIngredientAdded() {
        // Arrange
        String name = "Tomato";
        double amount = 5.0;
        String measurement = "kg";
        LocalDate expireDate = LocalDate.of(2023, 12, 31);
        double price = 50.0;

        // Act
        storage.addIngredient(name, amount, measurement, expireDate, price);

        // Assert
        List<Ingredient> ingredients = storage.getIngredients();
        assertEquals(1, ingredients.size());
        Ingredient ingredient = ingredients.get(0);
        assertEquals(name, ingredient.getIngredientName());
        assertEquals(amount, ingredient.getIngredientAmount());
        assertEquals(measurement, ingredient.getIngredientMeasurement());
        assertEquals(expireDate, ingredient.getExpireDate());
        assertEquals(price, ingredient.getIngredientPrice());
    }

    @Test
    void testAddIngredient_ExistingIngredientDetailsUpdated() {
        // Arrange
        String name = "Tomato";
        storage.addIngredient(name, 2.0, "kg", LocalDate.of(2023, 12, 31), 500.0);

        // Mock user confirmation for updates
        when(mockScanner.nextLine())
                .thenReturn("y") // Confirm updating existing ingredient
                .thenReturn("y") // Confirm updating expiration date
                .thenReturn("y"); // Confirm updating price

        // Act
        storage.setScanner(mockScanner);
        storage.addIngredient(name, 3.0, "kg", LocalDate.of(2024, 1, 1), 550.0);

        // Assert
        List<Ingredient> ingredients = storage.getIngredients();
        assertEquals(1, ingredients.size(), "There should still be only one ingredient entry.");
        Ingredient ingredient = ingredients.get(0);

        // Validate updated details
        assertEquals(5.0, ingredient.getIngredientAmount(), 0.01, "The amount should be updated.");
        assertEquals("kg", ingredient.getIngredientMeasurement(), "The measurement unit should remain metric.");
        assertEquals(LocalDate.of(2024, 1, 1), ingredient.getExpireDate(), "The expiration date should be updated.");
        assertEquals(550.0, ingredient.getIngredientPrice(), 0.01, "The price should be updated to NOK.");
    }

    @Test
    void testAddIngredient_NewEntryAddedOnRejection() {
        // Arrange
        String name = "Tomato";
        storage.addIngredient(name, 2.0, "kg", LocalDate.of(2023, 12, 31), 500.0);

        // Mock user rejection for updates
        when(mockScanner.nextLine())
                .thenReturn("n") // Reject updating existing ingredient
                .thenReturn("y"); // Confirm adding a new entry

        // Act
        storage.setScanner(mockScanner);
        storage.addIngredient(name, 3.0, "kg", LocalDate.of(2024, 1, 1), 550.0);

        // Assert
        List<Ingredient> ingredients = storage.getIngredients();
        assertEquals(2, ingredients.size(), "There should be two ingredient entries.");

        Ingredient first = ingredients.get(0);
        Ingredient second = ingredients.get(1);

        // Validate first ingredient remains unchanged
        assertEquals(2.0, first.getIngredientAmount(), 0.01, "The amount of the first ingredient should remain unchanged.");
        assertEquals("kg", first.getIngredientMeasurement(), "The measurement unit of the first ingredient should remain metric.");
        assertEquals(LocalDate.of(2023, 12, 31), first.getExpireDate(), "The expiration date of the first ingredient should remain unchanged.");
        assertEquals(500.0, first.getIngredientPrice(), 0.01, "The price of the first ingredient should remain in NOK.");

        // Validate new entry
        assertEquals(3.0, second.getIngredientAmount(), 0.01, "The amount of the second ingredient should match the new entry.");
        assertEquals("kg", second.getIngredientMeasurement(), "The measurement unit of the second ingredient should be in metric.");
        assertEquals(LocalDate.of(2024, 1, 1), second.getExpireDate(), "The expiration date of the second ingredient should match the new entry.");
        assertEquals(550.0, second.getIngredientPrice(), 0.01, "The price of the second ingredient should be in NOK.");
    }

    @Test
    void testGetIngredientsByName_Found() {
        // Arrange
        storage.addIngredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 31), 50.0);
        storage.addIngredient("Potato", 10.0, "kg", LocalDate.of(2024, 1, 31), 20.0);

        // Act
        List<Ingredient> result = storage.getIngredientsByName("Tomato");

        // Assert
        assertEquals(1, result.size());
        assertEquals("Tomato", result.get(0).getIngredientName());
    }

    @Test
    void testGetIngredientsByName_NotFound() {
        // Arrange
        storage.addIngredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 31), 50.0);

        // Act
        List<Ingredient> result = storage.getIngredientsByName("Carrot");

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testRemoveIngredientByNameAndAmount_RemovesPartialAmount() {
        // Arrange
        storage.addIngredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 31), 50.0);

        // Act
        storage.removeIngredientByNameAndAmount("Tomato", 3.0);

        // Assert
        List<Ingredient> ingredients = storage.getIngredients();
        assertEquals(1, ingredients.size());
        assertEquals(2.0, ingredients.get(0).getIngredientAmount());
    }

    @Test
    void testRemoveIngredientByNameAndAmount_RemovesAllAmount() {
        // Arrange
        storage.addIngredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 31), 50.0);

        // Act
        storage.removeIngredientByNameAndAmount("Tomato", 5.0);

        // Assert
        List<Ingredient> ingredients = storage.getIngredients();
        assertTrue(ingredients.isEmpty());
    }

    @Test
    void testRemoveIngredientByNameAndAmount_NotFound() {
        // Arrange
        storage.addIngredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 31), 50.0);

        // Act
        storage.removeIngredientByNameAndAmount("Carrot", 3.0);

        // Assert
        List<Ingredient> ingredients = storage.getIngredients();
        assertEquals(1, ingredients.size());
    }

    @Test
    void testGetIngredientsInDateInterval_Found() {
        // Arrange
        storage.addIngredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 15), 50.0);
        storage.addIngredient("Potato", 10.0, "kg", LocalDate.of(2024, 1, 1), 20.0);

        // Act
        List<Ingredient> result = storage.getIngredientsInDateInterval(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 31));

        // Assert
        assertEquals(1, result.size());
        assertEquals("Tomato", result.get(0).getIngredientName());
    }

    @Test
    void testGetIngredientsInDateInterval_NotFound() {
        // Arrange
        storage.addIngredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 15), 50.0);

        // Act
        List<Ingredient> result = storage.getIngredientsInDateInterval(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 12, 31));

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void testGetIngredientsInDateInterval_InvalidRange() {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () ->
                storage.getIngredientsInDateInterval(LocalDate.of(2024, 1, 1), LocalDate.of(2023, 12, 31))
        );
    }
}

