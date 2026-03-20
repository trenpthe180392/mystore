import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "/", 
});

axiosInstance.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

// THÊM: Interceptor cho Response để xử lý lỗi 401 toàn cục
axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response && error.response.status === 401) {
      console.warn("Token hết hạn hoặc không hợp lệ. Đang đăng xuất...");
      localStorage.removeItem("accessToken"); // Xóa token "rác"
      window.location.href = "/login"; // Đẩy về trang đăng nhập
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;