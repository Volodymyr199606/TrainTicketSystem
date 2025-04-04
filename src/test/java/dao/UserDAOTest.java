package dao;

import org.com.dao.UserDAO;
import org.com.database.DatabaseConnection;
import org.com.models.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private Connection connection;
    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        userDAO = new UserDAO(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testRegisterUser() throws SQLException {
        User user = new User(0, "John Doe", "john.doe@example.com", "password123");

        boolean result = userDAO.registerUser(user);
        assertTrue(result, "User should be registered successfully");
        assertTrue(user.getId() > 0, "Generated ID should be greater than 0");
    }

    @Test
    public void testLoginUser() {
        User user = userDAO.loginUser("john.doe@example.com", "password123");
        assertNotNull(user, "User should be logged in successfully");
        assertEquals("John Doe", user.getName(), "User name should match");
    }
}