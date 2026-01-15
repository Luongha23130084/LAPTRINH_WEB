// ============================================
// LOGOUTSERVLET.JAVA - Xử lý đăng xuất
// ============================================
package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        System.out.println("\n========================================");
        System.out.println("🚪 LOGOUT REQUEST");
        System.out.println("========================================");
        
        // Get current session
        HttpSession session = request.getSession(false);
        
        if (session != null) {
            // Get user info before logout
            Object userObj = session.getAttribute("user");
            if (userObj != null) {
                System.out.println("👤 User logging out: " + userObj.toString());
            }
            
            // Invalidate session
            session.invalidate();
            System.out.println("✅ Session invalidated successfully");
        } else {
            System.out.println("⚠️ No active session found");
        }
        
        System.out.println("🔄 Redirecting to: index.jsp");
        System.out.println("========================================\n");
        
        // Redirect to home page
        response.sendRedirect("index.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        // Support POST as well
        doGet(request, response);
    }
}