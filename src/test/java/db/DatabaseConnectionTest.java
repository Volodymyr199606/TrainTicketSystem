package db;

import org.com.database.DatabaseConnection;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DatabaseConnectionTest {

    @Test
    public void testGetConnection() throws SQLException {

        assertNotNull(System.getenv("DB_URL"), "DB_URL environment variable is not set");
        assertNotNull(System.getenv("DB_USER"), "DB_USER environment variable is not set");
        assertNotNull(System.getenv("DB_PASSWORD"), "DB_PASSWORD environment variable is not set");

        Connection connection = DatabaseConnection.getConnection();
        assertNotNull(connection, "Connection should not be null");
    }
}