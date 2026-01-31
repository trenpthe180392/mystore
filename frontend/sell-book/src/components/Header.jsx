import React from 'react';
import { Link } from 'react-router-dom'; // Import Link ở đây
import '../assets/styles/Header.css';

const Header = () => {
  return (
    <header className="main-header">
      <div className="container">
        {/* Logo */}
        <div className="logo">
          {/* Thay href bằng to */}
          <Link to="/">BookStore<span>.</span></Link>
        </div>

        {/* Thanh điều hướng */}
        <nav className="navbar">
          <ul>
            <li><Link to="/">Trang chủ</Link></li>
            <li><Link to="/books">Sách</Link></li>
            <li><Link to="/categories">Thể loại</Link></li>
            <li><Link to="/contact">Liên hệ</Link></li>
            {/* Bạn có thể thêm nút Đăng nhập ở đây */}
            <li><Link to="/login">Đăng nhập</Link></li>
          </ul>
        </nav>

        {/* Các nút chức năng */}
        <div className="header-actions">
          <button className="search-btn">🔍</button>
          <div className="cart-icon">
            <Link to="/cart">
               🛒 <span className="cart-count">0</span>
            </Link>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;