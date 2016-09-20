package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * GKislin
 * 15.06.2015.
 */
public interface MealService {
    List<MealWithExceed> getFilteredMeals(int userId, int caloriesPerDay, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);

    Meal getMeal(int userId, int mealId);

    Meal removeMeal(int userId, int mealId);

    Meal saveMeal(int userId, Meal meal);

    Meal updateMeal(int userId, Meal meal);
}
