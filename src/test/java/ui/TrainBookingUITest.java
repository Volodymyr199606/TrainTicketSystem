package ui;

import org.com.models.Train;
import org.com.models.User;
import org.com.services.BookingService;
import org.com.services.TrainService;
import org.com.services.UserServiceInterface;
import org.com.ui.TrainBookingUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;

import javax.swing.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TrainBookingUITest {
    private UserServiceInterface userService;
    private TrainService trainService;
    private BookingService bookingService;
    private TrainBookingUI trainBookingUI;

    @BeforeEach
    public void setUp() throws Exception {
        userService = mock(UserServiceInterface.class);
        trainService = mock(TrainService.class);
        bookingService = mock(BookingService.class);
        trainBookingUI = new TrainBookingUI(userService, trainService, bookingService);
        User loggedInUser = new User(1, "John Doe", "john.doe@example.com", "password");

        // Use reflection to set the private field 'loggedInUser'
        Field loggedInUserField = TrainBookingUI.class.getDeclaredField("loggedInUser");
        loggedInUserField.setAccessible(true);
        loggedInUserField.set(trainBookingUI, loggedInUser);
    }

    @Test
    public void testStartBookingProcess() throws Exception {

        String trainName = "Hogwarts Express";
        String departureTime = "08:00";
        int seatCount = 2;
        LocalDateTime bookingTime = LocalDateTime.now();
        String formattedBookingTime = bookingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        try (MockedStatic<JOptionPane> mockedJOptionPane = mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(null, "Select Train:", "Train Selection", JOptionPane.QUESTION_MESSAGE, null, new Object[]{trainName}, trainName))
                    .thenReturn(trainName);
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(null, "Select Departure Time (24h format):", "Departure Time", JOptionPane.QUESTION_MESSAGE, null, new Object[]{departureTime}, departureTime))
                    .thenReturn(departureTime);
            mockedJOptionPane.when(() -> JOptionPane.showInputDialog(null, "Select Number of Seats:", "Seat Selection", JOptionPane.QUESTION_MESSAGE, null, new Object[]{seatCount}, seatCount))
                    .thenReturn(seatCount);

            when(trainService.registerTrain(any(Train.class))).thenReturn(true);
            when(bookingService.bookTicket(anyInt(), anyInt(), anyInt(), anyString())).thenReturn(true);

            Method startBookingProcessMethod = TrainBookingUI.class.getDeclaredMethod("startBookingProcess");
            startBookingProcessMethod.setAccessible(true);
            startBookingProcessMethod.invoke(trainBookingUI);

            ArgumentCaptor<Train> trainCaptor = ArgumentCaptor.forClass(Train.class);
            verify(trainService).registerTrain(trainCaptor.capture());
            Train registeredTrain = trainCaptor.getValue();
            assertEquals(trainName, registeredTrain.getName());
            assertEquals(departureTime, registeredTrain.getDepartureTime());

            verify(bookingService).bookTicket(eq(1), eq(registeredTrain.getId()), eq(seatCount), eq(formattedBookingTime));
        }
    }
}