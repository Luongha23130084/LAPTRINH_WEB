<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<%
    User user = (User) session.getAttribute("user");
    String successMessage = request.getParameter("registered");
    
    // Lấy ngôn ngữ từ session, mặc định là tiếng Việt
    String lang = (String) session.getAttribute("lang");
    if (lang == null) {
        lang = "vi";
        session.setAttribute("lang", lang);
    }
%>
<!DOCTYPE html>
<html lang="<%= lang %>">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Buffet Paradise - Nhà hàng buffet cao cấp">
    <title id="pageTitle">Buffet Paradise - Nhà Hàng Buffet Cao Cấp</title>

    <link rel="stylesheet" href="css/variables.css">
    <link rel="stylesheet" href="css/components.css">
    <link rel="stylesheet" href="css/layout.css">
    <link rel="stylesheet" href="css/pages.css">

    <link rel="icon" href="data:image/svg+xml,<svg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 100 100'><text y='.9em' font-size='90'>🍽️</text></svg>">
    
    <style>
        /* Header Improvements */
        .header {
            background: #000;
            padding: 0;
            box-shadow: 0 2px 10px rgba(0,0,0,0.3);
        }
        
        .header-content {
            display: flex;
            align-items: center;
            justify-content: space-between;
            gap: 2rem;
            padding: 1rem 0;
        }
        
        /* Navigation Desktop - Improved */
        .nav-desktop {
            display: flex;
            align-items: center;
            gap: 0;
            flex: 1;
        }
        
        .nav-desktop a {
            color: #fff;
            text-decoration: none;
            padding: 1rem 1.5rem;
            font-weight: 600;
            font-size: 0.9rem;
            transition: all 0.3s ease;
            border-bottom: 3px solid transparent;
            white-space: nowrap;
        }
        
        .nav-desktop a:hover {
            color: var(--gold-primary);
            border-bottom-color: var(--gold-primary);
            background: rgba(212, 175, 55, 0.1);
        }
        
        /* Header Actions - Improved */
        .header-actions {
            display: flex;
            align-items: center;
            gap: 0.75rem;
        }

        /* Language Dropdown - REDESIGNED */
        .lang-dropdown {
            position: relative;
            display: inline-block;
        }

        .lang-btn-main {
            display: flex;
            align-items: center;
            gap: 0.5rem;
            background: transparent;
            border: 2px solid var(--gold-primary);
            color: var(--gold-primary);
            padding: 0.6rem 1.2rem;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 700;
            font-size: 0.85rem;
            transition: all 0.3s ease;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        .lang-btn-main:hover {
            background: var(--gold-primary);
            color: #000;
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(212, 175, 55, 0.4);
        }

        .lang-icon {
            font-size: 1.1rem;
        }

        .lang-arrow {
            transition: transform 0.3s ease;
            font-size: 0.7rem;
            margin-left: 0.25rem;
        }

        .lang-dropdown.active .lang-arrow {
            transform: rotate(180deg);
        }

        .lang-menu {
            position: absolute;
            top: calc(100% + 10px);
            right: 0;
            background: #1a1a1a;
            border: 2px solid var(--gold-primary);
            border-radius: 8px;
            min-width: 200px;
            box-shadow: 0 8px 24px rgba(0, 0, 0, 0.5);
            opacity: 0;
            visibility: hidden;
            transform: translateY(-10px);
            transition: all 0.3s ease;
            z-index: 1000;
            overflow: hidden;
        }

        .lang-dropdown.active .lang-menu {
            opacity: 1;
            visibility: visible;
            transform: translateY(0);
        }

        .lang-option {
            display: flex;
            align-items: center;
            gap: 1rem;
            padding: 1rem 1.25rem;
            cursor: pointer;
            transition: all 0.3s ease;
            color: #fff;
            font-weight: 600;
            font-size: 0.9rem;
            border-left: 3px solid transparent;
        }

        .lang-option:hover {
            background: rgba(212, 175, 55, 0.15);
            border-left-color: var(--gold-primary);
            padding-left: 1.5rem;
        }

        .lang-option.active {
            background: rgba(212, 175, 55, 0.2);
            color: var(--gold-primary);
            border-left-color: var(--gold-primary);
        }

        .lang-option.active::after {
            content: '✓';
            margin-left: auto;
            font-weight: bold;
            font-size: 1.1rem;
        }

        .lang-flag {
            font-size: 1.5rem;
        }

        /* Button Improvements */
        .btn {
            font-weight: 600;
            transition: all 0.3s ease;
        }
        
        .btn-sm {
            padding: 0.6rem 1.2rem;
            font-size: 0.85rem;
        }
        
        .btn-primary {
            background: var(--gold-primary);
            color: #000;
        }
        
        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 4px 12px rgba(212, 175, 55, 0.4);
        }
        
        .btn-outline {
            border: 2px solid var(--gold-primary);
            color: var(--gold-primary);
            background: transparent;
        }
        
        .btn-outline:hover {
            background: var(--gold-primary);
            color: #000;
        }
        
        /* Welcome text */
        .welcome-text {
            color: #aaa;
            margin-right: 1rem;
            font-size: 0.85rem;
        }
        
        .welcome-text strong {
            color: var(--gold-primary);
            font-weight: 700;
        }
        
        @media (max-width: 1024px) {
            .nav-desktop {
                display: none;
            }
            
            .header-actions {
                flex-wrap: wrap;
            }
        }
        
        @media (max-width: 768px) {
            .lang-menu {
                right: 0;
                left: auto;
            }
            
            .header-actions {
                gap: 0.5rem;
            }
            
            .btn-sm {
                padding: 0.5rem 0.8rem;
                font-size: 0.75rem;
            }
        }
    </style>
