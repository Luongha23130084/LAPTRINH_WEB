/**
 * TABLES-MANAGEMENT.JS
 * Logic xử lý trang quản lý bàn riêng
 * Chức năng: CRUD bàn, lọc, tìm kiếm, thống kê
 */

// ==================== GLOBAL STATE ====================
let currentEditId = null;
let filteredTables = [];

// ==================== INITIALIZATION ====================

/**
 * Khởi tạo trang
 */
document.addEventListener('DOMContentLoaded', function() {
    renderTables();
    updateStatistics();
});

// ==================== RENDERING ====================

/**
 * Render danh sách bàn
 */
function renderTables() {
    const tables = dataStorage.getAllTables();
    filteredTables = tables;
    
    const container = document.getElementById('tablesGrid');
    const emptyState = document.getElementById('emptyState');
    
    if (tables.length === 0) {
        container.style.display = 'none';
        emptyState.style.display = 'flex';
        return;
    }
    
    container.style.display = 'grid';
    emptyState.style.display = 'none';
    
    container.innerHTML = tables.map(table => createTableCard(table)).join('');
    updateStatistics();
}

/**
 * Tạo HTML cho card bàn
 */
function createTableCard(table) {
    const statusText = dataStorage.getStatusText(table.status);
    const statusClass = dataStorage.getStatusClass(table.status);
    
    return `
        <div class="table-card" data-status="${table.status}">
            <div class="table-card-header">
                <h3>${table.number}</h3>
                <span class="status-badge ${statusClass}">${statusText}</span>
            </div>
            
            <div class="table-info">
                <span>👥</span>
                <span>${table.seats} ghế</span>
            </div>
            
            <div class="table-card-footer">
                <button class="btn-view" onclick="viewTableDetail(${table.id})">
                    👁️ Xem
                </button>
                <button class="btn-edit" onclick="editTable(${table.id})">
                    ✏️ Sửa
                </button>
                <button class="btn-danger" onclick="deleteTable(${table.id})">
                    🗑️ Xóa
                </button>
            </div>
            
            ${table.status === 'available' ? `
                <div class="table-quick-actions">
                    <button class="btn-quick" onclick="quickChangeStatus(${table.id}, 'occupied')">
                        → Đang dùng
                    </button>
                    <button class="btn-quick" onclick="quickChangeStatus(${table.id}, 'reserved')">
                        → Đã đặt
                    </button>
                </div>
            ` : ''}
        </div>
    `;
}

/**
 * Cập nhật thống kê
 */
function updateStatistics() {
    const tables = dataStorage.getAllTables();
    
    const total = tables.length;
    const available = tables.filter(t => t.status === 'available').length;
    const occupied = tables.filter(t => t.status === 'occupied').length;
    const reserved = tables.filter(t => t.status === 'reserved').length;
    
    document.getElementById('totalTables').textContent = total;
    document.getElementById('availableTables').textContent = available;
    document.getElementById('occupiedTables').textContent = occupied;
    document.getElementById('reservedTables').textContent = reserved;
}

// ==================== MODAL MANAGEMENT ====================

/**
 * Hiển thị modal thêm bàn
 */
function showAddTableModal() {
    currentEditId = null;
    document.getElementById('modalTitle').textContent = 'Thêm Bàn Mới';
    document.getElementById('submitBtn').textContent = 'Thêm Bàn';
    document.getElementById('tableForm').reset();
    document.getElementById('tableId').value = '';
    document.getElementById('tableModal').classList.add('active');
}

/**
 * Đóng modal bàn
 */
function closeTableModal() {
    document.getElementById('tableModal').classList.remove('active');
    document.getElementById('tableForm').reset();
    currentEditId = null;
}

/**
 * Xử lý submit form
 */
function handleTableSubmit(event) {
    event.preventDefault();
    
    const id = currentEditId;
    const number = document.getElementById('tableNumber').value.trim();
    const seats = parseInt(document.getElementById('tableSeats').value);
    const status = document.getElementById('tableStatus').value;
    
    if (id) {
        // Update existing table
        dataStorage.updateTable(id, { number, seats, status });
        alert('Cập nhật bàn thành công!');
    } else {
        // Add new table
        dataStorage.addTable(number, seats);
        alert('Thêm bàn thành công!');
    }
    
    closeTableModal();
    renderTables();
}

