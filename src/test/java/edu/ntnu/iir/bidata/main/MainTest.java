package edu.ntnu.iir.bidata.main;

import edu.ntnu.iir.bidata.ui.UserInterface;
import edu.ntnu.iir.bidata.foodhandling.ClientCookbook;
import edu.ntnu.iir.bidata.foodhandling.ClientStorage;
import edu.ntnu.iir.bidata.model.Cookbook;
import edu.ntnu.iir.bidata.model.Storage;

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
