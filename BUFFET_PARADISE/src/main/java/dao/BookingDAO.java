// ============================================
// BOOKINGDAO.JAVA - SQL SERVER VERSION
// ============================================
package dao;

import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Booking;
import java.math.BigDecimal;

public class BookingDAO {

    // ==================== CUSTOMER METHODS ====================
    
    /**
     * Create new booking
     */
    public boolean createBooking(Booking booking) {
        String sql = "INSERT INTO bookings (booking_code, user_id, booking_date, booking_time, " +
                    "guests, occasion, special_requests, status, total_amount, table_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, booking.getBookingCode());
            pstmt.setInt(2, booking.getUserId());
            pstmt.setDate(3, booking.getBookingDate());
            pstmt.setTime(4, booking.getBookingTime());
            pstmt.setInt(5, booking.getGuests());
            pstmt.setString(6, booking.getOccasion());
            pstmt.setString(7, booking.getSpecialRequests());
            pstmt.setString(8, booking.getStatus());
            pstmt.setBigDecimal(9, booking.getTotalAmount());
            
            if (booking.getTableId() != null) {
                pstmt.setInt(10, booking.getTableId());
            } else {
                pstmt.setNull(10, Types.INTEGER);
            }
            
            int affected = pstmt.executeUpdate();
            
            if (affected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        booking.setBookingId(rs.getInt(1));
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get booking by ID with full details
     */
    public Booking getBookingById(int bookingId) {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "WHERE b.booking_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractBookingFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get booking by code
     */
    public Booking getBookingByCode(String bookingCode) {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "WHERE b.booking_code = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, bookingCode);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractBookingFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all bookings by user
     */
    public List<Booking> getBookingsByUser(int userId) {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "WHERE b.user_id = ? " +
                    "ORDER BY b.booking_date DESC, b.booking_time DESC";
        
        List<Booking> bookings = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(extractBookingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }

    /**
     * Get bookings by user and status
     */
    public List<Booking> getBookingsByStatus(int userId, String status) {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "WHERE b.user_id = ? AND b.status = ? " +
                    "ORDER BY b.booking_date DESC, b.booking_time DESC";
        
        List<Booking> bookings = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            pstmt.setString(2, status);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(extractBookingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }

    /**
     * Update booking
     */
    public boolean updateBooking(Booking booking) {
        String sql = "UPDATE bookings SET booking_date = ?, booking_time = ?, " +
                    "guests = ?, occasion = ?, special_requests = ?, " +
                    "total_amount = ?, table_id = ?, status = ? " +
                    "WHERE booking_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, booking.getBookingDate());
            pstmt.setTime(2, booking.getBookingTime());
            pstmt.setInt(3, booking.getGuests());
            pstmt.setString(4, booking.getOccasion());
            pstmt.setString(5, booking.getSpecialRequests());
            pstmt.setBigDecimal(6, booking.getTotalAmount());
            
            if (booking.getTableId() != null) {
                pstmt.setInt(7, booking.getTableId());
            } else {
                pstmt.setNull(7, Types.INTEGER);
            }
            
            pstmt.setString(8, booking.getStatus());
            pstmt.setInt(9, booking.getBookingId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update booking status
     */
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE bookings SET status = ? WHERE booking_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, bookingId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cancel booking - SQL Server uses GETDATE() instead of CURRENT_TIMESTAMP
     */
    public boolean cancelBooking(int bookingId, String reason) {
        String sql = "UPDATE bookings SET status = 'cancelled', " +
                    "cancelled_at = GETDATE(), cancel_reason = ? " +
                    "WHERE booking_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, reason);
            pstmt.setInt(2, bookingId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Check availability for date and time
     */
    public int checkAvailability(Date date, Time time) {
        String sql = "SELECT available_tables FROM availability_slots " +
                    "WHERE date = ? AND time_slot = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, date);
            pstmt.setTime(2, time);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("available_tables");
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get upcoming bookings count for user - SQL Server uses CAST(GETDATE() AS DATE)
     */
    public int getUpcomingBookingsCount(int userId) {
        String sql = "SELECT COUNT(*) FROM bookings " +
                    "WHERE user_id = ? AND status IN ('pending', 'confirmed') " +
                    "AND booking_date >= CAST(GETDATE() AS DATE)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    // ==================== ADMIN METHODS ====================
    
    /**
     * Get ALL bookings (for admin)
     */
    public List<Booking> getAllBookings() {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "ORDER BY b.created_at DESC";
        
        List<Booking> bookings = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(extractBookingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }

    /**
     * Get today's bookings count - SQL Server
     */
    public int getTodayBookingsCount() {
        String sql = "SELECT COUNT(*) FROM bookings WHERE booking_date = CAST(GETDATE() AS DATE)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get bookings count by status
     */
    public int getBookingsByStatusCount(String status) {
        String sql = "SELECT COUNT(*) FROM bookings WHERE status = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Get today's revenue - SQL Server uses ISNULL instead of COALESCE
     */
    public BigDecimal getTodayRevenue() {
        String sql = "SELECT ISNULL(SUM(total_amount), 0) FROM bookings " +
                    "WHERE booking_date = CAST(GETDATE() AS DATE) AND status IN ('confirmed', 'completed')";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
            return BigDecimal.ZERO;
        } catch (SQLException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    /**
     * Get this month's revenue - SQL Server date functions
     */
    public BigDecimal getMonthRevenue() {
        String sql = "SELECT ISNULL(SUM(total_amount), 0) FROM bookings " +
                    "WHERE MONTH(booking_date) = MONTH(GETDATE()) " +
                    "AND YEAR(booking_date) = YEAR(GETDATE()) " +
                    "AND status IN ('confirmed', 'completed')";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBigDecimal(1);
            }
            return BigDecimal.ZERO;
        } catch (SQLException e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }

    /**
     * Get average rating
     */
    public double getAverageRating() {
        String sql = "SELECT ISNULL(AVG(CAST(rating AS FLOAT)), 0) FROM reviews WHERE status = 'approved'";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble(1);
            }
            return 0.0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    /**
     * Get bookings by date range
     */
    public List<Booking> getBookingsByDateRange(Date startDate, Date endDate) {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "WHERE b.booking_date BETWEEN ? AND ? " +
                    "ORDER BY b.booking_date, b.booking_time";
        
        List<Booking> bookings = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, startDate);
            pstmt.setDate(2, endDate);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(extractBookingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }

    /**
     * Search bookings by keyword
     */
    public List<Booking> searchBookings(String keyword) {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "WHERE b.booking_code LIKE ? OR u.full_name LIKE ? OR u.phone LIKE ? " +
                    "ORDER BY b.created_at DESC";
        
        List<Booking> bookings = new ArrayList<>();
        String searchPattern = "%" + keyword + "%";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(extractBookingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }

    /**
     * Get bookings for specific date
     */
    public List<Booking> getBookingsByDate(Date date) {
        String sql = "SELECT b.*, u.full_name, u.email, u.phone, t.table_number, t.zone " +
                    "FROM bookings b " +
                    "JOIN users u ON b.user_id = u.user_id " +
                    "LEFT JOIN tables t ON b.table_id = t.table_id " +
                    "WHERE b.booking_date = ? " +
                    "ORDER BY b.booking_time";
        
        List<Booking> bookings = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDate(1, date);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                bookings.add(extractBookingFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }

    /**
     * Delete booking (admin only, for testing)
     */
    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM bookings WHERE booking_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==================== HELPER METHODS ====================
    
    /**
     * Extract Booking object from ResultSet
     */
    private Booking extractBookingFromResultSet(ResultSet rs) throws SQLException {
        Booking booking = new Booking();
        
        booking.setBookingId(rs.getInt("booking_id"));
        booking.setBookingCode(rs.getString("booking_code"));
        booking.setUserId(rs.getInt("user_id"));
        booking.setBookingDate(rs.getDate("booking_date"));
        booking.setBookingTime(rs.getTime("booking_time"));
        booking.setGuests(rs.getInt("guests"));
        booking.setOccasion(rs.getString("occasion"));
        booking.setSpecialRequests(rs.getString("special_requests"));
        booking.setStatus(rs.getString("status"));
        booking.setTotalAmount(rs.getBigDecimal("total_amount"));
        booking.setCreatedAt(rs.getTimestamp("created_at"));
        booking.setUpdatedAt(rs.getTimestamp("updated_at"));
        booking.setCancelledAt(rs.getTimestamp("cancelled_at"));
        booking.setCancelReason(rs.getString("cancel_reason"));
        
        int tableId = rs.getInt("table_id");
        if (!rs.wasNull()) {
            booking.setTableId(tableId);
        }
        
        booking.setCustomerName(rs.getString("full_name"));
        booking.setCustomerEmail(rs.getString("email"));
        booking.setCustomerPhone(rs.getString("phone"));
        booking.setTableNumber(rs.getString("table_number"));
        booking.setZone(rs.getString("zone"));
        
        return booking;
    }
}