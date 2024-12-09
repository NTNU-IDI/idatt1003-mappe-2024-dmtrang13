package edu.ntnu.iir.bidata.ui;

import edu.ntnu.iir.bidata.inputvalidator.InputValidator;
import edu.ntnu.iir.bidata.foodhandling.ClientCookbook;
import edu.ntnu.iir.bidata.foodhandling.ClientStorage;
import edu.ntnu.iir.bidata.model.Cookbook;
import edu.ntnu.iir.bidata.model.Storage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;


class UserInterfaceTest {

    private ClientStorage clientStorage;
    private ClientCookbook clientCookbook;
    private Storage storage;
    private Cookbook cookbook;
    private InputValidator inputValidator;
    private UserInterface userInterface;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Initialize dependencies
        storage = new Storage();
        cookbook = new Cookbook();
        clientStorage = new ClientStorage();
        clientCookbook = new ClientCookbook();

        // Redirect system output for testing
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testStart_ExitImmediately() {
        // Simulate input for "Exit Application" option
        simulateUserInput("3\n");

        inputValidator = new InputValidator(new Scanner(System.in));
        userInterface = new UserInterface(clientStorage, clientCookbook, storage, cookbook, inputValidator);

        // Run the application
        userInterface.start();

        // Verify output
        String output = outputStream.toString();
        assertTrue(output.contains("Exiting application... Goodbye!"));
    }

    @Test
    void testStart_ManageStorageAndExit() {
        // Simulate input: Manage Storage -> Return to Main Menu -> Exit Application
        simulateUserInput("1\n8\n3\n");

        inputValidator = new InputValidator(new Scanner(System.in));
        userInterface = new UserInterface(clientStorage, clientCookbook, storage, cookbook, inputValidator);

        // Run the application
        userInterface.start();

        // Verify output
        String output = outputStream.toString();
        assertTrue(output.contains("Storage Menu"));
        assertTrue(output.contains("Returning to Main Menu..."));
        assertTrue(output.contains("Exiting application... Goodbye!"));
    }

    @Test
    void testStart_ManageCookbookAndExit() {
        // Simulate input: Manage Cookbook -> Return to Main Menu -> Exit Application
        simulateUserInput("2\n7\n3\n");

        inputValidator = new InputValidator(new Scanner(System.in));
        userInterface = new UserInterface(clientStorage, clientCookbook, storage, cookbook, inputValidator);

        // Run the application
        userInterface.start();

        // Verify output
        String output = outputStream.toString();
        assertTrue(output.contains("Cookbook Menu"));
        assertTrue(output.contains("Returning to Main Menu..."));
        assertTrue(output.contains("Exiting application... Goodbye!"));
    }

    @Test
    void testManageStorage_ViewAllIngredients() {
        // Simulate input: Manage Storage -> View All Ingredients -> Return to Main Menu -> Exit Application
        simulateUserInput("1\n1\n8\n3\n");

        inputValidator = new InputValidator(new Scanner(System.in));
        userInterface = new UserInterface(clientStorage, clientCookbook, storage, cookbook, inputValidator);

        userInterface.start();

        String output = outputStream.toString();
        if (storage.getIngredients().isEmpty()) {
            assertTrue(output.contains("Storage is empty."));
        } else {
            assertTrue(output.contains("All the ingredients in storage:"));
        }
    }

    @Test
    void testManageCookbook_ViewAllRecipes() {
        // Simulate input: Manage Cookbook -> View All Recipes -> Return to Main Menu -> Exit Application
        simulateUserInput("2\n1\n7\n3\n");

        inputValidator = new InputValidator(new Scanner(System.in));
        userInterface = new UserInterface(clientStorage, clientCookbook, storage, cookbook, inputValidator);

        // Run the application
        userInterface.start();

        // Verify output
        String output = outputStream.toString();
        assertTrue(output.contains("No recipes found in the cookbook."));
        assertTrue(output.contains("Returning to Main Menu..."));
        assertTrue(output.contains("Exiting application... Goodbye!"));
    }

    /**
     * Utility method to simulate user input.
     * @param input the input to simulate
     */
    private void simulateUserInput(String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);
    }
}
