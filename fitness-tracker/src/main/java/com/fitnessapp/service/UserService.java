package com.fitnessapp.service;

import com.fitnessapp.model.User;
import java.util.List;

public interface UserService {

    User registerUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    User updateUser(Long id, User updatedUser);

    void deleteUser(Long id);

	User getUserByEmail(String email);
}
