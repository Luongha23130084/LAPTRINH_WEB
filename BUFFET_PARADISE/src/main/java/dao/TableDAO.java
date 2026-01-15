package dao;

import util.DatabaseUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Table;

public class TableDAO {

    /**
     * Get all tables
     */
    public List<Table> getAllTables() {
        String sql = "SELECT * FROM tables ORDER BY table_number ASC";
        
        List<Table> tables = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                tables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tables;
    }

    /**
     * Get all available tables
     */
    public List<Table> getAllAvailableTables() {
        String sql = "SELECT * FROM tables WHERE status = 'available' " +
                    "ORDER BY table_number ASC";
        
        List<Table> tables = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                tables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tables;
    }

    /**
     * Get table by ID
     */
    public Table getTableById(int tableId) {
        String sql = "SELECT * FROM tables WHERE table_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, tableId);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractTableFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get table by number
     */
    public Table getTableByNumber(String tableNumber) {
        String sql = "SELECT * FROM tables WHERE table_number = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tableNumber);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractTableFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get tables by capacity
     */
    public List<Table> getTablesByCapacity(int minCapacity) {
        String sql = "SELECT * FROM tables WHERE capacity >= ? AND status = 'available' " +
                    "ORDER BY capacity ASC";
        
        List<Table> tables = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, minCapacity);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                tables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tables;
    }

    /**
     * Get tables by zone
     */
    public List<Table> getTablesByZone(String zone) {
        String sql = "SELECT * FROM tables WHERE zone = ? " +
                    "ORDER BY table_number ASC";
        
        List<Table> tables = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, zone);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                tables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tables;
    }

    /**
     * Get tables by status
     */
    public List<Table> getTablesByStatus(String status) {
        String sql = "SELECT * FROM tables WHERE status = ? " +
                    "ORDER BY table_number ASC";
        
        List<Table> tables = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                tables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tables;
    }

    /**
     * Create new table
     */
    public boolean createTable(Table table) {
        String sql = "INSERT INTO tables (table_number, capacity, floor, zone, status, " +
                    "position_x, position_y) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, table.getTableNumber());
            pstmt.setInt(2, table.getCapacity());
            pstmt.setString(3, table.getFloor());
            pstmt.setString(4, table.getZone());
            pstmt.setString(5, table.getStatus());
            pstmt.setInt(6, table.getPositionX());
            pstmt.setInt(7, table.getPositionY());
            
            int affected = pstmt.executeUpdate();
            
            if (affected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        table.setTableId(rs.getInt(1));
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
     * Update table
     */
    public boolean updateTable(Table table) {
        String sql = "UPDATE tables SET table_number = ?, capacity = ?, floor = ?, " +
                    "zone = ?, status = ?, position_x = ?, position_y = ? " +
                    "WHERE table_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, table.getTableNumber());
            pstmt.setInt(2, table.getCapacity());
            pstmt.setString(3, table.getFloor());
            pstmt.setString(4, table.getZone());
            pstmt.setString(5, table.getStatus());
            pstmt.setInt(6, table.getPositionX());
            pstmt.setInt(7, table.getPositionY());
            pstmt.setInt(8, table.getTableId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Update table status
     */
    public boolean updateTableStatus(int tableId, String status) {
        String sql = "UPDATE tables SET status = ? WHERE table_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, tableId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete table
     */
    public boolean deleteTable(int tableId) {
        String sql = "DELETE FROM tables WHERE table_id = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, tableId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get available tables count
     */
    public int getAvailableTablesCount() {
        String sql = "SELECT COUNT(*) FROM tables WHERE status = 'available'";
        
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
     * Get tables count by status
     */
    public int getTablesCountByStatus(String status) {
        String sql = "SELECT COUNT(*) FROM tables WHERE status = ?";
        
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
     * Find suitable table for guests - SQL Server uses TOP instead of LIMIT
     */
    public Table findSuitableTable(int guests) {
        String sql = "SELECT TOP 1 * FROM tables WHERE status = 'available' " +
                    "AND capacity >= ? ORDER BY capacity ASC";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, guests);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return extractTableFromResultSet(rs);
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get tables by floor
     */
    public List<Table> getTablesByFloor(String floor) {
        String sql = "SELECT * FROM tables WHERE floor = ? " +
                    "ORDER BY table_number ASC";
        
        List<Table> tables = new ArrayList<>();
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, floor);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                tables.add(extractTableFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tables;
    }

    /**
     * Check if table number exists
     */
    public boolean tableNumberExists(String tableNumber) {
        String sql = "SELECT COUNT(*) FROM tables WHERE table_number = ?";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tableNumber);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Get total capacity of all tables
     */
    public int getTotalCapacity() {
        String sql = "SELECT SUM(capacity) FROM tables";
        
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
     * Get tables statistics
     */
    public java.util.Map<String, Integer> getTablesStatistics() {
        java.util.Map<String, Integer> stats = new java.util.HashMap<>();
        
        String sql = "SELECT status, COUNT(*) as count FROM tables GROUP BY status";
        
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                stats.put(rs.getString("status"), rs.getInt("count"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return stats;
    }

    // ==================== HELPER METHODS ====================
    
    /**
     * Extract Table object from ResultSet
     */
    private Table extractTableFromResultSet(ResultSet rs) throws SQLException {
        Table table = new Table();
        
        table.setTableId(rs.getInt("table_id"));
        table.setTableNumber(rs.getString("table_number"));
        table.setCapacity(rs.getInt("capacity"));
        table.setFloor(rs.getString("floor"));
        table.setZone(rs.getString("zone"));
        table.setStatus(rs.getString("status"));
        table.setPositionX(rs.getInt("position_x"));
        table.setPositionY(rs.getInt("position_y"));
        table.setCreatedAt(rs.getTimestamp("created_at"));
        table.setUpdatedAt(rs.getTimestamp("updated_at"));
        
        return table;
    }
}