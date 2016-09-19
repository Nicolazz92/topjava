package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by nicolas on 19.09.2016.
 */
@Repository
public class MealUserRelation {
    private static Map<Integer, Integer> mealUserRelation = new ConcurrentHashMap();
    private static Map<Integer, List<Integer>> userMealRelation = new ConcurrentHashMap();

    public static Integer getUserIdByMeaIdl(int mealId) {
        return mealUserRelation.get(mealId);
    }

    public static List<Integer> getMealsIdsByUserId(int userId) {
        return userMealRelation.get(userId);
    }

    public static void addRelation(int mealId, int userId) {
        mealUserRelation.put(mealId, userId);

        if (userMealRelation.get(userId) == null) {
            userMealRelation.put(userId, Collections.singletonList(mealId));
        } else {
            List<Integer> meals = userMealRelation.get(userId);
            meals.add(mealId);
            userMealRelation.put(userId, meals);
        }
    }

    public static void removeRelation(int mealId, int userId) {
        mealUserRelation.remove(mealId);

        List<Integer> meals = userMealRelation.get(userId);
        meals.remove(mealId);
        userMealRelation.put(userId, meals);
    }
}
