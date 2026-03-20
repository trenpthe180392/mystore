const OrderSummary = ({ totalAmount, loading, onPlaceOrder, error }) => (
  <div className="order-summary-card">
    <h3>Tổng kết đơn hàng</h3>
    <div className="summary-line">
      <span>Tạm tính:</span>
      <span>{totalAmount.toLocaleString()}đ</span>
    </div>
    <div className="summary-line">
      <span>Phí vận chuyển:</span>
      <span className="free-shipping">Miễn phí</span>
    </div>
    <hr />
    <div className="summary-line total">
      <span>Tổng cộng:</span>
      <span className="final-price">{totalAmount.toLocaleString()}đ</span>
    </div>
    
    {error && <p className="error-message">{error}</p>}
    
    <button 
      className="btn-amazon-style" 
      onClick={onPlaceOrder}
      disabled={loading}
    >
      {loading ? "Đang xử lý..." : "Đặt hàng"}
    </button>
  </div>
);
export default OrderSummary;