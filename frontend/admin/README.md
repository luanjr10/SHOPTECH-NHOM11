# ShopTech Admin

Giao diện quản trị ShopTech (React 19 + TypeScript + Vite + Tailwind CSS 4).

## Chức năng

- Đăng nhập (quản trị viên / nhân viên)
- Dashboard tổng quan: doanh thu, đơn hàng, người dùng mới, biểu đồ, xếp hạng, hoạt động gần đây
- Quản lý sản phẩm (ảnh, thông số kỹ thuật, biến thể, Quick Link)
- Quản lý danh mục (cây danh mục cha/con, ảnh hoặc icon, thương hiệu, Quick Link)
- Quản lý thương hiệu
- Quản lý nhân viên và phân quyền theo từng chức năng

## Chạy local

```bash
npm install
npm run dev
```

Mặc định gọi API tại `http://localhost:8000`; đổi bằng biến `VITE_BACKEND_URL` trong file `.env`.

Giao diện dựa trên template [Mosaic Lite](https://github.com/cruip/tailwind-dashboard-template) của Cruip.
