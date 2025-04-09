package org.com;

import org.com.database.DatabaseConnection;
import org.com.services.BookingService;
import org.com.services.TrainService;
import org.com.services.UserService;
import org.com.services.UserServiceInterface;
import org.com.ui.TrainBookingUI;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            UserServiceInterface userService = new UserService(conn);
            TrainService trainService = new TrainService(conn);
            BookingService bookingService = new BookingService(conn);

            TrainBookingUI bookingUI = new TrainBookingUI(userService, trainService, bookingService);
            bookingUI.startApp();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
