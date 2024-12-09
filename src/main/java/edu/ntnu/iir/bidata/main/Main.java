package edu.ntnu.iir.bidata.main;

import edu.ntnu.iir.bidata.ui.UserInterface;
import edu.ntnu.iir.bidata.inputvalidator.InputValidator;
import edu.ntnu.iir.bidata.foodhandling.ClientCookbook;
import edu.ntnu.iir.bidata.foodhandling.ClientStorage;
import edu.ntnu.iir.bidata.model.Cookbook;
import edu.ntnu.iir.bidata.model.Storage;

import java.util.Scanner;

/**
 * The Main class serves as the entry point for the Recipe & Storage application.
 * This refactored version allows for dependency injection, making the application
 * more modular and testable.
 */
public class Main {

    private final ClientStorage clientStorage;
    private final ClientCookbook clientCookbook;
    private final UserInterface userInterface;

    /**
     * Constructs the Main class with the required dependencies.
     * This constructor facilitates dependency injection for testing purposes.
     *
     * @param clientStorage  the client responsible for managing storage operations
     * @param clientCookbook the client responsible for managing cookbook operations
     * @param userInterface  the user interface for interacting with the application
     */
    public Main(ClientStorage clientStorage, ClientCookbook clientCookbook, UserInterface userInterface) {
        this.clientStorage = clientStorage;
        this.clientCookbook = clientCookbook;
        this.userInterface = userInterface;
    }

    /**
     * The main method initializes the application components and starts the application.
     * Uses default implementations for production.
     *
     * @param args the command-line arguments, not used in this application
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator(scanner);
        Storage storage = ClientStorage.init();
        Cookbook cookbook = ClientCookbook.init();

        // Create the user interface with default dependencies
        UserInterface ui = new UserInterface(new ClientStorage(), new ClientCookbook(), storage, cookbook, inputValidator);

        // Start the application
        new Main(new ClientStorage(), new ClientCookbook(), ui).start();
    }

    /**
     * Starts the application by invoking the user interface's start method.
     * This method is the entry point for the UI-driven interaction.
     */
    public void start() {
        userInterface.start();
    }
}
