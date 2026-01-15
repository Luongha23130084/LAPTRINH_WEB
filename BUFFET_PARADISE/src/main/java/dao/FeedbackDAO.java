package dao;

import model.Feedback;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDAO {

    /**
     * Add new feedback with validation
     */
    public boolean add(Feedback f) {
        // ✅ VALIDATION
        if (f.getRating() < 1 || f.getRating() > 5) {
            System.err.println("Invalid rating: " + f.getRating());
            return false;
        }
        
        String sql = "INSERT INTO feedback(reservation_id, customer_id, rating, comment, created_at) " +
                    "VALUES(?, ?, ?, ?, GETDATE())";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, f.getReservationId());
            ps.setInt(2, f.getCustomerId());
            ps.setInt(3, f.getRating());
            ps.setString(4, f.getComment());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding feedback: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Find all feedback
     */
    public List<Feedback> findAll() {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM feedback ORDER BY created_at DESC";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackId(rs.getInt("feedback_id"));
                f.setReservationId(rs.getInt("reservation_id"));
                f.setCustomerId(rs.getInt("customer_id"));
                f.setRating(rs.getInt("rating"));
                f.setComment(rs.getString("comment"));
                f.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error finding all feedback: " + e.getMessage());
            e.printStackTrace();
        }
        
        return list;
    }
    
    /**
     * Find feedback by customer
     */
    public List<Feedback> findByCustomer(int customerId) {
        List<Feedback> list = new ArrayList<>();
        String sql = "SELECT * FROM feedback WHERE customer_id = ? ORDER BY created_at DESC";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackId(rs.getInt("feedback_id"));
                f.setReservationId(rs.getInt("reservation_id"));
                f.setCustomerId(rs.getInt("customer_id"));
                f.setRating(rs.getInt("rating"));
                f.setComment(rs.getString("comment"));
                f.setCreatedAt(rs.getTimestamp("created_at"));
                list.add(f);
            }
        } catch (SQLException e) {
            System.err.println("Error finding feedback by customer: " + e.getMessage());
            e.printStackTrace();
        }
        
        return list;
    }
    
    /**
     * Find feedback by reservation
     */
    public Feedback findByReservation(int reservationId) {
        String sql = "SELECT * FROM feedback WHERE reservation_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, reservationId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Feedback f = new Feedback();
                f.setFeedbackId(rs.getInt("feedback_id"));
                f.setReservationId(rs.getInt("reservation_id"));
                f.setCustomerId(rs.getInt("customer_id"));
                f.setRating(rs.getInt("rating"));
                f.setComment(rs.getString("comment"));
                f.setCreatedAt(rs.getTimestamp("created_at"));
                return f;
            }
        } catch (SQLException e) {
            System.err.println("Error finding feedback by reservation: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }
    
    /**
     * Delete feedback (admin only)
     */
    public boolean delete(int feedbackId) {
        String sql = "DELETE FROM feedback WHERE feedback_id = ?";
        
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            
            ps.setInt(1, feedbackId);
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting feedback: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}