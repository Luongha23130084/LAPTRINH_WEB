<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.jsp?error=not_logged_in");
        return; 
    }
%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Đặt Bàn - Buffet Paradise</title>
    
    <link rel="stylesheet" href="css/variables.css">
    <link rel="stylesheet" href="css/components.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/pages.css">
    
    <style>
        .booking-page {
            min-height: 100vh;
            background: var(--bg-primary);
            padding: var(--spacing-xl) 0;
        }
        
        .booking-container {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: 1fr 400px;
            gap: var(--spacing-xl);
        }
        
        .booking-main {
            background: var(--bg-secondary);
            border-radius: var(--radius-lg);
            padding: var(--spacing-xl);
            border: 1px solid rgba(212, 175, 55, 0.2);
        }
        
        .booking-summary {
            background: var(--bg-secondary);
            border-radius: var(--radius-lg);
            padding: var(--spacing-xl);
            border: 1px solid rgba(212, 175, 55, 0.2);
            height: fit-content;
            position: sticky;
            top: 20px;
        }
        
        .form-section {
            margin-bottom: var(--spacing-xl);
        }
        
        .section-title {
            font-size: var(--font-xl);
            font-weight: 700;
            color: var(--gold-primary);
            margin-bottom: var(--spacing-md);
            display: flex;
            align-items: center;
            gap: 0.5rem;
        }
        
        .form-group {
            margin-bottom: var(--spacing-md);
        }
        
        .form-label {
            display: block;
            font-size: var(--font-sm);
            color: var(--text-secondary);
            margin-bottom: 0.5rem;
        }
        
        .form-input {
            width: 100%;
            padding: 0.75rem 1rem;
            background: rgba(0, 0, 0, 0.3);
            border: 1px solid rgba(212, 175, 55, 0.3);
            border-radius: var(--radius-md);
            color: var(--text-primary);
            font-size: var(--font-md);
        }
        
        .form-input:focus {
            outline: none;
            border-color: var(--gold-primary);
        }
        
        .zone-grid {
            display: grid;
            grid-template-columns: repeat(2, 1fr);
            gap: var(--spacing-md);
        }
        
        .zone-card {
            background: rgba(0, 0, 0, 0.3);
            border: 2px solid rgba(212, 175, 55, 0.3);
            border-radius: var(--radius-md);
            padding: var(--spacing-md);
            cursor: pointer;
            transition: all 0.3s;
            text-align: center;
        }
        
        .zone-card:hover {
            border-color: var(--gold-primary);
            transform: translateY(-3px);
        }
        
        .zone-card.selected {
            border-color: var(--gold-primary);
            background: rgba(212, 175, 55, 0.2);
        }
        
        .zone-icon {
            font-size: 2.5rem;
            margin-bottom: 0.5rem;
        }
        
        .zone-name {
            font-size: var(--font-md);
            font-weight: 600;
            color: var(--text-primary);
            margin-bottom: 0.25rem;
        }
        
        .zone-price {
            font-size: var(--font-sm);
            color: var(--gold-primary);
        }
        
        .table-grid {
            display: grid;
            grid-template-columns: repeat(4, 1fr);
            gap: var(--spacing-sm);
        }
        
        .table-item {
            background: rgba(0, 0, 0, 0.3);
            border: 2px solid rgba(212, 175, 55, 0.3);
            border-radius: var(--radius-md);
            padding: var(--spacing-sm);
            cursor: pointer;
            transition: all 0.3s;
            text-align: center;
        }
        
        .table-item:hover {
            border-color: var(--gold-primary);
        }
        
        .table-item.selected {
            border-color: var(--gold-primary);
            background: rgba(212, 175, 55, 0.2);
        }
        
        .table-item.unavailable {
            opacity: 0.5;
            cursor: not-allowed;
            border-color: rgba(255, 0, 0, 0.5);
        }
        
        .table-name {
            font-size: var(--font-md);
            font-weight: 600;
            color: var(--text-primary);
        }
        
        .table-capacity {
            font-size: var(--font-xs);
            color: var(--text-tertiary);
        }
        
        .summary-item {
            display: flex;
            justify-content: space-between;
            padding: var(--spacing-sm) 0;
            border-bottom: 1px solid rgba(255, 255, 255, 0.1);
        }
        
        .summary-label {
            color: var(--text-secondary);
        }
        
        .summary-value {
            color: var(--text-primary);
            font-weight: 600;
        }
        
        .total-price {
            font-size: var(--font-xl);
            font-weight: 700;
            color: var(--gold-primary);
            text-align: right;
            margin-top: var(--spacing-md);
        }
        
        @media (max-width: 968px) {
            .booking-container {
                grid-template-columns: 1fr;
            }
            
            .booking-summary {
                position: static;
            }
            
            .zone-grid {
                grid-template-columns: 1fr;
            }
            
            .table-grid {
                grid-template-columns: repeat(2, 1fr);
            }
        }
    </style>
