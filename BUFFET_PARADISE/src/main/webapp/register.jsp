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
                <p>Tạo tài khoản để đặt bàn và nhận ưu đãi</p>
            </div>
            
            <div class="auth-features">
                <div class="feature">
                    <span class="feature-icon">⚡</span>
                    <div>
                        <h3>Đặt Bàn Nhanh Chóng</h3>
                        <p>Chỉ trong vài giây</p>
                    </div>
                </div>
                
                <div class="feature">
                    <span class="feature-icon">🎁</span>
                    <div>
                        <h3>Ưu Đãi Đặc Biệt</h3>
                        <p>Dành cho thành viên</p>
                    </div>
                </div>
                
                <div class="feature">
                    <span class="feature-icon">📊</span>
                    <div>
                        <h3>Quản Lý Đặt Bàn</h3>
                        <p>Theo dõi lịch sử dễ dàng</p>
                    </div>
                </div>
            </div>
        </div>
        
        <!-- Right Side - Register Form -->
        <div class="auth-right">
            <div class="auth-box">
                <div class="auth-header">
                    <h2>Đăng Ký Tài Khoản</h2>
                    <p>Tham gia Buffet Paradise ngay hôm nay!</p>
                </div>
                
                <!-- Alert Messages -->
                <% 
                String error = request.getParameter("error");
                
                if (error != null) { %>
                    <div class="alert alert-error">
                        ❌ 
                        <% if (error.equals("exists")) { %>
                            Email đã được sử dụng!
                        <% } else if (error.equals("required")) { %>
                            Vui lòng điền đầy đủ thông tin!
                        <% } else if (error.equals("password")) { %>
                            Mật khẩu xác nhận không khớp!
                        <% } else if (error.equals("password_short")) { %>
                            Mật khẩu phải có ít nhất 6 ký tự!
                        <% } else if (error.equals("failed")) { %>
                            Không thể tạo tài khoản. Vui lòng thử lại!
                        <% } else { %>
                            Đã có lỗi xảy ra. Vui lòng thử lại!
                        <% } %>
                    </div>
                <% } %>
                
                <!-- Register Form -->
                <form action="RegisterServlet" method="post" data-validate>
                    <div class="form-group">
                        <label for="fullName">👤 Họ và Tên</label>
                        <input 
                            type="text" 
                            id="fullName" 
                            name="fullName" 
                            placeholder="Nguyễn Văn A"
                            required>
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
                    
                    <div class="form-group">
                        <label for="phone">📱 Số Điện Thoại</label>
                        <input 
                            type="tel" 
                            id="phone" 
                            name="phone" 
                            placeholder="0901234567"
                            pattern="[0-9]{10}"
                            required>
                    </div>
                    
                    <div class="form-group">
                        <label for="password">🔒 Mật Khẩu</label>
                        <input 
                            type="password" 
                            id="password" 
                            name="password" 
                            placeholder="••••••••"
                            minlength="6"
                            required>
                        <small style="color: var(--text-tertiary); font-size: 0.75rem;">
                            Tối thiểu 6 ký tự
                        </small>
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
                    
                    <div class="form-group">
                        <label style="display: flex; align-items: center; gap: 8px; cursor: pointer;">
                            <input type="checkbox" name="terms" style="width: auto;" required>
                            <span style="font-weight: normal; font-size: 0.875rem;">
                                Tôi đồng ý với <a href="#" style="color: var(--gold-primary);">điều khoản sử dụng</a>
                            </span>
                        </label>
                    </div>
                    
                    <button type="submit" class="btn btn-primary btn-full btn-large">
                        📝 Đăng Ký
                    </button>
                </form>
                
                <!-- Divider -->
                <div class="divider"></div>
                
                <!-- Login Link -->
                <p class="text-center" style="margin: 0;">
                    Đã có tài khoản? 
                    <a href="login.jsp" style="color: var(--gold-primary); font-weight: 600;">
                        Đăng nhập ngay
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
        // Form validation
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.querySelector('form[data-validate]');
            
            if (form) {
                form.addEventListener('submit', function(e) {
                    const fullName = document.getElementById('fullName').value.trim();
                    const email = document.getElementById('email').value.trim();
                    const phone = document.getElementById('phone').value.trim();
                    const password = document.getElementById('password').value;
                    const confirmPassword = document.getElementById('confirmPassword').value;
                    const terms = document.querySelector('input[name="terms"]').checked;
                    
                    // Check empty fields
                    if (!fullName || !email || !phone || !password || !confirmPassword) {
                        e.preventDefault();
                        alert('❌ Vui lòng điền đầy đủ thông tin!');
                        return;
                    }
                    
                    // Check email format
                    if (!email.includes('@')) {
                        e.preventDefault();
                        alert('❌ Email không hợp lệ!');
                        return;
                    }
                    
                    // Check phone format
                    if (!/^[0-9]{10}$/.test(phone)) {
                        e.preventDefault();
                        alert('❌ Số điện thoại phải có 10 chữ số!');
                        return;
                    }
                    
                    // Check password length
                    if (password.length < 6) {
                        e.preventDefault();
                        alert('❌ Mật khẩu phải có ít nhất 6 ký tự!');
                        return;
                    }
                    
                    // Check password match
                    if (password !== confirmPassword) {
                        e.preventDefault();
                        alert('❌ Mật khẩu xác nhận không khớp!');
                        return;
                    }
                    
                    // Check terms
                    if (!terms) {
                        e.preventDefault();
                        alert('❌ Vui lòng đồng ý với điều khoản sử dụng!');
                        return;
                    }
                });
            }
        });
    </script>
    
    <style>
        @keyframes slideInDown {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }
    </style>
</body>
</html>