package controller;


import model.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class RegisterController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        User u = new User();
        u.setEmail(req.getParameter("email"));
        u.setPassword(req.getParameter("password"));
        u.setFullName(req.getParameter("fullname"));
        u.setPhone(req.getParameter("phone"));

        new UserDAO().register(u);
        res.sendRedirect("login.jsp");
    }
}
