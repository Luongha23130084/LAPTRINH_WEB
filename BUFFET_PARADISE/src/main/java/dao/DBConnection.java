// ============================================
// DBCONNECTION.JAVA - SIMPLE VERSION
// Không cần HikariCP - Đơn giản nhưng chậm hơn
// ============================================
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    
    // ===== SQL SERVER CONFIGURATION =====
    private static final String DB_HOST = "localhost";
    private static final String DB_PORT = "1433";
    private static final String DB_NAME = "buffet_paradise";
    private static final String DB_USERNAME = "sa";
    private static final String DB_PASSWORD = "YourPassword123"; // ⚠️ ĐỔI PASSWORD CỦA BẠN
    
    // JDBC URL
    private static final String DB_URL = "jdbc:sqlserver://" + DB_HOST + ":" + DB_PORT 
            + ";databaseName=" + DB_NAME 
            + ";encrypt=false"
            + ";trustServerCertificate=true"
            + ";characterEncoding=UTF-8";
    
    // JDBC Driver
    private static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    
    // Static initializer - Load driver một lần
    static {
        try {
            Class.forName(DB_DRIVER);
            System.out.println("✅ SQL Server JDBC Driver loaded successfully");
        } catch (ClassNotFoundException e) {
            System.err.println("❌ SQL Server JDBC Driver not found!");
            System.err.println("Please add mssql-jdbc JAR to your project");
            throw new RuntimeException("Failed to load SQL Server JDBC Driver", e);
        }
    }

    /**
     * Get database connection
     * ⚠️ CHÚ Ý: Mỗi lần gọi sẽ tạo connection MỚI
     */
    public static Connection getConnection() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            conn.setAutoCommit(true);
            return conn;
        } catch (SQLException e) {
            System.err.println("❌ Failed to create database connection!");
            System.err.println("URL: " + DB_URL);
            System.err.println("Username: " + DB_USERNAME);
            System.err.println("Error: " + e.getMessage());
            System.err.println("\nPlease check:");
            System.err.println("1. SQL Server is running");
            System.err.println("2. Database '" + DB_NAME + "' exists");
            System.err.println("3. Username and password are correct");
            System.err.println("4. SQL Server Authentication is enabled");
            throw e;
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
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }

    /**
     * Test database connection
     */
    public static boolean testConnection() {
        Connection conn = null;
        try {
            conn = getConnection();
            if (conn != null && !conn.isClosed()) {
                System.out.println("\n✅ Database connection successful!");
                System.out.println("Database: " + conn.getCatalog());
                System.out.println("Driver: " + conn.getMetaData().getDriverName());
                System.out.println("Version: " + conn.getMetaData().getDriverVersion());
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.err.println("\n❌ Database connection failed!");
            e.printStackTrace();
            return false;
        } finally {
            closeConnection(conn);
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║  BUFFET PARADISE - SQL SERVER TEST   ║");
        System.out.println("╚═══════════════════════════════════════╝");
        
        System.out.println("\nConfiguration:");
        System.out.println("Host: " + DB_HOST);
        System.out.println("Port: " + DB_PORT);
        System.out.println("Database: " + DB_NAME);
        System.out.println("Username: " + DB_USERNAME);
        
        System.out.println("\nTesting Database Connection...\n");
        
        if (testConnection()) {
            System.out.println("\n✅ ALL TESTS PASSED! Database is ready.");
        } else {
            System.out.println("\n❌ Connection test FAILED!");
            System.out.println("\n╔═══════════════════════════════════════╗");
            System.out.println("║  TROUBLESHOOTING CHECKLIST            ║");
            System.out.println("╚═══════════════════════════════════════╝");
            System.out.println("1. ✓ SQL Server is running");
            System.out.println("   - Check SQL Server Configuration Manager");
            System.out.println();
            System.out.println("2. ✓ TCP/IP protocol is enabled");
            System.out.println("   - Enable in SQL Server Configuration Manager");
            System.out.println("   - Default port: 1433");
            System.out.println();
            System.out.println("3. ✓ SQL Server Authentication is enabled");
            System.out.println("   - Mixed Mode Authentication");
            System.out.println();
            System.out.println("4. ✓ Database 'buffet_paradise' exists");
            System.out.println("   - Create using SSMS or script");
            System.out.println();
            System.out.println("5. ✓ Username 'sa' and password are correct");
            System.out.println("   - Update in DBConnection.java");
        }
    }
}