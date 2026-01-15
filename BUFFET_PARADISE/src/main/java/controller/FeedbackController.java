package controller;

import dao.FeedbackDAO;
import dao.ReservationDAO;
import model.Feedback;
import model.Reservation;
import model.User;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class FeedbackController extends HttpServlet {
    
    private FeedbackDAO feedbackDAO = new FeedbackDAO();
    private ReservationDAO reservationDAO = new ReservationDAO();
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
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
        
        try {
            // Get parameters
            String reservationIdStr = req.getParameter("reservation_id");
            String ratingStr = req.getParameter("rating");
            String comment = req.getParameter("comment");
            
            // ✅ VALIDATE INPUT
            if (reservationIdStr == null || reservationIdStr.trim().isEmpty()) {
                req.setAttribute("error", "Mã đặt bàn không được để trống");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            if (ratingStr == null || ratingStr.trim().isEmpty()) {
                req.setAttribute("error", "Vui lòng chọn đánh giá");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            int reservationId = Integer.parseInt(reservationIdStr);
            int rating = Integer.parseInt(ratingStr);
            
            // ✅ VALIDATE RATING RANGE
            if (rating < 1 || rating > 5) {
                req.setAttribute("error", "Đánh giá phải từ 1-5 sao");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // ✅ VALIDATE COMMENT LENGTH
            if (comment != null && comment.length() > 1000) {
                req.setAttribute("error", "Nhận xét không được quá 1000 ký tự");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // ✅ CHECK AUTHORIZATION - Verify reservation belongs to user
            Reservation reservation = reservationDAO.findById(reservationId);
            
            if (reservation == null) {
                req.setAttribute("error", "Không tìm thấy đơn đặt bàn");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            if (reservation.getCustomerId() != user.getUserId()) {
                req.setAttribute("error", "Bạn không có quyền đánh giá đơn này");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // ✅ CHECK IF RESERVATION IS COMPLETED
            if (!"completed".equals(reservation.getStatus())) {
                req.setAttribute("error", "Chỉ có thể đánh giá đơn đã hoàn thành");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // ✅ CHECK IF ALREADY REVIEWED
            Feedback existingFeedback = feedbackDAO.findByReservation(reservationId);
            if (existingFeedback != null) {
                req.setAttribute("error", "Bạn đã đánh giá đơn này rồi");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // Create feedback
            Feedback f = new Feedback();
            f.setReservationId(reservationId);
            f.setCustomerId(user.getUserId());
            f.setRating(rating);
            f.setComment(comment != null ? comment.trim() : "");
            
            boolean success = feedbackDAO.add(f);
            
            if (success) {
                req.setAttribute("success", "Cảm ơn bạn đã đánh giá!");
                System.out.println("✅ Feedback added successfully by user: " + user.getUserId());
            } else {
                req.setAttribute("error", "Không thể gửi đánh giá. Vui lòng thử lại.");
                System.err.println("❌ Failed to add feedback for reservation: " + reservationId);
            }
            
        } catch (NumberFormatException e) {
            req.setAttribute("error", "Dữ liệu không hợp lệ");
            System.err.println("Invalid number format in feedback: " + e.getMessage());
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi hệ thống. Vui lòng thử lại sau.");
            System.err.println("Error in FeedbackController: " + e.getMessage());
            e.printStackTrace();
        }
        
        res.sendRedirect("customer_reservations.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        // Redirect to POST
        res.sendRedirect("customer_reservations.jsp");
    }
}