// ==================== TABLE ACTIONS ====================

/**
 * Xem chi tiết bàn
 */
function viewTableDetail(id) {
    const table = dataStorage.getTableById(id);
    if (!table) return;
    
    const statusText = dataStorage.getStatusText(table.status);
    const statusClass = dataStorage.getStatusClass(table.status);
    
    const detailContent = document.getElementById('detailContent');
    detailContent.innerHTML = `
        <div class="detail-row">
            <strong>Tên bàn:</strong>
            <span>${table.number}</span>
        </div>
        <div class="detail-row">
            <strong>Số ghế:</strong>
            <span>${table.seats} ghế</span>
        </div>
        <div class="detail-row">
            <strong>Trạng thái:</strong>
            <span class="status-badge ${statusClass}">${statusText}</span>
        </div>
        <div class="detail-row">
            <strong>ID:</strong>
            <span>#${table.id}</span>
        </div>
    `;
    
    document.getElementById('detailModal').classList.add('active');
}

/**
 * Đóng modal chi tiết
 */
function closeDetailModal() {
    document.getElementById('detailModal').classList.remove('active');
}

/**
 * Sửa bàn
 */
function editTable(id) {
    const table = dataStorage.getTableById(id);
    if (!table) return;
    
    currentEditId = id;
    document.getElementById('modalTitle').textContent = 'Sửa Thông Tin Bàn';
    document.getElementById('submitBtn').textContent = 'Cập Nhật';
    document.getElementById('tableId').value = id;
    document.getElementById('tableNumber').value = table.number;
    document.getElementById('tableSeats').value = table.seats;
    document.getElementById('tableStatus').value = table.status;
    
    document.getElementById('tableModal').classList.add('active');
}

/**
 * Xóa bàn
 */
function deleteTable(id) {
    const table = dataStorage.getTableById(id);
    if (!table) return;
    
    if (confirm(`Bạn có chắc muốn xóa "${table.number}"?`)) {
        dataStorage.deleteTable(id);
        renderTables();
        alert('Đã xóa bàn!');
    }
}

/**
 * Thay đổi trạng thái nhanh
 */
function quickChangeStatus(id, newStatus) {
    dataStorage.updateTable(id, { status: newStatus });
    renderTables();
}

// ==================== FILTER & SEARCH ====================

/**
 * Lọc bàn theo trạng thái
 */
function filterTables() {
    const status = document.getElementById('statusFilter').value;
    const searchTerm = document.getElementById('searchTable').value.toLowerCase();
    
    let tables = dataStorage.getAllTables();
    
    // Filter by status
    if (status !== 'all') {
        tables = tables.filter(t => t.status === status);
    }
    
    // Filter by search term
    if (searchTerm) {
        tables = tables.filter(t => 
            t.number.toLowerCase().includes(searchTerm)
        );
    }
    
    filteredTables = tables;
    displayFilteredTables(tables);
}

/**
 * Tìm kiếm bàn
 */
function searchTables() {
    filterTables();
}

/**
 * Hiển thị kết quả lọc
 */
function displayFilteredTables(tables) {
    const container = document.getElementById('tablesGrid');
    const emptyState = document.getElementById('emptyState');
    
    if (tables.length === 0) {
        container.style.display = 'none';
        emptyState.style.display = 'flex';
        return;
    }
    
    container.style.display = 'grid';
    emptyState.style.display = 'none';
    container.innerHTML = tables.map(table => createTableCard(table)).join('');
}

// ==================== UTILITY ====================

/**
 * Đăng xuất
 */
function logout() {
    if (confirm('Bạn có chắc muốn đăng xuất?')) {
        window.location.href = '../index.jsp';
    }
}

/**
 * Click outside modal to close
 */
window.onclick = function(event) {
    const tableModal = document.getElementById('tableModal');
    const detailModal = document.getElementById('detailModal');
    
    if (event.target === tableModal) {
        closeTableModal();
    }
    if (event.target === detailModal) {
        closeDetailModal();
    }
}