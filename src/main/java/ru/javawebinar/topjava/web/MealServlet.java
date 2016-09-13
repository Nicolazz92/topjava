package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.Data;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (Data.meals.size() == 0) {
            MealsUtil.startInit();
            LOG.info("start init done");
        }

        LOG.debug("redirect to mealList");

        request.setAttribute("fullList", MealsUtil.getFilteredWithExceeded(
                Data.meals, null, null, 2000));
        request.getRequestDispatcher("/mealList.jsp").forward(request, response);
    }
}
