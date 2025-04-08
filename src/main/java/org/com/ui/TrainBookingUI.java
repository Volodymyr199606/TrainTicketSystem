package org.com.ui;

import org.com.models.Train;
import org.com.models.User;
import org.com.services.BookingService;
import org.com.services.TrainService;
import org.com.services.UserServiceInterface;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class TrainBookingUI {
    private final UserServiceInterface userService;
    private final TrainService trainService;
    private final BookingService bookingService;
    private User loggedInUser;

    public TrainBookingUI(UserServiceInterface userService, TrainService trainService, BookingService bookingService) {
        this.userService = userService;
        this.trainService = trainService;
        this.bookingService = bookingService;
    }

    public void startApp() {
        while (loggedInUser == null) {
            String[] options = {"Login", "Register", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome to the Train Booking System!",
                    "Main Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[0]);

            if (choice == 0) {
                handleLogin();
            } else if (choice == 1) {
                handleRegistration();
            } else {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                System.exit(0);
            }
        }

        showUserMenu();
    }

    private void showUserMenu() {
        while (true) {
            String[] options = {"Book Train Ticket", "Logout", "Exit"};
            int choice = JOptionPane.showOptionDialog(null, "Welcome, " + loggedInUser.getName() + "!",
                    "User Menu", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null,
                    options, options[0]);

            if (choice == 0) {
                startBookingProcess();
            } else if (choice == 1) {
                loggedInUser = null;
                startApp();
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Goodbye!");
                System.exit(0);
            }
        }
    }

    private void handleLogin() {
        String email = JOptionPane.showInputDialog("Enter your email:");
        String password = JOptionPane.showInputDialog("Enter your password:");
        User user = userService.loginUser(email, password);
        if (user != null) {
            loggedInUser = user;
        } else {
            JOptionPane.showMessageDialog(null, "Login failed. Try again.");
        }
    }

    private void handleRegistration() {
        String name = JOptionPane.showInputDialog("Enter your name:");
        String email = JOptionPane.showInputDialog("Enter your email:");
        String password = JOptionPane.showInputDialog("Enter your password:");
        User user = new User(0, name, email, password);
        boolean success = userService.registerUser(user);
        if (success) {
            JOptionPane.showMessageDialog(null, "Registration successful. You can now log in.");
        } else {
            JOptionPane.showMessageDialog(null, "Registration failed. Try again.");
        }
    }

    private void startBookingProcess() {
        try {
            String[] trains = {"Hogwarts Express", "Azkaban Express", "Knight Bus", "Thestral Rider"};
            String trainName = (String) JOptionPane.showInputDialog(null, "Select Train:", "Train Selection",
                    JOptionPane.QUESTION_MESSAGE, null, trains, trains[0]);

            String[] departureTimes = {"08:00", "10:30", "13:00", "15:45", "18:00"};
            String departureTime = (String) JOptionPane.showInputDialog(null, "Select Departure Time (24h format):",
                    "Departure Time", JOptionPane.QUESTION_MESSAGE, null, departureTimes, departureTimes[0]);

            String arrivalTime = calculateArrivalTime(departureTime, new Random().nextInt(3) + 1);

            int distance = new Random().nextInt(300) + 100;

            Integer[] seatOptions = {1, 2, 3, 4, 5};
            int seatCount = (Integer) JOptionPane.showInputDialog(null, "Select Number of Seats:", "Seat Selection",
                    JOptionPane.QUESTION_MESSAGE, null, seatOptions, seatOptions[0]);

            LocalDateTime bookingTime = LocalDateTime.now();
            String formattedBookingTime = bookingTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

            // Register train
            Train train = new Train(0, trainName, departureTime, arrivalTime, distance, 100);
            boolean trainRegistered = trainService.registerTrain(train);
            if (!trainRegistered) throw new RuntimeException("Train registration failed.");

            // Book ticket
            boolean bookingSuccess = bookingService.bookTicket(loggedInUser.getId(), train.getId(), seatCount, formattedBookingTime);
            if (!bookingSuccess) throw new RuntimeException("Booking failed.");

            // Show confirmation
            JOptionPane.showMessageDialog(null,
                    "Please review your booking:\n\n" +
                            "Name: " + loggedInUser.getName() + "\n" +
                            "Email: " + loggedInUser.getEmail() + "\n" +
                            "Train: " + trainName + "\n" +
                            "Departure: " + departureTime + "\n" +
                            "Arrival: " + arrivalTime + "\n" +
                            "Distance: " + distance + " km\n" +
                            "Seats: " + seatCount + "\n" +
                            "Booking Time: " + formattedBookingTime,
                    "Booking Confirmation",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String calculateArrivalTime(String departure, int hoursToAdd) {
        String[] parts = departure.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);
        LocalDateTime depTime = LocalDateTime.now().withHour(hour).withMinute(minute).withSecond(0);
        return depTime.plusHours(hoursToAdd).format(DateTimeFormatter.ofPattern("HH:mm"));
    }
}
