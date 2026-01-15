package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseUtil - SQL SERVER VERSION
 * Quản lý kết nối database cho Buffet Paradise
 */
public class DatabaseUtil {
    
    // ===== SQL SERVER CONFIGURATION =====
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "1433";
    private static final String DB_NAME = "buffet_paradise";
    private static final String DB_USERNAME = "sa"; // ⚠️ THAY ĐỔI USERNAME CỦA BẠN
    private static final String DB_PASSWORD = "YourPassword123"; // ⚠️ THAY ĐỔI PASSWORD CỦA BẠN
    
    // SQL Server JDBC URL
    private static final String DB_URL = "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT 
            + ";databaseName=" + DB_NAME 
            + ";encrypt=false"
            + ";trustServerCertificate=true"
            + ";characterEncoding=UTF-8";
    
    // SQL Server JDBC Driver
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    // Static initializer - Load driver khi class được load
    static {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("✓ SQL Server JDBC Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("✗ SQL Server JDBC Driver not found!");
            System.err.println("⚠️  Please add mssql-jdbc JAR to your project");
            e.printStackTrace();
        }
    }
    
    /**
     * Get database connection
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException e) {
            System.err.println("✗ Failed to create database connection!");
            System.err.println("URL: " + DB_URL);
            System.err.println("Username: " + DB_USERNAME);
            System.err.println("\n⚠️  Please check:");
            System.err.println("1. SQL Server is running");
            System.err.println("2. Database '" + DB_NAME + "' exists");
            System.err.println("3. Username and password are correct");
            System.err.println("4. SQL Server Authentication is enabled");
            throw e;
        }
    }
    
    /**
     * Test connection
     */
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Close connection safely
     */
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                if (!conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Print configuration (for debugging)
     */
    public static void printConfig() {
        System.out.println("\n=== Database Configuration ===");
        System.out.println("Database: " + DB_NAME);
        System.out.println("Host: " + DB_HOST + ":" + DB_PORT);
        System.out.println("Username: " + DB_USERNAME);
        System.out.println("==============================\n");
    }
}