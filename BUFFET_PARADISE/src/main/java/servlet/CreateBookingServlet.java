package servlet;

import dao.BookingDAO; // Giả sử bạn có lớp này để lưu DB
import model.User;
import model.Booking; // Giả sử bạn có model Booking

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/CreateBookingServlet")
public class CreateBookingServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // 1. Kiểm tra session (Mapping User)
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        if (user == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            return;
        }

        try {
            // 2. Lấy dữ liệu từ Fetch gửi sang
            String date = request.getParameter("bookingDate");
            String time = request.getParameter("bookingTime");
            int guestCount = Integer.parseInt(request.getParameter("guestCount"));
            String zone = request.getParameter("zone");
            String tableId = request.getParameter("tableId");
            String notes = request.getParameter("notes");

            // 3. Tạo đối tượng Booking để lưu
            // Giả sử constructor: Booking(userId, date, time, guests, zone, table, notes)
            // Booking newBooking = new Booking(user.getUserId(), date, time, guestCount, zone, tableId, notes);
            
            // 4. Gọi DAO để lưu vào Database
            // BookingDAO bookingDAO = new BookingDAO();
            // boolean success = bookingDAO.insertBooking(newBooking);
            
            System.out.println("📝 Đang lưu đặt bàn cho user: " + user.getFullName());
            System.out.println("📍 Bàn: " + tableId + " - Ngày: " + date);

            // Tạm thời giả định lưu thành công
            boolean success = true; 

            if (success) {
                response.setStatus(HttpServletResponse.SC_OK); // 200
            } else {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 500
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
        }
    }
}