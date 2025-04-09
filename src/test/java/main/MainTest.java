
package main;

import org.com.Main;
import org.com.database.DatabaseConnection;
import org.com.services.BookingService;
import org.com.services.TrainService;
import org.com.services.UserService;
import org.com.services.UserServiceInterface;
import org.com.ui.TrainBookingUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.sql.Connection;

import static org.mockito.Mockito.*;

public class MainTest {

    private Connection mockConnection;
    private UserServiceInterface mockUserService;
    private TrainService mockTrainService;
    private BookingService mockBookingService;
    private TrainBookingUI mockTrainBookingUI;

    @BeforeEach
    public void setUp() {
        mockConnection = mock(Connection.class);
        mockUserService = mock(UserService.class);
        mockTrainService = mock(TrainService.class);
        mockBookingService = mock(BookingService.class);
        mockTrainBookingUI = mock(TrainBookingUI.class);
    }

    @Test
    public void testMain() throws Exception {
        try (MockedStatic<DatabaseConnection> mockedDatabaseConnection = mockStatic(DatabaseConnection.class)) {

            mockedDatabaseConnection.when(DatabaseConnection::getConnection).thenReturn(mockConnection);


            TrainBookingUI bookingUI = new TrainBookingUI(mockUserService, mockTrainService, mockBookingService);


            doNothing().when(mockTrainBookingUI).startApp();


            Main.main(new String[]{});


            verify(mockTrainBookingUI).startApp();
        }
    }
}