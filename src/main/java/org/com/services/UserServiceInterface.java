package org.com.services;

import org.com.models.User;

public interface UserServiceInterface {
    boolean registerUser(User user);
    User loginUser(String email, String password);
}
