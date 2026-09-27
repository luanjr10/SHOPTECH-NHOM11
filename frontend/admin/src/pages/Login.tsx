import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Eye, EyeOff, Lock, LayoutDashboard, ShieldCheck, User, Zap } from "lucide-react";
import { useAuth } from "../context/AuthContext";

export default function LoginPage() {
  const { login, logout } = useAuth();
  const navigate = useNavigate();

  const [loginId, setLoginId] = useState("");
  const [password, setPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [submitting, setSubmitting] = useState(false);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError(null);
    setSubmitting(true);
    try {
      const user = await login(loginId, password);
      if (user.role !== "admin" && user.role !== "employee") {
        await logout();
        setError("Tài khoản này không có quyền truy cập trang quản lý");
        return;
      }
      navigate("/");
    } catch (err: any) {
      setError(err?.response?.data?.message ?? "Đăng nhập thất bại");
    } finally {
      setSubmitting(false);
    }
  };

  return (
    <div className="flex min-h-screen bg-gray-50 dark:bg-gray-950">
      <div className="relative hidden w-1/2 flex-col justify-between overflow-hidden bg-gradient-to-br from-violet-700 via-violet-600 to-indigo-700 p-12 text-white md:flex">
        <div className="pointer-events-none absolute -top-24 -right-24 size-96 rounded-full bg-white/10 blur-3xl" />
        <div className="pointer-events-none absolute -bottom-32 -left-16 size-96 rounded-full bg-indigo-400/20 blur-3xl" />

        <div className="relative flex items-center gap-2 text-lg font-bold">
          <div className="flex size-9 items-center justify-center rounded-xl bg-white/15">
            <LayoutDashboard className="size-5" />
          </div>
          ShopTech Dashboard
        </div>

        <div className="relative flex flex-col gap-6">
          <h1 className="text-4xl leading-tight font-bold">
            Quản trị &amp; vận hành sàn thương mại điện tử của bạn.
          </h1>
          <p className="max-w-md text-violet-100">
            Theo dõi doanh thu, quản lý sản phẩm, danh mục, thương hiệu và phân quyền nhân viên
            tại một nơi duy nhất.
          </p>
          <div className="flex flex-col gap-3 text-sm text-violet-100">
            <div className="flex items-center gap-2.5">
              <ShieldCheck className="size-4.5 shrink-0" /> Phân quyền chi tiết theo từng chức năng
            </div>
            <div className="flex items-center gap-2.5">
              <Zap className="size-4.5 shrink-0" /> Vận hành nhanh — sản phẩm, danh mục, thương hiệu
            </div>
          </div>
        </div>

        <p className="relative text-xs text-violet-200/70">© {new Date().getFullYear()} ShopTech</p>
      </div>

      <div className="flex w-full flex-1 items-center justify-center px-4 py-10 md:w-1/2">
        <div className="w-full max-w-sm">
          <div className="mb-8 flex flex-col items-center gap-2 text-center md:items-start md:text-left">
            <div className="flex size-10 items-center justify-center rounded-xl bg-violet-600 text-white md:hidden">
              <LayoutDashboard className="size-5" />
            </div>
            <h2 className="text-2xl font-bold text-gray-800 dark:text-white">Đăng nhập</h2>
            <p className="text-sm text-gray-400">Dành cho quản trị viên và nhân viên</p>
          </div>

          {error && (
            <div className="mb-4 rounded-lg bg-rose-50 px-3 py-2 text-sm text-rose-600 dark:bg-rose-500/10 dark:text-rose-400">
              {error}
            </div>
          )}

          <form onSubmit={handleSubmit} className="flex flex-col gap-4">
            <div>
              <label className="mb-1 block text-sm text-gray-600 dark:text-gray-300">
                Tên đăng nhập hoặc email
              </label>
              <div className="relative">
                <User className="absolute top-1/2 left-3 size-4 -translate-y-1/2 text-gray-400" />
                <input
                  value={loginId}
                  onChange={(e) => setLoginId(e.target.value)}
                  required
                  autoFocus
                  placeholder="admin"
                  className="w-full rounded-xl border border-gray-200 py-2.5 pr-3 pl-9 text-sm text-gray-800 outline-none focus:border-violet-500 focus:ring-2 focus:ring-violet-500/20 dark:border-gray-700 dark:bg-gray-900 dark:text-gray-100"
                />
              </div>
            </div>

            <div>
              <label className="mb-1 block text-sm text-gray-600 dark:text-gray-300">Mật khẩu</label>
              <div className="relative">
                <Lock className="absolute top-1/2 left-3 size-4 -translate-y-1/2 text-gray-400" />
                <input
                  type={showPassword ? "text" : "password"}
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                  placeholder="••••••••"
                  className="w-full rounded-xl border border-gray-200 py-2.5 pr-10 pl-9 text-sm text-gray-800 outline-none focus:border-violet-500 focus:ring-2 focus:ring-violet-500/20 dark:border-gray-700 dark:bg-gray-900 dark:text-gray-100"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword((v) => !v)}
                  className="absolute top-1/2 right-3 -translate-y-1/2 text-gray-400 hover:text-gray-600 dark:hover:text-gray-300"
                  tabIndex={-1}
                >
                  {showPassword ? <EyeOff className="size-4" /> : <Eye className="size-4" />}
                </button>
              </div>
            </div>

            <button
              type="submit"
              disabled={submitting}
              className="mt-1 w-full rounded-xl bg-violet-600 py-2.5 text-sm font-semibold text-white transition-colors hover:bg-violet-500 disabled:opacity-60"
            >
              {submitting ? "Đang đăng nhập..." : "Đăng nhập"}
            </button>
          </form>


          <p className="mt-6 text-center text-xs text-gray-400">
            Chỉ dành cho tài khoản quản trị viên và nhân viên đã được cấp quyền.
          </p>
        </div>
      </div>
    </div>
  );
}
