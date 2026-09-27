import axios from "axios";

export const BACKEND_URL =
  (import.meta.env.VITE_BACKEND_URL as string | undefined) ??
  (import.meta.env.PROD ? "" : "http://localhost:8000");

// JWT nằm trong cookie HttpOnly nên mọi request phải gửi kèm cookie.
const api = axios.create({
  baseURL: `${BACKEND_URL}/api`,
  withCredentials: true,
  headers: { Accept: "application/json" },
});

export default api;
