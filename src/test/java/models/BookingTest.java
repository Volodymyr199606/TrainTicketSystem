package models;

import org.com.models.Booking;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BookingTest {

    @Test
    public void testBookingConstructorAndGetters() {
        int id = 1;
        int userId = 2;
        int trainId = 3;
        int seatsBooked = 4;
        String bookingTime = "2023-10-10 10:00:00";

        Booking booking = new Booking(id, userId, trainId, seatsBooked, bookingTime);

        assertEquals(id, booking.getId());
        assertEquals(userId, booking.getUserId());
        assertEquals(trainId, booking.getTrainId());
        assertEquals(seatsBooked, booking.getSeatsBooked());
        assertEquals(bookingTime, booking.getBookingTime());
    }
}