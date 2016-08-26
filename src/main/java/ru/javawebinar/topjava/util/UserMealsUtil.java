package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510)
        );
        List l = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        System.out.printf("");
    }

    public static List<UserMealWithExceed> getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> caloriesMap = createCaloriesMap(mealList);

        List<UserMealWithExceed> result = new ArrayList<>();

        mealList.parallelStream().filter(userMeal -> userMeal.getDateTime().toLocalTime().isAfter(startTime) && //check startTime
                userMeal.getDateTime().toLocalTime().isBefore(endTime)).forEach(userMeal -> { //check endTime
            if (caloriesMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay) { //check calories
                result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
            } else {
                result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
            }
        });

//        for (UserMeal userMeal : mealList) {
//            if (userMeal.getDateTime().toLocalTime().isAfter(startTime) && //check startTime
//                    userMeal.getDateTime().toLocalTime().isBefore(endTime)) { //check endTime
//                if (caloriesMap.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay) { //check calories
//                    result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), true));
//                } else {
//                    result.add(new UserMealWithExceed(userMeal.getDateTime(), userMeal.getDescription(), userMeal.getCalories(), false));
//                }
//            }
//        }

        return result;
    }

    private static Map<LocalDate, Integer> createCaloriesMap(List<UserMeal> mealList) {
        HashMap<LocalDate, Integer> result = new HashMap<>();

        mealList.parallelStream().forEach(userMeal -> {
            LocalDate date = userMeal.getDateTime().toLocalDate();
            Integer calories = userMeal.getCalories();
            if (result.containsKey(date)) {
                result.put(date, calories + result.get(date));
            } else {
                result.put(date, calories);
            }
        });

//        for (UserMeal userMeal : mealList) {
//            LocalDate date = userMeal.getDateTime().toLocalDate();
//            Integer calories = userMeal.getCalories();
//            if (result.containsKey(date)) {
//                result.put(date, calories + result.get(date));
//            } else {
//                result.put(date, calories);
//            }
//        }

        return result;
    }
}
