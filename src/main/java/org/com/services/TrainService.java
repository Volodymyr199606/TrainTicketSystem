package org.com.services;

import org.com.dao.TrainDAO;
import org.com.models.Train;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TrainService {
    private final TrainDAO trainDAO;

    public TrainService(Connection conn) {
        this.trainDAO = new TrainDAO(conn);
    }

    public boolean registerTrain(Train train) throws SQLException {
        return trainDAO.addTrain(train);
    }

    public List<Train> getAllTrains() throws SQLException {
        return trainDAO.getTrains();
    }
}
