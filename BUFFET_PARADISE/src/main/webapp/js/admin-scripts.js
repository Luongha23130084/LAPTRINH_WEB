// Admin Dashboard JavaScript

// Wait for DOM to load
document.addEventListener('DOMContentLoaded', function() {
    initializeAdminPanel();
});

// Initialize admin panel
function initializeAdminPanel() {
    setupDeleteConfirmation();
    setupEditButtons();
    setupViewButtons();
    setupTableSearch();
    setupStatusFilters();
    addAnimations();
}

// Delete Confirmation
function setupDeleteConfirmation() {
    const deleteButtons = document.querySelectorAll('.btn-delete');
    
    deleteButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            
            const row = this.closest('tr');
            const itemName = row.querySelector('td:nth-child(2)').textContent;
            
            if (confirm(`Bạn có chắc chắn muốn xóa "${itemName}" không?`)) {
                deleteItem(row);
            }
        });
    });
}

// Delete item with animation
function deleteItem(row) {
    row.style.backgroundColor = '#f8d7da';
    row.style.transition = 'all 0.3s ease';
    
    setTimeout(() => {
        row.style.opacity = '0';
        row.style.transform = 'translateX(-100%)';
        
        setTimeout(() => {
            row.remove();
            showNotification('Đã xóa thành công!', 'success');
        }, 300);
    }, 200);
}

// Edit Buttons
function setupEditButtons() {
    const editButtons = document.querySelectorAll('.btn-edit');
    
    editButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            
            const row = this.closest('tr');
            const itemId = row.querySelector('td:first-child').textContent;
            
            // Redirect to edit page or open modal
            editItem(itemId, row);
        });
    });
}

// Edit item
function editItem(itemId, row) {
    // Check if this is a booking confirmation button
    const isConfirmButton = row.querySelector('.status-pending');
    
    if (isConfirmButton) {
        if (confirm('Xác nhận đặt bàn này?')) {
            confirmBooking(row);
        }
    } else {
        // Redirect to edit page
        console.log('Edit item:', itemId);
        // window.location.href = 'edit-item.jsp?id=' + itemId;
        showNotification('Chức năng chỉnh sửa đang được phát triển', 'info');
    }
}

// Confirm booking
function confirmBooking(row) {
    const statusBadge = row.querySelector('.status-badge');
    statusBadge.className = 'status-badge status-confirmed';
    statusBadge.textContent = 'Đã xác nhận';
    
    const editBtn = row.querySelector('.btn-edit');
    editBtn.textContent = 'Sửa';
    
    showNotification('Đã xác nhận đặt bàn!', 'success');
}

// View Buttons
function setupViewButtons() {
    const viewButtons = document.querySelectorAll('.btn-view');
    
    viewButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            e.preventDefault();
            
            const row = this.closest('tr');
            viewItemDetails(row);
        });
    });
}

// View item details
function viewItemDetails(row) {
    const cells = row.querySelectorAll('td');
    let details = 'Chi tiết:\n\n';
    
    cells.forEach((cell, index) => {
        if (index < cells.length - 1) { // Skip action column
            const header = row.closest('table').querySelector(`th:nth-child(${index + 1})`).textContent;
            details += `${header}: ${cell.textContent}\n`;
        }
    });
    
    alert(details);
}

// Table Search
function setupTableSearch() {
    // Create search box if doesn't exist
    const tableContainer = document.querySelector('.admin-table-container');
    if (!tableContainer) return;
    
    const searchBox = document.createElement('div');
    searchBox.className = 'search-box';
    searchBox.innerHTML = `
        <input type="text" id="tableSearch" placeholder="🔍 Tìm kiếm..." class="search-input">
    `;
    
    tableContainer.insertBefore(searchBox, tableContainer.firstChild);
    
    // Search functionality
    const searchInput = document.getElementById('tableSearch');
    searchInput.addEventListener('keyup', function() {
        const searchTerm = this.value.toLowerCase();
        const table = document.querySelector('.admin-table tbody');
        const rows = table.querySelectorAll('tr');
        
        rows.forEach(row => {
            const text = row.textContent.toLowerCase();
            if (text.includes(searchTerm)) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        });
    });
}

