<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Đặt bàn - Buffet Paradise Admin</title>
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
                    <a href="admin-dashboard.jsp">📊 Dashboard</a>
                    <a href="admin-bookings.jsp" class="active">📅 Quản Lý Đặt Bàn</a>
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
                <h1>📅 Quản Lý Đặt Bàn</h1>
                <p>Quản lý và xử lý các đơn đặt bàn của khách hàng</p>
            </div>
            <div class="page-actions">
                <button class="btn" onclick="location.reload()">🔄 Làm Mới</button>
                <button class="btn btn-primary" onclick="alert('Tạo đơn mới')">➕ Tạo Đơn Mới</button>
            </div>
        </div>

        <!-- Bookings Table -->
        <div class="card">
            <div class="card-header">
                <h3>📋 Danh Sách Đặt Bàn</h3>
            </div>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Mã Đơn</th>
                            <th>Khách Hàng</th>
                            <th>SĐT</th>
                            <th>Ngày & Giờ</th>
                            <th>Số Người</th>
                            <th>Bàn</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><strong>BP-20260116-A3B7</strong></td>
                            <td>Nguyễn Văn A</td>
                            <td>0901234567</td>
                            <td>16/01/2026 19:00</td>
                            <td>4 người</td>
                            <td>Bàn 05</td>
                            <td><span class="status-badge status-pending">⏳ Chờ xác nhận</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Xác nhận">✅</button>
                                    <button class="btn-icon" title="Hủy">❌</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>BP-20260116-X2Y9</strong></td>
                            <td>Trần Thị B</td>
                            <td>0907654321</td>
                            <td>16/01/2026 18:30</td>
                            <td>2 người</td>
                            <td>Bàn 02</td>
                            <td><span class="status-badge status-confirmed">✅ Đã xác nhận</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>BP-20260116-M5N8</strong></td>
                            <td>Lê Văn C</td>
                            <td>0912345678</td>
                            <td>16/01/2026 20:00</td>
                            <td>6 người</td>
                            <td>Bàn 08</td>
                            <td><span class="status-badge status-pending">⏳ Chờ xác nhận</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Xác nhận">✅</button>
                                    <button class="btn-icon" title="Hủy">❌</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>BP-20260115-P9Q2</strong></td>
                            <td>Phạm Thị D</td>
                            <td>0923456789</td>
                            <td>15/01/2026 19:30</td>
                            <td>3 người</td>
                            <td>Bàn 12</td>
                            <td><span class="status-badge status-cancelled">❌ Đã hủy</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
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