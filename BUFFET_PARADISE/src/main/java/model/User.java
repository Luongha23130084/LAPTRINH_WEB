// ============================================
// USER.JAVA - Model cho User
// ============================================
package model;

import java.sql.Timestamp;
import java.util.Date;

public class User {
    
    // ===== PRIMARY FIELDS =====
    private int userId;
    private String fullName;
    private String email;
    private String phone;
    private String passwordHash;
    private Date dateOfBirth;
    private String role; // customer, admin, staff
    private String status; // active, inactive, banned
    
    // ===== PROFILE FIELDS =====
    private String address;
    private String avatar;
    private String gender; // male, female, other
    
    // ===== TIMESTAMPS =====
    private Timestamp createdAt;
    private Timestamp updatedAt;
    private Timestamp lastLogin;
    
    // ===== CONSTRUCTORS =====
    
    public User() {
        this.role = "customer";
        this.status = "active";
    }
    
    public User(String fullName, String email, String passwordHash) {
        this();
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
    }
    
    public User(String fullName, String email, String phone, String passwordHash) {
        this(fullName, email, passwordHash);
        this.phone = phone;
    }
    
    // ===== GETTERS & SETTERS =====
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getFullName() {
        return fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getPasswordHash() {
        return passwordHash;
    }
    
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAvatar() {
        return avatar;
    }
    
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
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
    
    public Timestamp getLastLogin() {
        return lastLogin;
    }
    
    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    // ===== HELPER METHODS =====
    
    /**
     * Check if user is admin
     */
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(this.role);
    }
    
    /**
     * Check if user is staff
     */
    public boolean isStaff() {
        return "staff".equalsIgnoreCase(this.role);
    }
    
    /**
     * Check if user is customer
     */
    public boolean isCustomer() {
        return "customer".equalsIgnoreCase(this.role);
    }
    
    /**
     * Check if user is active
     */
    public boolean isActive() {
        return "active".equalsIgnoreCase(this.status);
    }
    
    /**
     * Check if user is banned
     */
    public boolean isBanned() {
        return "banned".equalsIgnoreCase(this.status);
    }
    
    /**
     * Check if user has admin or staff role
     */
    public boolean isAdminOrStaff() {
        return isAdmin() || isStaff();
    }
    
    /**
     * Get role display text (Vietnamese)
     */
    public String getRoleText() {
        switch (role.toLowerCase()) {
            case "admin":
                return "Quản trị viên";
            case "staff":
                return "Nhân viên";
            case "customer":
                return "Khách hàng";
            default:
                return role;
        }
    }
    
    /**
     * Get status display text (Vietnamese)
     */
    public String getStatusText() {
        switch (status.toLowerCase()) {
            case "active":
                return "Hoạt động";
            case "inactive":
                return "Không hoạt động";
            case "banned":
                return "Bị khóa";
            default:
                return status;
        }
    }
    
    /**
     * Get gender display text (Vietnamese)
     */
    public String getGenderText() {
        if (gender == null) return "Chưa cập nhật";
        
        switch (gender.toLowerCase()) {
            case "male":
                return "Nam";
            case "female":
                return "Nữ";
            case "other":
                return "Khác";
            default:
                return gender;
        }
    }
    
