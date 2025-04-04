package org.com.services;

import org.com.dao.BookingDAO;
import org.com.models.Booking;
import java.sql.Connection;
import java.util.List;

public class BookingService {
    private final BookingDAO bookingDAO;

    public BookingService(Connection conn) {
        this.bookingDAO = new BookingDAO(conn);
    }

    public boolean bookTicket(int userId, int trainId, int seatCount, String bookingTime) {
        return bookingDAO.bookTicket(userId, trainId, seatCount, bookingTime);
    }

    public List<Booking> getBookingHistory(int userId) {
        try {
            return bookingDAO.getBookingsByUserId(userId);
        } catch (Exception e) {
            System.out.println("Error retrieving booking history: " + e.getMessage());
            return null;
        }
    }

    public boolean cancelBooking(int bookingId) {
        try {
            return bookingDAO.deleteBooking(bookingId);
        } catch (Exception e) {
            System.out.println("Error canceling booking: " + e.getMessage());
            return false;
        }
    }
}


