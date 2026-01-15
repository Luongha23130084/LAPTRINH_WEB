package dao;

import model.Reservation;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

    /**
     * Create new reservation with validation
     */
    public boolean create(Reservation r) {
        // ✅ VALIDATION
        if (r.getGuests() <= 0 || r.getGuests() > 50) {
            System.err.println("Invalid guest count: " + r.getGuests());
            return false;
        }
        
        String sql = "INSERT INTO reservations(customer_id, reservation_date, reservation_time, " +
                    "guests, area, status, created_at) " +
                    "VALUES(?, ?, ?, ?, ?, 'pending', GETDATE())";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, r.getCustomerId());
            ps.setDate(2, r.getReservationDate());
            ps.setTime(3, r.getReservationTime());
            ps.setInt(4, r.getGuests());
            ps.setString(5, r.getArea());
            
            int affected = ps.executeUpdate();
            
            if (affected > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        r.setReservationId(rs.getInt(1));
                        return true;
                    }
                }
            }
            return false;
            
        } catch (SQLException e) {
            System.err.println("Error creating reservation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Find reservations by customer with authorization check
     */
    public List<Reservation> findByCustomer(int customerId) {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE customer_id = ? ORDER BY reservation_date DESC";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reservation r = extractReservation(rs);
                list.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error finding reservations by customer: " + e.getMessage());
            e.printStackTrace();
        }
        
        return list;
    }

    /**
     * Find pending reservations
     */
    public List<Reservation> findPending() {
        List<Reservation> list = new ArrayList<>();
        String sql = "SELECT * FROM reservations WHERE status = 'pending' " +
                    "ORDER BY reservation_date, reservation_time";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Reservation r = extractReservation(rs);
                list.add(r);
            }
        } catch (SQLException e) {
            System.err.println("Error finding pending reservations: " + e.getMessage());
            e.printStackTrace();
        }
        
        return list;
    }
    
    /**
     * Find reservation by ID
     */
    public Reservation findById(int reservationId) {
        String sql = "SELECT * FROM reservations WHERE reservation_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return extractReservation(rs);
            }
        } catch (SQLException e) {
            System.err.println("Error finding reservation by ID: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    /**
     * Update reservation status with transaction
     */
    public boolean updateStatus(int id, String status) {
        // ✅ VALIDATE STATUS
        if (!isValidStatus(status)) {
            System.err.println("Invalid status: " + status);
            return false;
        }
        
        String sql = "UPDATE reservations SET status = ?, updated_at = GETDATE() " +
                    "WHERE reservation_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setString(1, status);
            ps.setInt(2, id);
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating reservation status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Update reservation
     */
    public boolean update(Reservation r) {
        String sql = "UPDATE reservations SET reservation_date = ?, reservation_time = ?, " +
                    "guests = ?, area = ?, status = ?, updated_at = GETDATE() " +
                    "WHERE reservation_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setDate(1, r.getReservationDate());
            ps.setTime(2, r.getReservationTime());
            ps.setInt(3, r.getGuests());
            ps.setString(4, r.getArea());
            ps.setString(5, r.getStatus());
            ps.setInt(6, r.getReservationId());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating reservation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Delete reservation
     */
    public boolean delete(int reservationId) {
        String sql = "DELETE FROM reservations WHERE reservation_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, reservationId);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting reservation: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    
    // ===== HELPER METHODS =====
    
    /**
     * Extract reservation from ResultSet
     */
    private Reservation extractReservation(ResultSet rs) throws SQLException {
        Reservation r = new Reservation();
        r.setReservationId(rs.getInt("reservation_id"));
        r.setCustomerId(rs.getInt("customer_id"));
        r.setReservationDate(rs.getDate("reservation_date"));
        r.setReservationTime(rs.getTime("reservation_time"));
        r.setGuests(rs.getInt("guests"));
        r.setArea(rs.getString("area"));
        r.setStatus(rs.getString("status"));
        return r;
    }
    
    /**
     * Validate status
     */
    private boolean isValidStatus(String status) {
        return status != null && 
               (status.equals("pending") || status.equals("confirmed") || 
                status.equals("cancelled") || status.equals("completed"));
    }
}