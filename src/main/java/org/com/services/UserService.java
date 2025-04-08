package org.com.services;

import org.com.dao.UserDAO;
import org.com.models.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserService implements UserServiceInterface {
    private final UserDAO userDAO;

    public UserService(Connection conn) {
        this.userDAO = new UserDAO(conn);
    }

    @Override
    public boolean registerUser(User user) {
        try {
            return userDAO.registerUser(user);
        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    @Override
    public User loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }
}
