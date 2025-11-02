package com.fitnessapp.repository;

import com.fitnessapp.model.CustomGoal;
import com.fitnessapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomGoalRepository extends JpaRepository<CustomGoal, Long> {
    List<CustomGoal> findByUser(User user);
    List<CustomGoal> findByUserId(Long userId);
    List<CustomGoal> findByUserIdAndDate(Long userId, LocalDate date);

}
