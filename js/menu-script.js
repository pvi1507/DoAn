/**
 * ============================================
 * MENU PAGE JAVASCRIPT - Le Château Doré
 * ============================================
 * Chức năng:
 * - Lọc món ăn theo danh mục
 * - Hiệu ứng chuyển đổi tab
 * - Xử lý sự kiện click
 */

// ============================================
// DOM READY - Chạy khi trang đã tải xong
// ============================================
document.addEventListener("DOMContentLoaded", function () {
  // Khởi tạo các chức năng menu
  initMenuFiltering();
});

/**
 * ============================================
 * MENU FILTERING SYSTEM
 * - Lọc món ăn theo danh mục
 * - Xử lý sự kiện click trên nút danh mục
 * ============================================
 */
function initMenuFiltering() {
  // Lấy tất cả các nút danh mục menu
  const categoryButtons = document.querySelectorAll(".menu-category-btn");

  // Lấy tất cả các phần menu
  const menuSections = document.querySelectorAll(".menu-section");

  // Kiểm tra nếu không có phần tử nào thì không làm gì
  if (!categoryButtons.length || !menuSections.length) {
    return;
  }

  // Thêm sự kiện click cho mỗi nút danh mục
  categoryButtons.forEach((button) => {
    button.addEventListener("click", function () {
      // ============================================
      // 1. Cập nhật trạng thái active cho nút
      // ============================================

      // Xóa class active từ tất cả các nút
      categoryButtons.forEach((btn) => {
        btn.classList.remove("active");
      });

      // Thêm class active cho nút được click
      this.classList.add("active");

      // ============================================
      // 2. Lấy danh mục được chọn
      // ============================================
      const category = this.getAttribute("data-category");

      // ============================================
      // 3. Hiện/ẩn các phần menu tương ứng
      // ============================================
      menuSections.forEach((section) => {
        // Nếu chọn "Tất Cả" hoặc ID phần trùng với danh mục
        if (category === "all" || section.id === category) {
          // Hiện phần menu
          section.classList.add("active");

          // Thêm hiệu ứng fade in
          section.style.opacity = "0";
          setTimeout(() => {
            section.style.opacity = "1";
          }, 50);
        } else {
          // Ẩn phần menu
          section.classList.remove("active");
        }
      });

      // ============================================
      // 4. Cuộn đến phần menu đầu tiên (nếu cần)
      // ============================================
      if (category !== "all") {
        const targetSection = document.getElementById(category);
        if (targetSection) {
          smoothScrollToElement(targetSection);
        }
      }
    });
  });
}

/**
 * ============================================
 * SMOOTH SCROLL TO ELEMENT
 * - Cuộn mượt đến phần tử target
 * - Có tính đến chiều cao của header
 * ============================================
 */
function smoothScrollToElement(element) {
  // Lấy chiều cao header
  const header = document.querySelector(".header");
  const headerHeight = header ? header.offsetHeight : 0;

  // Tính vị trí cuộn đến
  const elementPosition = element.getBoundingClientRect().top;
  const offsetPosition =
    elementPosition + window.pageYOffset - headerHeight - 20;

  // Cuộn đến vị trí
  window.scrollTo({
    top: offsetPosition,
    behavior: "smooth",
  });
}

/**
 * ============================================
 * ADD TO CART (Optional - for future use)
 * - Thêm món vào giỏ hàng
 * ============================================
 */
function addToCart(itemName, itemPrice) {
  // Kiểm tra xem giỏ hàng đã tồn tại chưa
  let cart = JSON.parse(localStorage.getItem("restaurantCart")) || [];

  // Thêm sản phẩm vào giỏ
  cart.push({
    name: itemName,
    price: itemPrice,
    quantity: 1,
    date: new Date().toISOString(),
  });

  // Lưu vào localStorage
  localStorage.setItem("restaurantCart", JSON.stringify(cart));

  // Hiển thị thông báo
  showNotification(`Đã thêm "${itemName}" vào giỏ hàng`);

  // Cập nhật số lượng giỏ hàng (nếu có)
  updateCartCount();
}

/**
 * ============================================
 * UPDATE CART COUNT
 * - Cập nhật số lượng sản phẩm trong giỏ
 * ============================================
 */
function updateCartCount() {
  const cart = JSON.parse(localStorage.getItem("restaurantCart")) || [];
  const cartCountElement = document.querySelector(".cart-count");

  if (cartCountElement) {
    cartCountElement.textContent = cart.length;
    cartCountElement.style.display = cart.length > 0 ? "block" : "none";
  }
}

/**
 * ============================================
 * SHOW NOTIFICATION
 * - Hiển thị thông báo popup
 * ============================================
 */
function showNotification(message) {
  // Tạo phần tử thông báo
  const notification = document.createElement("div");
  notification.className = "menu-notification";
  notification.innerHTML = `
        <i class="fas fa-check-circle"></i>
        <span>${message}</span>
    `;

  // Thêm styles
  notification.style.cssText = `
        position: fixed;
        top: 100px;
        right: 20px;
        z-index: 10000;
        background: linear-gradient(135deg, #2ecc71, #27ae60);
        color: white;
        padding: 15px 25px;
        border-radius: 8px;
        box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);
        font-family: 'Cormorant Garamond', serif;
        font-size: 16px;
        display: flex;
        align-items: center;
        gap: 12px;
        animation: slideInRight 0.4s ease;
    `;

  // Thêm vào body
  document.body.appendChild(notification);

  // Xóa sau 3 giây
  setTimeout(() => {
    notification.style.animation = "slideOutRight 0.4s ease";
    setTimeout(() => {
      notification.remove();
    }, 400);
  }, 3000);
}

// ============================================
// KEYBOARD NAVIGATION
// - Hỗ trợ điều hướng bằng bàn phím
// ============================================
document.addEventListener("keydown", function (e) {
  // Chỉ xử lý nếu đang ở trang menu
  if (!document.querySelector(".menu-page")) {
    return;
  }

  const buttons = Array.from(document.querySelectorAll(".menu-category-btn"));
  const activeIndex = buttons.findIndex((btn) =>
    btn.classList.contains("active"),
  );

  if (e.key === "ArrowRight" || e.key === "ArrowLeft") {
    let newIndex;

    if (e.key === "ArrowRight") {
      newIndex = (activeIndex + 1) % buttons.length;
    } else {
      newIndex = (activeIndex - 1 + buttons.length) % buttons.length;
    }

    // Click vào nút danh mục mới
    buttons[newIndex].click();
  }
});
