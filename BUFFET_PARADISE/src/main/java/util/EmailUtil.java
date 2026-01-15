// ============================================
// EMAILUTIL.JAVA - Simple Version (No JavaMail required)
// Chỉ log ra console, không gửi email thật
// ============================================
package util;

public class EmailUtil {
    
    /**
     * Send booking confirmation email
     * VERSION ĐƠN GIẢN - CHỈ LOG, KHÔNG GỬI EMAIL THẬT
     */
    public static boolean sendBookingConfirmation(String toEmail, String customerName, 
            String bookingCode, String date, String time, int guests) {
        
        System.out.println("=================================");
        System.out.println("📧 EMAIL CONFIRMATION");
        System.out.println("=================================");
        System.out.println("To: " + toEmail);
        System.out.println("Customer: " + customerName);
        System.out.println("Booking Code: " + bookingCode);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Guests: " + guests);
        System.out.println("=================================");
        
        // Return true để giả lập gửi thành công
        return true;
    }

    /**
     * Send cancellation email
     * VERSION ĐƠN GIẢN - CHỈ LOG, KHÔNG GỬI EMAIL THẬT
     */
    public static boolean sendCancellationEmail(String toEmail, String customerName, 
            String bookingCode) {
        
        System.out.println("=================================");
        System.out.println("❌ EMAIL CANCELLATION");
        System.out.println("=================================");
        System.out.println("To: " + toEmail);
        System.out.println("Customer: " + customerName);
        System.out.println("Booking Code: " + bookingCode);
        System.out.println("=================================");
        
        return true;
    }
    
    /**
     * Send reminder email
     */
    public static boolean sendReminderEmail(String toEmail, String customerName,
            String bookingCode, String date, String time) {
        
        System.out.println("=================================");
        System.out.println("⏰ EMAIL REMINDER");
        System.out.println("=================================");
        System.out.println("To: " + toEmail);
        System.out.println("Customer: " + customerName);
        System.out.println("Booking Code: " + bookingCode);
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("=================================");
        
        return true;
    }
}

/* 
 * ============================================
 * LƯU Ý: 
 * Đây là version đơn giản chỉ để test
 * Khi deploy thật, cần dùng version có JavaMail
 * hoặc dùng email service như SendGrid, Mailgun
 * ============================================
 */