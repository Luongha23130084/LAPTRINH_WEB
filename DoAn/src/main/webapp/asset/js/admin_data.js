/**
 * ADMIN-MAIN.JS
 * Lớp xử lý giao diện - Presentation Layer
 * Chịu trách nhiệm: Render UI, xử lý sự kiện, tương tác với user
 */

// ==================== UI CONTROLLER ====================

/**
 * Class: UIController
 * Quản lý toàn bộ giao diện và tương tác người dùng
 */
class UIController {
    constructor(storage) {
        this.storage = storage;
        this.currentTab = 'tables';
        this.init();
    }

    /**
     * Khởi tạo ứng dụng
     */
    init() {
        this.renderTables();
        this.renderBookings();
    }

    // ==================== TAB MANAGEMENT ====================

    /**
     * Chuyển tab
     * @param {string} tabName - Tên tab (tables/bookings)
     */
    switchTab(tabName) {
        this.currentTab = tabName;

        // Update tab buttons
        document.querySelectorAll('.tab').forEach(tab => {
            tab.classList.remove('active');
        });
        document.getElementById(`tab-${tabName}`).classList.add('active');

        // Update content sections
        document.querySelectorAll('.content-section').forEach(section => {
            section.classList.remove('active');
        });
        document.getElementById(`section-${tabName}`).classList.add('active');
    }

    // ==================== TABLES RENDERING ====================

    /**
     * Render danh sách bàn
     */
    renderTables() {
        const tables = this.storage.getAllTables();
        const container = document.getElementById('tablesGrid');
        
        container.innerHTML = tables.map(table => this.createTableCard(table)).join('');
    }

    /**
     * Tạo HTML cho một card bàn
     * @param {Object} table - Dữ liệu bàn
     * @returns {string} HTML string
     */
    createTableCard(table) {
        const statusText = this.storage.getStatusText(table.status);
        const statusClass = this.storage.getStatusClass(table.status);

        return `
            <div class="table-card">
                <div class="table-card-header">
                    <h3>${table.number}</h3>
                    <span class="status-badge ${statusClass}">${statusText}</span>
                </div>
                <div class="table-info">
                    👥 <span>${table.seats} ghế</span>
                </div>
                <div class="table-actions">
                    <button class="btn-edit" onclick="uiController.editTable(${table.id})">Sửa</button>
                    <button class="btn-danger" onclick="uiController.deleteTable(${table.id})">Xóa</button>
                </div>
            </div>
        `;
    }

    // ==================== BOOKINGS RENDERING ====================

    /**
     * Render danh sách đặt bàn
     */
    renderBookings() {
        const bookings = this.storage.getAllBookings();
        const tbody = document.querySelector('#bookingsTable tbody');
        
        tbody.innerHTML = bookings.map(booking => this.createBookingRow(booking)).join('');
    }

    /**
     * Tạo HTML cho một row đặt bàn
     * @param {Object} booking - Dữ liệu đặt bàn
     * @returns {string} HTML string
     */
    createBookingRow(booking) {
        const statusText = this.storage.getStatusText(booking.status);
        const statusClass = this.storage.getStatusClass(booking.status);

        let actions = '';
        if (booking.status === 'pending') {
            actions = `
                <button class="btn-success" onclick="uiController.confirmBooking(${booking.id})">Xác nhận</button>
                <button class="btn-danger" onclick="uiController.rejectBooking(${booking.id})">Từ chối</button>
            `;
        } else if (booking.status === 'confirmed') {
            actions = `
                <button class="btn-secondary" onclick="uiController.completeBooking(${booking.id})">Hoàn thành</button>
            `;
        }

        return `
            <tr>
                <td>${booking.id}</td>
                <td>${booking.customer}</td>
                <td>${booking.phone}</td>
                <td>${booking.date}</td>
                <td>${booking.time}</td>
                <td>${booking.guests}</td>
                <td><span class="status-badge ${statusClass}">${statusText}</span></td>
                <td><div class="table-actions-cell">${actions}</div></td>
            </tr>
        `;
    }

