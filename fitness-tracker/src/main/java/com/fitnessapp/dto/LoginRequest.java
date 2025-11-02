package com.fitnessapp.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for user login requests.
 */
public class LoginRequest {

    @NotBlank
    private String email;
    
    @NotBlank
    private String password;

    public String getUsername() { return email; }
    public void setUsername(String username) { this.email = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}