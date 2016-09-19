package ru.javawebinar.topjava.web.meal;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Created by nicolas on 18.09.2016.
 */
public interface MealRestController {
    List<MealWithExceed> getMeals();

    List<MealWithExceed> getFilteredMeals(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime);

    Meal getMeal(int mealId);

    Meal removeMeal(int mealId);

    void saveMeal(Meal meal);

    void updateMeal(Meal meal);
}
