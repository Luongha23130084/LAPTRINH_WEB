// Global state
let isLoginMode = true;
let isLoggedIn = false;

// Toggle mobile menu
function toggleMobileMenu() {
    const menu = document.getElementById('mobileMenu');
    menu.classList.toggle('active');
}

// Show login modal
function showLoginModal() {
    const modal = document.getElementById('loginModal');
    modal.classList.add('active');
    resetAuthForm();
}

// Close login modal
function closeLoginModal() {
    const modal = document.getElementById('loginModal');
    modal.classList.remove('active');
}

// Show booking modal
function showBookingModal() {
    const modal = document.getElementById('bookingModal');
    modal.classList.add('active');
}

// Close booking modal
function closeBookingModal() {
    const modal = document.getElementById('bookingModal');
    modal.classList.remove('active');
}

// Toggle between login and register
function toggleAuthMode() {
    isLoginMode = !isLoginMode;
    
    const modalTitle = document.getElementById('modalTitle');
    const nameField = document.getElementById('nameField');
    const phoneField = document.getElementById('phoneField');
    const submitText = document.getElementById('submitText');
    const toggleLink = document.getElementById('toggleLink');
    
    if (isLoginMode) {
        modalTitle.textContent = 'Đăng Nhập';
        nameField.style.display = 'none';
        phoneField.style.display = 'none';
        submitText.textContent = 'Đăng Nhập';
        toggleLink.textContent = 'Chưa có tài khoản? Đăng ký ngay';
        
        // Clear required attribute
        document.getElementById('name').removeAttribute('required');
        document.getElementById('phone').removeAttribute('required');
    } else {
        modalTitle.textContent = 'Đăng Ký';
        nameField.style.display = 'block';
        phoneField.style.display = 'block';
        submitText.textContent = 'Đăng Ký';
        toggleLink.textContent = 'Đã có tài khoản? Đăng nhập';
        
        // Add required attribute
        document.getElementById('name').setAttribute('required', 'required');
        document.getElementById('phone').setAttribute('required', 'required');
    }
    
    return false;
}

// Reset auth form
function resetAuthForm() {
    document.getElementById('authForm').reset();
    isLoginMode = true;
    toggleAuthMode();
    toggleAuthMode(); // Call twice to reset to login mode
}

// Handle authentication
function handleAuth(event) {
    event.preventDefault();
    
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    
    if (isLoginMode) {
        // Login logic
        console.log('Login:', { email, password });
        alert('Đăng nhập thành công!');
        isLoggedIn = true;
        closeLoginModal();
        showBookingModal();
    } else {
        // Register logic
        const name = document.getElementById('name').value;
        const phone = document.getElementById('phone').value;
        console.log('Register:', { name, phone, email, password });
        alert('Đăng ký thành công! Vui lòng đăng nhập.');
        toggleAuthMode(); // Switch to login mode
    }
    
    return false;
}

// Handle booking
function handleBooking(event) {
    event.preventDefault();
    
    const formData = new FormData(event.target);
    console.log('Booking submitted:', Object.fromEntries(formData));
    
    alert('Đặt bàn thành công! Chúng tôi sẽ liên hệ với bạn sớm nhất.');
    event.target.reset();
    
    return false;
}

// Logout
function logout() {
    isLoggedIn = false;
    closeBookingModal();
    alert('Đã đăng xuất!');
}

// Close modal when clicking outside
window.onclick = function(event) {
    const loginModal = document.getElementById('loginModal');
    const bookingModal = document.getElementById('bookingModal');
    
    if (event.target === loginModal) {
        closeLoginModal();
    }
    if (event.target === bookingModal) {
        closeBookingModal();
    }
}

// Smooth scrolling for anchor links
document.addEventListener('DOMContentLoaded', function() {
    const links = document.querySelectorAll('a[href^="#"]');
    
    links.forEach(link => {
        link.addEventListener('click', function(e) {
            e.preventDefault();
            
            const targetId = this.getAttribute('href').substring(1);
            const targetElement = document.getElementById(targetId);
            
            if (targetElement) {
                targetElement.scrollIntoView({
                    behavior: 'smooth',
                    block: 'start'
                });
                
                // Close mobile menu if open
                const mobileMenu = document.getElementById('mobileMenu');
                if (mobileMenu.classList.contains('active')) {
                    mobileMenu.classList.remove('active');
                }
            }
        });
    });
});