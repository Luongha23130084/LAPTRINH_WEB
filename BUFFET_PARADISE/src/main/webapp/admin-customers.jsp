<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Khách hàng - Buffet Paradise Admin</title>
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
                    <a href="admin-bookings.jsp">📅 Quản Lý Đặt Bàn</a>
                    <a href="admin-tables.jsp">🪑 Quản Lý Bàn</a>
                    <a href="admin-customers.jsp" class="active">👥 Khách Hàng</a>
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
                <h1>👥 Quản Lý Khách Hàng</h1>
                <p>Danh sách và thông tin khách hàng của nhà hàng</p>
            </div>
            <div class="page-actions">
                <button class="btn" onclick="location.reload()">🔄 Làm Mới</button>
                <button class="btn btn-primary" onclick="alert('Thêm khách hàng')">➕ Thêm Khách Hàng</button>
            </div>
        </div>

        <!-- Stats -->
        <div class="stats-grid-4">
            <div class="card stat-card">
                <span class="stat-label">Tổng Khách Hàng</span>
                <span class="stat-value">1,234</span>
                <span class="stat-change positive">↗ +25 người mới tuần này</span>
            </div>
            <div class="card stat-card">
                <span class="stat-label">Khách VIP</span>
                <span class="stat-value warning">87</span>
            </div>
            <div class="card stat-card">
                <span class="stat-label">Khách Mới Tháng Này</span>
                <span class="stat-value success">156</span>
            </div>
            <div class="card stat-card">
                <span class="stat-label">Tổng Đơn Đã Đặt</span>
                <span class="stat-value">3,456</span>
            </div>
        </div>

        <!-- Customers Table -->
        <div class="card">
            <div class="card-header">
                <h3>📋 Danh Sách Khách Hàng</h3>
            </div>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Họ và Tên</th>
                            <th>Email</th>
                            <th>Số Điện Thoại</th>
                            <th>Ngày Đăng Ký</th>
                            <th>Số Đơn</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><strong>001</strong></td>
                            <td>Nguyễn Văn A</td>
                            <td>nguyenvana@email.com</td>
                            <td>0901234567</td>
                            <td>15/01/2026</td>
                            <td><span class="status-badge status-confirmed">12 đơn</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                    <button class="btn-icon" title="Xóa">🗑️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>002</strong></td>
                            <td>Trần Thị B</td>
                            <td>tranthib@email.com</td>
                            <td>0907654321</td>
                            <td>14/01/2026</td>
                            <td><span class="status-badge status-confirmed">8 đơn</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                    <button class="btn-icon" title="Xóa">🗑️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>003</strong></td>
                            <td>Lê Văn C</td>
                            <td>levanc@email.com</td>
                            <td>0912345678</td>
                            <td>13/01/2026</td>
                            <td><span class="status-badge status-confirmed">5 đơn</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                    <button class="btn-icon" title="Xóa">🗑️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>004</strong></td>
                            <td>Phạm Thị D</td>
                            <td>phamthid@email.com</td>
                            <td>0923456789</td>
                            <td>12/01/2026</td>
                            <td><span class="status-badge status-pending">2 đơn</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                    <button class="btn-icon" title="Xóa">🗑️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>005</strong></td>
                            <td>Hoàng Văn E</td>
                            <td>hoangvane@email.com</td>
                            <td>0934567890</td>
                            <td>11/01/2026</td>
                            <td><span class="status-badge status-confirmed">15 đơn</span></td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem chi tiết">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                    <button class="btn-icon" title="Xóa">🗑️</button>
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