package servlet;

import dao.UserDAO;
import model.User;
import util.PasswordUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("\n========================================");
        System.out.println("🔐 LOGIN ATTEMPT STARTED");
        System.out.println("========================================");
        
        // Set encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Get form data
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        System.out.println("📧 Email received: " + email);
        System.out.println("🔑 Password received: " + (password != null ? "***" : "NULL"));
        
        // Validate input
        if (email == null || email.trim().isEmpty() || 
            password == null || password.trim().isEmpty()) {
            System.out.println("❌ ERROR: Missing email or password");
            response.sendRedirect("login.jsp?error=required");
            return;
        }
        
        try {
            System.out.println("🔍 Searching for user in database...");
            
            // Find user by email
            User user = userDAO.findByEmail(email.trim());
            
            if (user == null) {
                System.out.println("❌ ERROR: User not found for email: " + email);
                response.sendRedirect("login.jsp?error=invalid");
                return;
            }
            
            System.out.println("✅ User found: " + user.getFullName());
            System.out.println("   User ID: " + user.getUserId());
            System.out.println("   Role: " + user.getRole());
            System.out.println("   Status: " + user.getStatus());
            
            // Verify password
            System.out.println("🔐 Verifying password...");
            boolean passwordMatch = PasswordUtil.verifyPassword(password, user.getPasswordHash());
            
            if (!passwordMatch) {
                System.out.println("❌ ERROR: Wrong password");
                response.sendRedirect("login.jsp?error=invalid");
                return;
            }
            
            System.out.println("✅ Password verified successfully");
            
            // Check if user is active
            if (!"active".equalsIgnoreCase(user.getStatus())) {
                System.out.println("❌ ERROR: User account is not active: " + user.getStatus());
                response.sendRedirect("login.jsp?error=inactive");
                return;
            }
            
            System.out.println("✅ User account is active");
            
            // ✅ CREATE SESSION
            System.out.println("🔄 Creating session...");
            HttpSession session = request.getSession(true); // Force create new session
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userRole", user.getRole());
            session.setMaxInactiveInterval(30 * 60); // 30 minutes
            
            System.out.println("✅ Session created successfully");
            System.out.println("   Session ID: " + session.getId());
            System.out.println("   User in session: " + ((User) session.getAttribute("user")).getFullName());
            
            // Update last login
            System.out.println("📝 Updating last login timestamp...");
            userDAO.updateLastLogin(user.getUserId());
            
            // ✅ REDIRECT BASED ON ROLE
            String redirectUrl;
            if (user.isAdmin() || user.isStaff()) {
                redirectUrl = "admin_dashboard.jsp";
                System.out.println("👨‍💼 Admin/Staff detected → Redirecting to: " + redirectUrl);
            } else {
                redirectUrl = "booking.jsp";
                System.out.println("👤 Customer detected → Redirecting to: " + redirectUrl);
            }
            
            System.out.println("🚀 Sending redirect to: " + redirectUrl);
            System.out.println("========================================");
            System.out.println("✅ LOGIN SUCCESSFUL");
            System.out.println("========================================\n");
            
            response.sendRedirect(redirectUrl);
            
        } catch (Exception e) {
            System.out.println("========================================");
            System.out.println("❌ CRITICAL ERROR IN LOGIN PROCESS");
            System.out.println("========================================");
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=system");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests to login page
        response.sendRedirect("login.jsp");
    }
}
