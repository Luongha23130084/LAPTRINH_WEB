package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import model.User;


public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        UserDAO dao = new UserDAO();
        User user = dao.login(req.getParameter("email"),
                              req.getParameter("password"));

        if (user != null) {
            req.getSession().setAttribute("user", user);
            res.sendRedirect("index.jsp");
        } else {
            req.setAttribute("error", "Login failed");
            req.getRequestDispatcher("login.jsp").forward(req, res);
        }
    }
}
