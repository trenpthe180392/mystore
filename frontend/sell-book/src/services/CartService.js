import axios from 'axios';

const API_URL = "http://localhost:8080/api/cart"; // Thay đổi theo đúng path của bạn

export const addToCart = async (productId, quantity) => {
    try {
        const response = await axios.post(`${API_URL}/add`, {
            productId: productId, // Phải khớp với tên field trong CartRequestDTO của Java
            quantity: quantity
        });
        return response.data; // Trả về text "Thêm thành công"
    } catch (error) {
        // Log lỗi ra console để debug
        console.error("Chi tiết lỗi API:", error.response?.data);
        
        // Ném lỗi cụ thể từ Backend về cho Component
        throw error.response?.data || "Không thể kết nối đến server";
    }
};