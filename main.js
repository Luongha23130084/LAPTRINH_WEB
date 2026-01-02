/**
 * MAIN.JS - JAVASCRIPT CHÍNH
 * Xử lý: Navigation, Smooth Scroll, Active States, Animations
 */

// ==================== DOM READY ====================
document.addEventListener('DOMContentLoaded', function() {
    initSmoothScroll();
    initHeaderScroll();
    initActiveNav();
    initAnimations();
    initModals();
    initForms();
    consoleWelcome();
});

// ==================== SMOOTH SCROLL ====================
function initSmoothScroll() {
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
                const mobileMenu = document.querySelector('.mobile-menu');
                if (mobileMenu) {
                    mobileMenu.classList.remove('active');
                }
            }
        });
    });
}

// ==================== ACTIVE NAVIGATION ====================
function initActiveNav() {
    window.addEventListener('scroll', debounce(updateActiveNav, 100));
}

function updateActiveNav() {
    const sections = document.querySelectorAll('section[id]');
    const navLinks = document.querySelectorAll('.nav-desktop a, .sidebar-menu a');
    
    let current = '';
    
    sections.forEach(section => {
        const sectionTop = section.offsetTop;
        const sectionHeight = section.clientHeight;
        
        if (pageYOffset >= sectionTop - 100) {
            current = section.getAttribute('id');
        }
    });
    
    navLinks.forEach(link => {
        link.classList.remove('active');
        if (link.getAttribute('href') === '#' + current) {
            link.classList.add('active');
        }
    });
}

// ==================== HEADER SCROLL EFFECT ====================
function initHeaderScroll() {
    const header = document.querySelector('.header');
    if (!header) return;
    
    let lastScroll = 0;
    
    window.addEventListener('scroll', function() {
        const currentScroll = window.pageYOffset;
        
        if (currentScroll > 50) {
            header.classList.add('scrolled');
        } else {
            header.classList.remove('scrolled');
        }
        
        lastScroll = currentScroll;
    });
}

// ==================== ANIMATION ON SCROLL ====================
function initAnimations() {
    const observerOptions = {
        threshold: 0.1,
        rootMargin: '0px 0px -50px 0px'
    };
    
    const observer = new IntersectionObserver(function(entries) {
        entries.forEach(entry => {
            if (entry.isIntersecting) {
                entry.target.style.opacity = '1';
                entry.target.style.transform = 'translateY(0)';
            }
        });
    }, observerOptions);
    
    // Apply animation to cards
    const cards = document.querySelectorAll('.menu-card, .space-card, .feature-item, .stat-card');
    
    cards.forEach(card => {
        card.style.opacity = '0';
        card.style.transform = 'translateY(30px)';
        card.style.transition = 'all 0.6s ease';
        observer.observe(card);
    });
}

// ==================== MODAL SYSTEM ====================
function initModals() {
    // Open modal
    document.querySelectorAll('[data-modal-open]').forEach(btn => {
        btn.addEventListener('click', function() {
            const modalId = this.getAttribute('data-modal-open');
            const modal = document.getElementById(modalId);
            if (modal) {
                modal.classList.add('active');
                document.body.style.overflow = 'hidden';
            }
        });
    });
    
    // Close modal
    document.querySelectorAll('[data-modal-close], .modal-overlay').forEach(el => {
        el.addEventListener('click', function() {
            const modal = this.closest('.modal');
            if (modal) {
                modal.classList.remove('active');
                document.body.style.overflow = '';
            }
        });
    });
    
    // Close on ESC key
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') {
            document.querySelectorAll('.modal.active').forEach(modal => {
                modal.classList.remove('active');
                document.body.style.overflow = '';
            });
        }
    });
}

// ==================== FORM VALIDATION ====================
function initForms() {
    const forms = document.querySelectorAll('form[data-validate]');
    
    forms.forEach(form => {
        form.addEventListener('submit', function(e) {
            e.preventDefault();
            
            if (validateForm(this)) {
                // Form is valid, can submit
                showToast('Form submitted successfully!', 'success');
                // this.submit(); // Uncomment to actually submit
            }
        });
    });
}

