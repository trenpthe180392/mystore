const OrderItem = ({ item }) => (
  <div className="checkout-item-row">
    <div className="item-info">
      <span className="item-name">{item.bookTitle || item.title}</span>
      <span className="item-qty">Số lượng: {item.quantity}</span>
    </div>
    <span className="item-price">{(item.price * item.quantity).toLocaleString()}đ</span>
  </div>
);
export default OrderItem;