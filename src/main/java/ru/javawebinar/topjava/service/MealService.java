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
    List<MealWithExceed> getFiltered(int userId, int caloriesPerDay, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);

    Meal get(int userId, int mealId);

    Meal remove(int userId, int mealId);

    Meal save(int userId, Meal meal);

    Meal update(int userId, Meal meal);
}
