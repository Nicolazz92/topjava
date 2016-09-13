package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Data;
import ru.javawebinar.topjava.model.Meal;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Created by nicolas on 13.09.2016.
 */
public class ManipulateServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(req.getParameter("id"));
        if (id < 0) {
            addNewMeal(req, resp);
        } else {
            updateMeal(req, resp, id);
        }
    }

    private void addNewMeal(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("localdate"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Data.meals.add(new Meal(localDateTime, description, calories));

        resp.sendRedirect("meals");
    }

    private void updateMeal(HttpServletRequest req, HttpServletResponse resp, int id) throws IOException {
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("localdate"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        for (Meal m : Data.meals) {
            if (m.getId() == id) {
                m.setDateTime(localDateTime);
                m.setDescription(description);
                m.setCalories(calories);
                break;
            }
        }

        resp.sendRedirect("meals");
    }
}
