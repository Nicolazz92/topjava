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
    List<MealWithExceed> getFilteredMeals(int authorizedUserId, int caloriesPerDay, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);

    Meal getMeal(int authorizedUserId, int mealId);

    Meal removeMeal(int authorizedUserId, int mealId);

    Meal saveMeal(int authorizedUserId, Meal meal);

    Meal updateMeal(int authorizedUserId, Meal meal);
}
