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
export const getBooksByCategory = async (categoryId) => {
  try {
    const res = await axiosInstance.get(`/api/books/category/${categoryId}`);
    return res.data;
  } catch (error) {
    console.error(`Can not fetch books for category ${categoryId}:`, error);
    return [];
  }
};
export const getBooksByAuthor = async (authorId) => {
    try {
        const res = await axiosInstance.get(`/api/books/author/${authorId}`);
        return res.data;
    } catch (error) {
        console.error(`Lỗi tải sách của tác giả ${authorId}:`, error);
        return [];
    }
};