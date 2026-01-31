import React, { useState } from 'react';
import { authService } from '../services/auth.service';
import InputField from '../components/InputField';
import '../assets/styles/login.css';

const LoginPage = () => {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    setError('');

    try {
      const data = await authService.login(email, password);
      // Lưu token vào LocalStorage hoặc Context
      localStorage.setItem('token', data.token);
      window.location.href = '/dashboard'; 
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
          label="Email" 
          type="email" 
          value={email} 
          onChange={(e) => setEmail(e.target.value)} 
          placeholder="Nhập email của bạn..."
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