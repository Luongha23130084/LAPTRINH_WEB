// ============================================
// TABLE.JAVA - Model cho Table
// ============================================
package model;

import java.sql.Timestamp;

public class Table {
    
    // ===== PRIMARY FIELDS =====
    private int tableId;
    private String tableNumber;
    private int capacity;
    private String floor; // Ground, First, Second
    private String zone; // Indoor, Outdoor, VIP, Window
    private String status; // available, reserved, occupied, maintenance
    private int positionX; // For visual layout
    private int positionY; // For visual layout
    
    // ===== TIMESTAMPS =====
    private Timestamp createdAt;
    private Timestamp updatedAt;
    
    // ===== CONSTRUCTORS =====
    
    public Table() {
        this.status = "available";
        this.floor = "Ground";
        this.zone = "Indoor";
        this.positionX = 0;
        this.positionY = 0;
    }
    
    public Table(String tableNumber, int capacity) {
        this();
        this.tableNumber = tableNumber;
        this.capacity = capacity;
    }
    
    public Table(String tableNumber, int capacity, String floor, String zone) {
        this(tableNumber, capacity);
        this.floor = floor;
        this.zone = zone;
    }
    
    // ===== GETTERS & SETTERS =====
    
    public int getTableId() {
        return tableId;
    }
    
    public void setTableId(int tableId) {
        this.tableId = tableId;
    }
    
    public String getTableNumber() {
        return tableNumber;
    }
    
    public void setTableNumber(String tableNumber) {
        this.tableNumber = tableNumber;
    }
    
