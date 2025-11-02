package com.fitnessapp.service;

import com.fitnessapp.model.User;

public interface AuthService {
    User register(User user);
    String login(String email, String password);
}
