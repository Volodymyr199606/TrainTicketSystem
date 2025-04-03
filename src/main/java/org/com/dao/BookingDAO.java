package org.com.dao;

import org.com.models.Booking;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    private final Connection conn;

    public BookingDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean bookTicket(int userId, int trainId, int seatCount, String bookingTime) {
        String query = "INSERT INTO bookings (user_id, train_id, seat_count, booking_time) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, trainId);
            stmt.setInt(3, seatCount);
            stmt.setString(4, bookingTime);

            int result = stmt.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            System.out.println("Booking failed: " + e.getMessage());
            return false;
        }
    }


    public List<Booking> getBookingsByUserId(int userId) throws SQLException {
        List<Booking> bookings = new ArrayList<>();
        String query = "SELECT * FROM bookings WHERE user_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                bookings.add(new Booking(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("train_id"),
                        rs.getInt("seats_booked"),
                        rs.getTimestamp("booking_time").toString()
                ));
            }
        }
        return bookings;
    }


    public boolean deleteBooking(int bookingId) throws SQLException {
        String query = "DELETE FROM bookings WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookingId);
            return stmt.executeUpdate() > 0;
        }
    }

}
