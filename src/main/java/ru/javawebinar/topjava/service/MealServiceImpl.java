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

/**
 * GKislin
 * 06.03.2015.
 */
@Service
public class MealServiceImpl implements MealService {
    private static final Logger LOG = getLogger(MealServiceImpl.class);

    private MealRepository repository;

    @Override
    public List<MealWithExceed> getFilteredMeals(int authorizedUserId, int caloriesPerDay, LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LOG.info("getFilteredMeals for user {}, parameters: {}, {}, {}, {}, {}", authorizedUserId, caloriesPerDay, startDate, startTime, endDate, endTime);
        return MealsUtil.getFilteredWithExceeded(repository.getAll(authorizedUserId), startTime, endTime, caloriesPerDay)
                .stream()
                .filter(mealWithExceed -> mealWithExceed.getDateTime().toLocalDate().isAfter(startDate) &&
                        mealWithExceed.getDateTime().toLocalDate().isBefore(endDate))
                .collect(Collectors.toList());
    }

    @Override
    public Meal getMeal(int authorizedUserId, int mealId) throws NotFoundException {
        LOG.info("getMeal for user {}, mealId = {}", authorizedUserId, mealId);
        Meal result = repository.get(mealId, authorizedUserId);
        if (result == null) {
            throw new NotFoundException("MealServiceImpl.getMeal: Have no meal");
        } else {
            return result;
        }
    }

    @Override
    public Meal removeMeal(int authorizedUserId, int mealId) throws NotFoundException {
        LOG.info("removeMeal for user {}, mealId = {}", authorizedUserId, mealId);
        Meal result = repository.remove(mealId, authorizedUserId);
        if (result == null) {
            throw new NotFoundException("MealServiceImpl.removeMeal: Have no meal");
        } else {
            return result;
        }
    }

    @Override
    public Meal saveMeal(int authorizedUserId, Meal meal) throws NotFoundException {
        LOG.info("saveMeal for user {}, mealId = {}", authorizedUserId, meal.getId());
        Meal result = repository.save(meal, authorizedUserId);
        if (result == null) {
            throw new NotFoundException("MealServiceImpl.saveMeal: Have no meal");
        } else {
            return result;
        }
    }

    @Override
    public Meal updateMeal(int authorizedUserId, Meal meal) throws NotFoundException {
        LOG.info("updateMeal for user {}, mealId = {}", authorizedUserId, meal.getId());
        Meal result = saveMeal(authorizedUserId, meal);
        if (result == null) {
            throw new NotFoundException("MealServiceImpl.updateMeal: Have no meal");
        } else {
            return result;
        }
    }
}
