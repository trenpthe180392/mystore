import React from 'react';
import '../assets/styles/Header.css';

const Header = () => {
  return (
    <header className="main-header">
      <div className="container">
        {/* Logo */}
        <div className="logo">
          <a href="/">BookStore<span>.</span></a>
        </div>

        {/* Thanh điều hướng */}
        <nav className="navbar">
          <ul>
            <li><a href="/">Trang chủ</a></li>
            <li><a href="/books">Sách</a></li>
            <li><a href="/categories">Thể loại</a></li>
            <li><a href="/contact">Liên hệ</a></li>
          </ul>
        </nav>

        {/* Các nút chức năng (Giỏ hàng, Tìm kiếm) */}
        <div className="header-actions">
          <button className="search-btn">🔍</button>
          <div className="cart-icon">
            🛒 <span className="cart-count">0</span>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;