/**
 * Contact Page JavaScript
 */

document.addEventListener("DOMContentLoaded", function () {
  initContactForm();
});

/**
 * Contact form handling
 */
function initContactForm() {
  const form = document.getElementById("contactForm");

  if (!form) return;

  form.addEventListener("submit", function (e) {
    e.preventDefault();

    // Get form data
    const formData = new FormData(form);
    const data = Object.fromEntries(formData.entries());

    // Validate
    if (!validateForm(data)) {
      return;
    }

    // Show loading state
    const submitBtn = form.querySelector('button[type="submit"]');
    const originalText = submitBtn.innerHTML;
    submitBtn.innerHTML = '<i class="fas fa-spinner fa-spin"></i> Đang gửi...';
    submitBtn.disabled = true;

    // Simulate API call
    setTimeout(function () {
      // Show success message
      showSuccessMessage(data);

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

  if (!data.message || data.message.trim() === "") {
    showAlert("Vui lòng nhập nội dung tin nhắn!", "error");
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
 * Show success message
 */
function showSuccessMessage(data) {
  const formContainer = document.querySelector(".contact-form-container");

  const subjectNames = {
    reservation: "Đặt bàn",
    feedback: "Góp ý",
    event: "Tổ chức sự kiện",
    career: "Tuyển dụng",
    other: "Khác",
  };

  const successHTML = `
        <div class="contact-success show">
            <div class="success-icon">
                <i class="fas fa-check"></i>
            </div>
            <h3>Gửi Tin Nhắn Thành Công!</h3>
            <p>Cảm ơn bạn đã liên hệ với Le Château Doré.<br>
            Chúng tôi sẽ phản hồi qua email <strong>${data.email}</strong> trong thời gian sớm nhất.</p>
            
            <div class="contact-details" style="margin-top: 20px; text-align: left; background: #f9f9f9; padding: 20px; border-radius: 8px;">
                <p><strong>Thông tin đã gửi:</strong></p>
                <p>📝 Chủ đề: ${subjectNames[data.subject] || "Không có"}</p>
                <p>👤 Tên: ${data.name}</p>
                ${data.phone ? `<p>📱 Điện thoại: ${data.phone}</p>` : ""}
            </div>
            
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