    // ==================== TABLE ACTIONS ====================

    /**
     * Hiển thị modal thêm bàn
     */
    showAddTableModal() {
        const modal = document.getElementById('addTableModal');
        modal.classList.add('active');
        document.getElementById('addTableForm').reset();
    }

    /**
     * Đóng modal thêm bàn
     */
    closeAddTableModal() {
        const modal = document.getElementById('addTableModal');
        modal.classList.remove('active');
    }

    /**
     * Xử lý thêm bàn
     * @param {Event} event - Form submit event
     */
    handleAddTable(event) {
        event.preventDefault();

        const number = document.getElementById('tableNumber').value;
        const seats = parseInt(document.getElementById('tableSeats').value);

        // Thêm bàn vào storage
        this.storage.addTable(number, seats);

        // Re-render
        this.renderTables();

        // Đóng modal
        this.closeAddTableModal();

        alert('Đã thêm bàn thành công!');
    }

    /**
     * Sửa bàn
     * @param {number} id - ID bàn
     */
    editTable(id) {
        const table = this.storage.getTableById(id);
        if (!table) return;

        // Hiển thị prompt đơn giản (có thể thay bằng modal)
        const newNumber = prompt('Tên bàn mới:', table.number);
        const newSeats = prompt('Số ghế mới:', table.seats);

        if (newNumber && newSeats) {
            this.storage.updateTable(id, {
                number: newNumber,
                seats: parseInt(newSeats)
            });
            this.renderTables();
            alert('Đã cập nhật bàn!');
        }
    }

    /**
     * Xóa bàn
     * @param {number} id - ID bàn
     */
    deleteTable(id) {
        if (confirm('Bạn có chắc muốn xóa bàn này?')) {
            this.storage.deleteTable(id);
            this.renderTables();
            alert('Đã xóa bàn!');
        }
    }

    // ==================== BOOKING ACTIONS ====================

    /**
     * Xác nhận đặt bàn
     * @param {number} id - ID đặt bàn
     */
    confirmBooking(id) {
        this.storage.updateBookingStatus(id, 'confirmed');
        this.renderBookings();
        alert('Đã xác nhận đặt bàn!');
    }

    /**
     * Từ chối đặt bàn
     * @param {number} id - ID đặt bàn
     */
    rejectBooking(id) {
        if (confirm('Bạn có chắc muốn từ chối đặt bàn này?')) {
            this.storage.deleteBooking(id);
            this.renderBookings();
            alert('Đã từ chối đặt bàn!');
        }
    }

    /**
     * Hoàn thành đặt bàn
     * @param {number} id - ID đặt bàn
     */
    completeBooking(id) {
        this.storage.updateBookingStatus(id, 'completed');
        this.renderBookings();
        alert('Đã đánh dấu hoàn thành!');
    }

    // ==================== UTILITY ====================

    /**
     * Đăng xuất
     */
    logout() {
        if (confirm('Bạn có chắc muốn đăng xuất?')) {
            window.location.href = '../index.jsp';
        }
    }
}

// ==================== GLOBAL FUNCTIONS ====================
// Các hàm này được gọi từ HTML (onclick)

/**
 * Khởi tạo UI Controller
 */
const uiController = new UIController(dataStorage);

/**
 * Chuyển tab
 */
function switchTab(tabName) {
    uiController.switchTab(tabName);
}

/**
 * Hiển thị modal thêm bàn
 */
function showAddTableModal() {
    uiController.showAddTableModal();
}

/**
 * Đóng modal thêm bàn
 */
function closeAddTableModal() {
    uiController.closeAddTableModal();
}

/**
 * Xử lý thêm bàn
 */
function handleAddTable(event) {
    uiController.handleAddTable(event);
}

/**
 * Đăng xuất
 */
function logout() {
    uiController.logout();
}

// ==================== MODAL CLICK OUTSIDE ====================

window.onclick = function(event) {
    const modal = document.getElementById('addTableModal');
    if (event.target === modal) {
        closeAddTableModal();
    }
}