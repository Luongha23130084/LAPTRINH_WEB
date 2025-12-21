<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Quản Lý - Nhà Hàng Buffet</title>
    <link rel="stylesheet" href="../assets/css/admin-style.css">
</head>
<body>
    <!-- Header -->
    <header class="admin-header">
        <div class="container">
            <h1>Quản Lý Nhà Hàng</h1>
            <button class="btn-logout" onclick="logout()">Đăng Xuất</button>
        </div>
    </header>

    <!-- Navigation Tabs -->
    <div class="tabs-container">
        <div class="container">
            <div class="tabs">
                <button class="tab active" onclick="switchTab('tables')" id="tab-tables">
                    Quản Lý Bàn
                </button>
                <button class="tab" onclick="switchTab('bookings')" id="tab-bookings">
                    Quản Lý Đặt Bàn
                </button>
            </div>
        </div>
    </div>

    <!-- Main Content -->
    <main class="main-content">
        <div class="container">
            <!-- Tables Section -->
            <div id="section-tables" class="content-section active">
                <div class="section-header">
                    <h2>Danh Sách Bàn</h2>
                    <button class="btn-primary" onclick="showAddTableModal()">+ Thêm Bàn</button>
                </div>

                <div id="tablesGrid" class="tables-grid">
                    <!-- Tables will be rendered here by JavaScript -->
                </div>
            </div>

            <!-- Bookings Section -->
            <div id="section-bookings" class="content-section">
                <div class="section-header">
                    <h2>Danh Sách Đặt Bàn</h2>
                </div>

                <div class="table-container">
                    <table id="bookingsTable" class="data-table">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Khách Hàng</th>
                                <th>SĐT</th>
                                <th>Ngày</th>
                                <th>Giờ</th>
                                <th>Số Khách</th>
                                <th>Trạng Thái</th>
                                <th>Thao Tác</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Bookings will be rendered here by JavaScript -->
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </main>

    <!-- Modal Thêm Bàn -->
    <div id="addTableModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeAddTableModal()">&times;</span>
            <h3>Thêm Bàn Mới</h3>
            
            <form id="addTableForm" onsubmit="handleAddTable(event)">
                <div class="form-group">
                    <label>Tên Bàn</label>
                    <input type="text" id="tableNumber" placeholder="Vd: Bàn 7" required>
                </div>
                
                <div class="form-group">
                    <label>Số Ghế</label>
                    <input type="number" id="tableSeats" min="1" value="4" required>
                </div>
                
                <div class="form-actions">
                    <button type="submit" class="btn-primary">Thêm</button>
                    <button type="button" class="btn-secondary" onclick="closeAddTableModal()">Hủy</button>
                </div>
            </form>
        </div>
    </div>

    <script src="../assets/js/admin-data.js"></script>
    <script src="../assets/js/admin-main.js"></script>
</body>
</html>