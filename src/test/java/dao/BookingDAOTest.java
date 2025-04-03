package dao;

import org.com.dao.BookingDAO;
import org.com.database.DatabaseConnection;
import org.com.models.Booking;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BookingDAOTest {
    private Connection connection;
    private BookingDAO bookingDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        bookingDAO = new BookingDAO(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testBookTicket() throws SQLException {
        boolean result = bookingDAO.bookTicket(1, 1, 2, "2023-10-10 10:00:00");
        assertTrue(result, "Booking should be successful");
    }

    @Test
    public void testGetBookingsByUserId() throws SQLException {
        List<Booking> bookings = bookingDAO.getBookingsByUserId(1);
        assertNotNull(bookings, "Bookings should not be null");
        assertFalse(bookings.isEmpty(), "Bookings list should not be empty");
    }

    @Test
    public void testDeleteBooking() throws SQLException {
        boolean result = bookingDAO.deleteBooking(1);
        assertTrue(result, "Booking should be deleted successfully");
    }
}