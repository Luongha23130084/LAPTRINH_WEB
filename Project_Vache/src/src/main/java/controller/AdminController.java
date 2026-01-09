package controller;

import dao.FeedbackDAO;
import dao.StatisticDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminController extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setAttribute("statistic", new StatisticDAO().getStatistic());
        req.setAttribute("feedbackList", new FeedbackDAO().findAll());
        req.getRequestDispatcher("admin_dashboard.jsp").forward(req, res);
    }
}