    /**
     * Get first name from full name
     */
    public String getFirstName() {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "";
        }
        String[] parts = fullName.trim().split("\\s+");
        return parts[parts.length - 1];
    }
    
    /**
     * Get initials for avatar
     */
    public String getInitials() {
        if (fullName == null || fullName.trim().isEmpty()) {
            return "?";
        }
        
        String[] parts = fullName.trim().split("\\s+");
        if (parts.length == 1) {
            return parts[0].substring(0, Math.min(2, parts[0].length())).toUpperCase();
        } else {
            return (parts[0].charAt(0) + "" + parts[parts.length - 1].charAt(0)).toUpperCase();
        }
    }
    
    /**
     * Get avatar URL or default
     */
    public String getAvatarUrl() {
        if (avatar != null && !avatar.trim().isEmpty()) {
            return avatar;
        }
        // Return default avatar URL based on gender
        if ("male".equalsIgnoreCase(gender)) {
            return "/images/avatar-male.png";
        } else if ("female".equalsIgnoreCase(gender)) {
            return "/images/avatar-female.png";
        }
        return "/images/avatar-default.png";
    }
    
    /**
     * Get formatted phone number
     */
    public String getFormattedPhone() {
        if (phone == null || phone.isEmpty()) {
            return "Chưa cập nhật";
        }
        
        // Format: 0123 456 789
        String cleaned = phone.replaceAll("[^0-9]", "");
        if (cleaned.length() == 10) {
            return cleaned.substring(0, 4) + " " + 
                   cleaned.substring(4, 7) + " " + 
                   cleaned.substring(7);
        }
        return phone;
    }
    
    /**
     * Get age from date of birth
     */
    public Integer getAge() {
        if (dateOfBirth == null) {
            return null;
        }
        
        Date now = new Date();
        long diff = now.getTime() - dateOfBirth.getTime();
        return (int) (diff / (1000L * 60 * 60 * 24 * 365));
    }
    
    /**
     * Check if user profile is complete
     */
    public boolean isProfileComplete() {
        return fullName != null && !fullName.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               phone != null && !phone.trim().isEmpty() &&
               dateOfBirth != null;
    }
    
    /**
     * Get profile completion percentage
     */
    public int getProfileCompletionPercentage() {
        int completed = 0;
        int total = 7;
        
        if (fullName != null && !fullName.trim().isEmpty()) completed++;
        if (email != null && !email.trim().isEmpty()) completed++;
        if (phone != null && !phone.trim().isEmpty()) completed++;
        if (dateOfBirth != null) completed++;
        if (address != null && !address.trim().isEmpty()) completed++;
        if (avatar != null && !avatar.trim().isEmpty()) completed++;
        if (gender != null && !gender.trim().isEmpty()) completed++;
        
        return (completed * 100) / total;
    }
    
    /**
     * Check if user can make bookings
     */
    public boolean canMakeBookings() {
        return isActive() && isCustomer();
    }
    
    /**
     * Check if user can access admin panel
     */
    public boolean canAccessAdminPanel() {
        return isActive() && isAdminOrStaff();
    }
    
    /**
     * Validate user data
     */
    public boolean isValid() {
        return fullName != null && !fullName.trim().isEmpty() &&
               email != null && !email.trim().isEmpty() &&
               passwordHash != null && !passwordHash.trim().isEmpty() &&
               role != null && !role.trim().isEmpty() &&
               status != null && !status.trim().isEmpty();
    }
    
    /**
     * Mask email for display
     */
    public String getMaskedEmail() {
        if (email == null || !email.contains("@")) {
            return email;
        }
        
        String[] parts = email.split("@");
        String local = parts[0];
        String domain = parts[1];
        
        if (local.length() <= 3) {
            return local.charAt(0) + "***@" + domain;
        }
        
        return local.substring(0, 2) + "***" + local.charAt(local.length() - 1) + "@" + domain;
    }
    
    /**
     * Get status badge color
     */
    public String getStatusBadgeColor() {
        switch (status.toLowerCase()) {
            case "active":
                return "success";
            case "inactive":
                return "warning";
            case "banned":
                return "danger";
            default:
                return "secondary";
        }
    }
    
    /**
     * Get role badge color
     */
    public String getRoleBadgeColor() {
        switch (role.toLowerCase()) {
            case "admin":
                return "danger";
            case "staff":
                return "info";
            case "customer":
                return "primary";
            default:
                return "secondary";
        }
    }
    
    // ===== TOSTRING =====
    
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
    
    /**
     * To string without sensitive data
     */
    public String toStringSafe() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + getMaskedEmail() + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
    
    // ===== EQUALS & HASHCODE =====
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        User user = (User) o;
        return userId == user.userId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(userId);
    }
}