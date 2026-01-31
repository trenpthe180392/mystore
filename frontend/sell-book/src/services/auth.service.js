const API_URL = "http://localhost:8080/api/auth";

export const authService = {
  login: async (email, password) => {
    const response = await fetch(`${API_URL}/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });

    if (!response.ok) {
      throw new Error("Thông tin đăng nhập không chính xác");
    }

    // Trả về LoginResponseDTO (chứa Token, UserInfo)
    return await response.json();
  }
};