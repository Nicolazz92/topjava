package ru.javawebinar.topjava;

import java.time.LocalTime;

import static ru.javawebinar.topjava.util.MealsUtil.getFilteredWithExceededByCycle;
import static ru.javawebinar.topjava.util.MealsUtil.getMeals;

/**
 * User: gkislin
 * Date: 05.08.2015
 *
 * @link http://caloriesmng.herokuapp.com/
 * @link https://github.com/JavaOPs/topjava
 */
public class Main {
    public static void main(String[] args) {
        System.out.format("Hello Topjava Enterprise!");

        System.out.println(getFilteredWithExceededByCycle(getMeals(), LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));

    }
}