function validateForm(form) {
    let isValid = true;
    const inputs = form.querySelectorAll('input[required], select[required], textarea[required]');
    
    inputs.forEach(input => {
        if (!input.value.trim()) {
            isValid = false;
            input.classList.add('error');
            showError(input, 'This field is required');
        } else {
            input.classList.remove('error');
            removeError(input);
            
            // Email validation
            if (input.type === 'email' && !isValidEmail(input.value)) {
                isValid = false;
                input.classList.add('error');
                showError(input, 'Please enter a valid email');
            }
            
            // Phone validation
            if (input.type === 'tel' && !isValidPhone(input.value)) {
                isValid = false;
                input.classList.add('error');
                showError(input, 'Please enter a valid phone number');
            }
        }
    });
    
    return isValid;
}

function showError(input, message) {
    removeError(input);
    const error = document.createElement('small');
    error.className = 'error-message';
    error.style.color = '#ef4444';
    error.style.fontSize = '0.75rem';
    error.style.marginTop = '0.25rem';
    error.style.display = 'block';
    error.textContent = message;
    input.parentElement.appendChild(error);
}

function removeError(input) {
    const error = input.parentElement.querySelector('.error-message');
    if (error) {
        error.remove();
    }
}

function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}

function isValidPhone(phone) {
    return /^[0-9]{10,11}$/.test(phone.replace(/\s/g, ''));
}

// ==================== TOAST NOTIFICATION ====================
function showToast(message, type = 'info') {
    const toast = document.createElement('div');
    toast.className = `alert alert-${type}`;
    toast.style.position = 'fixed';
    toast.style.top = '20px';
    toast.style.right = '20px';
    toast.style.zIndex = '10000';
    toast.style.minWidth = '250px';
    toast.style.opacity = '0';
    toast.style.transition = 'opacity 0.3s ease';
    toast.textContent = message;
    
    document.body.appendChild(toast);
    
    // Fade in
    setTimeout(() => {
        toast.style.opacity = '1';
    }, 10);
    
    // Remove after 3 seconds
    setTimeout(() => {
        toast.style.opacity = '0';
        setTimeout(() => {
            document.body.removeChild(toast);
        }, 300);
    }, 3000);
}

// ==================== UTILITY FUNCTIONS ====================
function debounce(func, wait) {
    let timeout;
    return function executedFunction(...args) {
        const later = () => {
            clearTimeout(timeout);
            func(...args);
        };
        clearTimeout(timeout);
        timeout = setTimeout(later, wait);
    };
}

function formatCurrency(amount) {
    return new Intl.NumberFormat('vi-VN', {
        style: 'currency',
        currency: 'VND'
    }).format(amount);
}

function formatDate(date) {
    return new Intl.DateTimeFormat('vi-VN', {
        year: 'numeric',
        month: 'long',
        day: 'numeric'
    }).format(new Date(date));
}

function formatTime(time) {
    return new Intl.DateTimeFormat('vi-VN', {
        hour: '2-digit',
        minute: '2-digit'
    }).format(new Date(time));
}

// ==================== LOADING STATE ====================
window.addEventListener('load', function() {
    document.body.classList.add('loaded');
    
    // Hide loading spinner if exists
    const loader = document.querySelector('.loader');
    if (loader) {
        loader.style.opacity = '0';
        setTimeout(() => {
            loader.style.display = 'none';
        }, 300);
    }
});

// ==================== MOBILE MENU ====================
function initMobileMenu() {
    const menuBtn = document.querySelector('.mobile-menu-btn');
    const mobileMenu = document.querySelector('.mobile-menu');
    
    if (menuBtn && mobileMenu) {
        menuBtn.addEventListener('click', function() {
            mobileMenu.classList.toggle('active');
            this.classList.toggle('active');
        });
    }
}

// ==================== CONSOLE WELCOME ====================
function consoleWelcome() {
    console.log('%c🍽️ Buffet Paradise', 'font-size: 24px; color: #d4af37; font-weight: bold;');
    console.log('%cWelcome to our website!', 'font-size: 14px; color: #b0b0b0;');
    console.log('%cBuilt with ❤️ using Java, JSP, and vanilla JavaScript', 'font-size: 12px; color: #808080;');
}

// ==================== EXPORT FOR USE IN OTHER SCRIPTS ====================
window.BuffetParadise = {
    showToast,
    formatCurrency,
    formatDate,
    formatTime,
    debounce
};