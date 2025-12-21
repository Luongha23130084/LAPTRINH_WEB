/**
 * BOOKINGS-MANAGEMENT.JS
 * Logic xử lý trang quản lý đặt bàn riêng
 * Chức năng: Xem, lọc, xác nhận, từ chối, hoàn thành đặt bàn
 */

// ==================== GLOBAL STATE ====================
let selectedBookings = new Set();
let currentSortColumn = 'date';
let currentSortDirection = 'asc';
let confirmCallback = null;

// ==================== INITIALIZATION ====================

/**
 * Khởi tạo trang
 */
document.addEventListener('DOMContentLoaded', function() {
    renderBookings();
    updateStatistics();
    setDefaultDate();
});

/**
 * Set ngày mặc định cho filter
 */
function setDefaultDate() {
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('dateFilter').value = today;
}

// ==================== RENDERING ====================

/**
 * Render danh sách đặt bàn
 */
function renderBookings() {
    const bookings = dataStorage.getAllBookings();
    const tbody = document.getElementById('bookingsTableBody');
    const emptyState = document.getElementById('emptyState');
    
    if (bookings.length === 0) {
        tbody.innerHTML = '';
        emptyState.style.display = 'flex';
        document.querySelector('.table-container').style.display = 'none';
        return;
    }
    
    emptyState.style.display = 'none';
    document.querySelector('.table-container').style.display = 'block';
    
    tbody.innerHTML = bookings.map(booking => createBookingRow(booking)).join('');
    updateStatistics();
}

/**
 * Tạo HTML cho row đặt bàn
 */
function createBookingRow(booking) {
    const statusText = dataStorage.getStatusText(booking.status);
    const statusClass = dataStorage.getStatusClass(booking.status);
    const isSelected = selectedBookings.has(booking.id);
    
    let actions = '';
    if (booking.status === 'pending') {
        actions = `
            <button class="btn-success" onclick="confirmBooking(${booking.id})" title="Xác nhận">
                ✅
            </button>
            <button class="btn-danger" onclick="rejectBooking(${booking.id})" title="Từ chối">
                ❌
            </button>
        `;
    } else if (booking.status === 'confirmed') {
        actions = `
            <button class="btn-secondary" onclick="completeBooking(${booking.id})" title="Hoàn thành">
                ✔️
            </button>
        `;
    } else if (booking.status === 'completed') {
        actions = `
            <button class="btn-secondary" onclick="viewBookingDetail(${booking.id})" title="Xem chi tiết">
                👁️
            </button>
        `;
    }
    
    return `
        <tr class="${isSelected ? 'selected-row' : ''}">
            <td>
                <input type="checkbox" 
                    ${isSelected ? 'checked' : ''} 
                    onchange="toggleBookingSelection(${booking.id})">
            </td>
            <td>${booking.id}</td>
            <td><strong>${booking.customer}</strong></td>
            <td>${booking.phone}</td>
            <td>${formatDate(booking.date)}</td>
            <td>${booking.time}</td>
            <td>${booking.guests} người</td>
            <td>
                <span class="status-badge ${statusClass}">${statusText}</span>
            </td>
            <td>
                <div class="table-actions-cell">
                    ${actions}
                    <button class="btn-edit" onclick="viewBookingDetail(${booking.id})" title="Chi tiết">
                        📋
                    </button>
                </div>
            </td>
        </tr>
    `;
}

/**
 * Cập nhật thống kê
 */
function updateStatistics() {
    const bookings = dataStorage.getAllBookings();
    
    const total = bookings.length;
    const pending = bookings.filter(b => b.status === 'pending').length;
    const confirmed = bookings.filter(b => b.status === 'confirmed').length;
    const completed = bookings.filter(b => b.status === 'completed').length;
    
    document.getElementById('totalBookings').textContent = total;
    document.getElementById('pendingBookings').textContent = pending;
    document.getElementById('confirmedBookings').textContent = confirmed;
    document.getElementById('completedBookings').textContent = completed;
}

// ==================== BOOKING ACTIONS ====================

/**
 * Xác nhận đặt bàn
 */
function confirmBooking(id) {
    const booking = dataStorage.getBookingById(id);
    if (!booking) return;
    
    showConfirmModal(
        `Xác nhận đặt bàn cho ${booking.customer}?`,
        () => {
            dataStorage.updateBookingStatus(id, 'confirmed');
            renderBookings();
            alert('Đã xác nhận đặt bàn!');
        }
    );
}

/**
 * Từ chối đặt bàn
 */
