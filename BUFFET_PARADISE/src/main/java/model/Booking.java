// ============================================
// BOOKING.JAVA - Model cho Booking
// ============================================
package model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Booking {
    
    // ===== PRIMARY FIELDS =====
    private int bookingId;
    private String bookingCode;
    private int userId;
    private Date bookingDate;
    private Time bookingTime;
    private int guests;
    private String occasion;
    private String specialRequests;
    private String status; // pending, confirmed, completed, cancelled, no_show
    private BigDecimal totalAmount;
    private Integer tableId; // Nullable
    
    // ===== TIMESTAMPS =====
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp cancelledAt;
    private String cancelReason;
    
    // ===== JOINED DATA (from users table) =====
    private String customerName;
    private String customerEmail;
    private String customerPhone;
    
    // ===== JOINED DATA (from tables table) =====
    private String tableNumber;
    private String zone;
    
    // ===== CONSTRUCTORS =====
    
    public Booking() {
        this.status = "pending";
        this.totalAmount = BigDecimal.ZERO;
    }
    
    public Booking(String bookingCode, int userId, Date bookingDate, Time bookingTime, 
                   int guests, BigDecimal totalAmount) {
        this.bookingCode = bookingCode;
        this.userId = userId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.guests = guests;
        this.totalAmount = totalAmount;
        this.status = "pending";
    }
    
    // ===== GETTERS & SETTERS =====
    
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public String getBookingCode() {
        return bookingCode;
    }
    
    public void setBookingCode(String bookingCode) {
        this.bookingCode = bookingCode;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Date getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }
    
    public Time getBookingTime() {
        return bookingTime;
    }
    
    public void setBookingTime(Time bookingTime) {
        this.bookingTime = bookingTime;
    }
    
    public int getGuests() {
        return guests;
    }
    
    public void setGuests(int guests) {
        this.guests = guests;
    }
    
    public String getOccasion() {
        return occasion;
    }
    
    public void setOccasion(String occasion) {
        this.occasion = occasion;
    }
    
    public String getSpecialRequests() {
        return specialRequests;
    }
    
    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public Integer getTableId() {
        return tableId;
    }
    
    public void setTableId(Integer tableId) {
        this.tableId = tableId;
    }
    
    public Timestamp getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
    
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Timestamp getCancelledAt() {
        return cancelledAt;
    }
    
    public void setCancelledAt(Timestamp cancelledAt) {
        this.cancelledAt = cancelledAt;
    }
    
    public String getCancelReason() {
        return cancelReason;
    }
    
    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }
    
    // ===== JOINED DATA GETTERS & SETTERS =====
    
    public String getCustomerName() {
        return customerName;
    }
    
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    
    public String getCustomerEmail() {
        return customerEmail;
    }
    
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
    
    public String getCustomerPhone() {
        return customerPhone;
    }
    
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
    
    public String getTableNumber() {
        return tableNumber;
    }
    
    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }
    
    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    // ===== HELPER METHODS =====
    
    /**
     * Check if booking is pending
     */
    public boolean isPending() {
        return "pending".equals(this.status);
    }
    
    /**
     * Check if booking is confirmed
     */
    public boolean isConfirmed() {
        return "confirmed".equals(this.status);
    }
    
    /**
     * Check if booking is completed
     */
    public boolean isCompleted() {
        return "completed".equals(this.status);
    }
    
    /**
     * Check if booking is cancelled
     */
    public boolean isCancelled() {
        return "cancelled".equals(this.status);
    }
    
    /**
     * Check if booking can be cancelled
     */
    public boolean canBeCancelled() {
        return "pending".equals(this.status) || "confirmed".equals(this.status);
    }
    
    /**
     * Check if booking can be modified
     */
    public boolean canBeModified() {
        return "pending".equals(this.status);
    }
    
    /**
     * Get formatted booking date time
     */
    public String getFormattedDateTime() {
        if (bookingDate != null && bookingTime != null) {
            return bookingDate.toString() + " " + bookingTime.toString();
        }
        return "";
    }
    
    /**
     * Get status display text
     */
    public String getStatusText() {
        switch (status) {
            case "pending":
                return "Chờ xác nhận";
            case "confirmed":
                return "Đã xác nhận";
            case "completed":
                return "Hoàn thành";
            case "cancelled":
                return "Đã hủy";
            case "no_show":
                return "Không đến";
            default:
                return status;
        }
    }
    
    /**
     * Get occasion display text
     */
    public String getOccasionText() {
        if (occasion == null || occasion.isEmpty()) {
            return "Không có";
        }
        
        switch (occasion) {
            case "birthday":
                return "Sinh nhật";
            case "anniversary":
                return "Kỷ niệm";
            case "business":
                return "Công tác";
            case "party":
                return "Tiệc nhóm";
            default:
                return occasion;
        }
    }
    
    // ===== TOSTRING =====
    
    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", bookingCode='" + bookingCode + '\'' +
                ", userId=" + userId +
                ", bookingDate=" + bookingDate +
                ", bookingTime=" + bookingTime +
                ", guests=" + guests +
                ", status='" + status + '\'' +
                ", totalAmount=" + totalAmount +
                ", tableId=" + tableId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}