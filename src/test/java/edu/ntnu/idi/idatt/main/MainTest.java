package edu.ntnu.idi.idatt.main;

import edu.ntnu.idi.idatt.ui.UserInterface;
import edu.ntnu.idi.idatt.foodhandling.ClientCookbook;
import edu.ntnu.idi.idatt.foodhandling.ClientStorage;
import edu.ntnu.idi.idatt.model.Cookbook;
import edu.ntnu.idi.idatt.model.Storage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

class MainTest {

    @Mock
    private ClientStorage mockClientStorage;
    @Mock
    private ClientCookbook mockClientCookbook;
    @Mock
    private Storage mockStorage;
    @Mock
    private Cookbook mockCookbook;
    @Mock
    private UserInterface mockUserInterface;

    private Main main;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inject the mocked dependencies into the Main class
        main = new Main(mockClientStorage, mockClientCookbook, mockUserInterface);
    }

    @Test
    void testMain_StartsApplication() {
        // Arrange
        doNothing().when(mockUserInterface).start();

        // Act
        main.start();

        // Assert
        verify(mockUserInterface, times(1)).start();
    }
}
