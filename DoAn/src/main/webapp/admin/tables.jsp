<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý Bàn - Nhà Hàng Buffet</title>
    <link rel="stylesheet" href="../assets/css/admin-style.css">
</head>
<body>
    <!-- Header -->
    <header class="admin-header">
        <div class="container">
            <h1>Quản Lý Bàn</h1>
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
                    <h2>Danh Sách Bàn</h2>
                    <p class="subtitle">Quản lý toàn bộ bàn trong nhà hàng</p>
                </div>
                <button class="btn-primary" onclick="showAddTableModal()">+ Thêm Bàn Mới</button>
            </div>

            <!-- Filter Section -->
            <div class="filter-section">
                <div class="filter-group">
                    <label>Lọc theo trạng thái:</label>
                    <select id="statusFilter" onchange="filterTables()">
                        <option value="all">Tất cả</option>
                        <option value="available">Trống</option>
                        <option value="occupied">Đang dùng</option>
                        <option value="reserved">Đã đặt</option>
                    </select>
                </div>
                
                <div class="filter-group">
                    <label>Tìm kiếm:</label>
                    <input type="text" id="searchTable" placeholder="Tìm theo tên bàn..." onkeyup="searchTables()">
                </div>
            </div>

            <!-- Statistics Cards -->
            <div class="stats-grid">
                <div class="stat-card stat-total">
                    <div class="stat-icon">🍽️</div>
                    <div class="stat-info">
                        <p class="stat-label">Tổng số bàn</p>
                        <p class="stat-value" id="totalTables">0</p>
                    </div>
                </div>
                
                <div class="stat-card stat-available">
                    <div class="stat-icon">✅</div>
                    <div class="stat-info">
                        <p class="stat-label">Bàn trống</p>
                        <p class="stat-value" id="availableTables">0</p>
                    </div>
                </div>
                
                <div class="stat-card stat-occupied">
                    <div class="stat-icon">🔴</div>
                    <div class="stat-info">
                        <p class="stat-label">Đang sử dụng</p>
                        <p class="stat-value" id="occupiedTables">0</p>
                    </div>
                </div>
                
                <div class="stat-card stat-reserved">
                    <div class="stat-icon">📅</div>
                    <div class="stat-info">
                        <p class="stat-label">Đã đặt trước</p>
                        <p class="stat-value" id="reservedTables">0</p>
                    </div>
                </div>
            </div>

            <!-- Tables Grid -->
            <div id="tablesGrid" class="tables-grid">
                <!-- Tables will be rendered here -->
            </div>

            <!-- Empty State -->
            <div id="emptyState" class="empty-state" style="display: none;">
                <div class="empty-icon">🍽️</div>
                <h3>Không tìm thấy bàn nào</h3>
                <p>Thử thay đổi bộ lọc hoặc thêm bàn mới</p>
            </div>
        </div>
    </main>

    <!-- Modal Thêm/Sửa Bàn -->
    <div id="tableModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeTableModal()">&times;</span>
            <h3 id="modalTitle">Thêm Bàn Mới</h3>
            
            <form id="tableForm" onsubmit="handleTableSubmit(event)">
                <input type="hidden" id="tableId">
                
                <div class="form-group">
                    <label>Tên Bàn <span class="required">*</span></label>
                    <input type="text" id="tableNumber" placeholder="Vd: Bàn 7" required>
                    <small>Tên bàn phải là duy nhất</small>
                </div>
                
                <div class="form-group">
                    <label>Số Ghế <span class="required">*</span></label>
                    <input type="number" id="tableSeats" min="1" max="20" value="4" required>
                    <small>Số ghế từ 1 đến 20</small>
                </div>
                
                <div class="form-group">
                    <label>Trạng Thái</label>
                    <select id="tableStatus">
                        <option value="available">Trống</option>
                        <option value="occupied">Đang dùng</option>
                        <option value="reserved">Đã đặt</option>
                    </select>
                </div>
                
                <div class="form-group">
                    <label>Ghi chú</label>
                    <textarea id="tableNote" rows="3" placeholder="Ghi chú về bàn (tùy chọn)"></textarea>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn-primary" id="submitBtn">Thêm Bàn</button>
                    <button type="button" class="btn-secondary" onclick="closeTableModal()">Hủy</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Modal Chi Tiết Bàn -->
    <div id="detailModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeDetailModal()">&times;</span>
            <h3>Chi Tiết Bàn</h3>
            
            <div class="detail-content" id="detailContent">
                <!-- Details will be rendered here -->
            </div>
            
            <div class="form-actions">
                <button class="btn-secondary" onclick="closeDetailModal()">Đóng</button>
            </div>
        </div>
    </div>

    <script src="../assets/js/admin-data.js"></script>
    <script src="../assets/js/tables-management.js"></script>
</body>
</html>