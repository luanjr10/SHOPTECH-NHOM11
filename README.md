# ShopTech

Hệ thống quản trị sàn thương mại điện tử bán đồ công nghệ.

| Thư mục            | Mô tả                                                                 |
|--------------------|-----------------------------------------------------------------------|
| `backend/`         | REST API — Java Spring Boot (Spring MVC, JPA/MySQL, MongoDB, Spring Security + JWT) |
| `frontend/admin/`  | Giao diện quản trị — React + TypeScript + Vite + Tailwind CSS         |

## Chức năng

- **Dashboard** — KPI 30 ngày, doanh thu theo phương thức thanh toán, top danh mục / gian hàng / khách hàng, hoạt động gần đây
- **Quản lý sản phẩm** — thêm/sửa/xoá, nhiều ảnh, thông số kỹ thuật, biến thể (SKU, giá, tồn kho), Quick Link
- **Quản lý danh mục** — danh mục cha/con, hiển thị bằng icon hoặc ảnh, gắn thương hiệu, Quick Link
- **Quản lý thương hiệu** — thêm/sửa/xoá, logo
- **Quản lý nhân viên** — tạo tài khoản nhân viên, phân quyền xem/thêm/sửa/xoá theo từng chức năng
- **Khách hàng** — danh sách, tổng chi tiêu, hạng thành viên (Đồng/Bạc/Vàng/Kim Cương), lịch sử đơn
- **Nổi bật trang chủ** — hẹn giờ kết thúc Flash sale, chọn sản phẩm Flash sale / Hot trend
- **Người bán** — duyệt / từ chối đơn đăng ký mở gian hàng
- **Gian hàng** — theo dõi, tạm ẩn / kích hoạt gian hàng
- **Đơn hàng & Hoá đơn** — xem chi tiết đơn theo gian hàng, xuất hoá đơn PDF, gửi hoá đơn qua email
- **Đánh giá & Theo dõi** — kiểm duyệt đánh giá sản phẩm, xem người theo dõi gian hàng

## Phân công

| Thành viên     | Chức năng phụ trách                                                                 |
|----------------|--------------------------------------------------------------------------------------|
| Bùi Văn Luân   | Dashboard, Quản lý sản phẩm, Quản lý danh mục, Quản lý thương hiệu, Quản lý nhân viên |
| Hồ Trọng Dũng  | Khách hàng, Nổi bật trang chủ, Người bán, Gian hàng, Đơn hàng & Hoá đơn, Đánh giá & Theo dõi |

## Kiến trúc

```
React (frontend/admin)
        │  REST / JSON (JWT trong cookie HttpOnly)
        ▼
Spring Boot (backend) — Modular Monolith
  controller → service → repository → entity / document
        │
   ┌────┴────┐
   ▼         ▼
 MySQL    MongoDB
```

## Chạy local

```bash
# Backend (cần JDK 21+, cấu hình backend/.env theo backend/.env.example)
cd backend
./mvnw spring-boot:run
```

```bash
# Frontend
cd frontend/admin
npm install
npm run dev
```

Backend chạy ở `http://localhost:8000`, trang quản trị ở `http://localhost:5173`.
