package com.fitnessapp.service;

import com.fitnessapp.model.Role;
import com.fitnessapp.model.User;
import com.fitnessapp.repository.RoleRepository;
import com.fitnessapp.repository.UserRepository;
import com.fitnessapp.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public String login(String email, String password) {
        // Authenticate the user using email & password
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

        // Load user details for JWT generation
        UserDetails userDetails = userDetailsService.loadUserByUsername(email);

        // Generate JWT token
        return jwtUtil.generateToken(userDetails);
    }

    @Override
    public User register(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        // Encrypt password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Assign default role: ROLE_USER
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");

        if (userRole == null) {
            userRole = new Role();
            userRole.setName("ROLE_USER");
            roleRepository.save(userRole);
        }

        roles.add(userRole);
        user.setRoles(roles);

        return userRepository.save(user);
    }
}