</head>
<body>
    <header class="header">
        <div class="container">
            <div class="header-content">
                <a href="index.jsp" class="logo">
                    <span class="logo-icon">🍽️</span>
                    <span class="logo-text">Buffet Paradise</span>
                </a>
                
                <nav class="nav-desktop">
                    <a href="index.jsp">🏠 Trang Chủ</a>
                    <a href="booking.jsp" class="active">📅 Đặt Bàn</a>
                    <a href="my-booking.jsp">📋 Đơn Của Tôi</a>
                </nav>
                
                <div class="header-actions">
                    <% if (user != null) { %>
                        <span style="color: var(--text-secondary); margin-right: 1rem;">
                            Xin chào, <strong style="color: var(--gold-primary);"><%= user.getFullName() %></strong>
                        </span>
                        <button class="btn btn-outline btn-sm" onclick="window.location.href='LogoutServlet'">
                            🚪 Đăng Xuất
                        </button>
                    <% } %>
                </div>
            </div>
        </div>
    </header>

    <div class="booking-page">
        <div class="container">
            <h1 style="color: var(--gold-primary); margin-bottom: var(--spacing-xl); text-align: center;">
                📅 Đặt Bàn Buffet Paradise
            </h1>

            <div class="booking-container">
                <div class="booking-main">
                    <!-- Bước 1: Thông tin cơ bản -->
                    <div class="form-section">
                        <div class="section-title">
                            📝 Thông Tin Đặt Bàn
                        </div>
                        
                        <div style="display: grid; grid-template-columns: 1fr 1fr; gap: var(--spacing-md);">
                            <div class="form-group">
                                <label class="form-label">📅 Ngày</label>
                                <input type="date" class="form-input" id="bookingDate" required>
                            </div>
                            
                            <div class="form-group">
                                <label class="form-label">🕐 Giờ</label>
                                <select class="form-input" id="bookingTime" required>
                                    <option value="">Chọn giờ</option>
                                    <option value="11:00">11:00</option>
                                    <option value="11:30">11:30</option>
                                    <option value="12:00">12:00</option>
                                    <option value="12:30">12:30</option>
                                    <option value="17:00">17:00</option>
                                    <option value="17:30">17:30</option>
                                    <option value="18:00">18:00</option>
                                    <option value="18:30">18:30</option>
                                    <option value="19:00">19:00</option>
                                    <option value="19:30">19:30</option>
                                </select>
                            </div>
                        </div>
                        
                        <div class="form-group">
                            <label class="form-label">👥 Số khách</label>
                            <input type="number" class="form-input" id="guestCount" min="1" max="20" value="2" required>
                        </div>
                    </div>

                    <!-- Bước 2: Chọn khu vực -->
                    <div class="form-section">
                        <div class="section-title">
                            🛋️ Chọn Khu Vực
                        </div>
                        
                        <div class="zone-grid">
                            <div class="zone-card" data-zone="vip" data-price="299000" onclick="selectZone(this)">
                                <div class="zone-icon">👑</div>
                                <div class="zone-name">Khu VIP</div>
                                <div class="zone-price">299.000đ/người</div>
                            </div>
                            
                            <div class="zone-card" data-zone="family" data-price="299000" onclick="selectZone(this)">
                                <div class="zone-icon">👨‍👩‍👧‍👦</div>
                                <div class="zone-name">Khu Gia Đình</div>
                                <div class="zone-price">299.000đ/người</div>
                            </div>
                            
                            <div class="zone-card" data-zone="outdoor" data-price="299000" onclick="selectZone(this)">
                                <div class="zone-icon">🌳</div>
                                <div class="zone-name">Khu Ngoài Trời</div>
                                <div class="zone-price">299.000đ/người</div>
                            </div>
                            
                            <div class="zone-card" data-zone="romantic" data-price="349000" onclick="selectZone(this)">
                                <div class="zone-icon">💝</div>
                                <div class="zone-name">Khu Lãng Mạn</div>
                                <div class="zone-price">349.000đ/người</div>
                            </div>
                        </div>
                    </div>

                    <!-- Bước 3: Chọn bàn -->
                    <div class="form-section" id="tableSection" style="display: none;">
                        <div class="section-title">
                            🪑 Chọn Bàn
                        </div>
                        
                        <div class="table-grid" id="tableGrid">
                            <!-- Tables will be generated by JavaScript -->
                        </div>
                    </div>

                    <!-- Ghi chú -->
                    <div class="form-section">
                        <div class="section-title">
                            📝 Ghi Chú
                        </div>
                        
                        <div class="form-group">
                            <textarea class="form-input" id="notes" rows="3" 
                                placeholder="Yêu cầu đặc biệt (nếu có)..."></textarea>
                        </div>
                    </div>
                </div>

                <!-- Thanh tóm tắt bên phải -->
                <div class="booking-summary">
                    <h3 style="color: var(--gold-primary); margin-bottom: var(--spacing-md);">
                        📋 Tóm Tắt Đơn
                    </h3>
                    
                    <div class="summary-item">
                        <span class="summary-label">📅 Ngày:</span>
                        <span class="summary-value" id="summaryDate">--</span>
                    </div>
                    
                    <div class="summary-item">
                        <span class="summary-label">🕐 Giờ:</span>
                        <span class="summary-value" id="summaryTime">--</span>
                    </div>
                    
                    <div class="summary-item">
                        <span class="summary-label">👥 Số khách:</span>
                        <span class="summary-value" id="summaryGuests">--</span>
                    </div>
                    
                    <div class="summary-item">
                        <span class="summary-label">🛋️ Khu vực:</span>
                        <span class="summary-value" id="summaryZone">--</span>
                    </div>
                    
                    <div class="summary-item">
                        <span class="summary-label">🪑 Bàn:</span>
                        <span class="summary-value" id="summaryTable">--</span>
                    </div>
                    
                    <div style="border-top: 2px solid var(--gold-primary); margin-top: var(--spacing-md); padding-top: var(--spacing-md);">
                        <div class="total-price" id="totalPrice">0đ</div>
                    </div>
                    
                    <button class="btn btn-primary" style="width: 100%; margin-top: var(--spacing-lg);" 
                        onclick="submitBooking()">
                        ✅ Xác Nhận Đặt Bàn
                    </button>
                </div>
            </div>
        </div>
    </div>

    <script>
        let selectedZone = null;
        let selectedTable = null;
        let pricePerPerson = 0;

        // Set minimum date to today
        const today = new Date().toISOString().split('T')[0];
        document.getElementById('bookingDate').min = today;

        // Update summary when inputs change
        document.getElementById('bookingDate').addEventListener('change', updateSummary);
        document.getElementById('bookingTime').addEventListener('change', updateSummary);
        document.getElementById('guestCount').addEventListener('change', updateSummary);

        function selectZone(element) {
            // Remove previous selection
            document.querySelectorAll('.zone-card').forEach(card => {
                card.classList.remove('selected');
            });
            
            // Add selection
            element.classList.add('selected');
            selectedZone = element.dataset.zone;
            pricePerPerson = parseInt(element.dataset.price);
            
            // Show table section
            document.getElementById('tableSection').style.display = 'block';
            
            // Generate tables for this zone
            generateTables(selectedZone);
            
            updateSummary();
        }

        function generateTables(zone) {
            const tableGrid = document.getElementById('tableGrid');
            tableGrid.innerHTML = '';
            
            const zoneNames = {
                vip: 'VIP',
                family: 'GĐ',
                outdoor: 'NT',
                romantic: 'LM'
            };
            
            const tableCount = 12;
            for (let i = 1; i <= tableCount; i++) {
                const capacity = i % 3 === 0 ? 6 : (i % 2 === 0 ? 4 : 2);
                const isAvailable = Math.random() > 0.3; // 70% available
                
                const tableDiv = document.createElement('div');
                tableDiv.className = `table-item ${!isAvailable ? 'unavailable' : ''}`;
                tableDiv.onclick = isAvailable ? () => selectTable(tableDiv, `${zoneNames[zone]}-${i}`) : null;
                
                tableDiv.innerHTML = `
                    <div class="table-name">${zoneNames[zone]}-${i}</div>
                    <div class="table-capacity">${capacity} chỗ</div>
                `;
                
                tableGrid.appendChild(tableDiv);
            }
        }

        function selectTable(element, tableName) {
            // Remove previous selection
            document.querySelectorAll('.table-item').forEach(table => {
                table.classList.remove('selected');
            });
            
            // Add selection
            element.classList.add('selected');
            selectedTable = tableName;
            
            updateSummary();
        }

        function updateSummary() {
            const date = document.getElementById('bookingDate').value;
            const time = document.getElementById('bookingTime').value;
            const guests = document.getElementById('guestCount').value;
            
            document.getElementById('summaryDate').textContent = date ? new Date(date).toLocaleDateString('vi-VN') : '--';
            document.getElementById('summaryTime').textContent = time || '--';
            document.getElementById('summaryGuests').textContent = guests ? `${guests} người` : '--';
            
            const zoneNames = {
                vip: 'Khu VIP 👑',
                family: 'Khu Gia Đình 👨‍👩‍👧‍👦',
                outdoor: 'Khu Ngoài Trời 🌳',
                romantic: 'Khu Lãng Mạn 💝'
            };
            
            document.getElementById('summaryZone').textContent = selectedZone ? zoneNames[selectedZone] : '--';
            document.getElementById('summaryTable').textContent = selectedTable || '--';
            
            const totalPrice = guests && pricePerPerson ? guests * pricePerPerson : 0;
            document.getElementById('totalPrice').textContent = totalPrice.toLocaleString('vi-VN') + 'đ';
        }

        function submitBooking() {
            const date = document.getElementById('bookingDate').value;
            const time = document.getElementById('bookingTime').value;
            const guests = document.getElementById('guestCount').value;
            const notes = document.getElementById('notes').value;
            
            if (!date || !time || !guests || !selectedZone || !selectedTable) {
                alert('❌ Vui lòng chọn đầy đủ thông tin ngày, giờ, khu vực và bàn!');
                return;
            }

            // Pack dữ liệu
            const params = new URLSearchParams();
            params.append('bookingDate', date);
            params.append('bookingTime', time);
            params.append('guestCount', guests);
            params.append('zone', selectedZone);
            params.append('tableId', selectedTable);
            params.append('notes', notes);

            // Gửi đi
            fetch('CreateBookingServlet', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                },
                body: params
            })
            .then(response => {
                if (response.ok) {
                    alert('🎉 Chúc mừng! Bạn đã đặt bàn thành công.');
                    window.location.href = 'my-booking.jsp'; // Chuyển đến trang quản lý đơn
                } else if (response.status === 401) {
                    alert('❌ Phiên làm việc hết hạn. Vui lòng đăng nhập lại!');
                    window.location.href = 'login.jsp';
                } else {
                    alert('❌ Lỗi hệ thống khi đặt bàn. Vui lòng thử lại sau!');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('❌ Không thể kết nối đến máy chủ!');
            });
        }
            
            // Simulate booking
            alert(`✅ Đặt bàn thành công!\n\n` +
                  `📅 ${date}\n` +
                  `🕐 ${time}\n` +
                  `👥 ${guests} người\n` +
                  `🛋️ ${document.getElementById('summaryZone').textContent}\n` +
                  `🪑 Bàn ${selectedTable}\n` +
                  `💰 ${document.getElementById('totalPrice').textContent}`);
            
            // Redirect to my-booking page
            setTimeout(() => {
                window.location.href = 'my-booking.jsp';
            }, 1000);
        }
    </script>
</body>
</html>