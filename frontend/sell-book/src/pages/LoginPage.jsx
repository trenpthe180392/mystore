import React, { useState } from 'react';
import { authService } from '../services/auth.service';
import InputField from '../components/InputField';
import '../assets/styles/login.css';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const data = await authService.login(username, password);
      // Lưu token vào LocalStorage hoặc Context
      // Xóa key cũ
    localStorage.removeItem("authToken");
    localStorage.removeItem("token");
    localStorage.removeItem("user");
      localStorage.setItem("accessToken", data.accessToken);
      localStorage.setItem("refreshToken", data.refreshToken);
      window.location.href = '/';
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <div className="login-container">
      <form className="login-form" onSubmit={handleLogin}>
        <h2>Đăng Nhập SellBook</h2>
        {error && <p className="error-msg">{error}</p>}

        <InputField
          label="UserName"
          type="text"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          placeholder="Nhập tên tài khoản của bạn..."
        />

        <InputField
          label="Mật khẩu"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          placeholder="********"
        />

        <button type="submit" className="login-btn">Đăng nhập</button>
      </form>
    </div>
  );
};

export default LoginPage;