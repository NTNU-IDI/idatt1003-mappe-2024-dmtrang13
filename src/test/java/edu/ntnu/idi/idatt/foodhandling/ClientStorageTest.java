package edu.ntnu.idi.idatt.foodhandling;
import edu.ntnu.idi.idatt.inputvalidator.InputValidator;
import edu.ntnu.idi.idatt.model.Ingredient;
import edu.ntnu.idi.idatt.model.Storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@ExtendWith(MockitoExtension.class)
class ClientStorageTest {
    @Mock
    private Storage mockStorage;
    @Mock
    private InputValidator mockInputValidator;

    @Test
    void testInit() {
        // Arrange
        // No additional setup needed.

        // Act
        Storage storage = ClientStorage.init();

        // Assert
        assertNotNull(storage, "Storage should not be null after initialization.");
        assertFalse(storage.getIngredients().isEmpty(), "Storage should not be empty after initialization.");
    }

    @Test
    void testViewAllIngredients() {
        // Arrange
        when(mockStorage.getIngredients()).thenReturn(new ArrayList<>());

        // Act
        ClientStorage.viewAllIngredients(mockStorage);

        // Assert
        verify(mockStorage, times(1)).getIngredients();
    }

    @Test
    void testGetIngredientsByName() {
        // Arrange
        String ingredientName = "Milk";
        ArrayList<Ingredient> mockIngredients = new ArrayList<>(List.of(
                new Ingredient(ingredientName, 2.0, "L", LocalDate.of(2023, 11, 30), 60.0)
        ));

        when(mockInputValidator.getNonEmptyString(anyString())).thenReturn(ingredientName);
        when(mockStorage.getIngredientsByName(ingredientName)).thenReturn(mockIngredients);

        // Act
        ClientStorage.getIngredientsByName(mockStorage, mockInputValidator);

        // Assert
        verify(mockInputValidator, times(1)).getNonEmptyString(anyString());
        verify(mockStorage, times(1)).getIngredientsByName(ingredientName);
    }

    @Test
    void testAddIngredient() {
        // Arrange
        when(mockInputValidator.getNonEmptyString(anyString()))
                .thenReturn("Tomato") // First call for name
                .thenReturn("kg");    // Second call for measurement
        when(mockInputValidator.getValidInt(anyString())).thenReturn(3);
        when(mockInputValidator.getValidExpirationDate(anyString())).thenReturn(LocalDate.of(2023, 12, 31));
        when(mockInputValidator.getValidDouble(anyString())).thenReturn(20.0);

        // Act
        ClientStorage.addIngredient(mockStorage, mockInputValidator);

        // Assert
        verify(mockStorage, times(1))
                .addIngredient("Tomato", 3, "kg", LocalDate.of(2023, 12, 31), 20.0);

        // Verify input calls
        verify(mockInputValidator, times(2)).getNonEmptyString(anyString());
        verify(mockInputValidator, times(1)).getValidInt(anyString());
        verify(mockInputValidator, times(1)).getValidExpirationDate(anyString());
        verify(mockInputValidator, times(1)).getValidDouble(anyString());
    }

    @Test
    void testRemoveIngredientByNameAndAmount() {
        // Arrange
        when(mockInputValidator.getNonEmptyString(anyString())).thenReturn("Flour");
        when(mockInputValidator.getValidDouble(anyString())).thenReturn(1.0);

        // Act
        ClientStorage.removeIngredientByNameAndAmount(mockStorage, mockInputValidator);

        // Assert
        verify(mockStorage, times(1)).removeIngredientByNameAndAmount("Flour", 1.0);
    }

    @Test
    void testGetTotalPrice() {
        // Arrange
        when(mockStorage.getIngredients()).thenReturn(new ArrayList<>());

        // Act
        ClientStorage.getTotalPrice(mockStorage);

        // Assert
        verify(mockStorage, times(1)).getIngredients();
    }

    @Test
    void testGetExpiredIngredients() {
        // Arrange
        when(mockStorage.getIngredients()).thenReturn(new ArrayList<>());

        // Act
        ArrayList<Ingredient> expiredIngredients = ClientStorage.getExpiredIngredients(mockStorage);

        // Assert
        assertNotNull(expiredIngredients, "Expired ingredients list should not be null.");
        verify(mockStorage, times(1)).getIngredients();
    }

    @Test
    void testGetExpiredPrice() {
        // Arrange
        ArrayList<Ingredient> mockExpiredIngredients = new ArrayList<>(List.of(
                new Ingredient("Milk", 2.0, "L", LocalDate.now().minusDays(1), 60.0)
        ));
        when(mockStorage.getIngredients()).thenReturn(mockExpiredIngredients);

        // Act
        double expiredPrice = ClientStorage.getExpiredPrice(mockStorage);

        // Assert
        assertEquals(60.0, expiredPrice, 0.01, "Expired price should be the price of expired items.");
        verify(mockStorage, times(1)).getIngredients(); // Now it should be called only once
    }

    @Test
    void testGetIngredientsInDateInterval() {
        // Arrange
        LocalDate lowerDate = LocalDate.of(2023, 11, 1);
        LocalDate upperDate = LocalDate.of(2023, 12, 1);

        ArrayList<Ingredient> mockIngredients = new ArrayList<>(List.of(
                new Ingredient("Milk", 2.0, "L", LocalDate.of(2023, 11, 30), 60.0)
        ));

        when(mockInputValidator.getValidExpirationDate(anyString()))
                .thenReturn(lowerDate)
                .thenReturn(upperDate);

        when(mockStorage.getIngredientsInDateInterval(lowerDate, upperDate)).thenReturn(mockIngredients);

        // Act
        ArrayList<Ingredient> result = ClientStorage.getIngredientsInDateInterval(mockStorage, mockInputValidator);

        // Assert
        assertEquals(1, result.size(), "There should be one ingredient in the interval.");
        verify(mockInputValidator, times(2)).getValidExpirationDate(anyString());
        verify(mockStorage, times(1)).getIngredientsInDateInterval(lowerDate, upperDate);
    }
}
