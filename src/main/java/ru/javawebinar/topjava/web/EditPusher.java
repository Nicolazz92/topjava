package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Data;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nicolas on 13.09.2016.
 */
public class EditPusher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));

        req.setAttribute("fullList", MealsUtil.getFilteredWithExceeded(
                Data.meals, null, null, 2000));
        req.setAttribute("editMeal", getEditMeal(id));
        req.getRequestDispatcher("/mealList.jsp").forward(req, resp);
    }

    private Meal getEditMeal(int id) {
        Meal result = null;
        for (Meal m : Data.meals) {
            if (m.getId() == id) {
                result = m;
                break;
            }
        }
        return result;
    }
}
