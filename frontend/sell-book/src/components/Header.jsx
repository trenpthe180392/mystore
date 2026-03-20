import React, { useState, useEffect, useCallback } from "react";
import { Link, useNavigate } from "react-router-dom";
import { getCartCount } from '../services/CartService';
import { getAllCategories } from '../services/CategoryService';
import "../assets/styles/Header.css";

const Header = () => {
  const [cartCount, setCartCount] = useState(0);
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate();
  
  // Dùng useState để giữ trạng thái login ổn định qua các lần render
  const [isLogin, setIsLogin] = useState(!!localStorage.getItem("accessToken"));

  // 1. Fetch danh sách thể loại (Chỉ chạy 1 lần duy nhất khi Mount)
  useEffect(() => {
    let isMounted = true;
    const fetchCategories = async () => {
      try {
        const data = await getAllCategories();
        if (isMounted && data) {
          setCategories(Array.isArray(data) ? data : []);
        }
      } catch (err) {
        console.error("Lỗi lấy danh sách thể loại:", err);
      }
    };

    fetchCategories();
    return () => { isMounted = false; };
  }, []);

  // 2. Hàm cập nhật giỏ hàng (Dùng useCallback để tránh tạo mới hàm vô ích)
  const updateCart = useCallback(async () => {
    if (!isLogin) {
      setCartCount(0);
      return;
    }
    try {
      const count = await getCartCount();
      setCartCount(count || 0);
    } catch (error) {
      console.error("Lỗi cập nhật giỏ hàng:", error);
    }
  }, [isLogin]);

  // 3. Theo dõi giỏ hàng và sự kiện Login/Logout
  useEffect(() => {
    updateCart();

    const handleCartUpdate = () => updateCart();
    
    // Lắng nghe sự kiện custom khi thêm vào giỏ hàng thành công
    window.addEventListener("cartUpdated", handleCartUpdate);
    
    return () => {
      window.removeEventListener("cartUpdated", handleCartUpdate);
    };
  }, [updateCart]);

  // Kiểm tra login liên tục từ localStorage (Tránh việc token bị xóa ở tab khác)
  useEffect(() => {
    const checkAuth = () => {
      const token = localStorage.getItem("accessToken");
      if (!!token !== isLogin) {
        setIsLogin(!!token);
      }
    };
    window.addEventListener('storage', checkAuth);
    return () => window.removeEventListener('storage', checkAuth);
  }, [isLogin]);

  const handleLogout = () => {
    localStorage.removeItem("accessToken");
    localStorage.removeItem("refreshToken"); // Xóa hết cho sạch
    setIsLogin(false);
    setCartCount(0);
    navigate("/login");
    // Thay vì reload(), ta điều hướng và update state là đủ
  };

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

            <li className="dropdown">
              <span className="dropbtn">Thể loại ▾</span>
              <div className="dropdown-content">
                {categories.length > 0 ? (
                  categories.map((cat) => (
                    <Link key={cat.id} to={`/?categoryId=${cat.id}`}>
                      {cat.name}
                    </Link>
                  ))
                ) : (
                  <span className="loading-text">Đang tải...</span>
                )}
              </div>
            </li>

            <li><Link to="/contact">Liên hệ</Link></li>

            <li className="auth-links">
              {isLogin ? (
                <>
                  <Link to="/orders">Đơn hàng</Link>
                  <button onClick={handleLogout} className="logout-btn">Đăng xuất</button>
                </>
              ) : (
                <Link to="/login">Đăng nhập</Link>
              )}
            </li>
          </ul>
        </nav>

        <div className="header-actions">
          <button className="search-btn" title="Tìm kiếm">🔍</button>
          <div className="cart-icon">
            <Link 
              to="/cart" 
              onClick={(e) => {
                if (!isLogin) {
                  e.preventDefault();
                  navigate("/login");
                }
              }}
            >
              <span className="icon">🛒</span>
              {cartCount > 0 && <span className="cart-count">{cartCount}</span>}
            </Link>
          </div>
        </div>
      </div>
    </header>
  );
};

export default Header;