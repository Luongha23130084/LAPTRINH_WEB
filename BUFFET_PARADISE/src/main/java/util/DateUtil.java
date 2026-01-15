// ============================================
// DATEUTIL.JAVA - Date Formatting Utilities
// ============================================
package util;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class DateUtil {
    
    private static final Locale VI_LOCALE = new Locale("vi", "VN");

    /**
     * Format SQL Date to Vietnamese format
     * Example: 15/01/2024 -> "15 Tháng 1, 2024"
     */
    public static String formatDate(Date date) {
        if (date == null) return "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd 'Tháng' M, yyyy", VI_LOCALE);
        return sdf.format(date);
    }

    /**
     * Format SQL Date to short format
     * Example: 15/01/2024
     */
    public static String formatDateShort(Date date) {
        if (date == null) return "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(date);
    }

    /**
     * Format SQL Time to string
     * Example: 19:30:00 -> "19:30"
     */
    public static String formatTime(Time time) {
        if (time == null) return "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(time);
    }

    /**
     * Format SQL Date to day of week + date
     * Example: "Thứ Hai, 15/01/2024"
     */
    public static String formatDateWithDayOfWeek(Date date) {
        if (date == null) return "";
        
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd/MM/yyyy", VI_LOCALE);
        return sdf.format(date);
    }

    /**
     * Check if date is today
     */
    public static boolean isToday(Date date) {
        if (date == null) return false;
        
        Date today = new Date(System.currentTimeMillis());
        return date.equals(today);
    }

    /**
     * Check if date is in the past
     */
    public static boolean isPast(Date date) {
        if (date == null) return false;
        
        Date today = new Date(System.currentTimeMillis());
        return date.before(today);
    }

    /**
     * Check if date is in the future
     */
    public static boolean isFuture(Date date) {
        if (date == null) return false;
        
        Date today = new Date(System.currentTimeMillis());
        return date.after(today);
    }

    /**
     * Get current date as SQL Date
     */
    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * Get current time as SQL Time
     */
    public static Time getCurrentTime() {
        return new Time(System.currentTimeMillis());
    }

    /**
     * Parse date string to SQL Date
     * Format: yyyy-MM-dd
     */
    public static Date parseDate(String dateStr) {
        try {
            return Date.valueOf(dateStr);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Parse time string to SQL Time
     * Format: HH:mm:ss or HH:mm
     */
    public static Time parseTime(String timeStr) {
        try {
            if (!timeStr.contains(":")) {
                return null;
            }
            
            // Add seconds if not present
            if (timeStr.split(":").length == 2) {
                timeStr += ":00";
            }
            
            return Time.valueOf(timeStr);
        } catch (Exception e) {
            return null;
        }
    }
}