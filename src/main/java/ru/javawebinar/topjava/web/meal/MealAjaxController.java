package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

/**
 * Created by nicolas on 02.11.2016.
 */
@RestController
@RequestMapping("/ajax/meals")
public class MealAjaxController extends AbstractMealController {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractMealController.class);

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }

    @RequestMapping("/filter")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getFiltered(
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime) {

        LocalDate start_date = Objects.equals(startDate, "") ? LocalDate.MIN : TimeUtil.parseLocalDate(startDate);
        LocalDate end_date = Objects.equals(endDate, "") ? LocalDate.MAX : TimeUtil.parseLocalDate(endDate);
        LocalTime start_time = Objects.equals(startTime, "") ? LocalTime.MIN : TimeUtil.parseLocalTime(startTime);
        LocalTime end_time = Objects.equals(endTime, "") ? LocalTime.MAX : TimeUtil.parseLocalTime(endTime);

        return super.getBetween(start_date, start_time, end_date, end_time);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("datetime") String dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") Integer calories) {

        Meal meal = new Meal(id, LocalDateTime.parse(dateTime), description, calories);
        if (meal.isNew()) {
            super.create(meal);
        } else {
            super.update(meal, id);
        }
    }

}
