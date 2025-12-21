<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Đặt Bàn - Nhà Hàng Buffet</title>
    <link rel="stylesheet" href="../assets/css/admin-style.css">
</head>
<body>
    <!-- Header -->
    <header class="admin-header">
        <div class="container">
            <h1>Quản Lý Đặt Bàn</h1>
            <div class="header-actions">
                <a href="dashboard.jsp" class="btn-secondary">← Quay lại Dashboard</a>
                <button class="btn-logout" onclick="logout()">Đăng Xuất</button>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <main class="main-content">
        <div class="container">
            <!-- Section Header -->
            <div class="section-header">
                <div class="header-info">
                    <h2>Danh Sách Đặt Bàn</h2>
                    <p class="subtitle">Quản lý và xử lý các yêu cầu đặt bàn</p>
                </div>
                <button class="btn-primary" onclick="exportBookings()">📥 Xuất Excel</button>
            </div>

            <!-- Filter Section -->
            <div class="filter-section">
                <div class="filter-group">
                    <label>Trạng thái:</label>
                    <select id="statusFilter" onchange="filterBookings()">
                        <option value="all">Tất cả</option>
                        <option value="pending">Chờ xác nhận</option>
                        <option value="confirmed">Đã xác nhận</option>
                        <option value="completed">Hoàn thành</option>
                    </select>
                </div>
                
                <div class="filter-group">
                    <label>Ngày:</label>
                    <input type="date" id="dateFilter" onchange="filterBookings()">
                </div>
                
                <div class="filter-group">
                    <label>Tìm kiếm:</label>
                    <input type="text" id="searchBooking" placeholder="Tên khách hoặc SĐT..." onkeyup="searchBookings()">
                </div>
                
                <button class="btn-secondary" onclick="resetFilters()">🔄 Đặt lại</button>
            </div>

            <!-- Statistics Cards -->
            <div class="stats-grid">
                <div class="stat-card stat-total">
                    <div class="stat-icon">📋</div>
                    <div class="stat-info">
                        <p class="stat-label">Tổng đặt bàn</p>
                        <p class="stat-value" id="totalBookings">0</p>
                    </div>
                </div>
                
                <div class="stat-card stat-pending">
                    <div class="stat-icon">⏳</div>
                    <div class="stat-info">
                        <p class="stat-label">Chờ xác nhận</p>
                        <p class="stat-value" id="pendingBookings">0</p>
                    </div>
                </div>
                
                <div class="stat-card stat-confirmed">
                    <div class="stat-icon">✅</div>
                    <div class="stat-info">
                        <p class="stat-label">Đã xác nhận</p>
                        <p class="stat-value" id="confirmedBookings">0</p>
                    </div>
                </div>
                
                <div class="stat-card stat-completed">
                    <div class="stat-icon">🎉</div>
                    <div class="stat-info">
                        <p class="stat-label">Hoàn thành</p>
                        <p class="stat-value" id="completedBookings">0</p>
                    </div>
                </div>
            </div>

            <!-- Bookings Table -->
            <div class="table-container">
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>
                                <input type="checkbox" id="selectAll" onchange="toggleSelectAll()">
                            </th>
                            <th onclick="sortTable('id')">ID ↕️</th>
                            <th onclick="sortTable('customer')">Khách Hàng ↕️</th>
                            <th onclick="sortTable('phone')">SĐT ↕️</th>
                            <th onclick="sortTable('date')">Ngày ↕️</th>
                            <th onclick="sortTable('time')">Giờ ↕️</th>
                            <th onclick="sortTable('guests')">Số Khách ↕️</th>
                            <th>Trạng Thái</th>
                            <th>Thao Tác</th>
                        </tr>
                    </thead>
                    <tbody id="bookingsTableBody">
                        <!-- Bookings will be rendered here -->
                    </tbody>
                </table>
            </div>

            <!-- Empty State -->
            <div id="emptyState" class="empty-state" style="display: none;">
                <div class="empty-icon">📅</div>
                <h3>Không có đặt bàn nào</h3>
                <p>Chưa có yêu cầu đặt bàn nào trong hệ thống</p>
            </div>

            <!-- Bulk Actions -->
            <div class="bulk-actions" id="bulkActions" style="display: none;">
                <span id="selectedCount">0 mục được chọn</span>
                <div class="bulk-buttons">
                    <button class="btn-success" onclick="bulkConfirm()">✅ Xác nhận tất cả</button>
                    <button class="btn-danger" onclick="bulkDelete()">🗑️ Xóa tất cả</button>
                </div>
            </div>
        </div>
    </main>

    <!-- Modal Chi Tiết Đặt Bàn -->
    <div id="detailModal" class="modal">
        <div class="modal-content modal-large">
            <span class="close" onclick="closeDetailModal()">&times;</span>
            <h3>Chi Tiết Đặt Bàn</h3>
            
            <div class="detail-grid" id="detailContent">
                <!-- Details will be rendered here -->
            </div>
            
            <div class="form-actions">
                <button class="btn-secondary" onclick="closeDetailModal()">Đóng</button>
            </div>
        </div>
    </div>

    <!-- Modal Xác Nhận Hành Động -->
    <div id="confirmModal" class="modal">
        <div class="modal-content modal-small">
            <h3>Xác Nhận</h3>
            <p id="confirmMessage">Bạn có chắc muốn thực hiện hành động này?</p>
            
            <div class="form-actions">
                <button class="btn-primary" id="confirmBtn" onclick="confirmAction()">Xác nhận</button>
                <button class="btn-secondary" onclick="closeConfirmModal()">Hủy</button>
            </div>
        </div>
    </div>

    <script src="../assets/js/admin-data.js"></script>
    <script src="../assets/js/bookings-management.js"></script>
</body>
</html>