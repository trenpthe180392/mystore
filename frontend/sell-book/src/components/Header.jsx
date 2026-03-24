import React, { useState, useEffect, useCallback } from "react";
import { Link, useNavigate } from "react-router-dom";
import { getCartCount } from '../services/CartService';
import { getAllCategories } from '../services/CategoryService';
import { useLocation } from "react-router-dom";
import "../assets/styles/Header.css";

const Header = () => {
  const location = useLocation();
  const [cartCount, setCartCount] = useState(0);
  const [categories, setCategories] = useState([]);
  const navigate = useNavigate();
  const isActive = (path) => location.pathname === path;

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
  const [searchTerm, setSearchTerm] = useState("");

  const handleSearch = (e) => {
    e.preventDefault();
    if (searchTerm.trim()) {
      // encodeURIComponent giúp mã hóa các khoảng trắng hoặc ký tự đặc biệt
      navigate(`/?search=${encodeURIComponent(searchTerm.trim())}&page=1`);
    } else {
      // Nếu người dùng xóa trắng ô tìm kiếm và ấn Enter -> Hiển thị tất cả sách
      navigate(`/`);
    }
  };

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
            {/* Trang chủ: Chỉ active khi đường dẫn là '/' và không có query tìm kiếm */}
            <li>
              <Link to="/" className={isActive("/") && !location.search ? "active" : ""}>
                Trang chủ
              </Link>
            </li>

            {/* Tác giả: Active khi đường dẫn là /authors */}
            <li>
              <Link to="/authors" className={isActive("/authors") ? "active" : ""}>
                Tác giả
              </Link>
            </li>

            {/* Dropdown Thể loại: Tự động đổi màu xanh nếu URL chứa categoryId */}
            <li className="dropdown">
              <span className={`dropbtn ${location.search.includes("categoryId") ? "active" : ""}`}>
                Thể loại <i className="arrow-down">▾</i>
              </span>
              <div className="dropdown-content">
                {categories.length > 0 ? (
                  categories.map((cat) => (
                    <Link
                      key={cat.id}
                      to={`/?categoryId=${cat.id}`}
                      className={location.search.includes(`categoryId=${cat.id}`) ? "active" : ""}
                    >
                      {cat.name}
                    </Link>
                  ))
                ) : (
                  <span className="loading-text">Đang tải...</span>
                )}
              </div>
            </li>

            {/* Liên hệ */}
            <li>
              <Link to="/contact" className={isActive("/contact") ? "active" : ""}>
                Liên hệ
              </Link>
            </li>

            {/* Auth links: Gom nhóm logic login/logout */}
            <li className="auth-links">
              {isLogin ? (
                <div className="user-nav">
                  <Link to="/orders" className={isActive("/orders") ? "active" : ""}>
                    Đơn hàng
                  </Link>
                  <button onClick={handleLogout} className="logout-btn">
                    Đăng xuất
                  </button>
                </div>
              ) : (
                <Link to="/login" className={isActive("/login") ? "active" : ""}>
                  Đăng nhập
                </Link>
              )}
            </li>
          </ul>
        </nav>

        <div className="header-actions">
          <form onSubmit={handleSearch} className="search-form">
            <input
              type="text"
              placeholder="Tìm tên sách..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button type="submit" className="search-btn">🔍</button>
          </form>
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