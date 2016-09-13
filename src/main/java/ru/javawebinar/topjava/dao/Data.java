package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nicolas on 13.09.2016.
 */
public class Data {
    public static List<Meal> meals = new ArrayList<>();

    public static int generateId() {
        if (meals.size() == 0) return 0;

        int result = Integer.MIN_VALUE;
        for (Meal m : meals) {
            if (m.getId() > result) {
                result = m.getId();
            }
        }
        return ++result;
    }
}
