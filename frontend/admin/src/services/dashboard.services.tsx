import api from "../api/axios";
import { DashboardSummary } from "../types/dashboard.types";

export const getDashboardSummary = async (): Promise<DashboardSummary> => {
  const res = await api.get("admin/dashboard");
  return res.data.data;
};
