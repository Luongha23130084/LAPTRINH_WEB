<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Buffet Paradise - Nhà hàng buffet cao cấp với hơn 100 món ăn đa dạng">
    <title>Buffet Paradise - Nhà Hàng Buffet Cao Cấp</title>
    
    <!-- ✅ IMPORT CSS FILES - THEO THỨ TỰ -->
    <link rel="stylesheet" href="css/variables.css">
    <link rel="stylesheet" href="css/components.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/pages.css">
    
    <!-- Favicon -->
    <link rel="icon" href="data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100'><text y='.9em' font-size='90'>🍽️</text></svg>">
</head>
<body>
    <!-- ========================================
         HEADER - NAVIGATION
    ======================================== -->
    <header class="header">
        <div class="container">
            <div class="header-content">
                <!-- Logo -->
                <a href="#home" class="logo">
                    <span class="logo-icon">🍽️</span>
                    <span class="logo-text">Buffet Paradise</span>
                </a>
                
                <!-- Navigation Desktop -->
                <nav class="nav-desktop">
                    <a href="#home">🏠 Trang Chủ</a>
                    <a href="#about">ℹ️ Giới Thiệu</a>
                    <a href="#menu">🍽️ Thực Đơn</a>
                    <a href="#space">🛋️ Không Gian</a>
                    <a href="#contact">📞 Liên Hệ</a>
                </nav>
                
                <!-- Header Actions -->
                <div class="header-actions">
                    <button class="btn btn-outline btn-sm" onclick="window.location.href='login.jsp'">
                        🔐 Đăng Nhập
                    </button>
                    <button class="btn btn-primary btn-sm" onclick="window.location.href='register.jsp'">
                        📝 Đăng Ký
                    </button>
                </div>
            </div>
        </div>
    </header>

    <!-- ========================================
         HERO SECTION - BANNER
    ======================================== -->
    <section id="home" class="hero" style="background-image: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)), url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=1920&h=1080&fit=crop')">
        <div class="hero-overlay"></div>
        <div class="container hero-container">
            <div class="hero-content">
                <h1 class="hero-title">✨ Chào Mừng Đến Buffet Paradise ✨</h1>
                <p class="hero-subtitle">
                    Trải nghiệm ẩm thực đỉnh cao với hơn 100 món ăn đa dạng từ Á đến Âu<br>
                    Không gian sang trọng - Phục vụ tận tâm - Giá cả hợp lý
                </p>
                <div class="hero-buttons">
                    <button class="btn btn-primary btn-large" onclick="window.location.href='register.jsp'">
                        📅 Đặt Bàn Ngay
                    </button>
                    <button class="btn btn-outline btn-large" onclick="document.getElementById('menu').scrollIntoView({behavior: 'smooth'})">
                        📖 Xem Thực Đơn
                    </button>
                </div>
            </div>
        </div>
    </section>

    <!-- ========================================
         ABOUT SECTION - GIỚI THIỆU
    ======================================== -->
    <section id="about" class="section section-white">
        <div class="container">
            <div class="section-header">
                <span class="icon">🏆</span>
                <h2>Về Chúng Tôi</h2>
            </div>
            
            <div class="about-content">
                <div class="about-image">
                    <img src="https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=800&h=600&fit=crop" 
                         alt="Không gian nhà hàng">
                </div>
                
                <div class="about-text">
                    <h3>🍽️ Buffet Paradise - Điểm Đến Ẩm Thực Lý Tưởng</h3>
                    
                    <p><strong>Buffet Paradise</strong> là nhà hàng buffet cao cấp với hơn 10 năm kinh nghiệm phục vụ, tự hào mang đến trải nghiệm ẩm thực đa dạng từ Á đến Âu.</p>
                    
                    <div class="features-grid">
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4>100+ Món Ăn</h4>
                                <p>Đa dạng, tươi ngon mỗi ngày</p>
                            </div>
                        </div>
                        
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4>Đầu Bếp Chuyên Nghiệp</h4>
                                <p>Kinh nghiệm quốc tế</p>
                            </div>
                        </div>
                        
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4>Không Gian Sang Trọng</h4>
                                <p>Phục vụ tận tâm</p>
                            </div>
                        </div>
                        
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4>Giá Cả Hợp Lý</h4>
                                <p>Chất lượng đảm bảo</p>
                            </div>
                        </div>
                    </div>
                    
                    <div class="price-info">
                        <h3>💰 Chỉ từ 299.000đ/người</h3>
                        <p>Buffet trưa & tối | Miễn phí nước uống | Giảm 10% cho sinh viên</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ========================================
         MENU SECTION - THỰC ĐƠN
    ======================================== -->
    <section id="menu" class="section section-gray">
        <div class="container">
            <div class="section-header">
                <span class="icon">🍽️</span>
                <h2>Thực Đơn Buffet Đa Dạng</h2>
            </div>
            <p class="section-subtitle">Hơn 100 món ăn được cập nhật và đổi mới liên tục</p>
            
            <div class="menu-grid">
                <!-- KHAI VỊ -->
                <div class="menu-card">
                    <div class="menu-card-header" style="background-image: url('https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=800&h=400&fit=crop')">
                        <h3>🥗 Khai Vị</h3>
                    </div>
                    <div class="menu-card-body">
                        <ul class="menu-list">
                            <li>
                                <span class="item-name">Salad Trộn Rau Xanh</span>
                                <span class="item-desc">Rau xanh tươi, dầu olive</span>
                            </li>
                            <li>
                                <span class="item-name">Gỏi Cuốn Tôm Thịt</span>
                                <span class="item-desc">Tôm tươi, thịt, rau sống</span>
                            </li>
                            <li>
                                <span class="item-name">Chả Giò Giòn Rụm</span>
                                <span class="item-desc">Nhân thịt tôm, rau củ</span>
                            </li>
                            <li>
                                <span class="item-name">Sushi & Sashimi</span>
                                <span class="item-desc">Cá hồi, cá ngừ Nhật</span>
                            </li>
                            <li>
                                <span class="item-name">Nem Rán Hà Nội</span>
                                <span class="item-desc">Công thức truyền thống</span>
                            </li>
                            <li>
                                <span class="item-name">Súp Hải Sản</span>
                                <span class="item-desc">Tôm, mực, nghêu tươi</span>
                            </li>
                        </ul>
                    </div>
                </div>
                
                <!-- MÓN CHÍNH -->
                <div class="menu-card">
                    <div class="menu-card-header" style="background-image: url('https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=800&h=400&fit=crop')">
                        <h3>🍖 Món Chính</h3>
                    </div>
                    <div class="menu-card-body">
                        <ul class="menu-list">
                            <li>
                                <span class="item-name">Bò Nướng BBQ Úc</span>
                                <span class="item-desc">Thịt bò cao cấp, sốt đặc biệt</span>
                            </li>
                            <li>
                                <span class="item-name">Hải Sản Tươi Sống</span>
                                <span class="item-desc">Tôm hùm, cua, sò điệp</span>
                            </li>
                            <li>
                                <span class="item-name">Lẩu Thái Tom Yum</span>
                                <span class="item-desc">Vị chua cay đặc trưng</span>
                            </li>
                            <li>
                                <span class="item-name">Pizza Ý Đa Dạng</span>
                                <span class="item-desc">Nhiều loại topping</span>
                            </li>
                            <li>
                                <span class="item-name">Mì Ý Spaghetti</span>
                                <span class="item-desc">Bò băm, hải sản, carbonara</span>
                            </li>
                            <li>
                                <span class="item-name">Cơm Chiên Hải Sản</span>
                                <span class="item-desc">Tôm, mực, trứng cá</span>
                            </li>
                        </ul>
                    </div>
                </div>
                
                <!-- TRÁNG MIỆNG -->
                <div class="menu-card">
                    <div class="menu-card-header" style="background-image: url('https://images.unsplash.com/photo-1488477181946-6428a0291777?w=800&h=400&fit=crop')">
                        <h3>🍰 Tráng Miệng</h3>
                    </div>
                    <div class="menu-card-body">
                        <ul class="menu-list">
                            <li>
                                <span class="item-name">Kem Tươi Nhiều Vị</span>
                                <span class="item-desc">Vani, socola, dâu, matcha</span>
                            </li>
                            <li>
                                <span class="item-name">Bánh Ngọt Pháp</span>
                                <span class="item-desc">Tiramisu, mousse, éclair</span>
                            </li>
                            <li>
                                <span class="item-name">Trái Cây Tươi</span>
                                <span class="item-desc">Dưa hấu, dứa, xoài, nho</span>
                            </li>
                            <li>
                                <span class="item-name">Chè Các Loại</span>
                                <span class="item-desc">Chè thái, sương sa hột lựu</span>
                            </li>
                            <li>
                                <span class="item-name">Pudding & Mousse</span>
                                <span class="item-desc">Đa dạng hương vị</span>
                            </li>
                            <li>
                                <span class="item-name">Bánh Flan Caramen</span>
                                <span class="item-desc">Mềm mịn, ngọt ngào</span>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ========================================
         SPACE SECTION - KHÔNG GIAN
    ======================================== -->
    <section id="space" class="section section-white">
        <div class="container">
            <div class="section-header">
                <span class="icon">🛋️</span>
                <h2>Không Gian Sang Trọng</h2>
            </div>
            <p class="section-subtitle">Sức chứa 200+ khách, phù hợp mọi sự kiện</p>
            
            <div class="space-grid">
                <!-- Khu VIP -->
                <div class="space-card">
                    <div class="space-image" style="background-image: url('https://images.unsplash.com/photo-1550966871-3ed3cdb5ed0c?w=800&h=600&fit=crop')">
                        <div class="space-overlay">
                            <span class="space-icon">👑</span>
                            <h3>Khu Vực VIP</h3>
                            <p>Sang trọng, riêng tư</p>
                            <span class="space-capacity">Sức chứa: 10-20 người</span>
                        </div>
                    </div>
                </div>
                
                <!-- Khu Gia Đình -->
                <div class="space-card">
                    <div class="space-image" style="background-image: url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=800&h=600&fit=crop')">
                        <div class="space-overlay">
                            <span class="space-icon">👨‍👩‍👧‍👦</span>
                            <h3>Khu Vực Gia Đình</h3>
                            <p>Ấm cúng, thoải mái</p>
                            <span class="space-capacity">Sức chứa: 50-100 người</span>
                        </div>
                    </div>
                </div>
                
                <!-- Khu Ngoài Trời -->
                <div class="space-card">
                    <div class="space-image" style="background-image: url('https://images.unsplash.com/photo-1552566626-52f8b828add9?w=800&h=600&fit=crop')">
                        <div class="space-overlay">
                            <span class="space-icon">🌳</span>
                            <h3>Khu Ngoài Trời</h3>
                            <p>Thoáng mát, view đẹp</p>
                            <span class="space-capacity">Sức chứa: 30-50 người</span>
                        </div>
                    </div>
                </div>
                
                <!-- Phòng Tiệc -->
                <div class="space-card">
                    <div class="space-image" style="background-image: url('https://images.unsplash.com/photo-1519167758481-83f29da8c424?w=800&h=600&fit=crop')">
                        <div class="space-overlay">
                            <span class="space-icon">🎉</span>
                            <h3>Phòng Tiệc Lớn</h3>
                            <p>Sang trọng, hiện đại</p>
                            <span class="space-capacity">Sức chứa: 100-200 người</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- ========================================
         CONTACT SECTION - LIÊN HỆ
    ======================================== -->
    <section id="contact" class="section section-contact" style="background-image: url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=1920&h=1080&fit=crop')">
        <div class="container">
            <div class="section-header">
                <span class="icon">📞</span>
                <h2>Liên Hệ Đặt Bàn</h2>
            </div>
            
            <div class="contact-content">
                <div class="contact-info">
                    <div class="contact-item">
                        <span class="contact-icon">📍</span>
                        <div>
                            <h4>Địa Chỉ</h4>
                            <p>123 Nguyễn Huệ, Quận 1, TP.HCM</p>
                        </div>
                    </div>
                    
                    <div class="contact-item">
                        <span class="contact-icon">📞</span>
                        <div>
                            <h4>Điện Thoại</h4>
                            <p>028 3822 xxxx</p>
                        </div>
                    </div>
                    
                    <div class="contact-item">
                        <span class="contact-icon">📧</span>
                        <div>
                            <h4>Email</h4>
                            <p>booking@buffetparadise.vn</p>
                        </div>
                    </div>
                    
                    <div class="contact-item">
                        <span class="contact-icon">🕐</span>
                        <div>
                            <h4>Giờ Mở Cửa</h4>
                            <p>Trưa: 11:00 - 14:00</p>
                            <p>Tối: 17:00 - 22:00</p>
                            <p>Hàng ngày (kể cả lễ, Tết)</p>
                        </div>
                    </div>
                </div>
                
                <div class="contact-action">
                    <h3>Đặt Bàn Trực Tuyến</h3>
                    <p>Đăng ký tài khoản để đặt bàn nhanh chóng và nhận ưu đãi đặc biệt!</p>
                    
                    <button class="btn btn-primary btn-large" onclick="window.location.href='register.jsp'">
                        📝 Đăng Ký Ngay
                    </button>
                    <p class="or-text">Hoặc</p>
                    <button class="btn btn-outline btn-large" onclick="window.location.href='login.jsp'">
                        🔐 Đăng Nhập
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
            <div class="footer-content">
                <!-- Column 1 -->
                <div class="footer-section">
                    <h3>🍽️ Buffet Paradise</h3>
                    <p>Nhà hàng buffet cao cấp</p>
                    <p>Hơn 100 món ăn đa dạng</p>
                    <p>Phục vụ tận tâm, chất lượng đảm bảo</p>
                </div>
                
                <!-- Column 2 -->
                <div class="footer-section">
                    <h3>🔗 Liên Kết</h3>
                    <a href="#home">Trang Chủ</a>
                    <a href="#about">Giới Thiệu</a>
                    <a href="#menu">Thực Đơn</a>
                    <a href="#space">Không Gian</a>
                    <a href="login.jsp">Đăng Nhập</a>
                    <a href="register.jsp">Đăng Ký</a>
                </div>
                
                <!-- Column 3 -->
                <div class="footer-section">
                    <h3>📱 Kết Nối</h3>
                    <div class="social-links">
                        <a href="#" title="Facebook">f</a>
                        <a href="#" title="Instagram">📷</a>
                        <a href="#" title="Zalo">Z</a>
                        <a href="#" title="TikTok">🎵</a>
                    </div>
                </div>
            </div>
            
            <div class="footer-bottom">
                <p>&copy; 2024 Buffet Paradise. All Rights Reserved.</p>
                <a href="admin/login.jsp" class="admin-link">🔒 Admin Login</a>
            </div>
        </div>
    </footer>

    <!-- JavaScript -->
    <script src="js/main.js"></script>
</body>
</html>