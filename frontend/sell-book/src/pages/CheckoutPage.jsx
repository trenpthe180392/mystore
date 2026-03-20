import React, { useState, useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import OrderService from '../services/OrderService';
import OrderItem from '../components/checkout/OrderItem';
import OrderSummary from '../components/checkout/OrderSummary';
import '../assets/styles/CheckoutPage.css';
import AddressSelector from '../components/checkout/AddressSelector';

const CheckoutPage = () => {
    const navigate = useNavigate();
    const location = useLocation();
    const { selectedItems = [], totalAmount = 0 } = location.state || {};

    // KHAI BÁO CÁC STATE (Lỗi của bạn nằm ở đây - thiếu phoneNumber)
    const [shippingAddress, setShippingAddress] = useState("");
    const [phoneNumber, setPhoneNumber] = useState(""); // Đã thêm
    const [paymentMethod, setPaymentMethod] = useState("COD");
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    useEffect(() => {
        if (selectedItems.length === 0) navigate('/cart');
    }, [selectedItems, navigate]);

    const handlePlaceOrder = async () => {
        if (!shippingAddress.trim() || !phoneNumber.trim()) {
            setError("Vui lòng nhập đầy đủ số điện thoại và địa chỉ!");
            return;
        }

        setLoading(true);
        try {
            const orderData = {
                cartItemIds: selectedItems.map(item => item.cartItemId),
                shippingAddress,
                phoneNumber, // Gửi SĐT lên backend
                paymentMethod
            };
            
            await OrderService.placeOrder(orderData);

            window.dispatchEvent(new Event("cartUpdated"));
            alert("Chúc mừng! Đơn hàng đã được đặt thành công.");
            navigate('/orders'); // Chuyển về trang lịch sử
        } catch (err) {
            setError(err.response?.data?.message || "Đặt hàng thất bại!");
        } finally {
            setLoading(false);
        }
    };

    return (
        <div className="amazon-checkout-container">
            <h1 className="checkout-title">Thanh toán ({selectedItems.length} mặt hàng)</h1>

            <div className="checkout-layout">
                <div className="checkout-main">
                    <section className="checkout-section">
                        <h3>1. Thông tin người nhận</h3>
                        {/* Thêm ô nhập số điện thoại */}
                        <div style={{ marginBottom: '15px' }}>
                            <label style={{ display: 'block', marginBottom: '5px', fontWeight: 'bold' }}>
                                Số điện thoại:
                            </label>
                            <input 
                                type="text" 
                                className="form-control"
                                style={{ width: '100%', padding: '8px', borderRadius: '4px', border: '1px solid #ddd' }}
                                value={phoneNumber}
                                onChange={(e) => setPhoneNumber(e.target.value)}
                                placeholder="Ví dụ: 0912345xxx"
                            />
                        </div>

                        <AddressSelector onAddressFinal={(fullAddr) => setShippingAddress(fullAddr)} />
                        
                        {shippingAddress && (
                            <p className="address-preview"><strong>Giao tới:</strong> {shippingAddress}</p>
                        )}
                    </section>

                    <section className="checkout-section">
                        <h3>2. Phương thức thanh toán</h3>
                        <div className="payment-options">
                            <label className="radio-container">
                                <input type="radio" checked readOnly /> Thanh toán khi nhận hàng (COD)
                            </label>
                        </div>
                    </section>

                    <section className="checkout-section">
                        <h3>3. Kiểm tra sản phẩm</h3>
                        <div className="checkout-items-list">
                            {selectedItems.map(item => <OrderItem key={item.cartItemId} item={item} />)}
                        </div>
                    </section>
                </div>

                <div className="checkout-sidebar">
                    <OrderSummary
                        totalAmount={totalAmount}
                        loading={loading}
                        onPlaceOrder={handlePlaceOrder}
                        error={error}
                    />
                </div>
            </div>
        </div>
    );
};

export default CheckoutPage;