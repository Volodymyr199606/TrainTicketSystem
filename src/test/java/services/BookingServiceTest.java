package services;

import org.com.dao.BookingDAO;
import org.com.models.Booking;
import org.com.services.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {
    private BookingDAO bookingDAO;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        bookingDAO = mock(BookingDAO.class, withSettings().withoutAnnotations());
        bookingService = new BookingService(bookingDAO);
    }

    @Test
    void testBookTicket() {
        when(bookingDAO.bookTicket(1, 1, 2, "2025-04-01")).thenReturn(true);
        assertTrue(bookingService.bookTicket(1, 1, 2, "2025-04-01"));
    }

    @Test
    void testCancelBooking() throws SQLException {
        when(bookingDAO.deleteBooking(10)).thenReturn(true);
        assertTrue(bookingService.cancelBooking(10));
    }

    @Test
    void testGetBookingHistory() throws SQLException {
        when(bookingDAO.getBookingsByUserId(1)).thenReturn(List.of());
        List<Booking> bookings = bookingService.getBookingHistory(1);
        assertNotNull(bookings);
    }
}
