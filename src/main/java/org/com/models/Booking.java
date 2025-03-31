package org.com.models;

public class Booking {
    private int id;
    private int userId;
    private int trainId;
    private int seatsBooked;
    private String bookingTime;

    public Booking(int id, int userId, int trainId, int seatsBooked, String bookingTime) {
        this.id = id;
        this.userId = userId;
        this.trainId = trainId;
        this.seatsBooked = seatsBooked;
        this.bookingTime = bookingTime;
    }

    public int getId() { return id; }
    public int getUserId() { return userId; }
    public int getTrainId() { return trainId; }
    public int getSeatsBooked() { return seatsBooked; }
    public String getBookingTime() { return bookingTime; }
}
