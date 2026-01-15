package servlet;

import dao.BookingDAO;
import dao.TableDAO;
import model.Booking;
import model.Table;
import model.User;
import util.BookingCodeUtil;
import util.EmailUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.math.BigDecimal;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO = new BookingDAO();
    private TableDAO tableDAO = new TableDAO();
    
    // Constants for validation
    private static final int MIN_GUESTS = 1;
    private static final int MAX_GUESTS = 50;
    private static final BigDecimal PRICE_PER_PERSON = new BigDecimal("299000");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // ✅ CHECK LOGIN
        User user = getLoggedInUser(request);
        if (user == null) {
            response.sendRedirect("login.jsp?error=unauthorized");
            return;
        }

        String action = request.getParameter("action");
        response.setContentType("application/json; charset=UTF-8");

        try {
            switch (action != null ? action : "") {
                case "myBookings":
                    getMyBookings(user.getUserId(), response);
                    break;
                case "detail":
                    getBookingDetail(user, request, response);
                    break;
                case "checkAvailability":
                    checkAvailability(request, response);
                    break;
                default:
                    sendError(response, "Invalid action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendError(response, "Lỗi server: " + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        // ✅ CHECK LOGIN
        User user = getLoggedInUser(request);
        if (user == null) {
            sendError(response, "Unauthorized - Vui lòng đăng nhập");
            return;
        }

        String action = request.getParameter("action");
        response.setContentType("application/json; charset=UTF-8");

        try {
            switch (action != null ? action : "") {
                case "create":
                    createBooking(user, request, response);
                    break;
                case "cancel":
                    cancelBooking(user, request, response);
                    break;
                default:
                    sendError(response, "Invalid action");
            }
        } catch (Exception e) {
            e.printStackTrace();
            sendError(response, "Lỗi server: " + e.getMessage());
        }
    }

    // ==================== GET METHODS ====================

    /**
     * Get user's bookings - with proper authorization
     */
    private void getMyBookings(int userId, HttpServletResponse response) throws IOException {
        List<Booking> bookings = bookingDAO.getBookingsByUser(userId);
        
        JSONArray bookingsArray = new JSONArray();
        for (Booking b : bookings) {
            JSONObject obj = new JSONObject();
            obj.put("bookingId", b.getBookingId());
            obj.put("bookingCode", b.getBookingCode());
            obj.put("bookingDate", b.getBookingDate().toString());
            obj.put("bookingTime", b.getBookingTime().toString());
            obj.put("guests", b.getGuests());
            obj.put("status", b.getStatus());
            obj.put("totalAmount", b.getTotalAmount());
            obj.put("occasion", b.getOccasion());
            obj.put("specialRequests", b.getSpecialRequests());
            obj.put("tableNumber", b.getTableNumber());
            obj.put("cancelReason", b.getCancelReason());
            bookingsArray.put(obj);
        }
        
        sendSuccess(response, new JSONObject().put("bookings", bookingsArray));
    }

    /**
     * Get booking detail - WITH AUTHORIZATION CHECK
     */
    private void getBookingDetail(User user, HttpServletRequest request, 
                                  HttpServletResponse response) throws IOException {
        
        String bookingIdStr = request.getParameter("bookingId");
        
        // ✅ VALIDATE INPUT
        if (bookingIdStr == null || bookingIdStr.trim().isEmpty()) {
            sendError(response, "Mã booking không hợp lệ");
            return;
        }
        
        int bookingId = Integer.parseInt(bookingIdStr);
        Booking booking = bookingDAO.getBookingById(bookingId);
        
        if (booking == null) {
            sendError(response, "Không tìm thấy đơn đặt bàn");
            return;
        }
        
        // ✅ CHECK AUTHORIZATION - User can only view their own bookings
        if (booking.getUserId() != user.getUserId() && !user.isAdminOrStaff()) {
            sendError(response, "Bạn không có quyền xem đơn này");
            return;
        }
        
        JSONObject obj = new JSONObject();
        obj.put("bookingId", booking.getBookingId());
        obj.put("bookingCode", booking.getBookingCode());
        obj.put("bookingDate", booking.getBookingDate().toString());
        obj.put("bookingTime", booking.getBookingTime().toString());
        obj.put("guests", booking.getGuests());
        obj.put("status", booking.getStatus());
        obj.put("totalAmount", booking.getTotalAmount());
        obj.put("occasion", booking.getOccasion());
        obj.put("specialRequests", booking.getSpecialRequests());
        obj.put("tableNumber", booking.getTableNumber());
        obj.put("cancelReason", booking.getCancelReason());
        
        sendSuccess(response, new JSONObject().put("booking", obj));
    }

    /**
     * Check availability
     */
    private void checkAvailability(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");

        // ✅ VALIDATE INPUT
        if (dateStr == null || timeStr == null) {
            sendError(response, "Thiếu thông tin ngày/giờ");
            return;
        }

        try {
            Date date = Date.valueOf(dateStr);
            Time time = Time.valueOf(timeStr + ":00");

            int available = bookingDAO.checkAvailability(date, time);
            
            JSONObject result = new JSONObject();
            result.put("available", available);
            result.put("message", available > 0 ? "Còn " + available + " bàn" : "Hết chỗ");
            
            sendSuccess(response, result);
        } catch (IllegalArgumentException e) {
            sendError(response, "Định dạng ngày/giờ không hợp lệ");
        }
    }

    // ==================== POST METHODS ====================

    /**
     * Create new booking - WITH FULL VALIDATION
     */
    private void createBooking(User user, HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        // ✅ VALIDATE REQUIRED FIELDS
        if (!json.has("date") || !json.has("time") || !json.has("guests")) {
            sendError(response, "Thiếu thông tin bắt buộc");
            return;
        }
        
        // Parse data
        String dateStr = json.getString("date");
        String timeStr = json.getString("time");
        int guests = json.getInt("guests");
        String occasion = json.optString("occasion", null);
        String notes = json.optString("notes", null);

        // ✅ VALIDATE GUEST COUNT
        if (guests < MIN_GUESTS || guests > MAX_GUESTS) {
            sendError(response, "Số lượng khách phải từ " + MIN_GUESTS + " đến " + MAX_GUESTS);
            return;
        }

        // ✅ VALIDATE SPECIAL REQUESTS LENGTH
        if (notes != null && notes.length() > 500) {
            sendError(response, "Ghi chú không được quá 500 ký tự");
            return;
        }

        try {
            Date bookingDate = Date.valueOf(dateStr);
            Time bookingTime = Time.valueOf(timeStr + ":00");

            // ✅ VALIDATE DATE - Must not be in the past
            Date today = new Date(System.currentTimeMillis());
            if (bookingDate.before(today)) {
                sendError(response, "Không thể đặt bàn cho ngày trong quá khứ");
                return;
            }

            // ✅ VALIDATE TIME - Restaurant hours (10:00 - 22:00)
            Time openTime = Time.valueOf("10:00:00");
            Time closeTime = Time.valueOf("22:00:00");
            
            if (bookingTime.before(openTime) || bookingTime.after(closeTime)) {
                sendError(response, "Giờ đặt bàn phải từ 10:00 đến 22:00");
                return;
            }

            // ✅ CHECK AVAILABILITY
            int available = bookingDAO.checkAvailability(bookingDate, bookingTime);
            if (available <= 0) {
                sendError(response, "Không còn bàn trống. Vui lòng chọn giờ khác.");
                return;
            }

            // ✅ CHECK USER BOOKING LIMIT (max 3 upcoming bookings)
            int upcomingCount = bookingDAO.getUpcomingBookingsCount(user.getUserId());
            if (upcomingCount >= 3) {
                sendError(response, "Bạn đã có 3 đơn đặt bàn sắp tới. Vui lòng hoàn thành hoặc hủy trước khi đặt thêm.");
                return;
            }

            // Find suitable table
            Table table = tableDAO.findSuitableTable(guests);
            
            // Calculate total
            BigDecimal total = PRICE_PER_PERSON.multiply(new BigDecimal(guests));

            // Create booking
            Booking booking = new Booking();
            booking.setBookingCode(BookingCodeUtil.generateBookingCode());
            booking.setUserId(user.getUserId());
            booking.setBookingDate(bookingDate);
            booking.setBookingTime(bookingTime);
            booking.setGuests(guests);
            booking.setOccasion(occasion);
            booking.setSpecialRequests(notes);
            booking.setStatus("pending");
            booking.setTotalAmount(total);
            
            if (table != null) {
                booking.setTableId(table.getTableId());
                // Don't update table status yet - only when confirmed
            }

            boolean success = bookingDAO.createBooking(booking);

            if (success) {
                // Send email (async)
                final String code = booking.getBookingCode();
                new Thread(() -> {
                    EmailUtil.sendBookingConfirmation(
                        user.getEmail(), user.getFullName(), code, dateStr, timeStr, guests
                    );
                }).start();

                JSONObject result = new JSONObject();
                result.put("message", "Đặt bàn thành công! Mã đơn: " + code);
                result.put("bookingCode", code);
                result.put("bookingId", booking.getBookingId());
                
                System.out.println("✅ Booking created: " + code + " by user: " + user.getUserId());
                sendSuccess(response, result);
            } else {
                sendError(response, "Không thể tạo đặt bàn. Vui lòng thử lại.");
            }
            
        } catch (IllegalArgumentException e) {
            sendError(response, "Định dạng ngày/giờ không hợp lệ");
        }
    }

    /**
     * Cancel booking - WITH AUTHORIZATION CHECK
     */
    private void cancelBooking(User user, HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        // ✅ VALIDATE INPUT
        if (!json.has("bookingId")) {
            sendError(response, "Thiếu mã booking");
            return;
        }
        
        int bookingId = json.getInt("bookingId");
        String reason = json.optString("reason", "Khách hàng hủy");

        // ✅ VALIDATE REASON LENGTH
        if (reason.length() > 200) {
            sendError(response, "Lý do hủy không được quá 200 ký tự");
            return;
        }

        Booking booking = bookingDAO.getBookingById(bookingId);
        
        if (booking == null) {
            sendError(response, "Không tìm thấy đơn đặt bàn");
            return;
        }

        // ✅ CHECK AUTHORIZATION - User can only cancel their own bookings
        if (booking.getUserId() != user.getUserId() && !user.isAdminOrStaff()) {
            sendError(response, "Bạn không có quyền hủy đơn này");
            return;
        }

        // ✅ CHECK IF ALREADY CANCELLED
        if ("cancelled".equals(booking.getStatus())) {
            sendError(response, "Đơn đã được hủy trước đó");
            return;
        }

        // ✅ CHECK IF COMPLETED
        if ("completed".equals(booking.getStatus())) {
            sendError(response, "Không thể hủy đơn đã hoàn thành");
            return;
        }

        // ✅ CHECK CANCELLATION DEADLINE (24 hours before booking)
        long bookingTime = booking.getBookingDate().getTime();
        long now = System.currentTimeMillis();
        long hoursDiff = (bookingTime - now) / (1000 * 60 * 60);
        
        if (hoursDiff < 24 && !user.isAdminOrStaff()) {
            sendError(response, "Không thể hủy đơn trong vòng 24 giờ trước giờ đặt. Vui lòng liên hệ nhà hàng.");
            return;
        }

        boolean success = bookingDAO.cancelBooking(bookingId, reason);

        if (success) {
            // Free table if assigned
            if (booking.getTableId() != null) {
                tableDAO.updateTableStatus(booking.getTableId(), "available");
            }

            // Send email (async)
            final String code = booking.getBookingCode();
            new Thread(() -> {
                EmailUtil.sendCancellationEmail(user.getEmail(), user.getFullName(), code);
            }).start();

            System.out.println("⚠️ Booking cancelled: " + code + " by user: " + user.getUserId());
            sendSuccess(response, new JSONObject().put("message", "Đã hủy đặt bàn thành công"));
        } else {
            sendError(response, "Không thể hủy đặt bàn. Vui lòng liên hệ nhà hàng.");
        }
    }

    // ==================== HELPER METHODS ====================

    /**
     * Get logged in user
     */
    private User getLoggedInUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            return (User) session.getAttribute("user");
        }
        return null;
    }

    /**
     * Read JSON from request body
     */
    private JSONObject readJSON(HttpServletRequest request) throws IOException {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = request.getReader().readLine()) != null) {
            sb.append(line);
        }
        return new JSONObject(sb.toString());
    }

    /**
     * Send success response
     */
    private void sendSuccess(HttpServletResponse response, JSONObject data) throws IOException {
        data.put("success", true);
        response.getWriter().print(data.toString());
        response.getWriter().flush();
    }

    /**
     * Send error response
     */
    private void sendError(HttpServletResponse response, String message) throws IOException {
        JSONObject error = new JSONObject();
        error.put("success", false);
        error.put("message", message);
        response.getWriter().print(error.toString());
        response.getWriter().flush();
    }
}