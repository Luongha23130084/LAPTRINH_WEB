<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Ký - Buffet Paradise</title>
    
    <!-- CSS Files -->
    <link rel="stylesheet" href="css/variables.css">
    <link rel="stylesheet" href="css/components.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/pages.css">
    
    <link rel="icon" href="data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100'><text y='.9em' font-size='90'>🍽️</text></svg>">
</head>
<body class="auth-body">
    <!-- Background Pattern -->
    <div class="auth-background"></div>
    
    <!-- Auth Container -->
    <div class="auth-container">
        <!-- Left Side - Branding -->
        <div class="auth-left">
            <div class="auth-brand">
                <span class="brand-icon">🍽️</span>
                <h1>Buffet Paradise</h1>
                <p>Tham gia để nhận nhiều ưu đãi</p>
            </div>
            
            <div class="auth-features">
                <div class="feature">
                    <span class="feature-icon">🎁</span>
                    <div>
                        <h3>Giảm 10% Ngay</h3>
                        <p>Cho lần đặt bàn đầu tiên</p>
                    </div>
                </div>
                
                <div class="feature">
                    <span class="feature-icon">🎂</span>
                    <div>
                        <h3>Ưu Đãi Sinh Nhật</h3>
                        <p>Miễn phí trong ngày sinh nhật</p>
                    </div>
                </div>
                
                <div class="feature">
                    <span class="feature-icon">⭐</span>
                    <div>
                        <h3>Tích Điểm Thưởng</h3>
                        <p>Đổi quà giá trị</p>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Right Side - Register Form -->
        <div class="auth-right">
            <div class="auth-box">
                <div class="auth-header">
                    <h2>Đăng Ký Tài Khoản</h2>
                    <p>Tạo tài khoản để bắt đầu</p>
                </div>
                
                <!-- Alert Messages -->
                <% 
                String error = request.getParameter("error");
                
                if (error != null) { %>
                    <div class="alert alert-error">
                        ❌ 
                        <% if (error.equals("exists")) { %>
                            Email này đã được đăng ký!
                        <% } else if (error.equals("password")) { %>
                            Mật khẩu xác nhận không khớp!
                        <% } else if (error.equals("required")) { %>
                            Vui lòng nhập đầy đủ thông tin!
                        <% } else { %>
                            Đã có lỗi xảy ra. Vui lòng thử lại!
                        <% } %>
                    </div>
                <% } %>
                
                <!-- Register Form -->
                <form action="RegisterServlet" method="post" id="registerForm" data-validate>
                    <div class="form-row">
                        <div class="form-group">
                            <label for="fullName">👤 Họ Tên</label>
                            <input 
                                type="text" 
                                id="fullName" 
                                name="fullName" 
                                placeholder="Nguyễn Văn A"
                                required>
                        </div>
                        
                        <div class="form-group">
                            <label for="phone">📱 Số Điện Thoại</label>
                            <input 
                                type="tel" 
                                id="phone" 
                                name="phone" 
                                placeholder="0901234567"
                                required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="email">📧 Email</label>
                        <input 
                            type="email" 
                            id="email" 
                            name="email" 
                            placeholder="example@email.com"
                            required>
                    </div>
                    
                    <div class="form-row">
                        <div class="form-group">
                            <label for="password">🔒 Mật Khẩu</label>
                            <input 
                                type="password" 
                                id="password" 
                                name="password" 
                                placeholder="••••••••"
                                minlength="6"
                                required>
                            <small>Tối thiểu 6 ký tự</small>
                        </div>
                        
                        <div class="form-group">
                            <label for="confirmPassword">🔒 Xác Nhận Mật Khẩu</label>
                            <input 
                                type="password" 
                                id="confirmPassword" 
                                name="confirmPassword" 
                                placeholder="••••••••"
                                minlength="6"
                                required>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label style="display: flex; align-items: center; gap: 8px; cursor: pointer;">
                            <input type="checkbox" name="terms" id="terms" required style="width: auto;">
                            <span style="font-weight: normal; font-size: var(--font-sm);">
                                Tôi đồng ý với 
                                <a href="#" style="color: var(--gold-primary);">Điều khoản sử dụng</a>
                            </span>
                        </label>
                    </div>
                    
                    <button type="submit" class="btn btn-primary btn-full btn-large">
                        📝 Đăng Ký Ngay
                    </button>
                </form>
                
                <!-- Divider -->
                <div class="divider"></div>
                
                <!-- Login Link -->
                <p class="text-center" style="margin: 0;">
                    Đã có tài khoản? 
                    <a href="login.jsp" style="color: var(--gold-primary); font-weight: 600;">
                        Đăng nhập
                    </a>
                </p>
                
                <!-- Back to Home -->
                <p class="text-center" style="margin-top: var(--spacing-md);">
                    <a href="index.jsp" style="color: var(--text-secondary); font-size: var(--font-sm);">
                        ← Quay lại trang chủ
                    </a>
                </p>
            </div>
        </div>
    </div>
    
    <!-- JavaScript -->
    <script src="js/main.js"></script>
    <script>
        // Additional register page specific JS
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.getElementById('registerForm');
            
            if (form) {
                form.addEventListener('submit', function(e) {
                    const password = document.getElementById('password').value;
                    const confirmPassword = document.getElementById('confirmPassword').value;
                    const terms = document.getElementById('terms').checked;
                    const phone = document.getElementById('phone').value;
                    
                    // Check password match
                    if (password !== confirmPassword) {
                        e.preventDefault();
                        BuffetParadise.showToast('Mật khẩu xác nhận không khớp!', 'error');
                        return;
                    }
                    
                    // Check password length
                    if (password.length < 6) {
                        e.preventDefault();
                        BuffetParadise.showToast('Mật khẩu phải có ít nhất 6 ký tự!', 'error');
                        return;
                    }
                    
                    // Check phone format
                    const phoneRegex = /^[0-9]{10,11}$/;
                    if (!phoneRegex.test(phone.replace(/\s/g, ''))) {
                        e.preventDefault();
                        BuffetParadise.showToast('Số điện thoại không hợp lệ!', 'error');
                        return;
                    }
                    
                    // Check terms
                    if (!terms) {
                        e.preventDefault();
                        BuffetParadise.showToast('Vui lòng đồng ý với điều khoản sử dụng!', 'error');
                        return;
                    }
                });
                
                // Real-time password match indicator
                const password = document.getElementById('password');
                const confirmPassword = document.getElementById('confirmPassword');
                
                confirmPassword.addEventListener('input', function() {
                    if (this.value && password.value) {
                        if (this.value === password.value) {
                            this.style.borderColor = '#10b981';
                        } else {
                            this.style.borderColor = '#ef4444';
                        }
                    } else {
                        this.style.borderColor = '';
                    }
                });
            }
        });
    </script>
</body>
</html>