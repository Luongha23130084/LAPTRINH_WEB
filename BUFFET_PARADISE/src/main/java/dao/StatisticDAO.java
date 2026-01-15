package dao;

import model.Statistic;
import java.sql.*;

public class StatisticDAO {

    /**
     * Get statistics with proper SQL Server syntax
     */
    public Statistic getStatistic() {
        Statistic s = new Statistic();
        
        try (Connection con = DBConnection.getConnection()) {
            
            // Total reservations
            String sql1 = "SELECT COUNT(*) as total FROM reservations";
            try (PreparedStatement ps = con.prepareStatement(sql1);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setTotalReservations(rs.getInt("total"));
                }
            }
            
            // Completed reservations
            String sql2 = "SELECT COUNT(*) as completed FROM reservations WHERE status = 'completed'";
            try (PreparedStatement ps = con.prepareStatement(sql2);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setCompletedReservations(rs.getInt("completed"));
                }
            }
            
            // Estimated revenue (SQL Server uses ISNULL instead of IFNULL)
            String sql3 = "SELECT ISNULL(SUM(guests * 300000), 0) as revenue " +
                         "FROM reservations WHERE status = 'completed'";
            try (PreparedStatement ps = con.prepareStatement(sql3);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setEstimatedRevenue(rs.getDouble("revenue"));
                }
            }
            
            // Average rating
            String sql4 = "SELECT ISNULL(AVG(CAST(rating AS FLOAT)), 0) as avg_rating FROM feedback";
            try (PreparedStatement ps = con.prepareStatement(sql4);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setAvgRating(rs.getDouble("avg_rating"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting statistics: " + e.getMessage());
            e.printStackTrace();
        }
        
        return s;
    }
    
    /**
     * Get today's statistics
     */
    public Statistic getTodayStatistic() {
        Statistic s = new Statistic();
        
        try (Connection con = DBConnection.getConnection()) {
            
            // Today's reservations
            String sql1 = "SELECT COUNT(*) as total FROM reservations " +
                         "WHERE CAST(created_at AS DATE) = CAST(GETDATE() AS DATE)";
            try (PreparedStatement ps = con.prepareStatement(sql1);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setTotalReservations(rs.getInt("total"));
                }
            }
            
            // Today's completed
            String sql2 = "SELECT COUNT(*) as completed FROM reservations " +
                         "WHERE status = 'completed' AND CAST(created_at AS DATE) = CAST(GETDATE() AS DATE)";
            try (PreparedStatement ps = con.prepareStatement(sql2);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setCompletedReservations(rs.getInt("completed"));
                }
            }
            
            // Today's revenue
            String sql3 = "SELECT ISNULL(SUM(guests * 300000), 0) as revenue " +
                         "FROM reservations WHERE status = 'completed' " +
                         "AND CAST(created_at AS DATE) = CAST(GETDATE() AS DATE)";
            try (PreparedStatement ps = con.prepareStatement(sql3);
                 ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    s.setEstimatedRevenue(rs.getDouble("revenue"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting today's statistics: " + e.getMessage());
            e.printStackTrace();
        }
        
        return s;
    }
    
    /**
     * Get statistics by date range
     */
    public Statistic getStatisticByDateRange(Date startDate, Date endDate) {
        Statistic s = new Statistic();
        
        try (Connection con = DBConnection.getConnection()) {
            
            // Total in range
            String sql1 = "SELECT COUNT(*) as total FROM reservations " +
                         "WHERE reservation_date BETWEEN ? AND ?";
            try (PreparedStatement ps = con.prepareStatement(sql1)) {
                ps.setDate(1, startDate);
                ps.setDate(2, endDate);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    s.setTotalReservations(rs.getInt("total"));
                }
            }
            
            // Completed in range
            String sql2 = "SELECT COUNT(*) as completed FROM reservations " +
                         "WHERE status = 'completed' AND reservation_date BETWEEN ? AND ?";
            try (PreparedStatement ps = con.prepareStatement(sql2)) {
                ps.setDate(1, startDate);
                ps.setDate(2, endDate);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    s.setCompletedReservations(rs.getInt("completed"));
                }
            }
            
            // Revenue in range
            String sql3 = "SELECT ISNULL(SUM(guests * 300000), 0) as revenue " +
                         "FROM reservations WHERE status = 'completed' " +
                         "AND reservation_date BETWEEN ? AND ?";
            try (PreparedStatement ps = con.prepareStatement(sql3)) {
                ps.setDate(1, startDate);
                ps.setDate(2, endDate);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    s.setEstimatedRevenue(rs.getDouble("revenue"));
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting statistics by date range: " + e.getMessage());
            e.printStackTrace();
        }
        
        return s;
    }
}