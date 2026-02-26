document.addEventListener("DOMContentLoaded", function() {

    /* ================= REGISTER ================= */

    const registerForm = document.getElementById("registerForm");

    if (registerForm) {
        registerForm.addEventListener("submit", function(e) {
            e.preventDefault();

            const email = document.getElementById("regEmail").value;
            const password = document.getElementById("regPassword").value;
            const error = document.getElementById("regError");

            if (password.length < 6) {
                error.textContent = "Mật khẩu phải ít nhất 6 ký tự!";
                return;
            }

            localStorage.setItem("userEmail", email);
            localStorage.setItem("userPassword", password);

            alert("Đăng ký thành công!");
            window.location.href = "login.html";
        });
    }

    /* ================= LOGIN ================= */

    const loginForm = document.getElementById("loginForm");

    if (loginForm) {
        loginForm.addEventListener("submit", function(e) {
            e.preventDefault();

            const email = document.getElementById("email").value;
            const password = document.getElementById("password").value;
            const error = document.getElementById("error");

            const savedEmail = localStorage.getItem("userEmail");
            const savedPassword = localStorage.getItem("userPassword");

            if (email === savedEmail && password === savedPassword) {
                localStorage.setItem("loggedIn", "true");
                alert("Đăng nhập thành công!");
                window.location.href = "index.html";
            } else {
                error.textContent = "Sai email hoặc mật khẩu!";
            }
        });
    }

});

/* ================= PASSWORD TOGGLE ================= */

function togglePassword() {
    const pass = document.getElementById("password");
    pass.type = pass.type === "password" ? "text" : "password";
}