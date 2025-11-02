package com.fitnessapp.repository;

import com.fitnessapp.model.Goal;
import com.fitnessapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    List<Goal> findByUser(User user);
    List<Goal> findByUserAndDate(User user, LocalDate date);
    List<Goal> findByUserId(Long userId);
    List<Goal> findByUserIdAndDate(Long userId, LocalDate date);
}
