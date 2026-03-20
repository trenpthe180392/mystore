import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom"; // Thêm useNavigate
import { getCart, updateCartItem } from "../services/CartService";
import "../assets/styles/CartPage.css";

function CartPage() {
  const [cartItems, setCartItems] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    loadCart();
  }, []);

  const loadCart = async () => {
    try {
      const data = await getCart();
      // Thêm thuộc tính selected: true mặc định cho tất cả item khi load
      const itemsWithSelection = data.map(item => ({ ...item, selected: true }));
      setCartItems(itemsWithSelection);
    } catch (error) {
      console.error("Lỗi load cart:", error);
    }
  };

  // Xử lý chọn/bỏ chọn từng item
  const handleSelectItem = (cartItemId) => {
    setCartItems(prev => prev.map(item => 
      item.cartItemId === cartItemId ? { ...item, selected: !item.selected } : item
    ));
  };

  const handleUpdateQuantity = async (cartItemId, newQuantity) => {
    if (newQuantity <= 0) return;
    try {
      await updateCartItem(cartItemId, newQuantity);
      setCartItems((prev) =>
        prev.map((item) =>
          item.cartItemId === cartItemId
            ? { ...item, quantity: newQuantity, subtotal: item.price * newQuantity }
            : item
        )
      );
    } catch (error) {
      alert("Không thể cập nhật số lượng");
    }
  };

  // Tính tổng tiền dựa trên các item ĐƯỢC CHỌN
  const getSelectedTotal = () => {
    return cartItems
      .filter(item => item.selected)
      .reduce((sum, item) => sum + item.subtotal, 0);
  };

  // Điều hướng sang trang Checkout
  const handleGoToCheckout = () => {
    const selectedItems = cartItems.filter(item => item.selected);
    if (selectedItems.length === 0) {
      alert("Vui lòng chọn ít nhất một sản phẩm để thanh toán!");
      return;
    }
    // Truyền dữ liệu sang trang Checkout qua state
    navigate('/checkout', { 
      state: { 
        selectedItems: selectedItems, 
        totalAmount: getSelectedTotal() 
      } 
    });
  };

  return (
    <div className="cart-container">
      <div className="section-header">
        <h2 className="section-title">Giỏ hàng của tôi</h2>
      </div>

      {cartItems.length === 0 ? (
        <div className="cart-empty">Giỏ hàng trống</div>
      ) : (
        <>
          <div className="cart-list">
            {cartItems.map((item) => (
              <div className={`cart-item ${item.selected ? 'selected' : ''}`} key={item.cartItemId}>
                {/* Checkbox chọn sản phẩm */}
                <div className="cart-selection">
                  <input 
                    type="checkbox" 
                    checked={item.selected} 
                    onChange={() => handleSelectItem(item.cartItemId)}
                  />
                </div>

                <img src={item.image} alt={item.title} className="cart-image" />

                <div className="cart-info">
                  <div className="cart-title">{item.title}</div>
                  <div className="cart-price">Đơn giá: {item.price.toLocaleString()} đ</div>
                  
                  <div className="cart-quantity">
                    <button onClick={() => handleUpdateQuantity(item.cartItemId, item.quantity - 1)}>-</button>
                    <span>{item.quantity}</span>
                    <button onClick={() => handleUpdateQuantity(item.cartItemId, item.quantity + 1)}>+</button>
                  </div>

                  <div className="cart-subtotal">
                    Thành tiền: {item.subtotal.toLocaleString()} đ
                  </div>
                </div>
              </div>
            ))}
          </div>

          <div className="cart-total-box">
            <div className="cart-total">
              Tổng thanh toán ({cartItems.filter(i => i.selected).length} sản phẩm): 
              <span className="total-amount"> {getSelectedTotal().toLocaleString()} đ</span>
            </div>
            <button className="btn-checkout" onClick={handleGoToCheckout}>
              Tiến hành thanh toán
            </button>
          </div>
        </>
      )}
    </div>
  );
}

export default CartPage;