    public int getCapacity() {
        return capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    public String getFloor() {
        return floor;
    }
    
    public void setFloor(String floor) {
        this.floor = floor;
    }
    
    public String getZone() {
        return zone;
    }
    
    public void setZone(String zone) {
        this.zone = zone;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public int getPositionX() {
        return positionX;
    }
    
    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }
    
    public int getPositionY() {
        return positionY;
    }
    
    public void setPositionY(int positionY) {
        this.positionY = positionY;
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
    
    // ===== HELPER METHODS =====
    
    /**
     * Check if table is available
     */
    public boolean isAvailable() {
        return "available".equalsIgnoreCase(this.status);
    }
    
    /**
     * Check if table is reserved
     */
    public boolean isReserved() {
        return "reserved".equalsIgnoreCase(this.status);
    }
    
    /**
     * Check if table is occupied
     */
    public boolean isOccupied() {
        return "occupied".equalsIgnoreCase(this.status);
    }
    
    /**
     * Check if table is under maintenance
     */
    public boolean isUnderMaintenance() {
        return "maintenance".equalsIgnoreCase(this.status);
    }
    
    /**
     * Check if table can accommodate the number of guests
     */
    public boolean canAccommodate(int guests) {
        return this.capacity >= guests;
    }
    
    /**
     * Check if table can be booked
     */
    public boolean canBeBooked() {
        return isAvailable();
    }
    
    /**
     * Get status display text (Vietnamese)
     */
    public String getStatusText() {
        switch (status.toLowerCase()) {
            case "available":
                return "Trống";
            case "reserved":
                return "Đã đặt";
            case "occupied":
                return "Đang sử dụng";
            case "maintenance":
                return "Bảo trì";
            default:
                return status;
        }
    }
    
    /**
     * Get floor display text (Vietnamese)
     */
    public String getFloorText() {
        switch (floor.toLowerCase()) {
            case "ground":
                return "Tầng trệt";
            case "first":
                return "Tầng 1";
            case "second":
                return "Tầng 2";
            case "third":
                return "Tầng 3";
            default:
                return floor;
        }
    }
    
    /**
     * Get zone display text (Vietnamese)
     */
    public String getZoneText() {
        switch (zone.toLowerCase()) {
            case "indoor":
                return "Trong nhà";
            case "outdoor":
                return "Ngoài trời";
            case "vip":
                return "Phòng VIP";
            case "window":
                return "Cạnh cửa sổ";
            case "garden":
                return "Khu vườn";
            case "balcony":
                return "Ban công";
            default:
                return zone;
        }
    }
    
    /**
     * Get capacity range display
     */
    public String getCapacityRange() {
        if (capacity <= 2) {
            return "1-2 người";
        } else if (capacity <= 4) {
            return "3-4 người";
        } else if (capacity <= 6) {
            return "5-6 người";
        } else if (capacity <= 8) {
            return "7-8 người";
        } else {
            return capacity + "+ người";
        }
    }
    
    /**
     * Get table type based on capacity
     */
    public String getTableType() {
        if (capacity <= 2) {
            return "Bàn đôi";
        } else if (capacity <= 4) {
            return "Bàn nhỏ";
        } else if (capacity <= 6) {
            return "Bàn vừa";
        } else if (capacity <= 8) {
            return "Bàn lớn";
        } else {
            return "Bàn tiệc";
        }
    }
    
    /**
     * Get status badge color for UI
     */
    public String getStatusBadgeColor() {
        switch (status.toLowerCase()) {
            case "available":
                return "success";
            case "reserved":
                return "warning";
            case "occupied":
                return "danger";
            case "maintenance":
                return "secondary";
            default:
                return "secondary";
        }
    }
    
    /**
     * Get zone icon for UI
     */
    public String getZoneIcon() {
        switch (zone.toLowerCase()) {
            case "indoor":
                return "🏠";
            case "outdoor":
                return "🌳";
            case "vip":
                return "👑";
            case "window":
                return "🪟";
            case "garden":
                return "🌿";
            case "balcony":
                return "🏞️";
            default:
                return "🍽️";
        }
    }
    
    /**
     * Get full table display name
     */
    public String getDisplayName() {
        return String.format("Bàn %s (%s - %s)", 
            tableNumber, getFloorText(), getZoneText());
    }
    
    /**
     * Get short display name
     */
    public String getShortDisplayName() {
        return "Bàn " + tableNumber;
    }
    
    /**
     * Get table info summary
     */
    public String getInfoSummary() {
        return String.format("%s - %d chỗ - %s - %s", 
            getShortDisplayName(), capacity, getFloorText(), getZoneText());
    }
    
    /**
     * Check if table is on ground floor
     */
    public boolean isGroundFloor() {
        return "ground".equalsIgnoreCase(floor);
    }
    
    /**
     * Check if table is in VIP zone
     */
    public boolean isVIP() {
        return "vip".equalsIgnoreCase(zone);
    }
    
    /**
     * Check if table is outdoor
     */
    public boolean isOutdoor() {
        return "outdoor".equalsIgnoreCase(zone);
    }
    
    /**
     * Check if table is indoor
     */
    public boolean isIndoor() {
        return "indoor".equalsIgnoreCase(zone);
    }
    
    /**
     * Check if table has window view
     */
    public boolean hasWindowView() {
        return "window".equalsIgnoreCase(zone);
    }
    
    /**
     * Calculate suitability score for guest count
     * Returns 0-100, higher is better match
     */
    public int getSuitabilityScore(int guests) {
        if (!canAccommodate(guests)) {
            return 0;
        }
        
        // Perfect match
        if (capacity == guests) {
            return 100;
        }
        
        // Slight overflow (1-2 extra seats)
        int difference = capacity - guests;
        if (difference <= 2) {
            return 90 - (difference * 5);
        }
        
        // Moderate overflow (3-4 extra seats)
        if (difference <= 4) {
            return 70 - (difference * 5);
        }
        
        // Large overflow (5+ extra seats)
        return Math.max(30, 60 - (difference * 3));
    }
    
    /**
     * Validate table data
     */
    public boolean isValid() {
        return tableNumber != null && !tableNumber.trim().isEmpty() &&
               capacity > 0 && capacity <= 20 &&
               floor != null && !floor.trim().isEmpty() &&
               zone != null && !zone.trim().isEmpty() &&
               status != null && !status.trim().isEmpty();
    }
    
    /**
     * Get position as coordinate string
     */
    public String getPosition() {
        return String.format("(%d, %d)", positionX, positionY);
    }
    
    /**
     * Set position from coordinates
     */
    public void setPosition(int x, int y) {
        this.positionX = x;
        this.positionY = y;
    }
    
    /**
     * Clone table object
     */
    public Table clone() {
        Table cloned = new Table();
        cloned.setTableId(this.tableId);
        cloned.setTableNumber(this.tableNumber);
        cloned.setCapacity(this.capacity);
        cloned.setFloor(this.floor);
        cloned.setZone(this.zone);
        cloned.setStatus(this.status);
        cloned.setPositionX(this.positionX);
        cloned.setPositionY(this.positionY);
        cloned.setCreatedAt(this.createdAt);
        cloned.setUpdatedAt(this.updatedAt);
        return cloned;
    }
    
    // ===== TOSTRING =====
    
    @Override
    public String toString() {
        return "Table{" +
                "tableId=" + tableId +
                ", tableNumber='" + tableNumber + '\'' +
                ", capacity=" + capacity +
                ", floor='" + floor + '\'' +
                ", zone='" + zone + '\'' +
                ", status='" + status + '\'' +
                ", position=(" + positionX + "," + positionY + ")" +
                '}';
    }
    
    /**
     * To string with Vietnamese text
     */
    public String toStringVN() {
        return String.format("Bàn %s - %d chỗ - %s - %s - %s",
                tableNumber, capacity, getFloorText(), 
                getZoneText(), getStatusText());
    }
    
    // ===== EQUALS & HASHCODE =====
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Table table = (Table) o;
        return tableId == table.tableId;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(tableId);
    }
    
    // ===== STATIC HELPER METHODS =====
    
    /**
     * Validate table number format
     */
    public static boolean isValidTableNumber(String tableNumber) {
        if (tableNumber == null || tableNumber.trim().isEmpty()) {
            return false;
        }
        // Format: A1, B12, etc. (Letter + Number)
        return tableNumber.matches("^[A-Z]\\d{1,3}$");
    }
    
    /**
     * Validate capacity
     */
    public static boolean isValidCapacity(int capacity) {
        return capacity > 0 && capacity <= 20;
    }
    
    /**
     * Validate floor
     */
    public static boolean isValidFloor(String floor) {
        return floor != null && 
               (floor.equalsIgnoreCase("Ground") || 
                floor.equalsIgnoreCase("First") || 
                floor.equalsIgnoreCase("Second") ||
                floor.equalsIgnoreCase("Third"));
    }
    
    /**
     * Validate zone
     */
    public static boolean isValidZone(String zone) {
        return zone != null && 
               (zone.equalsIgnoreCase("Indoor") || 
                zone.equalsIgnoreCase("Outdoor") || 
                zone.equalsIgnoreCase("VIP") ||
                zone.equalsIgnoreCase("Window") ||
                zone.equalsIgnoreCase("Garden") ||
                zone.equalsIgnoreCase("Balcony"));
    }
    
    /**
     * Validate status
     */
    public static boolean isValidStatus(String status) {
        return status != null && 
               (status.equalsIgnoreCase("available") || 
                status.equalsIgnoreCase("reserved") || 
                status.equalsIgnoreCase("occupied") ||
                status.equalsIgnoreCase("maintenance"));
    }
}