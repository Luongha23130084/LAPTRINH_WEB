package controller;

import dao.FeedbackDAO;
import dao.StatisticDAO;
import model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminController extends HttpServlet {
    
    private StatisticDAO statisticDAO = new StatisticDAO();
    private FeedbackDAO feedbackDAO = new FeedbackDAO();
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        // ✅ CHECK LOGIN
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect("login.jsp?error=unauthorized");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        if (user == null) {
            res.sendRedirect("login.jsp?error=unauthorized");
            return;
        }
        
        // ✅ CHECK IF USER IS ADMIN
        if (!user.isAdmin()) {
            req.setAttribute("error", "Chỉ admin mới có quyền truy cập trang này");
            res.sendRedirect("index.jsp");
            return;
        }
        
        try {
            System.out.println("📊 Admin dashboard accessed by: " + user.getFullName());
            
            // Get statistics
            req.setAttribute("statistic", statisticDAO.getStatistic());
            
            // Get all feedback
            req.setAttribute("feedbackList", feedbackDAO.findAll());
            
            // Forward to dashboard
            req.getRequestDispatcher("admin_dashboard.jsp").forward(req, res);
            
        } catch (Exception e) {
            System.err.println("❌ Error in AdminController: " + e.getMessage());
            e.printStackTrace();
            
            req.setAttribute("error", "Lỗi khi tải dữ liệu dashboard");
            req.getRequestDispatcher("admin_dashboard.jsp").forward(req, res);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        // Check authorization
        HttpSession session = req.getSession(false);
        if (session == null) {
            res.sendRedirect("login.jsp?error=unauthorized");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            res.sendRedirect("login.jsp?error=unauthorized");
            return;
        }
        
        // Handle POST actions (if needed)
        doGet(req, res);
    }
}