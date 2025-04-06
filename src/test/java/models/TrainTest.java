package models;

import org.com.models.Train;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrainTest {

    @Test
    public void testTrainConstructorAndGetters() {
        int id = 1;
        String trainName = "Express";
        String departureTime = "2023-10-10 10:00:00";
        String arrivalTime = "2023-10-10 14:00:00";
        int distanceKm = 300;
        int totalSeats = 200;

        Train train = new Train(id, trainName, departureTime, arrivalTime, distanceKm, totalSeats);

        assertEquals(id, train.getId());
        assertEquals(trainName, train.getTrainName());
        assertEquals(departureTime, train.getDepartureTime());
        assertEquals(arrivalTime, train.getArrivalTime());
        assertEquals(distanceKm, train.getDistanceKm());
        assertEquals(totalSeats, train.getTotalSeats());
    }

    @Test
    public void testTrainSetters() {
        Train train = new Train(0, "", "", "", 0, 0);

        int id = 1;
        String trainName = "Express";
        String departureTime = "2023-10-10 10:00:00";
        String arrivalTime = "2023-10-10 14:00:00";
        int distanceKm = 300;
        int totalSeats = 200;

        train.setId(id);
        train.setTrainName(trainName);
        train.setDepartureTime(departureTime);
        train.setArrivalTime(arrivalTime);
        train.setDistanceKm(distanceKm);
        train.setTotalSeats(totalSeats);

        assertEquals(id, train.getId());
        assertEquals(trainName, train.getTrainName());
        assertEquals(departureTime, train.getDepartureTime());
        assertEquals(arrivalTime, train.getArrivalTime());
        assertEquals(distanceKm, train.getDistanceKm());
        assertEquals(totalSeats, train.getTotalSeats());
    }
}