package models;

import org.com.models.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        int id = 1;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";

        User user = new User(id, name, email, password);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
    }

    @Test
    public void testUserSetters() {
        User user = new User(0, "", "", "");

        int id = 1;

        user.setId(id);

        assertEquals(id, user.getId());
    }

    @Test
    public void testToString() {
        int id = 1;
        String name = "John Doe";
        String email = "john.doe@example.com";
        String password = "password123";

        User user = new User(id, name, email, password);

        String expectedToString = "User{id=1, contact='John Doe (john.doe@example.com)', role=''}";
        assertEquals(expectedToString, user.toString());
    }
}