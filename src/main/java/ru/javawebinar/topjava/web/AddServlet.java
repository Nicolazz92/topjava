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
public class AddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime localDateTime = LocalDateTime.parse(req.getParameter("localdate"));
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));

        Data.meals.add(new Meal(localDateTime, description, calories));

        resp.sendRedirect("meals");
    }
}
