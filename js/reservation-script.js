/**
 * Reservation Page JavaScript
 */

document.addEventListener("DOMContentLoaded", function () {
  initGuestCounter();
  initDatePicker();
  initReservationForm();
});

/**
 * Guest counter functionality
 */
function initGuestCounter() {
  const decreaseBtn = document.getElementById("decreaseGuests");
  const increaseBtn = document.getElementById("increaseGuests");
  const guestsInput = document.getElementById("guests");

  if (!decreaseBtn || !increaseBtn || !guestsInput) return;

  decreaseBtn.addEventListener("click", function () {
    let currentValue = parseInt(guestsInput.value);
    if (currentValue > 1) {
      guestsInput.value = currentValue - 1;
    }
  });

  increaseBtn.addEventListener("click", function () {
    let currentValue = parseInt(guestsInput.value);
    if (currentValue < 20) {
      guestsInput.value = currentValue + 1;
    }
  });
}

/**
 * Date picker configuration
 */
function initDatePicker() {
  const dateInput = document.getElementById("date");

  if (!dateInput) return;

  // Set minimum date to today
  const today = new Date();
  const tomorrow = new Date(today);
  tomorrow.setDate(tomorrow.getDate() + 1);

  const minDate = tomorrow.toISOString().split("T")[0];
  dateInput.setAttribute("min", minDate);

  // Set maximum date to 60 days from now
  const maxDate = new Date();
  maxDate.setDate(maxDate.getDate() + 60);
  dateInput.setAttribute("max", maxDate.toISOString().split("T")[0]);
}

/**
 * Reservation form handling
 */
function initReservationForm() {
  const form = document.getElementById("reservationForm");

  if (!form) return;

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    // Get form data
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Add guests count
    data.guests = document.getElementById("guests").value;

    // Get selected area
    const selectedArea = document.querySelector('input[name="area"]:checked');
    if (selectedArea) {
      data.area = selectedArea.value;
    }

    // Validate
    if (!validateForm(data)) {
      return;
    }

    // Show loading state
    const submitBtn = form.querySelector('button[type="submit"]');
    const originalText = submitBtn.innerHTML;
    submitBtn.innerHTML =
      '<i class="fas fa-spinner fa-spin"></i> Đang xử lý...';
    submitBtn.disabled = true;

    // Simulate API call
    setTimeout(function () {
      // Generate order ID
      const orderId = "RVD" + Date.now().toString().slice(-8);

      // Show success message
      showSuccessMessage(orderId, data);

      // Reset button
      submitBtn.innerHTML = originalText;
      submitBtn.disabled = false;
    }, 2000);
  });
}

/**
 * Form validation
 */
function validateForm(data) {
  // Validate required fields
  if (!data.name || data.name.trim() === "") {
    showAlert("Vui lòng nhập họ và tên!", "error");
    return false;
  }

  if (!data.email || data.email.trim() === "") {
    showAlert("Vui lòng nhập địa chỉ email!", "error");
    return false;
  }

  if (!isValidEmail(data.email)) {
    showAlert("Vui lòng nhập địa chỉ email hợp lệ!", "error");
    return false;
  }

  if (!data.phone || data.phone.trim() === "") {
    showAlert("Vui lòng nhập số điện thoại!", "error");
    return false;
  }

  if (!isValidPhone(data.phone)) {
    showAlert("Vui lòng nhập số điện thoại hợp lệ!", "error");
    return false;
  }

  if (!data.date) {
    showAlert("Vui lòng chọn ngày!", "error");
    return false;
  }

  if (!data.time) {
    showAlert("Vui lòng chọn giờ!", "error");
    return false;
  }

  if (!data.guests || data.guests < 1) {
    showAlert("Vui lòng chọn số khách!", "error");
    return false;
  }

  return true;
}

/**
 * Email validation
 */
function isValidEmail(email) {
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  return emailRegex.test(email);
}

/**
 * Phone validation (Vietnamese format)
 */
function isValidPhone(phone) {
  // Remove all spaces and special characters
  const cleanPhone = phone.replace(/[\s\-\(\)]/g, "");

  // Check for Vietnamese phone numbers
  const phoneRegex = /^(0|\+84)[0-9]{9,10}$/;
  return phoneRegex.test(cleanPhone);
}