</head>
<body>
    <!-- Success Message -->
    <% if (successMessage != null && successMessage.equals("true")) { %>
        <div id="successBanner" style="position: fixed; top: 0; left: 0; right: 0; background: linear-gradient(135deg, #10b981, #059669); color: white; padding: 1rem; text-align: center; z-index: 10000; box-shadow: 0 4px 12px rgba(0,0,0,0.3); animation: slideDown 0.5s ease;">
            <div style="max-width: 1200px; margin: 0 auto; display: flex; align-items: center; justify-content: center; gap: 1rem;">
                <span style="font-size: 2rem;">✅</span>
                <div style="text-align: left;">
                    <h3 style="margin: 0; font-size: 1.25rem; font-weight: 700;" data-lang="register_success_title">Đăng ký thành công!</h3>
                    <p style="margin: 0; font-size: 0.875rem; opacity: 0.9;" data-lang="register_success_desc">Vui lòng đăng nhập để bắt đầu đặt bàn</p>
                </div>
                <button onclick="closeBanner()" style="background: rgba(255,255,255,0.2); border: none; color: white; padding: 0.5rem 1rem; border-radius: 0.375rem; cursor: pointer; font-weight: 600; margin-left: auto;">
                    <span data-lang="close">Đóng</span> ✕
                </button>
            </div>
        </div>
        <style>
            @keyframes slideDown {
                from { transform: translateY(-100%); }
                to { transform: translateY(0); }
            }
            body { padding-top: 80px !important; }
        </style>
    <% } %>

    <!-- HEADER -->
    <header class="header">
        <div class="container">
            <div class="header-content">
                <!-- Logo -->
                <a href="index.jsp" class="logo">
                    <span class="logo-icon">🍽️</span>
                    <span class="logo-text">Buffet Paradise</span>
                </a>

                <!-- Navigation Desktop -->
                <nav class="nav-desktop">
                    <a href="#home" data-lang="nav_home">🏠 TRANG CHỦ</a>
                    <a href="#about" data-lang="nav_about">ℹ️ GIỚI THIỆU</a>
                    <a href="#menu" data-lang="nav_menu">🍽️ THỰC ĐƠN</a>
                    <a href="#space" data-lang="nav_space">🛋️ KHÔNG GIAN</a>
                    <a href="#contact" data-lang="nav_contact">📞 LIÊN HỆ</a>
                </nav>

                <!-- Header Actions -->
                <div class="header-actions">
            
                    
                    <% if (user != null) { %>
                        <!-- ĐÃ ĐĂNG NHẬP -->
                        <span class="welcome-text">
                            <span data-lang="welcome">Xin chào</span>, <strong><%= user.getFullName() %></strong>
                        </span>
                        <button class="btn btn-primary btn-sm" onclick="window.location.href='booking.jsp'">
                            <span data-lang="btn_booking">📅 Đặt Bàn</span>
                        </button>
                        <button class="btn btn-outline btn-sm" onclick="window.location.href='my-booking.jsp'">
                            <span data-lang="btn_my_bookings">📋 Đơn Của Tôi</span>
                        </button>
                        <button class="btn btn-outline btn-sm" onclick="window.location.href='LogoutServlet'">
                            <span data-lang="btn_logout">🚪 Đăng Xuất</span>
                        </button>
                    <% } else if (successMessage != null && successMessage.equals("true")) { %>
                        <!-- VỪA ĐĂNG KÝ THÀNH CÔNG -->
                        <button class="btn btn-primary btn-sm" onclick="window.location.href='login.jsp'">
                            <span data-lang="btn_login_now">🔐 Đăng Nhập Ngay</span>
                        </button>
                    <% } else { %>
                        <!-- CHƯA ĐĂNG NHẬP -->
                        <button class="btn btn-outline btn-sm" onclick="window.location.href='login.jsp'">
                            <span data-lang="btn_login">🔐 Đăng Nhập</span>
                        </button>
                        <button class="btn btn-primary btn-sm" onclick="window.location.href='register.jsp'">
                            <span data-lang="btn_register">📝 Đăng Ký</span>
                        </button>
                    <% } %>
                </div>
            </div>
        </div>
    </header>

    <!-- HERO SECTION -->
    <section id="home" class="hero" style="background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=1920&h=1080&fit=crop')">
        <div class="hero-overlay"></div>
        <div class="container hero-container">
            <div class="hero-content">
                <h1 class="hero-title" data-lang="hero_title">✨ Chào Mừng Đến Buffet Paradise ✨</h1>
                <p class="hero-subtitle" data-lang="hero_subtitle">
                    Trải nghiệm ẩm thực đỉnh cao với hơn 100 món ăn đa dạng từ Á đến Âu<br>
                    Không gian sang trọng - Phục vụ tận tâm - Giá cả hợp lý
                </p>
                <div class="hero-buttons">
                    <% if (user != null) { %>
                        <button class="btn btn-primary" 
                                onclick="window.location.href='booking.jsp'"
                                style="font-size: 1.25rem; padding: 1rem 3rem; font-weight: 700;">
                            <span data-lang="hero_btn_book">📅 Đặt Bàn Ngay</span>
                        </button>
                    <% } else if (successMessage != null && successMessage.equals("true")) { %>
                        <button class="btn btn-primary" 
                                onclick="window.location.href='login.jsp'"
                                style="font-size: 1.25rem; padding: 1rem 3rem; font-weight: 700;">
                            <span data-lang="hero_btn_login">🔐 Đăng Nhập Ngay</span>
                        </button>
                    <% } else { %>
                        <button class="btn btn-primary btn-large" onclick="window.location.href='register.jsp'">
                            <span data-lang="hero_btn_book">📅 Đặt Bàn Ngay</span>
                        </button>
                    <% } %>
                    <button class="btn btn-outline btn-large" onclick="document.getElementById('menu').scrollIntoView({behavior: 'smooth'})">
                        <span data-lang="hero_btn_menu">📖 Xem Thực Đơn</span>
                    </button>
                </div>
            </div>
        </div>
    </section>

    <!-- ABOUT SECTION -->
    <section id="about" class="section section-white">
        <div class="container">
            <div class="section-header">
                <span class="icon">🏆</span>
                <h2 data-lang="about_title">Về Chúng Tôi</h2>
            </div>

            <div class="about-content">
                <div class="about-image">
                    <img src="https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=800&h=600&fit=crop" alt="Restaurant">
                </div>

                <div class="about-text">
                    <h3 data-lang="about_heading">🍽️ Buffet Paradise - Điểm Đến Ẩm Thực Lý Tưởng</h3>
                    <p data-lang="about_desc"><strong>Buffet Paradise</strong> là nhà hàng buffet cao cấp với hơn 10 năm kinh nghiệm phục vụ, tự hào mang đến trải nghiệm ẩm thực đa dạng từ Á đến Âu.</p>

                    <div class="features-grid">
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4 data-lang="feature1_title">100+ Món Ăn</h4>
                                <p data-lang="feature1_desc">Đa dạng, tươi ngon mỗi ngày</p>
                            </div>
                        </div>
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4 data-lang="feature2_title">Đầu Bếp Chuyên Nghiệp</h4>
                                <p data-lang="feature2_desc">Kinh nghiệm quốc tế</p>
                            </div>
                        </div>
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4 data-lang="feature3_title">Không Gian Sang Trọng</h4>
                                <p data-lang="feature3_desc">Phục vụ tận tâm</p>
                            </div>
                        </div>
                        <div class="feature-item">
                            <span class="feature-icon">✅</span>
                            <div>
                                <h4 data-lang="feature4_title">Giá Cả Hợp Lý</h4>
                                <p data-lang="feature4_desc">Chất lượng đảm bảo</p>
                            </div>
                        </div>
                    </div>

                    <div class="price-info">
                        <h3 data-lang="price_title">💰 Chỉ từ 299.000đ/người</h3>
                        <p data-lang="price_desc">Buffet trưa & tối | Miễn phí nước uống | Giảm 10% cho sinh viên</p>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- MENU SECTION -->
    <section id="menu" class="section section-gray">
        <div class="container">
            <div class="section-header">
                <span class="icon">🍽️</span>
                <h2 data-lang="menu_title">Thực Đơn Buffet Đa Dạng</h2>
            </div>
            <p class="section-subtitle" data-lang="menu_subtitle">Hơn 100 món ăn được cập nhật và đổi mới liên tục</p>

            <div class="menu-grid">
                <div class="menu-card">
                    <div class="menu-card-header" style="background-image: url('https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=800&h=400&fit=crop')">
                        <h3 data-lang="menu_appetizer">🥗 Khai Vị</h3>
                    </div>
                    <div class="menu-card-body">
                        <ul class="menu-list">
                            <li><span class="item-name" data-lang="dish1_name">Salad Trộn Rau Xanh</span> <span class="item-desc" data-lang="dish1_desc">Rau xanh tươi, dầu olive</span></li>
                            <li><span class="item-name" data-lang="dish2_name">Gỏi Cuốn Tôm Thịt</span> <span class="item-desc" data-lang="dish2_desc">Tôm tươi, thịt, rau sống</span></li>
                            <li><span class="item-name" data-lang="dish3_name">Chả Giò Giòn Rụm</span> <span class="item-desc" data-lang="dish3_desc">Nhân thịt tôm, rau củ</span></li>
                            <li><span class="item-name" data-lang="dish4_name">Sushi & Sashimi</span> <span class="item-desc" data-lang="dish4_desc">Cá hồi, cá ngừ Nhật</span></li>
                        </ul>
                    </div>
                </div>

                <div class="menu-card">
                    <div class="menu-card-header" style="background-image: url('https://images.unsplash.com/photo-1555939594-58d7cb561ad1?w=800&h=400&fit=crop')">
                        <h3 data-lang="menu_main">🍖 Món Chính</h3>
                    </div>
                    <div class="menu-card-body">
                        <ul class="menu-list">
                            <li><span class="item-name" data-lang="dish5_name">Bò Nướng BBQ Úc</span> <span class="item-desc" data-lang="dish5_desc">Thịt bò cao cấp</span></li>
                            <li><span class="item-name" data-lang="dish6_name">Hải Sản Tươi Sống</span> <span class="item-desc" data-lang="dish6_desc">Tôm hùm, cua, sò điệp</span></li>
                            <li><span class="item-name" data-lang="dish7_name">Lẩu Thái Tom Yum</span> <span class="item-desc" data-lang="dish7_desc">Vị chua cay đặc trưng</span></li>
                            <li><span class="item-name" data-lang="dish8_name">Pizza Ý Đa Dạng</span> <span class="item-desc" data-lang="dish8_desc">Nhiều loại topping</span></li>
                        </ul>
                    </div>
                </div>

                <div class="menu-card">
                    <div class="menu-card-header" style="background-image: url('https://images.unsplash.com/photo-1488477181946-6428a0291777?w=800&h=400&fit=crop')">
                        <h3 data-lang="menu_dessert">🍰 Tráng Miệng</h3>
                    </div>
                    <div class="menu-card-body">
                        <ul class="menu-list">
                            <li><span class="item-name" data-lang="dish9_name">Kem Tươi Nhiều Vị</span> <span class="item-desc" data-lang="dish9_desc">Vani, socola, dâu, matcha</span></li>
                            <li><span class="item-name" data-lang="dish10_name">Bánh Ngọt Pháp</span> <span class="item-desc" data-lang="dish10_desc">Tiramisu, mousse, éclair</span></li>
                            <li><span class="item-name" data-lang="dish11_name">Trái Cây Tươi</span> <span class="item-desc" data-lang="dish11_desc">Dưa hấu, dứa, xoài, nho</span></li>
                            <li><span class="item-name" data-lang="dish12_name">Chè Các Loại</span> <span class="item-desc" data-lang="dish12_desc">Chè thái, sương sa hột lựu</span></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!-- CONTACT SECTION -->
    <section id="contact" class="section section-contact" style="background-image: url('https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=1920&h=1080&fit=crop')">
        <div class="container">
            <div class="section-header">
                <span class="icon">📞</span>
                <h2 data-lang="contact_title">Liên Hệ Đặt Bàn</h2>
            </div>

            <div class="contact-content">
                <div class="contact-info">
                    <div class="contact-item">
                        <span class="contact-icon">📍</span>
                        <div>
                            <h4 data-lang="contact_address">Địa Chỉ</h4>
                            <p data-lang="contact_address_detail">123 Nguyễn Huệ, Quận 1, TP.HCM</p>
                        </div>
                    </div>
                    <div class="contact-item">
                        <span class="contact-icon">📞</span>
                        <div>
                            <h4 data-lang="contact_phone">Điện Thoại</h4>
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
                            <h4 data-lang="contact_hours">Giờ Mở Cửa</h4>
                            <p data-lang="contact_lunch">Trưa: 11:00 - 14:00</p>
                            <p data-lang="contact_dinner">Tối: 17:00 - 22:00</p>
                        </div>
                    </div>
                </div>

                <div class="contact-action">
                    <h3 data-lang="contact_book_title">Đặt Bàn Trực Tuyến</h3>
                    <% if (user != null) { %>
                        <p data-lang="contact_book_desc1">Bắt đầu đặt bàn ngay và nhận ưu đãi đặc biệt!</p>
                        <button class="btn btn-primary btn-large" onclick="window.location.href='booking.jsp'">
                            <span data-lang="hero_btn_book">📅 Đặt Bàn Ngay</span>
                        </button>
                    <% } else { %>
                        <p data-lang="contact_book_desc2">Đăng ký tài khoản để đặt bàn nhanh chóng và nhận ưu đãi đặc biệt!</p>
                        <button class="btn btn-primary btn-large" onclick="window.location.href='register.jsp'">
                            <span data-lang="btn_register">📝 Đăng Ký Ngay</span>
                        </button>
                    <% } %>
                </div>
            </div>
        </div>
    </section>

    <!-- FOOTER -->
    <footer class="footer">
        <div class="container">
            <div class="footer-content">
                <div class="footer-section">
                    <h3>🍽️ Buffet Paradise</h3>
                    <p data-lang="footer_desc1">Nhà hàng buffet cao cấp</p>
                    <p data-lang="footer_desc2">Hơn 100 món ăn đa dạng</p>
                    <p data-lang="footer_desc3">Phục vụ tận tâm, chất lượng đảm bảo</p>
                </div>

                <div class="footer-section">
                    <h3 data-lang="footer_links">🔗 Liên Kết</h3>
                    <a href="#home" data-lang="nav_home">Trang Chủ</a>
                    <a href="#about" data-lang="nav_about">Giới Thiệu</a>
                    <a href="#menu" data-lang="nav_menu">Thực Đơn</a>
                    <a href="login.jsp" data-lang="btn_login">Đăng Nhập</a>
                     <a href="register.jsp" data-lang="btn_register">Đăng Ký</a>
                </div>

                <div class="footer-section">
                    <h3 data-lang="footer_social">📱 Kết Nối</h3>
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
            </div>
        </div>
    </footer>

    <!-- Include language.js -->
    <script src="js/language.js"></script>
    
    <script>
        // Language dropdown functionality
        const langDropdown = document.getElementById('langDropdown');
        const langSelected = document.getElementById('langSelected');
        const langMenu = document.getElementById('langMenu');
        const currentFlag = document.getElementById('currentFlag');
        const currentLangText = document.getElementById('currentLang');

        // Toggle dropdown
        langSelected.addEventListener('click', (e) => {
            e.stopPropagation();
            langDropdown.classList.toggle('active');
        });

        // Close dropdown when clicking outside
        document.addEventListener('click', () => {
            langDropdown.classList.remove('active');
        });

        // Language selection
        const langOptions = document.querySelectorAll('.lang-option');
        langOptions.forEach(option => {
            option.addEventListener('click', (e) => {
                e.stopPropagation();
                
                const lang = option.getAttribute('data-lang');
                const flag = option.getAttribute('data-flag');
                const name = option.getAttribute('data-name');
                
                // Update UI
                currentFlag.textContent = flag;
                currentLangText.textContent = name;
                
                // Update active state
                langOptions.forEach(opt => opt.classList.remove('active'));
                option.classList.add('active');
                
                // Change language
                changeLanguage(lang);
                
                // Close dropdown
                langDropdown.classList.remove('active');
            });
        });
        
        // Close success banner function
        function closeBanner() {
            const banner = document.getElementById('successBanner');
            if (banner) {
                banner.style.animation = 'slideUp 0.5s ease';
               

