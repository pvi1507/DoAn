/**
 * Le Château Doré - JavaScript FULL
 * Version: Hoàn chỉnh có Login + Register
 */

// ===============================
// DOM READY
// ===============================
document.addEventListener("DOMContentLoaded", function() {
    initHeader();
    initSmoothScroll();
    initAnimations();
    initReservationForm();
    initNewsletterForm();
    initMobileMenu();
    initLogin();
    initRegister();
    updateLoginButton();
});

// ===============================
// HEADER SCROLL EFFECT
// ===============================
function initHeader() {
    const header = document.querySelector(".header");
    if (!header) return;

    window.addEventListener("scroll", function() {
        header.classList.toggle("scrolled", window.scrollY > 100);
    });
}

// ===============================
// SMOOTH SCROLL
// ===============================
function initSmoothScroll() {
    const navLinks = document.querySelectorAll('a[href^="#"]');

    navLinks.forEach((link) => {
        link.addEventListener("click", function(e) {
            e.preventDefault();

            const targetId = this.getAttribute("href");
            if (targetId === "#") return;

            const targetElement = document.querySelector(targetId);
            if (!targetElement) return;

            const header = document.querySelector(".header");
            const headerHeight = header ? header.offsetHeight : 0;

            const targetPosition = targetElement.offsetTop - headerHeight;

            window.scrollTo({
                top: targetPosition,
                behavior: "smooth",
            });
        });
    });
}

// ===============================
// SCROLL ANIMATIONS
// ===============================
function initAnimations() {
    if (!("IntersectionObserver" in window)) return;

    const observer = new IntersectionObserver((entries) => {
        entries.forEach((entry) => {
            if (entry.isIntersecting) {
                entry.target.classList.add("animate-in");
            }
        });
    }, { threshold: 0.1 });

    const elements = document.querySelectorAll(
        ".about-content, .menu-item, .contact-item, .section-header"
    );

    elements.forEach((el) => observer.observe(el));
}

// ===============================
// RESERVATION FORM (CẦN LOGIN)
// ===============================
function initReservationForm() {
    const form = document.querySelector(".reservation-form");
    if (!form) return;

    form.addEventListener("submit", function(e) {
        e.preventDefault();

        const loggedIn = localStorage.getItem("loggedIn");

        if (loggedIn !== "true") {
            showAlert("Bạn cần đăng nhập để đặt bàn!", "error");

            localStorage.setItem("redirectAfterLogin", "index.html#reservation");

            setTimeout(() => {
                window.location.href = "login.html";
            }, 1000);
            return;
        }

        showAlert("Đặt bàn thành công!", "success");
        form.reset();
    });
}

// ===============================
// NEWSLETTER
// ===============================
function initNewsletterForm() {
    const form = document.querySelector(".newsletter-form");
    if (!form) return;

    form.addEventListener("submit", function(e) {
        e.preventDefault();

        const emailInput = form.querySelector('input[type="email"]');
        const email = emailInput.value;

        if (!isValidEmail(email)) {
            showAlert("Vui lòng nhập email hợp lệ!", "error");
            return;
        }

        showAlert("Đăng ký thành công!", "success");
        emailInput.value = "";
    });
}

function isValidEmail(email) {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

// ===============================
// MOBILE MENU
// ===============================
function initMobileMenu() {
    const menuBtn = document.querySelector(".mobile-menu-btn");
    const navMenu = document.querySelector(".nav-menu");

    if (!menuBtn || !navMenu) return;

    menuBtn.addEventListener("click", function() {
        navMenu.classList.toggle("active");
    });
}

// ===============================
// ALERT BOX
// ===============================
function showAlert(message, type) {
    const existing = document.querySelector(".custom-alert");
    if (existing) existing.remove();

    const alert = document.createElement("div");
    alert.textContent = message;

    alert.style.position = "fixed";
    alert.style.top = "100px";
    alert.style.right = "20px";
    alert.style.padding = "15px 25px";
    alert.style.background = type === "success" ? "#27ae60" : "#e74c3c";
    alert.style.color = "#fff";
    alert.style.borderRadius = "6px";
    alert.style.zIndex = "10000";

    document.body.appendChild(alert);

    setTimeout(() => alert.remove(), 3000);
}

// ===============================
// LOGIN SYSTEM
// ===============================
function initLogin() {
    const loginForm = document.getElementById("loginForm");
    if (!loginForm) return;

    loginForm.addEventListener("submit", function(e) {
        e.preventDefault();

        const email = document.getElementById("email").value.trim();
        const password = document.getElementById("password").value.trim();
        const error = document.getElementById("error");

        error.textContent = "";

        let users = JSON.parse(localStorage.getItem("users")) || [];

        const validUser = users.find(
            user => user.email === email && user.password === password
        );

        if (validUser) {

            localStorage.setItem("loggedIn", "true");
            localStorage.setItem("currentUser", email);

            const redirect = localStorage.getItem("redirectAfterLogin");
            localStorage.removeItem("redirectAfterLogin");

            window.location.href = redirect || "index.html";

        } else {
            error.textContent = "Sai email hoặc mật khẩu!";
        }
    });
}

// ===============================
// REGISTER SYSTEM
// ===============================
function initRegister() {
    const registerForm = document.getElementById("registerForm");
    if (!registerForm) return;

    registerForm.addEventListener("submit", function(e) {
        e.preventDefault();

        const email = document.getElementById("regEmail").value.trim();
        const password = document.getElementById("regPassword").value.trim();
        const error = document.getElementById("regError");

        error.textContent = "";

        if (!email || !password) {
            error.textContent = "Vui lòng nhập đầy đủ thông tin!";
            return;
        }

        let users = JSON.parse(localStorage.getItem("users")) || [];

        const exists = users.find(user => user.email === email);
        if (exists) {
            error.textContent = "Email đã tồn tại!";
            return;
        }

        users.push({ email, password });
        localStorage.setItem("users", JSON.stringify(users));

        alert("Đăng ký thành công!");
        window.location.href = "login.html";
    });
}

// ===============================
// UPDATE LOGIN BUTTON
// ===============================
function updateLoginButton() {
    const navMenu = document.querySelector(".nav-menu ul");
    if (!navMenu) return;

    let loginBtn = document.getElementById("loginBtn");

    if (!loginBtn) {
        const li = document.createElement("li");
        li.innerHTML = `<a href="login.html" id="loginBtn">Đăng Nhập</a>`;
        navMenu.appendChild(li);
        loginBtn = document.getElementById("loginBtn");
    }

    const loggedIn = localStorage.getItem("loggedIn");

    if (loggedIn === "true") {
        loginBtn.textContent = "Đăng Xuất";
        loginBtn.href = "#";

        loginBtn.addEventListener("click", function(e) {
            e.preventDefault();
            localStorage.removeItem("loggedIn");
            localStorage.removeItem("currentUser");
            location.reload();
        });
    }
}