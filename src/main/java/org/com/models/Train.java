package org.com.models;

public class Train {
    private int id;
    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private int distanceKm;
    private int totalSeats;

    // Constructor
    public Train(int id, String trainName, String departureTime, String arrivalTime, int distanceKm, int totalSeats) {
        this.id = id;
        this.trainName = trainName;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.distanceKm = distanceKm;
        this.totalSeats = totalSeats;
    }
}