function rejectBooking(id) {
    const booking = dataStorage.getBookingById(id);
    if (!booking) return;
    
    showConfirmModal(
        `Từ chối đặt bàn của ${booking.customer}? Đặt bàn sẽ bị xóa khỏi hệ thống.`,
        () => {
            dataStorage.deleteBooking(id);
            renderBookings();
            alert('Đã từ chối đặt bàn!');
        }
    );
}

/**
 * Hoàn thành đặt bàn
 */
function completeBooking(id) {
    dataStorage.updateBookingStatus(id, 'completed');
    renderBookings();
    alert('Đã đánh dấu hoàn thành!');
}

/**
 * Xem chi tiết đặt bàn
 */
function viewBookingDetail(id) {
    const booking = dataStorage.getBookingById(id);
    if (!booking) return;
    
    const statusText = dataStorage.getStatusText(booking.status);
    const statusClass = dataStorage.getStatusClass(booking.status);
    
    const detailContent = document.getElementById('detailContent');
    detailContent.innerHTML = `
        <div class="detail-section">
            <h4>Thông Tin Khách Hàng</h4>
            <div class="detail-row">
                <strong>Họ và tên:</strong>
                <span>${booking.customer}</span>
            </div>
            <div class="detail-row">
                <strong>Số điện thoại:</strong>
                <span>${booking.phone}</span>
            </div>
        </div>
        
        <div class="detail-section">
            <h4>Thông Tin Đặt Bàn</h4>
            <div class="detail-row">
                <strong>Ngày:</strong>
                <span>${formatDate(booking.date)}</span>
            </div>
            <div class="detail-row">
                <strong>Giờ:</strong>
                <span>${booking.time}</span>
            </div>
            <div class="detail-row">
                <strong>Số khách:</strong>
                <span>${booking.guests} người</span>
            </div>
            <div class="detail-row">
                <strong>Trạng thái:</strong>
                <span class="status-badge ${statusClass}">${statusText}</span>
            </div>
        </div>
        
        <div class="detail-section">
            <h4>Thông Tin Khác</h4>
            <div class="detail-row">
                <strong>Mã đặt bàn:</strong>
                <span>#${booking.id}</span>
            </div>
        </div>
    `;
    
    document.getElementById('detailModal').classList.add('active');
}

// ==================== SELECTION MANAGEMENT ====================

/**
 * Toggle chọn một booking
 */
function toggleBookingSelection(id) {
    if (selectedBookings.has(id)) {
        selectedBookings.delete(id);
    } else {
        selectedBookings.add(id);
    }
    updateBulkActions();
    renderBookings();
}

/**
 * Toggle chọn tất cả
 */
function toggleSelectAll() {
    const selectAll = document.getElementById('selectAll').checked;
    const bookings = dataStorage.getAllBookings();
    
    if (selectAll) {
        bookings.forEach(b => selectedBookings.add(b.id));
    } else {
        selectedBookings.clear();
    }
    
    updateBulkActions();
    renderBookings();
}

/**
 * Cập nhật bulk actions
 */
function updateBulkActions() {
    const bulkActions = document.getElementById('bulkActions');
    const count = selectedBookings.size;
    
    if (count > 0) {
        bulkActions.style.display = 'flex';
        document.getElementById('selectedCount').textContent = `${count} mục được chọn`;
    } else {
        bulkActions.style.display = 'none';
    }
}

/**
 * Xác nhận hàng loạt
 */
function bulkConfirm() {
    if (selectedBookings.size === 0) return;
    
    showConfirmModal(
        `Xác nhận ${selectedBookings.size} đặt bàn đã chọn?`,
        () => {
            selectedBookings.forEach(id => {
                const booking = dataStorage.getBookingById(id);
                if (booking && booking.status === 'pending') {
                    dataStorage.updateBookingStatus(id, 'confirmed');
                }
            });
            selectedBookings.clear();
            renderBookings();
            alert('Đã xác nhận các đặt bàn!');
        }
    );
}

/**
 * Xóa hàng loạt
 */
function bulkDelete() {
    if (selectedBookings.size === 0) return;
    
    showConfirmModal(
        `Xóa ${selectedBookings.size} đặt bàn đã chọn?`,
        () => {
            selectedBookings.forEach(id => {
                dataStorage.deleteBooking(id);
            });
            selectedBookings.clear();
            renderBookings();
            alert('Đã xóa các đặt bàn!');
        }
    );
}

// ==================== FILTER & SEARCH ====================

/**
 * Lọc đặt bàn
 */
