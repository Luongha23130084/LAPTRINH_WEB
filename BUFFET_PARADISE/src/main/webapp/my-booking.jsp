<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
    // ✅ BỎ KIỂM TRA - AI CŨNG VÀO ĐƯỢC
    User user = (User) session.getAttribute("user");
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đơn Của Tôi - Buffet Paradise</title>
    
    <link rel="stylesheet" href="css/variables.css">
    <link rel="stylesheet" href="css/components.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/pages.css">
    
    <style>
        .my-bookings-page {
            min-height: 100vh;
            background: var(--bg-primary);
            padding: var(--spacing-xl) 0;
        }
        
        .booking-item {
            background: var(--bg-secondary);
            border-radius: var(--radius-lg);
            padding: var(--spacing-lg);
            margin-bottom: var(--spacing-md);
            border: 1px solid rgba(212, 175, 55, 0.2);
            transition: all 0.3s;
        }
        
        .booking-item:hover {
            transform: translateY(-3px);
            box-shadow: var(--shadow-lg);
        }
        
        .booking-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: var(--spacing-md);
        }
        
        .booking-code {
            font-size: var(--font-lg);
            font-weight: 700;
            color: var(--gold-primary);
        }
        
        .booking-info {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: var(--spacing-md);
            margin-bottom: var(--spacing-md);
        }
        
        .info-item {
            display: flex;
            flex-direction: column;
            gap: 0.25rem;
        }
        
        .info-label {
            font-size: var(--font-xs);
            color: var(--text-tertiary);
        }
        
        .info-value {
            font-size: var(--font-md);
            font-weight: 600;
            color: var(--text-primary);
        }
        
        @media (max-width: 768px) {
            .booking-info {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="container">
            <div class="header-content">
                <a href="index.jsp" class="logo">
                    <span class="logo-icon">🍽️</span>
                    <span class="logo-text">Buffet Paradise</span>
                </a>
                
                <nav class="nav-desktop">
                    <a href="index.jsp">🏠 Trang Chủ</a>
                    <a href="booking.jsp">📅 Đặt Bàn</a>
                    <a href="my-booking.jsp" class="active">📋 Đơn Của Tôi</a>
                </nav>
            <div class="header-actions">
    <% if (user != null) { %>
        <span style="color: var(--text-secondary); margin-right: 1rem;">
            Xin chào, <strong style="color: var(--gold-primary);"><%= user.getFullName() %></strong>
        </span>
        <button class="btn btn-outline btn-sm" onclick="window.location.href='LogoutServlet'">
            🚪 Đăng Xuất
        </button>
    <% } %>
</div>
    </header>

    <div class="my-bookings-page">
        <div class="container" style="max-width: 1200px;">
            <h1 style="color: var(--gold-primary); margin-bottom: var(--spacing-xl);">
                📋 Đơn Đặt Bàn Của Tôi
            </h1>

            <div id="bookingsList">
                <!-- ✅ DEMO DATA - KHÔNG CẦN ĐĂNG NHẬP -->
                <div class="booking-item">
                    <div class="booking-header">
                        <div class="booking-code">📋 BP-2024-001</div>
                        <span class="status-badge status-confirmed">Đã xác nhận</span>
                    </div>
                    
                    <div class="booking-info">
                        <div class="info-item">
                            <span class="info-label">📅 Ngày</span>
                            <span class="info-value">Thứ Bảy, 20/01/2024</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">🕐 Giờ</span>
                            <span class="info-value">18:00</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">👥 Số khách</span>
                            <span class="info-value">4 người</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">🛋️ Khu vực</span>
                            <span class="info-value">Khu VIP 👑</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">💰 Tổng tiền</span>
                            <span class="info-value">1.196.000đ</span>
                        </div>
                        <div class="info-item">
                            <button class="btn btn-danger btn-sm" onclick="cancelBooking(1)">
                                Hủy Đơn
                            </button>
                        </div>
                    </div>
                    
                    <div style="margin-top: var(--spacing-sm); padding-top: var(--spacing-sm); border-top: 1px solid rgba(255,255,255,0.1);">
                        <span class="info-label">📝 Ghi chú: </span>
                        <span style="color: var(--text-secondary);">Sinh nhật con gái, cần bánh kem</span>
                    </div>
                </div>

                <div class="booking-item">
                    <div class="booking-header">
                        <div class="booking-code">📋 BP-2024-002</div>
                        <span class="status-badge status-pending">Chờ xác nhận</span>
                    </div>
                    
                    <div class="booking-info">
                        <div class="info-item">
                            <span class="info-label">📅 Ngày</span>
                            <span class="info-value">Chủ Nhật, 28/01/2024</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">🕐 Giờ</span>
                            <span class="info-value">12:00</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">👥 Số khách</span>
                            <span class="info-value">8 người</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">🛋️ Khu vực</span>
                            <span class="info-value">Khu Gia Đình 👨‍👩‍👧‍👦</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">💰 Tổng tiền</span>
                            <span class="info-value">2.392.000đ</span>
                        </div>
                        <div class="info-item">
                            <button class="btn btn-danger btn-sm" onclick="cancelBooking(2)">
                                Hủy Đơn
                            </button>
                        </div>
                    </div>
                </div>

                <div class="booking-item">
                    <div class="booking-header">
                        <div class="booking-code">📋 BP-2024-003</div>
                        <span class="status-badge status-completed">Hoàn thành</span>
                    </div>
                    
                    <div class="booking-info">
                        <div class="info-item">
                            <span class="info-label">📅 Ngày</span>
                            <span class="info-value">Thứ Sáu, 12/01/2024</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">🕐 Giờ</span>
                            <span class="info-value">19:00</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">👥 Số khách</span>
                            <span class="info-value">2 người</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">🛋️ Khu vực</span>
                            <span class="info-value">Khu Ngoài Trời 🌳</span>
                        </div>
                        <div class="info-item">
                            <span class="info-label">💰 Tổng tiền</span>
                            <span class="info-value">598.000đ</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="js/main.js"></script>
    <script>
        function cancelBooking(bookingId) {
            if (!confirm('Bạn có chắc muốn hủy đơn này?')) return;
            
            const reason = prompt('Lý do hủy (tùy chọn):');
            
            alert('✅ Đã hủy đơn #' + bookingId + ' thành công!\n\n' + 
                  'Lý do: ' + (reason || 'Không có'));
            
            // Reload page
            location.reload();
        }
    </script>
</body>
</html>