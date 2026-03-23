import React from "react";
import { Link } from "react-router-dom";
import "../assets/styles/Footer.css";

const Footer = () => {
  return (
    <footer className="main-footer">
      <div className="container">
        <div className="footer-content">
          {/* Cột 1: Giới thiệu */}
          <div className="footer-section about">
            <div className="logo">
              <Link to="/">BookStore<span>.</span></Link>
            </div>
            <p>
              Khám phá thế giới tri thức vô tận tại BookStore. Chúng tôi cung cấp những đầu sách mới nhất, chất lượng nhất dành cho mọi lứa tuổi.
            </p>
            <div className="social-links">
              <a href="#" title="Facebook">FB</a>
              <a href="#" title="Instagram">IG</a>
              <a href="#" title="Twitter">TW</a>
            </div>
          </div>

          {/* Cột 3: Liên hệ & Newsletter */}
          <div className="footer-section contact-info">
            <h3>Liên hệ</h3>
            <p>📍 123 Đường ABC, Quận 1, TP. Hồ Chí Minh</p>
            <p>📞 +84 123 456 789</p>
            <p>✉️ support@bookstore.com</p>
          </div>
        </div>

        <div className="footer-bottom">
          <p>&copy; {new Date().getFullYear()} BookStore. Tất cả quyền được bảo lưu.</p>
        </div>
      </div>
    </footer>
  );
};

export default Footer;