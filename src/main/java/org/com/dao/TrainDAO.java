package org.com.dao;

import org.com.models.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrainDAO {
    private final Connection conn;

    public TrainDAO(Connection conn) {
        this.conn = conn;
    }

    // ✅ Save train and return generated ID
    public boolean addTrain(Train train) throws SQLException {
        String query = "INSERT INTO trains (train_name, departure_time, arrival_time, distance_km, total_seats) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, train.getTrainName());
            stmt.setString(2, train.getDepartureTime());
            stmt.setString(3, train.getArrivalTime());
            stmt.setInt(4, train.getDistanceKm());
            stmt.setInt(5, train.getTotalSeats());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    // set the generated ID back into the train object
                    int generatedId = rs.getInt(1);
                    train.setId(generatedId);
                    return true;
                }
            }
        }
        return false;
    }

    public List<Train> getTrains() throws SQLException {
        List<Train> trains = new ArrayList<>();
        String query = "SELECT * FROM trains";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                trains.add(new Train(
                        rs.getInt("id"),
                        rs.getString("train_name"),
                        rs.getString("departure_time"),
                        rs.getString("arrival_time"),
                        rs.getInt("distance_km"),
                        rs.getInt("total_seats")
                ));
            }
        }
        return trains;
    }
}
