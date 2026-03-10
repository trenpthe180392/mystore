import axiosInstance from "./axiosConfig";

export const getHomeBooks = async () => {
  try {
    const res = await axiosInstance.get("/api/books/home");
    return res.data;
  } catch (error) {
    console.error("Can not fetch data:", error);
    return [];
  }
};

export const getBookById = async (id) => {
  try {
    const res = await axiosInstance.get(`/api/books/${id}`);
    return res.data;
  } catch (error) {
    console.error("Can not fetch data:", error);
    return null;
  }
};