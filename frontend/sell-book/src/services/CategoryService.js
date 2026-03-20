import axiosInstance from "./axiosConfig";

export const getAllCategories = async () => {
  try {
    const res = await axiosInstance.get("/api/categories");
    return res.data;
  } catch (error) {
    console.error("Không thể lấy danh sách thể loại:", error);
    return [];
  }
};