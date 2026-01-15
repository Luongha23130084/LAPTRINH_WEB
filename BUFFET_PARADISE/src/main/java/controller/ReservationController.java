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
import javax.servlet.http.HttpSession;

public class ReservationController extends HttpServlet {
    
    private ReservationManager reservationManager = new ReservationManager();
    
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
            String dateStr = req.getParameter("date");
            String timeStr = req.getParameter("time");
            String guestsStr = req.getParameter("guests");
            String area = req.getParameter("area");
            
            // ✅ VALIDATE REQUIRED FIELDS
            if (dateStr == null || dateStr.trim().isEmpty()) {
                req.setAttribute("error", "Ngày đặt bàn không được để trống");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            if (timeStr == null || timeStr.trim().isEmpty()) {
                req.setAttribute("error", "Giờ đặt bàn không được để trống");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            if (guestsStr == null || guestsStr.trim().isEmpty()) {
                req.setAttribute("error", "Số lượng khách không được để trống");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // Parse and validate
            Date reservationDate = Date.valueOf(dateStr);
            Time reservationTime = Time.valueOf(timeStr + ":00");
            int guests = Integer.parseInt(guestsStr);
            
            // ✅ VALIDATE DATE - Must be today or future
            Date today = new Date(System.currentTimeMillis());
            if (reservationDate.before(today)) {
                req.setAttribute("error", "Không thể đặt bàn cho ngày trong quá khứ");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // ✅ VALIDATE GUEST COUNT
            if (guests <= 0) {
                req.setAttribute("error", "Số lượng khách phải lớn hơn 0");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            if (guests > 50) {
                req.setAttribute("error", "Số lượng khách tối đa là 50 người. Vui lòng liên hệ nhà hàng để đặt bàn lớn.");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // ✅ VALIDATE TIME - Restaurant hours (10:00 - 22:00)
            Time openTime = Time.valueOf("10:00:00");
            Time closeTime = Time.valueOf("22:00:00");
            
            if (reservationTime.before(openTime) || reservationTime.after(closeTime)) {
                req.setAttribute("error", "Giờ đặt bàn phải từ 10:00 đến 22:00");
                req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                return;
            }
            
            // ✅ VALIDATE AREA
            if (area != null && !area.trim().isEmpty()) {
                String[] validAreas = {"Indoor", "Outdoor", "VIP", "Garden"};
                boolean validArea = false;
                for (String valid : validAreas) {
                    if (valid.equalsIgnoreCase(area.trim())) {
                        validArea = true;
                        break;
                    }
                }
                if (!validArea) {
                    req.setAttribute("error", "Khu vực không hợp lệ");
                    req.getRequestDispatcher("customer_reservations.jsp").forward(req, res);
                    return;
                }
            }
            
            // Create reservation
            Reservation r = new Reservation();
            r.setCustomerId(user.getUserId());
            r.setReservationDate(reservationDate);
            r.setReservationTime(reservationTime);
            r.setGuests(guests);
            r.setArea(area != null ? area.trim() : "Indoor");
            
            boolean success = reservationManager.book(r);
            
            if (success) {
                req.setAttribute("success", "Đặt bàn thành công! Mã đơn: " + r.getReservationId());
                System.out.println("✅ Reservation created successfully: " + r.getReservationId());
            } else {
                req.setAttribute("error", "Không thể đặt bàn. Vui lòng thử lại.");
                System.err.println("❌ Failed to create reservation for user: " + user.getUserId());
            }
            
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "Định dạng ngày/giờ không hợp lệ");
            System.err.println("Invalid date/time format: " + e.getMessage());
        } catch (Exception e) {
            req.setAttribute("error", "Lỗi hệ thống. Vui lòng thử lại sau.");
            System.err.println("Error in ReservationController: " + e.getMessage());
            e.printStackTrace();
        }
        
        res.sendRedirect("customer_reservations.jsp");
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.sendRedirect("customer_reservations.jsp");
    }
}