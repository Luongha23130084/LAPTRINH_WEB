// ============================================
// ADMINBOOKINGSERVLET.JAVA - Admin Booking Management
// Sử dụng org.json (có sẵn trong Tomcat)
// ============================================
package servlet;

import dao.BookingDAO;
import dao.TableDAO;
import dao.UserDAO;
import model.Booking;
import model.Table;
import model.User;
import util.BookingCodeUtil;
import util.DateUtil;
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
import java.util.List;
import java.math.BigDecimal;

import org.json.JSONObject;
import org.json.JSONArray;

@WebServlet("/AdminBookingServlet")
public class AdminBookingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingDAO bookingDAO = new BookingDAO();
    private UserDAO userDAO = new UserDAO();
    private TableDAO tableDAO = new TableDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        if (!checkAdmin(request, response)) return;

        String action = request.getParameter("action");
        response.setContentType("application/json; charset=UTF-8");

        try {
            switch (action != null ? action : "") {
                case "list":
                    listBookings(response);
                    break;
                case "getCustomers":
                    getCustomers(response);
                    break;
                case "getTables":
                    getTables(response);
                    break;
                case "checkAvailability":
                    checkAvailability(request, response);
                    break;
                case "stats":
                    getStats(response);
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
        
        if (!checkAdmin(request, response)) return;

        String action = request.getParameter("action");
        response.setContentType("application/json; charset=UTF-8");

        try {
            switch (action != null ? action : "") {
                case "create":
                    createBooking(request, response);
                    break;
                case "update":
                    updateBooking(request, response);
                    break;
                case "updateStatus":
                    updateStatus(request, response);
                    break;
                case "cancel":
                    cancelBooking(request, response);
                    break;
                case "bulkUpdate":
                    bulkUpdate(request, response);
                    break;
                case "bulkCancel":
                    bulkCancel(request, response);
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
     * List all bookings
     */
    private void listBookings(HttpServletResponse response) throws IOException {
        List<Booking> bookings = bookingDAO.getAllBookings();
        
        JSONArray arr = new JSONArray();
        for (Booking b : bookings) {
            JSONObject obj = new JSONObject();
            obj.put("bookingId", b.getBookingId());
            obj.put("bookingCode", b.getBookingCode());
            obj.put("customerName", b.getCustomerName());
            obj.put("customerEmail", b.getCustomerEmail());
            obj.put("customerPhone", b.getCustomerPhone());
            obj.put("bookingDate", b.getBookingDate().toString());
            obj.put("bookingTime", b.getBookingTime().toString());
            obj.put("guests", b.getGuests());
            obj.put("status", b.getStatus());
            obj.put("totalAmount", b.getTotalAmount());
            obj.put("tableNumber", b.getTableNumber());
            obj.put("tableId", b.getTableId());
            obj.put("occasion", b.getOccasion());
            obj.put("specialRequests", b.getSpecialRequests());
            arr.put(obj);
        }
        
        JSONObject stats = new JSONObject();
        stats.put("today", bookingDAO.getTodayBookingsCount());
        stats.put("pending", bookingDAO.getBookingsByStatusCount("pending"));
        stats.put("confirmed", bookingDAO.getBookingsByStatusCount("confirmed"));
        stats.put("completed", bookingDAO.getBookingsByStatusCount("completed"));
        
        JSONObject result = new JSONObject();
        result.put("bookings", arr);
        result.put("stats", stats);
        
        sendSuccess(response, result);
    }

    /**
     * Get customers
     */
    private void getCustomers(HttpServletResponse response) throws IOException {
        List<User> customers = userDAO.getAllCustomers();
        
        JSONArray arr = new JSONArray();
        for (User c : customers) {
            JSONObject obj = new JSONObject();
            obj.put("userId", c.getUserId());
            obj.put("fullName", c.getFullName());
            obj.put("email", c.getEmail());
            obj.put("phone", c.getPhone());
            arr.put(obj);
        }
        
        sendSuccess(response, new JSONObject().put("customers", arr));
    }

    /**
     * Get tables
     */
    private void getTables(HttpServletResponse response) throws IOException {
        List<Table> tables = tableDAO.getAllAvailableTables();
        
        JSONArray arr = new JSONArray();
        for (Table t : tables) {
            JSONObject obj = new JSONObject();
            obj.put("tableId", t.getTableId());
            obj.put("tableNumber", t.getTableNumber());
            obj.put("capacity", t.getCapacity());
            arr.put(obj);
        }
        
        sendSuccess(response, new JSONObject().put("tables", arr));
    }

    /**
     * Check availability
     */
    private void checkAvailability(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        String dateStr = request.getParameter("date");
        String timeStr = request.getParameter("time");

        Date date = Date.valueOf(dateStr);
        Time time = Time.valueOf(timeStr + ":00");

        int available = bookingDAO.checkAvailability(date, time);
        
        sendSuccess(response, new JSONObject().put("available", available));
    }

    /**
     * Get stats
     */
    private void getStats(HttpServletResponse response) throws IOException {
        JSONObject stats = new JSONObject();
        stats.put("todayBookings", bookingDAO.getTodayBookingsCount());
        stats.put("todayRevenue", bookingDAO.getTodayRevenue());
        stats.put("pendingBookings", bookingDAO.getBookingsByStatusCount("pending"));
        stats.put("totalCustomers", userDAO.getTotalCustomersCount());
        stats.put("monthRevenue", bookingDAO.getMonthRevenue());
        stats.put("avgRating", bookingDAO.getAverageRating());
        
        sendSuccess(response, new JSONObject().put("stats", stats));
    }

    // ==================== POST METHODS ====================

    /**
     * Create booking
     */
    private void createBooking(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        int customerId = json.getInt("customerId");
        String dateStr = json.getString("date");
        String timeStr = json.getString("time");
        int guests = json.getInt("guests");
        String occasion = json.optString("occasion", null);
        String notes = json.optString("notes", null);
        Integer tableId = json.has("tableId") && !json.isNull("tableId") 
            ? json.getInt("tableId") : null;

        Date bookingDate = Date.valueOf(dateStr);
        Time bookingTime = Time.valueOf(timeStr + ":00");

        // Check availability
        int available = bookingDAO.checkAvailability(bookingDate, bookingTime);
        if (available <= 0) {
            sendError(response, "Không còn bàn trống");
            return;
        }

        // Calculate total
        BigDecimal total = new BigDecimal("299000").multiply(new BigDecimal(guests));

        // Create booking
        Booking booking = new Booking();
        booking.setBookingCode(BookingCodeUtil.generateBookingCode());
        booking.setUserId(customerId);
        booking.setBookingDate(bookingDate);
        booking.setBookingTime(bookingTime);
        booking.setGuests(guests);
        booking.setOccasion(occasion);
        booking.setSpecialRequests(notes);
        booking.setStatus("confirmed");
        booking.setTotalAmount(total);
        booking.setTableId(tableId);

        boolean success = bookingDAO.createBooking(booking);

        if (success) {
            // Send email
            User customer = userDAO.findById(customerId);
            if (customer != null) {
                final String code = booking.getBookingCode();
                new Thread(() -> {
                    EmailUtil.sendBookingConfirmation(
                        customer.getEmail(), customer.getFullName(), code,
                        DateUtil.formatDate(bookingDate),
                        DateUtil.formatTime(bookingTime), guests
                    );
                }).start();
            }

            JSONObject result = new JSONObject();
            result.put("message", "Đã tạo đặt bàn thành công");
            result.put("bookingCode", booking.getBookingCode());
            
            sendSuccess(response, result);
        } else {
            sendError(response, "Không thể tạo đặt bàn");
        }
    }

    /**
     * Update booking
     */
    private void updateBooking(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        int bookingId = json.getInt("bookingId");
        Booking booking = bookingDAO.getBookingById(bookingId);
        
        if (booking == null) {
            sendError(response, "Không tìm thấy đơn");
            return;
        }

        booking.setBookingDate(Date.valueOf(json.getString("date")));
        booking.setBookingTime(Time.valueOf(json.getString("time") + ":00"));
        booking.setGuests(json.getInt("guests"));
        booking.setStatus(json.getString("status"));
        booking.setSpecialRequests(json.getString("notes"));
        
        if (json.has("tableId") && !json.getString("tableId").isEmpty()) {
            booking.setTableId(Integer.parseInt(json.getString("tableId")));
        }

        BigDecimal total = new BigDecimal("299000").multiply(new BigDecimal(booking.getGuests()));
        booking.setTotalAmount(total);

        boolean success = bookingDAO.updateBooking(booking);

        JSONObject result = new JSONObject();
        result.put("message", success ? "Đã cập nhật" : "Cập nhật thất bại");
        sendSuccess(response, result);
    }

    /**
     * Update status
     */
    private void updateStatus(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        int bookingId = json.getInt("bookingId");
        String status = json.getString("status");

        boolean success = bookingDAO.updateBookingStatus(bookingId, status);

        sendSuccess(response, new JSONObject().put("message", 
            success ? "Đã cập nhật trạng thái" : "Thất bại"));
    }

    /**
     * Cancel booking
     */
    private void cancelBooking(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        int bookingId = json.getInt("bookingId");
        String reason = json.getString("reason");

        Booking booking = bookingDAO.getBookingById(bookingId);
        boolean success = bookingDAO.cancelBooking(bookingId, reason);

        if (success && booking != null) {
            final String code = booking.getBookingCode();
            new Thread(() -> {
                EmailUtil.sendCancellationEmail(
                    booking.getCustomerEmail(), booking.getCustomerName(), code
                );
            }).start();
        }

        sendSuccess(response, new JSONObject().put("message", 
            success ? "Đã hủy đặt bàn" : "Thất bại"));
    }

    /**
     * Bulk update
     */
    private void bulkUpdate(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        JSONArray ids = json.getJSONArray("bookingIds");
        String status = json.getString("status");

        int updated = 0;
        for (int i = 0; i < ids.length(); i++) {
            if (bookingDAO.updateBookingStatus(ids.getInt(i), status)) {
                updated++;
            }
        }

        sendSuccess(response, new JSONObject()
            .put("updated", updated)
            .put("message", "Đã cập nhật " + updated + " đơn"));
    }

    /**
     * Bulk cancel
     */
    private void bulkCancel(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        
        JSONObject json = readJSON(request);
        
        JSONArray ids = json.getJSONArray("bookingIds");
        String reason = json.getString("reason");

        int updated = 0;
        for (int i = 0; i < ids.length(); i++) {
            if (bookingDAO.cancelBooking(ids.getInt(i), reason)) {
                updated++;
            }
        }

        sendSuccess(response, new JSONObject()
            .put("updated", updated)
            .put("message", "Đã hủy " + updated + " đơn"));
    }

    // ==================== HELPER METHODS ====================

    /**
     * Check admin
     */
    private boolean checkAdmin(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null) {
            sendError(response, "Unauthorized");
            return false;
        }

        User user = (User) session.getAttribute("user");
        if (user == null || !user.isAdmin()) {
            sendError(response, "Admin only");
            return false;
        }

        return true;
    }

    /**
     * Read JSON
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
     * Send success
     */
    private void sendSuccess(HttpServletResponse response, JSONObject data) throws IOException {
        data.put("success", true);
        response.getWriter().print(data.toString());
        response.getWriter().flush();
    }

    /**
     * Send error
     */
    private void sendError(HttpServletResponse response, String message) throws IOException {
        JSONObject error = new JSONObject();
        error.put("success", false);
        error.put("message", message);
        response.getWriter().print(error.toString());
        response.getWriter().flush();
    }
}