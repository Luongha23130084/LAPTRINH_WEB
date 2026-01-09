package controller;


import model.Reservation;
import model.User;
import service.ReservationManager;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");

        Reservation r = new Reservation();
        r.setCustomerId(user.getUserId());
        r.setReservationDate(Date.valueOf(req.getParameter("date")));
        r.setReservationTime(Time.valueOf(req.getParameter("time")));
        r.setGuests(Integer.parseInt(req.getParameter("guests")));
        r.setArea(req.getParameter("area"));

        new ReservationManager().book(r);
        res.sendRedirect("customer_reservations.jsp");
    }
}
