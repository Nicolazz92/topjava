package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.to.MealWithExceed;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * GKislin
 * 06.03.2015.
 */
@Controller
public class RestControllerImpl implements RestController {
    private static final Logger LOG = getLogger(RestControllerImpl.class);
    private MealService service;
    private int authorizedUserId;

    @Override
    public List<MealWithExceed> getMeals() {
        LOG.info("getMeals for user {}", authorizedUserId);
        return getFilteredMeals(LocalDate.MIN, LocalTime.MIN, LocalDate.MAX, LocalTime.MAX);
    }

    @Override
    public List<MealWithExceed> getFilteredMeals(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        LOG.info("getFilteredMeals for user {}, parameters: {}, {}, {}, {}, {}", authorizedUserId, AuthorizedUser.getCaloriesPerDay(), startDate, startTime, endDate, endTime);
        return service.getFilteredMeals(authorizedUserId, AuthorizedUser.getCaloriesPerDay(), startDate, startTime, endDate, endTime);
    }

    @Override
    public Meal getMeal(int mealId) {
        LOG.info("getMeal for user {}, id = {}", authorizedUserId, mealId);
        return service.getMeal(authorizedUserId, mealId);
    }

    @Override
    public Meal removeMeal(int mealId) {
        LOG.info("removeMeal for user {}, id = {}", authorizedUserId, mealId);
        return service.removeMeal(authorizedUserId, mealId);
    }

    @Override
    public void saveMeal(Meal meal) {
        LOG.info("removeMeal for user {}, id = {}", authorizedUserId, meal.getId());
        service.saveMeal(authorizedUserId, meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        LOG.info("removeMeal for user {}, id = {}", authorizedUserId, meal.getId());
        service.updateMeal(authorizedUserId, meal);
    }
}
