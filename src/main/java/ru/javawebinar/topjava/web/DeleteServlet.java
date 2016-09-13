package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.dao.Data;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by nicolas on 13.09.2016.
 */
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        for (int i = 0; i < Data.meals.size(); i++) {
            if (Data.meals.get(i).getId() == id) {
                Data.meals.remove(i);
                break;
            }
        }
        resp.sendRedirect("meals");
    }
}
