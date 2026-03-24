import axiosInstance from "./axiosConfig"; // Hoặc axios tùy cách bạn đặt tên file config

// 1. Lấy tất cả tác giả để hiển thị lên Dropdown ở Header
export const getAllAuthors = async () => {
    try {
        const response = await axiosInstance.get("/api/authors");
        return response.data; // Trả về danh sách AuthorDTO [{id: 1, name: '...'}, ...]
    } catch (error) {
        console.error("Lỗi khi lấy danh sách tác giả:", error);
        throw error;
    }
};

// 2. Lấy danh sách sách của một tác giả cụ thể (Dùng khi nhấn vào tên tác giả)
export const getBooksByAuthor = async (authorId) => {
    try {
        const response = await axiosInstance.get(`/api/books/author/${authorId}`);
        return response.data; // Trả về danh sách sách của tác giả đó
    } catch (error) {
        console.error(`Lỗi khi lấy sách của tác giả ID ${authorId}:`, error);
        return [];
    }
};