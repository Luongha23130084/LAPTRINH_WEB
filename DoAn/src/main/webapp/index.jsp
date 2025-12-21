<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nhà Hàng Buffet Paradise - Trải Nghiệm Ẩm Thực Đỉnh Cao</title>
    <link rel="stylesheet" href="asset/css/style.css">
    <link rel="icon" href="data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100'><text y='.9em' font-size='90'>🍽️</text></svg>">
</head>
<body>
    <!-- ========================================
         HEADER - NAVIGATION BAR
    ======================================== -->
    <header class="header">
        <div class="container">
            <div class="header-content">
                <!-- Logo -->
                <h1 class="logo">Buffet Paradise</h1>
                
                <!-- Desktop Navigation -->
                <nav class="nav-desktop">
                    <a href="#home">🏠 Trang Chủ</a>
                    <a href="#about">ℹ️ Giới Thiệu</a>
                    <a href="#menu">🍽️ Thực Đơn</a>
                    <a href="#space">🏛️ Không Gian</a>
                    <a href="#contact">📞 Liên Hệ</a>
                </nav>
                
                <!-- Header Actions -->
                <div class="header-actions">
                    <button class="btn btn-white" onclick="showLoginModal()">📅 Đặt Bàn</button>
                    <button class="btn-menu" onclick="toggleMobileMenu()">☰</button>
                </div>
            </div>
            
            <!-- Mobile Menu -->
            <nav class="nav-mobile" id="mobileMenu">
                <a href="#home" onclick="toggleMobileMenu()">🏠 Trang Chủ</a>
                <a href="#about" onclick="toggleMobileMenu()">ℹ️ Giới Thiệu</a>
                <a href="#menu" onclick="toggleMobileMenu()">🍽️ Thực Đơn</a>
                <a href="#space" onclick="toggleMobileMenu()">🏛️ Không Gian</a>
                <a href="#contact" onclick="toggleMobileMenu()">📞 Liên Hệ</a>
            </nav>
        </div>
    </header>

    <!-- ========================================
         HERO SECTION - BANNER CHÀO MỪNG
    ======================================== -->
    <section id="home" class="hero">
        <div class="container">
            <h2 class="hero-title">✨ Chào Mừng Đến Buffet Paradise ✨</h2>
            <p class="hero-subtitle">Trải nghiệm ẩm thực đỉnh cao với hơn 100 món ăn đa dạng từ Á đến Âu</p>
            <button class="btn btn-white" onclick="showLoginModal()">Đặt Bàn Ngay</button>
        </div>
    </section>

    <!-- ========================================
         ABOUT SECTION - GIỚI THIỆU NHÀ HÀNG
    ======================================== -->
    <section id="about" class="section section-white">
        <div class="container">
            <div class="section-header">
                <span class="icon">🏆</span>
                <h3>Về Chúng Tôi</h3>
            </div>
            <div class="section-content">
                <p><strong>Buffet Paradise</strong> là nhà hàng buffet cao cấp với hơn 10 năm kinh nghiệm phục vụ, mang đến trải nghiệm ẩm thực đa dạng từ Á đến Âu với không gian sang trọng và phục vụ tận tâm.</p>
                
                <p>✅ <strong>100+ món ăn</strong> đa dạng, tươi ngon mỗi ngày</p>
                <p>✅ <strong>Đầu bếp chuyên nghiệp</strong> với kinh nghiệm quốc tế</p>
                <p>✅ <strong>Không gian sang trọng</strong>, phục vụ tận tâm</p>
                <p>✅ <strong>Giá cả hợp lý</strong>, chất lượng đảm bảo</p>
                
                <p style="margin-top: 1.5rem; font-size: 1.25rem;">
                    <strong style="color: #f97316;">💰 Chỉ từ 299.000đ/người</strong><br>
                    <small style="color: #6b7280;">Buffet trưa & tối | Miễn phí nước uống</small>
                </p>
            </div>
        </div>
    </section>

    <!-- ========================================
         MENU SECTION - THỰC ĐƠN BUFFET
    ======================================== -->
    <section id="menu" class="section section-gray">
        <div class="container">
            <div class="section-header">
                <span class="icon">🍽️</span>
                <h3>Thực Đơn Buffet Đa Dạng</h3>
            </div>
            
            <div class="menu-grid">
                <!-- DANH MỤC: KHAI VỊ -->
                <div class="menu-card">
                    <div class="menu-card-header">
                        <h4>🥗 Khai Vị</h4>
                    </div>
                    <div class="menu-card-body">
                        <ul>
                            <li>• Salad Trộn Rau Xanh</li>
                            <li>• Gỏi Cuốn Tôm Thịt</li>
                            <li>• Chả Giò Giòn Rụm</li>
                            <li>• Sushi & Sashimi Nhật</li>
                            <li>• Nem Rán Truyền Thống</li>
                            <li>• Súp Hải Sản</li>
                        </ul>
                    </div>
                </div>
                
                <!-- DANH MỤC: MÓN CHÍNH -->
                <div class="menu-card">
                    <div class="menu-card-header">
                        <h4>🍖 Món Chính</h4>
                    </div>
                    <div class="menu-card-body">
                        <ul>
                            <li>• Bò Nướng BBQ Úc</li>
                            <li>• Hải Sản Tươi Sống</li>
                            <li>• Lẩu Thái Tom Yum</li>
                            <li>• Pizza Ý Đa Dạng</li>
                            <li>• Mì Ý Spaghetti</li>
                            <li>• Cơm Chiên Hải Sản</li>
                        </ul>
                    </div>
                </div>
                
                <!-- DANH MỤC: TRÁNG MIỆNG -->
                <div class="menu-card">
                    <div class="menu-card-header">
                        <h4>🍰 Tráng Miệng</h4>
                    </div>
                    <div class="menu-card-body">
                        <ul>
                            <li>• Kem Tươi Nhiều Vị</li>
                            <li>• Bánh Ngọt Pháp</li>
                            <li>• Trái Cây Tươi Theo Mùa</li>
                            <li>• Chè Các Loại</li>
                            <li>• Pudding & Mousse</li>
                            <li>• Tiramisu Ý</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ========================================
         SPACE SECTION - KHÔNG GIAN NHÀ HÀNG
    ======================================== -->
    <section id="space" class="section section-white">
        <div class="container">
            <div class="section-header">
                <span class="icon">🏛️</span>
                <h3>Không Gian Sang Trọng</h3>
            </div>
            
            <div class="space-grid">
                <!-- Khu VIP -->
                <div class="space-card">
                    <div class="space-placeholder">
                        <span class="icon-large">👑</span>
                        <p>Khu Vực VIP</p>
                        <small>Sang trọng, riêng tư - Sức chứa 10-20 người</small>
                    </div>
                </div>
                
                <!-- Khu Gia Đình -->
                <div class="space-card">
                    <div class="space-placeholder">
                        <span class="icon-large">👨‍👩‍👧‍👦</span>
                        <p>Khu Vực Gia Đình</p>
                        <small>Ấm cúng, thoải mái - Sức chứa 50-100 người</small>
                    </div>
                </div>
            </div>
            
            <div style="text-align: center; margin-top: 2rem;">
                <p style="font-size: 1.125rem; color: #6b7280;">
                    🎉 <strong>Sức chứa tổng:</strong> 200+ người | Phù hợp cho tiệc gia đình, công ty, sinh nhật
                </p>
            </div>
        </div>
    </section>

    <!-- ========================================
         CONTACT SECTION - LIÊN HỆ
    ======================================== -->
    <section id="contact" class="section section-gray">
        <div class="container">
            <h3 class="section-title">📞 Liên Hệ Đặt Bàn</h3>
            <div class="contact-info">
                <p><strong>📍 Địa chỉ:</strong> 123 Nguyễn Huệ, Quận 1, TP.HCM</p>
                <p><strong>📞 Điện thoại:</strong> 028 3822 xxxx</p>
                <p><strong>📧 Email:</strong> booking@buffetparadise.vn</p>
                <p><strong>🕐 Giờ mở cửa:</strong></p>
                <p>Trưa: 11:00 - 14:00 | Tối: 17:00 - 22:00</p>
                <p>Hàng ngày (kể cả lễ, Tết)</p>
                
                <div style="margin-top: 1.5rem;">
                    <button class="btn btn-white" onclick="showLoginModal()" style="font-size: 1.125rem;">
                        📅 Đặt Bàn Trực Tuyến
                    </button>
                </div>
            </div>
        </div>
    </section>

    <!-- ========================================
         FOOTER
    ======================================== -->
    <footer class="footer">
        <div class="container">
            <p style="font-size: 1.125rem; margin-bottom: 0.5rem;">🍽️ <strong>Buffet Paradise</strong></p>
            <p>Nhà hàng buffet cao cấp - Trải nghiệm ẩm thực đỉnh cao</p>
            <p style="margin-top: 1rem; color: #9ca3af; font-size: 0.875rem;">
                © 2024 Buffet Paradise. All rights reserved.
            </p>
            <a href="admin/login.jsp" class="admin-link">🔐 Admin Login</a>
        </div>
    </footer>

    <!-- ========================================
         MODAL ĐĂNG NHẬP/ĐĂNG KÝ
    ======================================== -->
    <div id="loginModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeLoginModal()">&times;</span>
            
            <h2 id="modalTitle">🔐 Đăng Nhập</h2>
            
            <form id="authForm" onsubmit="handleAuth(event)">
                <!-- Field Họ Tên - Ẩn khi đăng nhập -->
                <div id="nameField" style="display: none;">
                    <label>👤 Họ và Tên</label>
                    <input type="text" id="name" placeholder="Nguyễn Văn A">
                </div>
                
                <!-- Field Số Điện Thoại - Ẩn khi đăng nhập -->
                <div id="phoneField" style="display: none;">
                    <label>📱 Số Điện Thoại</label>
                    <input type="tel" id="phone" placeholder="0901234567">
                </div>
                
                <!-- Email -->
                <div>
                    <label>📧 Email</label>
                    <input type="email" id="email" required placeholder="example@email.com">
                </div>
                
                <!-- Mật Khẩu -->
                <div>
                    <label>🔒 Mật Khẩu</label>
                    <input type="password" id="password" required placeholder="••••••••">
                </div>
                
                <!-- Submit Button -->
                <button type="submit" class="btn btn-primary btn-full">
                    <span id="submitText">Đăng Nhập</span>
                </button>
            </form>
            
            <!-- Toggle Link -->
            <div class="modal-footer">
                <a href="#" onclick="toggleAuthMode(event)" id="toggleLink">
                    Chưa có tài khoản? Đăng ký ngay
                </a>
            </div>
        </div>
    </div>

    <!-- ========================================
         MODAL ĐẶT BÀN
    ======================================== -->
    <div id="bookingModal" class="modal">
        <div class="modal-content">
            <span class="close" onclick="closeBookingModal()">&times;</span>
            
            <div class="booking-header">
                <h2>📅 Đặt Bàn</h2>
                <button class="btn btn-secondary" onclick="logout()">Đăng Xuất</button>
            </div>
            <p class="user-greeting">👋 Xin chào, <strong>Khách hàng</strong></p>
            
            <form id="bookingForm" onsubmit="handleBooking(event)">
                <div class="form-row">
                    <div class="form-group">
                        <label>📅 Ngày</label>
                        <input type="date" required>
                    </div>
                    <div class="form-group">
                        <label>🕐 Giờ</label>
                        <input type="time" required>
                    </div>
                </div>
                
                <div class="form-group">
                    <label>👥 Số Khách</label>
                    <input type="number" min="1" max="20" value="2" required>
                </div>
                
                <div class="form-group">
                    <label>📝 Ghi Chú</label>
                    <textarea rows="4" placeholder="Yêu cầu đặc biệt (nếu có)..."></textarea>
                </div>
                
                <button type="submit" class="btn btn-primary btn-full">
                    ✅ Xác Nhận Đặt Bàn
                </button>
            </form>
        </div>
    </div>

    <!-- ========================================
         JAVASCRIPT
    ======================================== -->
    <script src="assets/js/main.js"></script>
</body>
</html>