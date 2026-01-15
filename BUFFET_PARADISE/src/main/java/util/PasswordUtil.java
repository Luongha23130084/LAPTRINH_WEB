package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    
    // Salt rounds - Độ phức tạp của mã hóa (12 là đủ an toàn)
    private static final int SALT_ROUNDS = 12;

    /**
     * Hash password sử dụng BCrypt
     * 
     * @param plainPassword - Mật khẩu gốc từ user nhập
     * @return Mật khẩu đã được mã hóa
     * 
     * Ví dụ:
     * Input:  "123456"
     * Output: "$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewY5GyYIeWHgVzRu"
     */
    public static String hashPassword(String plainPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        try {
            // Generate salt và hash password
            String salt = BCrypt.gensalt(SALT_ROUNDS);
            String hashedPassword = BCrypt.hashpw(plainPassword, salt);
            
            System.out.println("🔒 Password hashed successfully");
            return hashedPassword;
            
        } catch (Exception e) {
            System.err.println("❌ Error hashing password: " + e.getMessage());
            throw new RuntimeException("Failed to hash password", e);
        }
    }

    /**
     * Verify password - Kiểm tra mật khẩu có đúng không
     * 
     * @param plainPassword - Mật khẩu user nhập khi login
     * @param hashedPassword - Mật khẩu đã mã hóa trong database
     * @return true nếu password đúng, false nếu sai
     * 
     * Ví dụ:
     * plainPassword:  "123456"
     * hashedPassword: "$2a$12$LQv3c1yqBWVHxkd0LHAkCOYz6TtxMQJqhN8/LewY5GyYIeWHgVzRu"
     * Return: true (nếu khớp)
     */
    public static boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (plainPassword == null || plainPassword.isEmpty()) {
            System.err.println("❌ Plain password is null or empty");
            return false;
        }
        
        if (hashedPassword == null || hashedPassword.isEmpty()) {
            System.err.println("❌ Hashed password is null or empty");
            return false;
        }
        
        try {
            // Verify password using BCrypt
            boolean matches = BCrypt.checkpw(plainPassword, hashedPassword);
            
            if (matches) {
                System.out.println("✅ Password verified successfully");
            } else {
                System.out.println("❌ Password verification failed");
            }
            
            return matches;
            
        } catch (Exception e) {
            System.err.println("❌ Error verifying password: " + e.getMessage());
            return false;
        }
    }

    /**
     * Check if a string is a valid BCrypt hash
     */
    public static boolean isValidBCryptHash(String hash) {
        if (hash == null || hash.isEmpty()) {
            return false;
        }
        
        // BCrypt hash always starts with $2a$, $2b$, or $2y$
        // and has a specific length (60 characters)
        return hash.matches("^\\$2[ayb]\\$.{56}$");
    }

    /**
     * Generate a random password
     * Useful for password reset or temporary passwords
     */
    public static String generateRandomPassword(int length) {
        if (length < 6) {
            throw new IllegalArgumentException("Password length must be at least 6");
        }
        
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%";
        StringBuilder password = new StringBuilder();
        
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        
        return password.toString();
    }

    /**
     * Check password strength
     * Returns: 0 (weak), 1 (medium), 2 (strong)
     */
    public static int checkPasswordStrength(String password) {
        if (password == null || password.length() < 6) {
            return 0; // Weak
        }
        
        int score = 0;
        
        // Length
        if (password.length() >= 8) score++;
        if (password.length() >= 12) score++;
        
        // Contains uppercase
        if (password.matches(".*[A-Z].*")) score++;
        
        // Contains lowercase
        if (password.matches(".*[a-z].*")) score++;
        
        // Contains digit
        if (password.matches(".*\\d.*")) score++;
        
        // Contains special char
        if (password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) score++;
        
        // Return strength level
        if (score >= 5) return 2; // Strong
        if (score >= 3) return 1; // Medium
        return 0; // Weak
    }

    /**
     * Get password strength text
     */
    public static String getPasswordStrengthText(String password) {
        int strength = checkPasswordStrength(password);
        
        switch (strength) {
            case 0:
                return "Weak";
            case 1:
                return "Medium";
            case 2:
                return "Strong";
            default:
                return "Unknown";
        }
    }

    /**
     * Main method for testing
     */
    public static void main(String[] args) {
        System.out.println("=== PasswordUtil Test ===\n");
        
        // Test 1: Hash password
        System.out.println("Test 1: Hash Password");
        String plainPassword = "admin123";
        String hashedPassword = hashPassword(plainPassword);
        System.out.println("Plain: " + plainPassword);
        System.out.println("Hash:  " + hashedPassword);
        System.out.println();
        
        // Test 2: Verify correct password
        System.out.println("Test 2: Verify Correct Password");
        boolean isValid = verifyPassword(plainPassword, hashedPassword);
        System.out.println("Result: " + (isValid ? "✅ PASS" : "❌ FAIL"));
        System.out.println();
        
        // Test 3: Verify wrong password
        System.out.println("Test 3: Verify Wrong Password");
        boolean isInvalid = verifyPassword("wrongpassword", hashedPassword);
        System.out.println("Result: " + (!isInvalid ? "✅ PASS" : "❌ FAIL"));
        System.out.println();
        
        // Test 4: Check hash validity
        System.out.println("Test 4: Check Hash Validity");
        boolean validHash = isValidBCryptHash(hashedPassword);
        System.out.println("Is valid BCrypt hash: " + validHash);
        System.out.println();
        
        // Test 5: Generate random password
        System.out.println("Test 5: Generate Random Password");
        String randomPass = generateRandomPassword(12);
        System.out.println("Random password: " + randomPass);
        System.out.println();
        
        // Test 6: Check password strength
        System.out.println("Test 6: Check Password Strength");
        String[] testPasswords = {"123", "admin123", "Admin@123", "MyStrongP@ssw0rd!"};
        for (String pass : testPasswords) {
            int strength = checkPasswordStrength(pass);
            String strengthText = getPasswordStrengthText(pass);
            System.out.println("Password: " + pass + " → Strength: " + strengthText + " (" + strength + ")");
        }
        
        System.out.println("\n=== All Tests Completed ===");
    }
}