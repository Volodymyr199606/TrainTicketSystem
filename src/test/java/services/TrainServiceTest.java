package services;

import org.com.dao.TrainDAO;
import org.com.models.Train;
import org.com.services.TrainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrainServiceTest {
    private TrainDAO trainDAO;
    private TrainService trainService;

    @BeforeEach
    void setUp() {
        trainDAO = mock(TrainDAO.class, withSettings().withoutAnnotations());
        trainService = new TrainService(trainDAO);
    }

    @Test
    void testRegisterTrain() throws SQLException {
        Train train = new Train(1, "Name", "Source", "Destination", 1000, 1200);
        when(trainDAO.addTrain(train)).thenReturn(true);
        assertTrue(trainService.registerTrain(train));
    }

    @Test
    void testGetAllTrains() throws SQLException {
        when(trainDAO.getTrains()).thenReturn(List.of());
        List<Train> result = trainService.getAllTrains();
        assertNotNull(result);
    }
}
