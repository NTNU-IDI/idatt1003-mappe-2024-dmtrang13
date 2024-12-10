package edu.ntnu.iir.bidata.inputvalidator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.Scanner;


@ExtendWith(MockitoExtension.class)
class InputValidatorTest {

    @Mock
    private Scanner mockScanner;
    private InputValidator inputValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        inputValidator = new InputValidator(mockScanner);
    }

    @Test
    void testGetValidInt_ValidInput() {
        // Arrange
        when(mockScanner.nextLine()).thenReturn("5");

        // Act
        int result = inputValidator.getValidInt("Enter an integer:");

        // Assert
        assertEquals(5, result);
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetValidInt_InvalidThenValidInput() {
        // Arrange
        when(mockScanner.nextLine()).thenReturn("abc", "5");

        // Act
        int result = inputValidator.getValidInt("Enter an integer:");

        // Assert
        assertEquals(5, result);
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetValidDouble_ValidInput() {
        // Arrange
        when(mockScanner.nextLine()).thenReturn("5.5");

        // Act
        double result = inputValidator.getValidDouble("Enter a double:");

        // Assert
        assertEquals(5.5, result, 0.0001);
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetValidDouble_InvalidThenValidInput() {
        // Arrange
        when(mockScanner.nextLine()).thenReturn("abc", "5.5");

        // Act
        double result = inputValidator.getValidDouble("Enter a double:");

        // Assert
        assertEquals(5.5, result, 0.0001);
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetNonEmptyString_ValidInput() {
        // Arrange
        when(mockScanner.nextLine()).thenReturn("Hello");

        // Act
        String result = inputValidator.getNonEmptyString("Enter a string:");

        // Assert
        assertEquals("Hello", result);
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetNonEmptyString_EmptyThenValidInput() {
        // Arrange
        when(mockScanner.nextLine()).thenReturn("", "Hello");

        // Act
        String result = inputValidator.getNonEmptyString("Enter a string:");

        // Assert
        assertEquals("Hello", result);
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_ValidInput() {
        // Arrange
        when(mockScanner.nextLine())
                .thenReturn("2023") // Year
                .thenReturn("12")   // Month
                .thenReturn("25");  // Day

        // Act
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");

        // Assert
        assertEquals(LocalDate.of(2023, 12, 25), result);
        verify(mockScanner, times(3)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_InvalidInputSequence() {
        // Arrange
        when(mockScanner.nextLine())
                .thenReturn("abc")  // Invalid year
                .thenReturn("2023") // Valid year
                .thenReturn("12")   // Valid month
                .thenReturn("25");  // Valid day

        // Act
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");

        // Assert
        assertEquals(LocalDate.of(2023, 12, 25), result);
        verify(mockScanner, times(4)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_FullInvalidInputs() {
        // Arrange
        when(mockScanner.nextLine())
                .thenReturn("abcd") // Invalid year
                .thenReturn("99")   // Invalid year again
                .thenReturn("12")   // Valid month
                .thenReturn("32");  // Invalid day

        // Act
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");

        // Assert
        assertNull(result, "Expected null due to repeated invalid inputs.");
        verify(mockScanner, times(4)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_FebruaryLeapYear() {
        // Arrange
        when(mockScanner.nextLine())
                .thenReturn("2024") // Leap year
                .thenReturn("2")    // February
                .thenReturn("29");  // Valid leap day

        // Act
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");

        // Assert
        assertEquals(LocalDate.of(2024, 2, 29), result);
        verify(mockScanner, times(3)).nextLine();
    }
}
