import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { addToCart, getCartCount } from '../services/CartService';
import "../assets/styles/Header.css";

const Header = () => {

  const [cartCount, setCartCount] = useState(0);

  const isLogin = localStorage.getItem("accessToken");

  useEffect(() => {

    const fetchCart = async () => {
      if (!isLogin) return;

      try {
        const count = await getCartCount();
        setCartCount(count);
      } catch (error) {
        console.error("Lỗi lấy cart:", error);
      }
    };

    fetchCart();

  }, [isLogin]);


  useEffect(() => {

    const handleCartUpdate = async () => {
      if (!isLogin) return;

      const count = await getCartCount();
      setCartCount(count);
    };

    window.addEventListener("cartUpdated", handleCartUpdate);

    return () => {
      window.removeEventListener("cartUpdated", handleCartUpdate);
    };

  }, [isLogin]);


  return (
    <header className="main-header">
      <div className="container">

        <div className="logo">
          <Link to="/">BookStore<span>.</span></Link>
        </div>

        <nav className="navbar">
          <ul>
            <li><Link to="/">Trang chủ</Link></li>
            <li><Link to="/books">Sách</Link></li>
            <li><Link to="/categories">Thể loại</Link></li>
            <li><Link to="/contact">Liên hệ</Link></li>

            <li>
              {isLogin ? (
                <button
                  onClick={() => {
                    localStorage.removeItem("accessToken");
                    window.location.reload();
                  }}
                >
                  Đăng xuất
                </button>
              ) : (
                <Link to="/login">Đăng nhập</Link>
              )}
            </li>

          </ul>
        </nav>

        <div className="header-actions">
          <button className="search-btn">🔍</button>

          <div className="cart-icon">
            <Link to="/cart">
              🛒
              {cartCount > 0 && (
                <span className="cart-count">{cartCount}</span>
              )}
            </Link>
          </div>

        </div>

      </div>
    </header>
  );
};

export default Header;