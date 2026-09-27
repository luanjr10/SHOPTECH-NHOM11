import Dashboard from "../pages/Dashboard";
import LoginPage from "../pages/Login";
import ForgotPasswordPage from "../pages/ForgotPassword";
import ManageBrandsPage from "../pages/ManageBrands";
import ManageCategoriesPage from "../pages/ManageCategory";
import ManageEmployeePage from "../pages/ManageEmployee";
import ManageProductsPage from "../pages/ManageProducts";
import ManageCustomerPage from "../pages/ManageCustomer";
import HomeHighlightsPage from "../pages/HomeHighlights";
import ManageSellersPage from "../pages/ManageSellers";
import ManageStoresPage from "../pages/ManageStores";
import ManageOrdersPage from "../pages/ManageOrders";
import ManageReviewsPage from "../pages/ManageReviews";
import ManageCommissionsPage from "../pages/ManageCommissions";
import ManageCouponsPage from "../pages/ManageCoupons";
import ManageWithdrawalsPage from "../pages/ManageWithdrawals";
import PlatformFundsPage from "../pages/PlatformFunds";
import SettingsPage from "../pages/Settings";
import LayoutDefault from "../partials/layout";
import RequireRole from "../components/RequireRole";

export const routes = [
  {
    path: "/login",
    element: <LoginPage />,
  },
  {
    path: "/quen-mat-khau",
    element: <ForgotPasswordPage />,
  },
  {
    element: (
      <RequireRole roles={["admin", "employee"]}>
        <LayoutDefault />
      </RequireRole>
    ),
    children: [
      { path: "/", element: <Dashboard /> },
      { path: "/products", element: <RequireRole roles={["admin", "employee"]} module="products"><ManageProductsPage /></RequireRole> },
      { path: "/categories", element: <RequireRole roles={["admin", "employee"]} module="categories"><ManageCategoriesPage /></RequireRole> },
      { path: "/brands", element: <RequireRole roles={["admin", "employee"]} module="brands"><ManageBrandsPage /></RequireRole> },
      { path: "/employee", element: <RequireRole roles={["admin"]}><ManageEmployeePage /></RequireRole> },
      { path: "/customers", element: <RequireRole roles={["admin", "employee"]} module="customers"><ManageCustomerPage /></RequireRole> },
      { path: "/home-highlights", element: <RequireRole roles={["admin", "employee"]} module="home_highlights"><HomeHighlightsPage /></RequireRole> },
      { path: "/sellers", element: <RequireRole roles={["admin", "employee"]} module="seller_applications"><ManageSellersPage /></RequireRole> },
      { path: "/stores", element: <RequireRole roles={["admin", "employee"]} module="stores"><ManageStoresPage /></RequireRole> },
      { path: "/orders", element: <RequireRole roles={["admin", "employee"]} module="orders"><ManageOrdersPage /></RequireRole> },
      { path: "/reviews", element: <RequireRole roles={["admin", "employee"]} module="reviews"><ManageReviewsPage /></RequireRole> },
      { path: "/commissions", element: <RequireRole roles={["admin", "employee"]} module="commissions"><ManageCommissionsPage /></RequireRole> },
      { path: "/vouchers", element: <RequireRole roles={["admin", "employee"]} module="vouchers"><ManageCouponsPage /></RequireRole> },
      { path: "/withdrawals", element: <RequireRole roles={["admin", "employee"]} module="withdrawals"><ManageWithdrawalsPage /></RequireRole> },
      { path: "/platform-funds", element: <RequireRole roles={["admin", "employee"]} module="platform_funds"><PlatformFundsPage /></RequireRole> },
      { path: "/settings", element: <SettingsPage /> },
    ],
  },
];
