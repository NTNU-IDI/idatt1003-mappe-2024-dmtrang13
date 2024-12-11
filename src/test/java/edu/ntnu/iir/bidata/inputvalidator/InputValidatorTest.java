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
        when(mockScanner.nextLine()).thenReturn("5");
        int result = inputValidator.getValidInt("Enter an integer:");
        assertEquals(5, result);
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetValidInt_InvalidThenValidInput() {
        when(mockScanner.nextLine()).thenReturn("abc", "5");
        int result = inputValidator.getValidInt("Enter an integer:");
        assertEquals(5, result);
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetValidDouble_ValidInput() {
        when(mockScanner.nextLine()).thenReturn("5.5");
        double result = inputValidator.getValidDouble("Enter a double:");
        assertEquals(5.5, result, 0.0001);
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetValidDouble_InvalidThenValidInput() {
        when(mockScanner.nextLine()).thenReturn("abc", "5.5");
        double result = inputValidator.getValidDouble("Enter a double:");
        assertEquals(5.5, result, 0.0001);
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetNonEmptyString_ValidInput() {
        when(mockScanner.nextLine()).thenReturn("Hello");
        String result = inputValidator.getNonEmptyString("Enter a string:");
        assertEquals("Hello", result);
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetNonEmptyString_EmptyThenValidInput() {
        when(mockScanner.nextLine()).thenReturn("", "Hello");
        String result = inputValidator.getNonEmptyString("Enter a string:");
        assertEquals("Hello", result);
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_ValidInput() {
        when(mockScanner.nextLine()).thenReturn("2023", "12", "25");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertEquals(LocalDate.of(2023, 12, 25), result);
        verify(mockScanner, times(3)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_InvalidInputSequence() {
        when(mockScanner.nextLine()).thenReturn("abc", "2023", "12", "25");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertEquals(LocalDate.of(2023, 12, 25), result);
        verify(mockScanner, times(4)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_FullInvalidInputs() {
        when(mockScanner.nextLine()).thenReturn("abcd", "99", "2023", "13", "12", "32", "25");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertEquals(LocalDate.of(2023, 12, 25), result);
        verify(mockScanner, times(7)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_UserCancels() {
        when(mockScanner.nextLine()).thenReturn("cancel");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertNull(result, "Expected null when user cancels input.");
        verify(mockScanner, times(1)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_FebruaryLeapYear() {
        when(mockScanner.nextLine()).thenReturn("2024", "2", "29");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertEquals(LocalDate.of(2024, 2, 29), result);
        verify(mockScanner, times(3)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_FebruaryNonLeapYear() {
        when(mockScanner.nextLine()).thenReturn("2023", "2", "29", "28");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertEquals(LocalDate.of(2023, 2, 28), result);
        verify(mockScanner, times(4)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_DayOutOfRange() {
        when(mockScanner.nextLine()).thenReturn("2023", "4", "31", "30");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertEquals(LocalDate.of(2023, 4, 30), result);
        verify(mockScanner, times(4)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_InvalidMonthThenValidInput() {
        when(mockScanner.nextLine()).thenReturn("2023", "13", "12", "25");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertEquals(LocalDate.of(2023, 12, 25), result);
        verify(mockScanner, times(4)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_UserCancelsAtMonth() {
        when(mockScanner.nextLine()).thenReturn("2023", "cancel");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertNull(result, "Expected null when user cancels at month input.");
        verify(mockScanner, times(2)).nextLine();
    }

    @Test
    void testGetValidExpirationDate_UserCancelsAtDay() {
        when(mockScanner.nextLine()).thenReturn("2023", "12", "cancel");
        LocalDate result = inputValidator.getValidExpirationDate("Enter an expiration date:");
        assertNull(result, "Expected null when user cancels at day input.");
        verify(mockScanner, times(3)).nextLine();
    }
}
