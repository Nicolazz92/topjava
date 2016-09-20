package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.Collection;

/**
 * GKislin
 * 06.03.2015.
 */
public interface MealRepository {
    Meal save(Meal Meal, int userId);

    Meal update(Meal Meal, int userId);

    Meal remove(int id, int userId);

    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getFilteredByDate(int userId, LocalDate startDate, LocalDate endDate);
}