/**
 * Show success message
 */
function showSuccessMessage(orderId, data) {
  const formContainer = document.querySelector(".reservation-form-container");

  // Format date
  const dateObj = new Date(data.date);
  const formattedDate = dateObj.toLocaleDateString("vi-VN", {
    weekday: "long",
    year: "numeric",
    month: "long",
    day: "numeric",
  });

  // Get area name
  const areaNames = {
    main: "Khu vực chính",
    vip: "Khu VIP",
    outdoor: "Sân vườn",
    balcony: "Ban công",
  };

  const successHTML = `
        <div class="reservation-success show">
            <div class="success-icon">
                <i class="fas fa-check"></i>
            </div>
            <h3>Đặt Bàn Thành Công!</h3>
            <p>Cảm ơn bạn đã đặt bàn tại Le Château Doré.<br>
            Chúng tôi đã gửi email xác nhận đến <strong>${data.email}</strong></p>
            
            <div class="order-id">Mã đặt bàn: ${orderId}</div>
            
            <div class="booking-details">
                <p><strong>Thông tin đặt bàn:</strong></p>
                <p>📅 Ngày: ${formattedDate}</p>
                <p>⏰ Giờ: ${data.time}</p>
                <p>👥 Số khách: ${data.guests} người</p>
                <p>📍 Khu vực: ${areaNames[data.area] || "Khu vực chính"}</p>
                <p>📱 Điện thoại: ${data.phone}</p>
            </div>
            
            <p style="margin-top: 20px; font-size: 14px; color: #666;">
                Nếu có thay đổi, vui lòng liên hệ trước 24 giờ.<br>
                Cảm ơn quý khách!
            </p>
            
            <a href="index.html" class="btn btn-primary" style="margin-top: 30px;">
                <i class="fas fa-home"></i> Về Trang Chủ
            </a>
        </div>
    `;

  // Hide form and show success
  formContainer.innerHTML = successHTML;
}

/**
 * Custom alert function
 */
function showAlert(message, type) {
  // Remove existing alerts
  const existingAlert = document.querySelector(".custom-alert");
  if (existingAlert) {
    existingAlert.remove();
  }

  // Create alert element
  const alert = document.createElement("div");
  alert.className = `custom-alert alert-${type}`;
  alert.innerHTML = `
        <div class="alert-content">
            <i class="fas fa-${type === "success" ? "check-circle" : "exclamation-circle"}"></i>
            <span>${message}</span>
        </div>
    `;

  // Add alert styles
  const style = document.createElement("style");
  style.textContent = `
        .custom-alert {
            position: fixed;
            top: 100px;
            right: 20px;
            z-index: 10000;
            animation: slideIn 0.4s ease;
        }
        
        .custom-alert .alert-content {
            display: flex;
            align-items: center;
            gap: 15px;
            padding: 18px 25px;
            border-radius: 8px;
            box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
            font-family: 'Cormorant Garamond', serif;
            font-size: 16px;
        }
        
        .alert-success .alert-content {
            background: linear-gradient(135deg, #2ecc71, #27ae60);
            color: white;
        }
        
        .alert-error .alert-content {
            background: linear-gradient(135deg, #e74c3c, #c0392b);
            color: white;
        }
        
        .alert-success i,
        .alert-error i {
            font-size: 22px;
        }
        
        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateX(100px);
            }
            to {
                opacity: 1;
                transform: translateX(0);
            }
        }
        
        @keyframes slideOut {
            from {
                opacity: 1;
                transform: translateX(0);
            }
            to {
                opacity: 0;
                transform: translateX(100px);
            }
        }
        
        .custom-alert.hiding {
            animation: slideOut 0.4s ease forwards;
        }
    `;
  document.head.appendChild(style);

  document.body.appendChild(alert);

  // Auto remove after 5 seconds
  setTimeout(() => {
    alert.classList.add("hiding");
    setTimeout(() => {
      alert.remove();
    }, 400);
  }, 5000);
}
