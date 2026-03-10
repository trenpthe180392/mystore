import axiosInstance from "./axiosConfig";

export const addToCart = async (productId, quantity) => {
    try {

        const response = await axiosInstance.post("/api/cart/add", {
            productId: productId,
            quantity: quantity
        });

        return response.data;

    } catch (error) {

        console.error("Chi tiết lỗi API:", error);

        if (error.response) {

            if (error.response.status === 401) {
                alert("Vui lòng đăng nhập!");
                window.location.href = "/login";
                return;
            }

            throw error.response.data || "Lỗi từ server";
        }

        throw "Không thể kết nối server";
    }
};
export const getCartCount = async () => {
  const response = await axiosInstance.get("/api/cart/count");
  return response.data;
};