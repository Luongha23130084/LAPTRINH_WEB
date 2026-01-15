<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đăng Nhập - Buffet Paradise</title>
    
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
                <p>Đăng nhập để đặt bàn và nhận ưu đãi</p>
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
        
        <!-- Right Side - Login Form -->
        <div class="auth-right">
            <div class="auth-box">
                <div class="auth-header">
                    <h2>Đăng Nhập</h2>
                    <p>Chào mừng bạn quay lại!</p>
                </div>
                
                <!-- Alert Messages -->
                <% 
                String error = request.getParameter("error");
                String success = request.getParameter("success");
                
                if (error != null) { %>
                    <div class="alert alert-error">
                        ❌ 
                        <% if (error.equals("invalid")) { %>
                            Email hoặc mật khẩu không đúng!
                        <% } else if (error.equals("required")) { %>
                            Vui lòng nhập đầy đủ thông tin!
                        <% } else { %>
                            Đã có lỗi xảy ra. Vui lòng thử lại!
                        <% } %>
                    </div>
                <% } %>
                
                <% if (success != null && success.equals("registered")) { %>
                    <div class="alert alert-success">
                        ✅ Đăng ký thành công! Vui lòng đăng nhập.
                    </div>
                <% } %>
                
                <!-- Login Form -->
                <form action="LoginServlet" method="post" data-validate>
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
                        <label for="password">🔒 Mật Khẩu</label>
                        <input 
                            type="password" 
                            id="password" 
                            name="password" 
                            placeholder="••••••••"
                            required>
                    </div>
                    
                    <div class="form-group">
                        <label style="display: flex; align-items: center; gap: 8px; cursor: pointer;">
                            <input type="checkbox" name="remember" style="width: auto;">
                            <span style="font-weight: normal;">Ghi nhớ đăng nhập</span>
                        </label>
                    </div>
                    
                    <button type="submit" class="btn btn-primary btn-full btn-large">
                        🔐 Đăng Nhập
                    </button>
                </form>
                
                <!-- Divider -->
                <div class="divider"></div>
                
                <!-- Register Link -->
                <p class="text-center" style="margin: 0;">
                    Chưa có tài khoản? 
                    <a href="register.jsp" style="color: var(--gold-primary); font-weight: 600;">
                        Đăng ký ngay
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
        // Additional login page specific JS
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.querySelector('form[data-validate]');
            
            if (form) {
                form.addEventListener('submit', function(e) {
                    const email = document.getElementById('email').value.trim();
                    const password = document.getElementById('password').value.trim();
                    
                    if (!email || !password) {
                        e.preventDefault();
                        BuffetParadise.showToast('Vui lòng nhập đầy đủ thông tin!', 'error');
                        return;
                    }
                    
                    if (!email.includes('@')) {
                        e.preventDefault();
                        BuffetParadise.showToast('Email không hợp lệ!', 'error');
                        return;
                    }
                });
            }
        });
    </script>
</body>
</html>