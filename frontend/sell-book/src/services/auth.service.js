const API_URL = "/api/auth";

export const authService = {
  login: async (username, password) => {

    const response = await fetch(`${API_URL}/login`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ username, password }),
    });

    if (!response.ok) {
      throw new Error("Thông tin đăng nhập không chính xác");
    }

    return await response.json();
  }
};