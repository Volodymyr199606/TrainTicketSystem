package dao;

import org.com.dao.TrainDAO;
import org.com.database.DatabaseConnection;
import org.com.models.Train;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainDAOTest {
    private Connection connection;
    private TrainDAO trainDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        trainDAO = new TrainDAO(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testAddTrain() throws SQLException {
        Train train = new Train(0, "Express", "2023-10-10 10:00:00", "2023-10-10 14:00:00", 300, 200);

        boolean result = trainDAO.addTrain(train);
        assertTrue(result, "Train should be added successfully");
        assertNotNull(train.getId(), "Generated ID should not be null");
    }

    @Test
    public void testGetTrains() throws SQLException {
        List<Train> trains = trainDAO.getTrains();
        assertNotNull(trains, "Trains list should not be null");
        assertFalse(trains.isEmpty(), "Trains list should not be empty");
    }
}