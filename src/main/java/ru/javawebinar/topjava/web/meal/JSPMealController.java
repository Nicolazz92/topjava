package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.TimeUtil;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * Created by nicolas on 22.10.2016.
 */
@Controller
@RequestMapping(value = "/meals")
public class JSPMealController {

    @Autowired
    private MealRestController mealRestController;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, HttpServletRequest request) {
        model.addAttribute("meal", mealRestController.get(Integer.parseInt(request.getParameter("id"))));
        return "meal";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("meal", new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), "", 1000));
        return "meal";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Model model, HttpServletRequest request) {
        String id = request.getParameter("id");

        Meal meal = new Meal(id == null || id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse(request.getParameter("dateTime")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")));

        if (meal.isNew()) {
            mealRestController.create(meal);
        } else {
            mealRestController.update(meal, meal.getId());
        }

        model.addAttribute("meals", mealRestController.getAll());
        return "meals";
    }

    @RequestMapping(value = "/filter", method = RequestMethod.POST)
    public String filter(Model model, HttpServletRequest request) {
        LocalDate startDate = TimeUtil.parseLocalDate(request.getParameter("startDate"));
        LocalTime startTime = TimeUtil.parseLocalTime(request.getParameter("startTime"));
        LocalDate endDate = TimeUtil.parseLocalDate(request.getParameter("endDate"));
        LocalTime endTime = TimeUtil.parseLocalTime(request.getParameter("endTime"));
        model.addAttribute("meals", mealRestController.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        mealRestController.delete(id);
//        model.addAttribute("meals", mealRestController.getAll());
//        return "meals";
        return "redirect:/meals/";
    }
}
