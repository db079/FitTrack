package com.fitnessapp.service;

import com.fitnessapp.dto.DailyCaloriesDTO;
import com.fitnessapp.dto.GoalHistoryDTO;
import java.util.List;

public interface HistoryService {
    List<GoalHistoryDTO> getUserHistory(Long userId);
    List<DailyCaloriesDTO> getDailyCalories(Long userId);

}
