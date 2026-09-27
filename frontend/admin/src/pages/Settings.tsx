import { ToastContainer } from "react-toastify";
import AccountSettingsTab from "../components/settings/AccountSettingsTab";

export default function SettingsPage() {
  return (
    <div className="flex flex-col gap-5 px-4 py-6 sm:gap-6 sm:px-6 sm:py-8 lg:px-10 lg:py-10">
      <div>
        <h2 className="text-2xl font-bold text-gray-800 dark:text-white">Cài đặt</h2>
        <p className="text-sm text-gray-400">Quản lý thông tin tài khoản của bạn.</p>
      </div>

      <AccountSettingsTab />

      <ToastContainer />
    </div>
  );
}
