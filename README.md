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
- **Hoa hồng** — tỉ lệ hoa hồng mặc định / theo danh mục / theo gian hàng
- **Voucher** — mã giảm % / số tiền / miễn phí vận chuyển, giới hạn theo hạng khách hàng
- **Rút tiền** — duyệt, từ chối, giải ngân qua MoMo / VNPay / OnePay / SePay (sandbox)
- **Quỹ sàn** — tiền đang giữ hộ người bán, tiền đã quyết toán, tổng đã chi trả
- **Cài đặt tài khoản** — hồ sơ, ảnh đại diện, đổi mật khẩu, đăng xuất thiết bị khác
- **Đăng ký / Đăng nhập** — đăng ký, đăng nhập (mật khẩu hoặc Google), quên mật khẩu bằng mã email, xác thực email

### Kênh người bán (Seller Center)

Người bán đăng nhập chung trang quản trị, menu hiển thị theo vai trò; chọn gian hàng đang quản lý ở thanh trên cùng.

- **Dashboard gian hàng** — doanh thu thực nhận, đơn hoàn tất, khách hàng (30 ngày), top sản phẩm / danh mục / khách hàng, hoạt động gần đây, số dư ví
- **Gian hàng** — tạo gian hàng (chờ admin duyệt), sửa tên / mô tả / logo, chuyển gian hàng đang quản lý
- **Sản phẩm** — thêm/sửa/xoá sản phẩm của gian hàng (dùng chung form với admin), tìm kiếm, sắp xếp, phân trang
- **Kho hàng** — tồn kho, lọc sản phẩm sắp hết hàng, nhập thêm / xuất hao hụt, lịch sử điều chỉnh

## Phân công

| Thành viên     | Chức năng phụ trách                                                                 |
|----------------|--------------------------------------------------------------------------------------|
| Bùi Văn Luân   | Dashboard, Quản lý sản phẩm, Quản lý danh mục, Quản lý thương hiệu, Quản lý nhân viên; Kênh người bán: Dashboard, Gian hàng, Sản phẩm, Kho hàng |
| Hồ Trọng Dũng  | Khách hàng, Nổi bật trang chủ, Người bán, Gian hàng, Đơn hàng & Hoá đơn, Đánh giá & Theo dõi |
| Nguyễn Phạm Thành Công | Hoa hồng, Voucher, Rút tiền, Quỹ sàn, Cài đặt tài khoản, Đăng ký / Đăng nhập |

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
