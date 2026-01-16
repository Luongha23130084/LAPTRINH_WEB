<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="model.User" %>

<%
    User admin = (User) session.getAttribute("admin");
    String adminName = "Admin Test"; // Tên mặc định
    
    // Nếu có admin trong session thì lấy tên thật
    if (admin != null && admin.getFullName() != null) {
        adminName = admin.getFullName();
    }
%>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - Buffet Paradise</title>
    <link rel="stylesheet" href="css/admin-styles.css">
    <script src="js/admin-scripts.js" defer></script>
</head>
<body>
    <!-- HEADER -->
    <header class="header">
        <div class="container">
            <div class="header-content">
                <a href="admin-dashboard.jsp" class="logo">
                    <span>🍽️</span>
                    <span>Buffet Paradise Admin</span>
                </a>
                
                <nav class="nav-desktop">
                    <a href="admin-dashboard.jsp" class="active">📊 Dashboard</a>
                    <a href="admin-bookings.jsp">📅 Quản Lý Đặt Bàn</a>
                    <a href="admin-tables.jsp">🪑 Quản Lý Bàn</a>
                    <a href="admin-customers.jsp">👥 Khách Hàng</a>
                </nav>
                
                <button class="btn" onclick="window.location.href='index.jsp'">
                    🏠 Về Trang Chủ
                </button>
            </div>
        </div>
    </header>

    <!-- MAIN CONTENT -->
    <div class="admin-main">
        <!-- Page Header -->
        <div class="page-header">
            <div>
                <h1>📊 Dashboard Tổng Quan</h1>
                <p>Xin chào, <%= adminName %>! Quản lý và theo dõi hoạt động nhà hàng</p>
            </div>
            <div class="page-actions">
                <button class="btn" onclick="location.reload()">🔄 Làm Mới</button>
                <button class="btn btn-primary" onclick="window.location.href='admin-bookings.jsp'">➕ Tạo Đơn Mới</button>
            </div>
        </div>

        <!-- Stats Grid -->
        <div class="stats-grid-4">
            <div class="card stat-card">
                <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                    <div>
                        <span class="stat-label">Đơn Hôm Nay</span>
                        <span class="stat-value">45</span>
                        <span class="stat-change positive">↗ +12% so với hôm qua</span>
                    </div>
                    <div style="font-size: 2.5rem; opacity: 0.3;">📅</div>
                </div>
            </div>

            <div class="card stat-card">
                <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                    <div>
                        <span class="stat-label">Chờ Xác Nhận</span>
                        <span class="stat-value warning">8</span>
                        <span class="stat-change">Cần xử lý</span>
                    </div>
                    <div style="font-size: 2.5rem; opacity: 0.3;">⏳</div>
                </div>
            </div>

            <div class="card stat-card">
                <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                    <div>
                        <span class="stat-label">Doanh Thu Hôm Nay</span>
                        <span class="stat-value success">13.455.000đ</span>
                        <span class="stat-change positive">↗ +8% so với hôm qua</span>
                    </div>
                    <div style="font-size: 2.5rem; opacity: 0.3;">💰</div>
                </div>
            </div>

            <div class="card stat-card">
                <div style="display: flex; justify-content: space-between; align-items: flex-start;">
                    <div>
                        <span class="stat-label">Tổng Khách Hàng</span>
                        <span class="stat-value">1,234</span>
                        <span class="stat-change positive">↗ +25 người mới</span>
                    </div>
                    <div style="font-size: 2.5rem; opacity: 0.3;">👥</div>
                </div>
            </div>
        </div>

        <!-- Charts Row -->
        <div class="charts-row">
            <!-- Revenue Chart -->
            <div class="card">
                <div class="card-header">
                    <h3>📈 Doanh Thu 7 Ngày Qua</h3>
                </div>
                <div class="chart-placeholder">
                    📊 Biểu đồ doanh thu (cần Chart.js để hiển thị)
                </div>
            </div>

            <!-- Table Status -->
            <div class="card">
                <div class="card-header">
                    <h3>🪑 Tình Trạng Bàn</h3>
                </div>
                <div>
                    <div class="summary-row">
                        <span>Bàn Trống</span>
                        <strong style="color: #10b981;">15</strong>
                    </div>
                    <div class="summary-row">
                        <span>Đã Đặt</span>
                        <strong style="color: #fbbf24;">8</strong>
                    </div>
                    <div class="summary-row">
                        <span>Đang Sử Dụng</span>
                        <strong style="color: #ef4444;">12</strong>
                    </div>
                    <div class="divider"></div>
                    <div class="summary-row">
                        <span>Tổng Số Bàn</span>
                        <strong>35</strong>
                    </div>
                </div>
            </div>
        </div>

        <!-- Recent Bookings -->
        <div class="card">
            <div class="card-header">
                <h3>📋 Đơn Đặt Bàn Mới Nhất</h3>
                <button class="btn" onclick="window.location.href='admin-bookings.jsp'">Xem Tất Cả →</button>
            </div>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Mã Đơn</th>
                            <th>Khách Hàng</th>
                            <th>Ngày & Giờ</th>
                            <th>Số Người</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><strong>BP-20260116-A3B7</strong></td>
                            <td>Nguyễn Văn A</td>
                            <td>16/01/2026 19:00</td>
                            <td>4 người</td>
                            <td><span class="status-badge status-pending">⏳ Chờ xác nhận</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Xác nhận">✅</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>BP-20260116-X2Y9</strong></td>
                            <td>Trần Thị B</td>
                            <td>16/01/2026 18:30</td>
                            <td>2 người</td>
                            <td><span class="status-badge status-confirmed">✅ Đã xác nhận</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>BP-20260116-M5N8</strong></td>
                            <td>Lê Văn C</td>
                            <td>16/01/2026 20:00</td>
                            <td>6 người</td>
                            <td><span class="status-badge status-pending">⏳ Chờ xác nhận</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Xác nhận">✅</button>
                                </div>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>