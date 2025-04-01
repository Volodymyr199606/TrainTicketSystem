package org.com.dao;

import org.com.models.Train;

import java.sql.*;

public class TrainDAO {
    private final Connection conn;

    public TrainDAO(Connection conn) {
        this.conn = conn;
    }

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

                    int generatedId = rs.getInt(1);
                    train.setId(generatedId);
                    return true;
                }
            }
        }
        return false;
    }
}
