import Dashboard from "../pages/Dashboard";
import LoginPage from "../pages/Login";
import ManageBrandsPage from "../pages/ManageBrands";
import ManageCategoriesPage from "../pages/ManageCategory";
import ManageEmployeePage from "../pages/ManageEmployee";
import ManageProductsPage from "../pages/ManageProducts";
import LayoutDefault from "../partials/layout";
import RequireRole from "../components/RequireRole";

export const routes = [
  {
    path: "/login",
    element: <LoginPage />,
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
    ],
  },
];
