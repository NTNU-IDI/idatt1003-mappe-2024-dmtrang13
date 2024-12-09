package edu.ntnu.iir.bidata.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;


class IngredientTest {
    private Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient("Tomato", 5.0, "kg", LocalDate.of(2023, 12, 31), 50.0);
    }

    @Test
    void testGetIngredientName() {
        // Act
        String name = ingredient.getIngredientName();

        // Assert
        assertEquals("Tomato", name);
    }

    @Test
    void testGetIngredientAmount() {
        // Act
        double amount = ingredient.getIngredientAmount();

        // Assert
        assertEquals(5.0, amount);
    }

    @Test
    void testSetIngredientAmount() {
        // Act
        ingredient.setIngredientAmount(10.0);

        // Assert
        assertEquals(10.0, ingredient.getIngredientAmount());
    }

    @Test
    void testGetIngredientMeasurement() {
        // Act
        String measurement = ingredient.getIngredientMeasurement();

        // Assert
        assertEquals("kg", measurement);
    }

    @Test
    void testSetIngredientMeasurement() {
        // Act
        ingredient.setIngredientMeasurement("g");

        // Assert
        assertEquals("g", ingredient.getIngredientMeasurement());
    }

    @Test
    void testGetExpireDate() {
        // Act
        LocalDate expireDate = ingredient.getExpireDate();

        // Assert
        assertEquals(LocalDate.of(2023, 12, 31), expireDate);
    }

    @Test
    void testSetExpireDate() {
        // Act
        ingredient.setExpireDate(LocalDate.of(2024, 1, 15));

        // Assert
        assertEquals(LocalDate.of(2024, 1, 15), ingredient.getExpireDate());
    }

    @Test
    void testGetIngredientPrice() {
        // Act
        double price = ingredient.getIngredientPrice();

        // Assert
        assertEquals(50.0, price);
    }

    @Test
    void testSetIngredientPrice() {
        // Act
        ingredient.setIngredientPrice(60.0);

        // Assert
        assertEquals(60.0, ingredient.getIngredientPrice());
    }

    @Test
    void testToString() {
        // Act
        String ingredientString = ingredient.toString();

        // Assert
        String expectedString = "Ingredient: Tomato 5.0 kg\n" +
                "Expire date: 2023-12-31\n" +
                "Price: 50.0 kr\n";
        assertEquals(expectedString, ingredientString);
    }

    @Test
    void testCompareTo_SameNameEarlierDate() {
        // Arrange
        Ingredient other = new Ingredient("Tomato", 10.0, "kg", LocalDate.of(2023, 12, 15), 100.0);

        // Act
        int comparison = ingredient.compareTo(other);

        // Assert
        assertTrue(comparison > 0); // `ingredient` has a later expiration date
    }

    @Test
    void testCompareTo_SameNameSameDate() {
        // Arrange
        Ingredient other = new Ingredient("Tomato", 10.0, "kg", LocalDate.of(2023, 12, 31), 100.0);

        // Act
        int comparison = ingredient.compareTo(other);

        // Assert
        assertEquals(0, comparison); // Both ingredients are equal by name and date
    }

    @Test
    void testCompareTo_DifferentName() {
        // Arrange
        Ingredient other = new Ingredient("Apple", 10.0, "kg", LocalDate.of(2023, 12, 31), 100.0);

        // Act
        int comparison = ingredient.compareTo(other);

        // Assert
        assertTrue(comparison > 0); // `ingredient` ("Tomato") is lexicographically greater than "Apple"
    }
}