package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;

@WebServlet("/admin-login")
public class AdminLoginController extends HttpServlet {

    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User u = userDAO.login(email, password);

        if (u != null && "admin".equals(u.getRole())) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", u);
            response.sendRedirect("admin");
        } else {
            request.setAttribute("error", "❌ Tài khoản admin không hợp lệ");
            request.getRequestDispatcher("admin_login.jsp").forward(request, response);
        }
    }
}