function filterBookings() {
    const status = document.getElementById('statusFilter').value;
    const date = document.getElementById('dateFilter').value;
    const searchTerm = document.getElementById('searchBooking').value.toLowerCase();
    
    let bookings = dataStorage.getAllBookings();
    
    // Filter by status
    if (status !== 'all') {
        bookings = bookings.filter(b => b.status === status);
    }
    
    // Filter by date
    if (date) {
        bookings = bookings.filter(b => b.date === date);
    }
    
    // Filter by search term
    if (searchTerm) {
        bookings = bookings.filter(b => 
            b.customer.toLowerCase().includes(searchTerm) ||
            b.phone.includes(searchTerm)
        );
    }
    
    displayFilteredBookings(bookings);
}

/**
 * Tìm kiếm đặt bàn
 */
function searchBookings() {
    filterBookings();
}

/**
 * Reset bộ lọc
 */
function resetFilters() {
    document.getElementById('statusFilter').value = 'all';
    document.getElementById('dateFilter').value = '';
    document.getElementById('searchBooking').value = '';
    renderBookings();
}

/**
 * Hiển thị kết quả lọc
 */
function displayFilteredBookings(bookings) {
    const tbody = document.getElementById('bookingsTableBody');
    const emptyState = document.getElementById('emptyState');
    
    if (bookings.length === 0) {
        tbody.innerHTML = '';
        emptyState.style.display = 'flex';
        document.querySelector('.table-container').style.display = 'none';
        return;
    }
    
    emptyState.style.display = 'none';
    document.querySelector('.table-container').style.display = 'block';
    tbody.innerHTML = bookings.map(booking => createBookingRow(booking)).join('');
}

// ==================== SORTING ====================

/**
 * Sắp xếp bảng
 */
function sortTable(column) {
    if (currentSortColumn === column) {
        currentSortDirection = currentSortDirection === 'asc' ? 'desc' : 'asc';
    } else {
        currentSortColumn = column;
        currentSortDirection = 'asc';
    }
    
    const bookings = dataStorage.getAllBookings();
    bookings.sort((a, b) => {
        let valA = a[column];
        let valB = b[column];
        
        if (typeof valA === 'string') {
            valA = valA.toLowerCase();
            valB = valB.toLowerCase();
        }
        
        if (currentSortDirection === 'asc') {
            return valA > valB ? 1 : -1;
        } else {
            return valA < valB ? 1 : -1;
        }
    });
    
    displayFilteredBookings(bookings);
}

// ==================== MODAL MANAGEMENT ====================

/**
 * Hiển thị modal xác nhận
 */
function showConfirmModal(message, callback) {
    document.getElementById('confirmMessage').textContent = message;
    confirmCallback = callback;
    document.getElementById('confirmModal').classList.add('active');
}

/**
 * Xác nhận hành động
 */
function confirmAction() {
    if (confirmCallback) {
        confirmCallback();
        confirmCallback = null;
    }
    closeConfirmModal();
}

/**
 * Đóng modal xác nhận
 */
function closeConfirmModal() {
    document.getElementById('confirmModal').classList.remove('active');
    confirmCallback = null;
}

/**
 * Đóng modal chi tiết
 */
function closeDetailModal() {
    document.getElementById('detailModal').classList.remove('active');
}

// ==================== EXPORT ====================

/**
 * Xuất danh sách đặt bàn ra Excel (giả lập)
 */
function exportBookings() {
    const bookings = dataStorage.getAllBookings();
    
    if (bookings.length === 0) {
        alert('Không có dữ liệu để xuất!');
        return;
    }
    
    // Trong thực tế sẽ dùng thư viện như SheetJS
    let csvContent = "data:text/csv;charset=utf-8,";
    csvContent += "ID,Khách hàng,SĐT,Ngày,Giờ,Số khách,Trạng thái\n";
    
    bookings.forEach(b => {
        csvContent += `${b.id},${b.customer},${b.phone},${b.date},${b.time},${b.guests},${dataStorage.getStatusText(b.status)}\n`;
    });
    
    const encodedUri = encodeURI(csvContent);
    const link = document.createElement("a");
    link.setAttribute("href", encodedUri);
    link.setAttribute("download", "danh-sach-dat-ban.csv");
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    
    alert('Đã xuất file thành công!');
}

// ==================== UTILITY ====================

/**
 * Format ngày
 */
function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleDateString('vi-VN');
}

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
    const confirmModal = document.getElementById('confirmModal');
    const detailModal = document.getElementById('detailModal');
    
    if (event.target === confirmModal) {
        closeConfirmModal();
    }
    if (event.target === detailModal) {
        closeDetailModal();
    }
}