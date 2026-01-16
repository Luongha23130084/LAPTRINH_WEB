<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản lý Bàn ăn - Buffet Paradise Admin</title>
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
                    <a href="admin-tables.jsp" class="active">🪑 Quản Lý Bàn</a>
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
                <h1>🪑 Quản Lý Bàn Ăn</h1>
                <p>Quản lý tình trạng và thông tin các bàn trong nhà hàng</p>
            </div>
            <div class="page-actions">
                <button class="btn" onclick="location.reload()">🔄 Làm Mới</button>
                <button class="btn btn-primary" onclick="alert('Thêm bàn mới')">➕ Thêm Bàn Mới</button>
            </div>
        </div>

        <!-- Stats -->
        <div class="stats-grid-4">
            <div class="card stat-card">
                <span class="stat-label">Tổng Số Bàn</span>
                <span class="stat-value">35</span>
            </div>
            <div class="card stat-card">
                <span class="stat-label">Bàn Trống</span>
                <span class="stat-value success">15</span>
            </div>
            <div class="card stat-card">
                <span class="stat-label">Đã Đặt</span>
                <span class="stat-value warning">8</span>
            </div>
            <div class="card stat-card">
                <span class="stat-label">Đang Sử Dụng</span>
                <span class="stat-value" style="color: #ef4444;">12</span>
            </div>
        </div>

        <!-- Tables List -->
        <div class="card">
            <div class="card-header">
                <h3>📋 Danh Sách Bàn</h3>
            </div>
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Số Bàn</th>
                            <th>Sức Chứa</th>
                            <th>Vị Trí</th>
                            <th>Trạng Thái</th>
                            <th>Ghi Chú</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><strong>Bàn 01</strong></td>
                            <td>4 người</td>
                            <td>Tầng 1 - Cạnh cửa sổ</td>
                            <td><span class="status-badge status-confirmed">✅ Trống</span></td>
                            <td>-</td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                    <button class="btn-icon" title="Xóa">🗑️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Bàn 02</strong></td>
                            <td>2 người</td>
                            <td>Tầng 1 - Giữa phòng</td>
                            <td><span class="status-badge status-pending">⏳ Đã đặt</span></td>
                            <td>Đặt lúc 18:30</td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Bàn 03</strong></td>
                            <td>6 người</td>
                            <td>Tầng 2 - VIP</td>
                            <td><span class="status-badge status-confirmed">✅ Trống</span></td>
                            <td>Phòng riêng</td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                    <button class="btn-icon" title="Xóa">🗑️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Bàn 04</strong></td>
                            <td>4 người</td>
                            <td>Tầng 1 - Gần bar</td>
                            <td><span class="status-badge status-cancelled">🔴 Đang dùng</span></td>
                            <td>Check-in 19:15</td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><strong>Bàn 05</strong></td>
                            <td>8 người</td>
                            <td>Tầng 2 - Góc yên tĩnh</td>
                            <td><span class="status-badge status-pending">⏳ Đã đặt</span></td>
                            <td>Đặt lúc 19:00</td>
                            <td>
                                <div class="action-buttons">
                                    <button class="btn-icon" title="Xem">👁️</button>
                                    <button class="btn-icon" title="Chỉnh sửa">✏️</button>
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