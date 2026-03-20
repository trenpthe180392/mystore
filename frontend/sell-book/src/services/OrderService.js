import axiosInstance from "./axiosConfig";

const OrderService = {
  // Đặt hàng
  placeOrder: async (orderData) => {
    try {
      const response = await axiosInstance.post("/api/orders/place", orderData);
      return response.data;
    } catch (error) {
      console.error("Lỗi khi đặt hàng:", error.response?.data || error.message);
      throw error;
    }
  },

  // Lấy danh sách lịch sử đơn hàng
  getOrderHistory: async () => {
    try {
      const response = await axiosInstance.get("/api/orders/my-orders");
      return response.data;
    } catch (error) {
      console.error("Lỗi khi lấy lịch sử đơn hàng:", error.response?.data || error.message);
      throw error;
    }
  },
  cancelOrder: async (orderId) => {
    try {
      const response = await axiosInstance.put(`/api/orders/${orderId}/cancel`);
      return response.data;
    } catch (error) {
      console.error("Lỗi khi hủy đơn hàng:", error.response?.data || error.message);
      throw error;
    }
  },

  // Lấy chi tiết 1 đơn hàng
  getOrderDetail: async (orderId) => {
    // Kiểm tra nếu orderId không hợp lệ trước khi gọi API
    if (!orderId) {
      console.error("OrderId không hợp lệ!");
      return null;
    }
    try {
      const response = await axiosInstance.get(`/api/orders/${orderId}`);
      console.log(`Dữ liệu chi tiết đơn hàng ${orderId}:`, response.data); // Debug dữ liệu
      return response.data;
    } catch (error) {
      console.error(`Lỗi khi lấy chi tiết đơn hàng ${orderId}:`, error.response?.data || error.message);
      throw error;
    }
  }

};

export default OrderService;