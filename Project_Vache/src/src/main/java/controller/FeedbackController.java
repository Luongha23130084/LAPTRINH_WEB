package controller;

import dao.FeedbackDAO;
import model.Feedback;
import model.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FeedbackController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        Feedback f = new Feedback();
        f.setReservationId(Integer.parseInt(req.getParameter("reservation_id")));
        f.setCustomerId(user.getUserId());
        f.setRating(Integer.parseInt(req.getParameter("rating")));
        f.setComment(req.getParameter("comment"));

        new FeedbackDAO().add(f);
        res.sendRedirect("customer_reservations.jsp");
    }
}