// Status Filters
function setupStatusFilters() {
    const table = document.querySelector('.admin-table');
    if (!table) return;
    
    // Check if table has status column
    const statusBadges = table.querySelectorAll('.status-badge');
    if (statusBadges.length === 0) return;
    
    // Create filter buttons
    const tableContainer = document.querySelector('.admin-table-container');
    const filterBox = document.createElement('div');
    filterBox.className = 'filter-box';
    filterBox.innerHTML = `
        <button class="filter-btn active" data-status="all">Tất cả</button>
        <button class="filter-btn" data-status="pending">Chờ xác nhận</button>
        <button class="filter-btn" data-status="confirmed">Đã xác nhận</button>
        <button class="filter-btn" data-status="cancelled">Đã hủy</button>
    `;
    
    const searchBox = tableContainer.querySelector('.search-box');
    if (searchBox) {
        searchBox.appendChild(filterBox);
    }
    
    // Filter functionality
    const filterButtons = filterBox.querySelectorAll('.filter-btn');
    filterButtons.forEach(btn => {
        btn.addEventListener('click', function() {
            // Update active button
            filterButtons.forEach(b => b.classList.remove('active'));
            this.classList.add('active');
            
            const status = this.dataset.status;
            filterByStatus(status);
        });
    });
}

// Filter table by status
function filterByStatus(status) {
    const rows = document.querySelectorAll('.admin-table tbody tr');
    
    rows.forEach(row => {
        if (status === 'all') {
            row.style.display = '';
        } else {
            const statusBadge = row.querySelector(`.status-${status}`);
            if (statusBadge) {
                row.style.display = '';
            } else {
                row.style.display = 'none';
            }
        }
    });
}

// Add animations
function addAnimations() {
    // Fade in table rows
    const rows = document.querySelectorAll('.admin-table tbody tr');
    rows.forEach((row, index) => {
        row.style.opacity = '0';
        row.style.transform = 'translateY(20px)';
        
        setTimeout(() => {
            row.style.transition = 'all 0.3s ease';
            row.style.opacity = '1';
            row.style.transform = 'translateY(0)';
        }, index * 50);
    });
    
    // Menu items animation
    const menuItems = document.querySelectorAll('.admin-nav li');
    menuItems.forEach((item, index) => {
        item.style.opacity = '0';
        item.style.transform = 'scale(0.9)';
        
        setTimeout(() => {
            item.style.transition = 'all 0.3s ease';
            item.style.opacity = '1';
            item.style.transform = 'scale(1)';
        }, index * 100);
    });
}

// Show notification
function showNotification(message, type = 'info') {
    // Remove existing notification
    const existingNotification = document.querySelector('.notification');
    if (existingNotification) {
        existingNotification.remove();
    }
    
    // Create notification
    const notification = document.createElement('div');
    notification.className = `notification notification-${type}`;
    notification.textContent = message;
    
    document.body.appendChild(notification);
    
    // Show notification
    setTimeout(() => {
        notification.classList.add('show');
    }, 100);
    
    // Hide notification after 3 seconds
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => {
            notification.remove();
        }, 300);
    }, 3000);
}

// Export data to CSV
function exportToCSV() {
    const table = document.querySelector('.admin-table');
    if (!table) return;
    
    let csv = [];
    const rows = table.querySelectorAll('tr');
    
    rows.forEach(row => {
        const cells = row.querySelectorAll('th, td');
        const rowData = [];
        
        cells.forEach((cell, index) => {
            // Skip action column
            if (index < cells.length - 1) {
                rowData.push(cell.textContent.trim());
            }
        });
        
        csv.push(rowData.join(','));
    });
    
    // Download CSV
    const csvContent = csv.join('\n');
    const blob = new Blob(['\ufeff' + csvContent], { type: 'text/csv;charset=utf-8;' });
    const link = document.createElement('a');
    link.href = URL.createObjectURL(blob);
    link.download = 'data_export_' + new Date().getTime() + '.csv';
    link.click();
    
    showNotification('Đã xuất dữ liệu thành công!', 'success');
}

// Print table
function printTable() {
    window.print();
}

// Utility: Format date
function formatDate(dateString) {
    const date = new Date(dateString);
    const day = String(date.getDate()).padStart(2, '0');
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const year = date.getFullYear();
    return `${day}/${month}/${year}`;
}

// Utility: Format time
function formatTime(timeString) {
    const [hours, minutes] = timeString.split(':');
    return `${hours}:${minutes}`;
}