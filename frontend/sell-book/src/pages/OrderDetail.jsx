import React, { useEffect, useState } from 'react';
import { useParams, useNavigate } from 'react-router-dom'; // Thêm useNavigate nếu muốn quay lại
import OrderService from '../services/OrderService';
import styles from "../assets/styles/OrderDetail.module.css";

const OrderDetail = () => {
    const { orderId } = useParams();
    const navigate = useNavigate();
    const [order, setOrder] = useState(null);
    const [loading, setLoading] = useState(true);
    const [isCancelling, setIsCancelling] = useState(false);

    useEffect(() => {
        const fetchDetails = async () => {
            try {
                setLoading(true);
                const data = await OrderService.getOrderDetail(orderId);
                setOrder(data);
            } catch (error) {
                console.error("Lỗi khi tải chi tiết đơn hàng:", error);
            } finally {
                setLoading(false);
            }
        };
        if (orderId) fetchDetails();
    }, [orderId]);

    const handleCancelOrder = async () => {
        // Sử dụng confirm để tránh bấm nhầm
        const confirmCancel = window.confirm("Bạn có chắc chắn muốn hủy đơn hàng này không? Lưu ý: Hành động này không thể hoàn tác.");
        
        if (confirmCancel) {
            try {
                setIsCancelling(true);
                const updatedOrder = await OrderService.cancelOrder(orderId);
                
                // Cập nhật lại state local để UI thay đổi ngay lập tức
                setOrder(updatedOrder); 
                alert("Đơn hàng đã được hủy thành công.");
            } catch (error) {
                // Hiển thị lỗi từ backend (ví dụ: "Đơn hàng đang giao không thể hủy")
                const errorMsg = error.response?.data?.message || "Không thể hủy đơn hàng vào lúc này. Vui lòng thử lại sau.";
                alert(errorMsg);
            } finally {
                setIsCancelling(false);
            }
        }
    };

    if (loading) return <div className={styles.status}>Đang tải dữ liệu...</div>;
    if (!order) return (
        <div className={styles.status}>
            <p>Đơn hàng không tồn tại hoặc đã bị xóa.</p>
            <button onClick={() => navigate('/orders')}>Quay lại danh sách</button>
        </div>
    );

    return (
        <div className={styles.detailContainer}>
            <div className={styles.header}>
                <div className={styles.titleSection}>
                    <h2>Chi tiết đơn hàng #{order.orderId}</h2>
                    {/* Badge trạng thái thay đổi màu dựa trên giá trị status */}
                    <span className={`${styles.statusBadge} ${styles[order.status.toLowerCase()]}`}>
                        {order.status === 'PENDING' ? 'Chờ xử lý' : 
                         order.status === 'CANCELLED' ? 'Đã hủy' : 
                         order.status === 'SHIPPING' ? 'Đang giao' : 'Hoàn thành'}
                    </span>
                </div>

                {/* Chỉ hiện nút Hủy nếu đơn hàng đang ở trạng thái PENDING */}
                {order.status === 'PENDING' && (
                    <button
                        className={styles.cancelBtn}
                        onClick={handleCancelOrder}
                        disabled={isCancelling}
                    >
                        {isCancelling ? "Đang xử lý..." : "Hủy đơn hàng"}
                    </button>
                )}
            </div>

            <div className={styles.infoGrid}>
                <div className={styles.infoBlock}>
                    <h4><i className="fa fa-map-marker"></i> Thông tin nhận hàng</h4>
                    <p><strong>Người nhận:</strong> {order.customerName || "Khách hàng"}</p>
                    <p><strong>Điện thoại:</strong> {order.phoneNumber}</p>
                    <p><strong>Địa chỉ:</strong> {order.shippingAddress}</p>
                </div>
                <div className={styles.infoBlock}>
                    <h4><i className="fa fa-credit-card"></i> Thanh toán & Thời gian</h4>
                    <p><strong>Phương thức:</strong> {order.paymentMethod === 'COD' ? 'Thanh toán khi nhận hàng' : order.paymentMethod}</p>
                    <p><strong>Ngày đặt:</strong> {order.createdAt ? new Date(order.createdAt).toLocaleString('vi-VN') : "Đang cập nhật"}</p>
                </div>
            </div>

            <div className={styles.productsTable}>
                <h4>Danh sách sản phẩm</h4>
                {order.items && order.items.length > 0 ? (
                    order.items.map((item, index) => (
                        <div key={index} className={styles.productRow}>
                            <div className={styles.imgWrapper}>
                                <img src={item.image || "/placeholder-book.png"} alt={item.title} />
                            </div>
                            <div className={styles.productInfo}>
                                <h5>{item.title}</h5>
                                <p>Số lượng: x{item.quantity}</p>
                                <p className={styles.mobilePrice}>{item.price.toLocaleString()}đ</p>
                            </div>
                            <div className={styles.productPrice}>
                                {item.price.toLocaleString()}đ
                            </div>
                        </div>
                    ))
                ) : (
                    <p className={styles.emptyMsg}>Không có dữ liệu sản phẩm.</p>
                )}
                
                <div className={styles.totalSection}>
                    <div className={styles.totalRow}>
                        <span>Tổng tiền hàng:</span>
                        <span>{order.totalAmount?.toLocaleString()}đ</span>
                    </div>
                    <div className={styles.totalRow}>
                        <span>Phí vận chuyển:</span>
                        <span>0đ</span>
                    </div>
                    <div className={`${styles.totalRow} ${styles.finalTotal}`}>
                        <span>Tổng thanh toán:</span>
                        <span>{order.totalAmount?.toLocaleString()}đ</span>
                    </div>
                </div>
            </div>
            
            <button className={styles.backBtn} onClick={() => navigate('/orders')}>
                ← Quay lại danh sách đơn hàng
            </button>
        </div>
    );
};

export default OrderDetail;