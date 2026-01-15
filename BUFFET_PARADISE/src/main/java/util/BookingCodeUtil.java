// ============================================
// BOOKINGCODEUTIL.JAVA - Generate Booking Codes
// ============================================
package util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class BookingCodeUtil {
    
    private static final String PREFIX = "BP";
    private static final Random random = new Random();

    /**
     * Generate unique booking code
     * Format: BP-YYYYMMDD-XXXX
     * Example: BP-20240115-A3B7
     */
    public static String generateBookingCode() {
        // Date part: YYYYMMDD
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String datePart = dateFormat.format(new Date());
        
        // Random part: 4 characters (A-Z, 0-9)
        String randomPart = generateRandomString(4);
        
        return PREFIX + "-" + datePart + "-" + randomPart;
    }

    /**
     * Generate random alphanumeric string
     */
    private static String generateRandomString(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            sb.append(chars.charAt(index));
        }
        
        return sb.toString();
    }

    /**
     * Validate booking code format
     */
    public static boolean isValidBookingCode(String code) {
        if (code == null || code.isEmpty()) {
            return false;
        }
        
        // Format: BP-YYYYMMDD-XXXX
        String pattern = "^BP-\\d{8}-[A-Z0-9]{4}$";
        return code.matches(pattern);
    }

    /**
     * Extract date from booking code
     */
    public static String extractDateFromCode(String code) {
        if (!isValidBookingCode(code)) {
            return null;
        }
        
        // Extract YYYYMMDD part
        String[] parts = code.split("-");
        if (parts.length >= 2) {
            return parts[1];
        }
        
        return null;
    }
}