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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("\n========================================");
        System.out.println("📝 REGISTRATION ATTEMPT STARTED");
        System.out.println("========================================");
        
        // Set encoding
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        // Get form data
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        
        System.out.println("📋 Registration data received:");
        System.out.println("   Full Name: " + fullName);
        System.out.println("   Email: " + email);
        System.out.println("   Phone: " + phone);
        System.out.println("   Password: " + (password != null ? "***" : "NULL"));
        
        // Validate input
        if (fullName == null || fullName.trim().isEmpty() ||
            email == null || email.trim().isEmpty() ||
            phone == null || phone.trim().isEmpty() ||
            password == null || password.trim().isEmpty()) {
            System.out.println("❌ ERROR: Required fields are missing");
            response.sendRedirect("register.jsp?error=required");
            return;
        }
        
        // Check password match
        if (!password.equals(confirmPassword)) {
            System.out.println("❌ ERROR: Passwords do not match");
            response.sendRedirect("register.jsp?error=password");
            return;
        }
        
        // Check password length
        if (password.length() < 6) {
            System.out.println("❌ ERROR: Password too short (< 6 characters)");
            response.sendRedirect("register.jsp?error=password_short");
            return;
        }
        
        System.out.println("✅ Validation passed");
        
        try {
            // Check if email already exists
            System.out.println("🔍 Checking if email exists...");
            if (userDAO.emailExists(email.trim())) {
                System.out.println("❌ ERROR: Email already registered: " + email);
                response.sendRedirect("register.jsp?error=exists");
                return;
            }
            
            System.out.println("✅ Email is available");
            
            // Hash password
            System.out.println("🔐 Hashing password...");
            String hashedPassword = PasswordUtil.hashPassword(password);
            System.out.println("✅ Password hashed successfully");
            
            // Create new user
            System.out.println("👤 Creating new user object...");
            User user = new User();
            user.setFullName(fullName.trim());
            user.setEmail(email.trim());
            user.setPhone(phone.trim());
            user.setPasswordHash(hashedPassword);
            user.setRole("customer");
            user.setStatus("active");
            
            System.out.println("💾 Saving user to database...");
            boolean success = userDAO.createUser(user);
            
            if (success) {
                System.out.println("✅ User saved successfully!");
                System.out.println("   User ID: " + user.getUserId());
                
                // ✅ AUTO LOGIN AFTER REGISTRATION
                System.out.println("🔄 Creating session for auto-login...");
                HttpSession session = request.getSession(true);
                session.setAttribute("user", user);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("userRole", user.getRole());
                session.setMaxInactiveInterval(30 * 60);
                
                System.out.println("✅ Session created successfully");
                System.out.println("   Session ID: " + session.getId());
                
                // Redirect to booking page
                String redirectUrl = "bookings.jsp";
                System.out.println("🚀 Redirecting to: " + redirectUrl);
                System.out.println("========================================");
                System.out.println("✅ REGISTRATION SUCCESSFUL");
                System.out.println("========================================\n");
                
                response.sendRedirect(redirectUrl);
                
            } else {
                System.out.println("❌ ERROR: Failed to save user to database");
                response.sendRedirect("register.jsp?error=failed");
            }
            
        } catch (Exception e) {
            System.out.println("========================================");
            System.out.println("❌ CRITICAL ERROR IN REGISTRATION");
            System.out.println("========================================");
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=system");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Redirect GET requests to register page
        response.sendRedirect("register.jsp");
    }
}