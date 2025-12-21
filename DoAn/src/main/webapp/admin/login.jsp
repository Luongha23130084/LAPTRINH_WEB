<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Login - Nhà Hàng Buffet</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 1rem;
        }

        .login-container {
            background: white;
            border-radius: 0.5rem;
            box-shadow: 0 20px 25px rgba(0,0,0,0.15);
            padding: 2rem;
            width: 100%;
            max-width: 400px;
        }

        .login-header {
            text-align: center;
            margin-bottom: 2rem;
        }

        .login-header h2 {
            color: #dc2626;
            font-size: 1.75rem;
            margin-bottom: 0.5rem;
        }

        .login-header p {
            color: #6b7280;
            font-size: 0.875rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        label {
            display: block;
            font-size: 0.875rem;
            font-weight: 500;
            color: #374151;
            margin-bottom: 0.5rem;
        }

        .input-group {
            position: relative;
        }

        .input-icon {
            position: absolute;
            left: 0.75rem;
            top: 50%;
            transform: translateY(-50%);
            color: #9ca3af;
        }

        input {
            width: 100%;
            padding: 0.75rem 0.75rem 0.75rem 2.5rem;
            border: 1px solid #d1d5db;
            border-radius: 0.5rem;
            font-size: 1rem;
            transition: all 0.3s;
        }

        input:focus {
            outline: none;
            border-color: #dc2626;
            box-shadow: 0 0 0 3px rgba(220, 38, 38, 0.1);
        }

        .btn-login {
            width: 100%;
            padding: 0.75rem;
            background: #dc2626;
            color: white;
            border: none;
            border-radius: 0.5rem;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s;
        }

        .btn-login:hover {
            background: #b91c1c;
        }

        .back-link {
            display: block;
            text-align: center;
            margin-top: 1rem;
            color: #6b7280;
            text-decoration: none;
            font-size: 0.875rem;
        }

        .back-link:hover {
            color: #374151;
        }

        .demo-info {
            margin-top: 1.5rem;
            padding: 1rem;
            background: #f3f4f6;
            border-radius: 0.5rem;
            font-size: 0.875rem;
            color: #4b5563;
        }

        .demo-info strong {
            color: #1f2937;
        }
    </style>
</head>
<body>
    <div class="login-container">
        <div class="login-header">
            <h2>Đăng Nhập Admin</h2>
            <p>Quản lý hệ thống nhà hàng</p>
        </div>

        <form onsubmit="handleAdminLogin(event)">
            <div class="form-group">
                <label for="username">Tên Đăng Nhập</label>
                <div class="input-group">
                    <span class="input-icon">👤</span>
                    <input 
                        type="text" 
                        id="username" 
                        name="username" 
                        placeholder="Nhập tên đăng nhập"
                        required
                    >
                </div>
            </div>

            <div class="form-group">
                <label for="password">Mật Khẩu</label>
                <div class="input-group">
                    <span class="input-icon">🔒</span>
                    <input 
                        type="password" 
                        id="password" 
                        name="password" 
                        placeholder="Nhập mật khẩu"
                        required
                    >
                </div>
            </div>

            <button type="submit" class="btn-login">Đăng Nhập</button>
        </form>

        <a href="../index.jsp" class="back-link">← Quay lại trang chủ</a>

        <div class="demo-info">
            <strong>Thông tin demo:</strong><br>
            Username: <strong>admin</strong><br>
            Password: <strong>admin</strong>
        </div>
    </div>

    <script>
        function handleAdminLogin(event) {
            event.preventDefault();
            
            const username = document.getElementById('username').value;
            const password = document.getElementById('password').value;
            
            // Demo validation
            if (username === 'admin' && password === 'admin') {
                alert('Đăng nhập thành công!');
                window.location.href = 'dashboard.jsp';
            } else {
                alert('Sai tên đăng nhập hoặc mật khẩu!');
            }
            
            return false;
        }
    </script>
</body>
</html>