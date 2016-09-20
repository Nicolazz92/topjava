package ru.javawebinar.topjava.service;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.javawebinar.topjava.util.exception.ExceptionUtil.checkNotFound;
import static ru.javawebinar.topjava.util.exception.ExceptionUtil.checkNotFoundWithId;

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    private static final Logger LOG = getLogger(MealServiceImpl.class);

    private MealRepository repository;

    @Override
    public List<MealWithExceed> getFilteredMeals(int userId, int caloriesPerDay, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LOG.info("getFilteredMeals for user {}, parameters: {}, {}, {}, {}, {}", userId, caloriesPerDay, startDate, startTime, endDate, endTime);
        return MealsUtil.getFilteredWithExceeded(repository.getFilteredByDate(userId, startDate, endDate), startTime, endTime, caloriesPerDay)
                .stream()
                .filter(mealWithExceed -> mealWithExceed.getDateTime().toLocalTime().isAfter(startTime) &&
                        mealWithExceed.getDateTime().toLocalTime().isBefore(endTime))
                .collect(Collectors.toList());
    }

    @Override
    public Meal getMeal(int userId, int mealId) throws NotFoundException {
        LOG.info("getMeal for user {}, mealId = {}", userId, mealId);
        return checkNotFoundWithId(repository.get(mealId, userId), mealId);
    }

    @Override
    public Meal removeMeal(int userId, int mealId) throws NotFoundException {
        LOG.info("removeMeal for user {}, mealId = {}", userId, mealId);
        return checkNotFoundWithId(repository.remove(mealId, userId), mealId);
    }

    @Override
    public Meal saveMeal(int userId, Meal meal) throws NotFoundException {
        LOG.info("saveMeal for user {}, mealId = {}", userId, meal.getId());
        return checkNotFound(repository.save(meal, userId), "userId=" + userId);
    }

    @Override
    public Meal updateMeal(int userId, Meal meal) throws NotFoundException {
        LOG.info("updateMeal for user {}, mealId = {}", userId, meal.getId());
        return checkNotFound(repository.update(meal, userId), "userId=" + userId);
    }
}
