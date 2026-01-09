package controller;

import service.ReservationManager;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StaffController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        String action = req.getParameter("action");

        ReservationManager m = new ReservationManager();
        if ("confirm".equals(action)) m.confirm(id);
        if ("cancel".equals(action)) m.cancel(id);

        res.sendRedirect("staff_dashboard.jsp");
    }
}
