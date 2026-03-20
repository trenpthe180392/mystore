import React, { useEffect, useState } from 'react';
import OrderService from '../services/OrderService';
import styles from '../assets/styles/OrderHistory.module.css';
import { Link } from 'react-router-dom';

const OrderHistory = () => {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const data = await OrderService.getOrderHistory();
                setOrders(data);
            } catch (error) {
                console.error("Lỗi khi lấy đơn hàng:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchOrders();
    }, []);

    if (loading) return <div className={styles.loading}>Đang tải đơn hàng...</div>;

    return (
        <div className={styles.container}>
            <h1 className={styles.title}>Đơn hàng của bạn</h1>
            {orders.length === 0 ? (
                <div className={styles.emptyState}>
                    <p>Bạn chưa có đơn hàng nào.</p>
                    <button onClick={() => window.location.href = '/'}>Tiếp tục mua sắm</button>
                </div>
            ) : (
                orders.map((order) => (
                    <div key={order.orderId} className={styles.orderCard}>
                        <div className={styles.orderHeader}>
                            <div className={styles.headerLeft}>
                                <div>
                                    <p className={styles.label}>NGÀY ĐẶT</p>
                                    <p className={styles.value}>{new Date(order.createdAt).toLocaleDateString('vi-VN')}</p>
                                </div>
                                <div>
                                    <p className={styles.label}>TỔNG TIỀN</p>
                                    <p className={styles.totalValue}>{order.totalAmount.toLocaleString()} đ</p>
                                </div>
                                <div>
                                    <p className={styles.label}>GIAO ĐẾN</p>
                                    <p className={styles.value}>{order.phoneNumber}</p>
                                </div>
                            </div>
                            <div className={styles.headerRight}>
                                <p className={styles.label}>
                                    {/* Sửa dòng này: Thêm Link xem chi tiết */}
                                    <Link to={`/orders/${order.orderId}`} className={styles.detailLink}>
                                        Xem chi tiết đơn hàng # {order.orderId}
                                    </Link>
                                </p>
                                <span className={`${styles.statusBadge} ${styles['status_' + order.status]}`}>
                                    {order.status}
                                </span>
                            </div>
                        </div>

                        <div className={styles.orderBody}>
                            {order.items.map((item, index) => (
                                <div key={index} className={styles.itemRow}>
                                    <img src={item.image || "/placeholder-book.png"} alt={item.title} className={styles.bookImage} />
                                    <div className={styles.itemInfo}>
                                        {/* Bạn cũng có thể bọc tiêu đề sách vào Link nếu muốn */}
                                        <h4>{item.title}</h4>
                                        <p>Số lượng: {item.quantity}</p>
                                        <p className={styles.price}>{item.price.toLocaleString()} đ</p>
                                    </div>
                                    <div className={styles.itemActions}>
                                        <button className={styles.buyAgainBtn}>Mua lại</button>
                                    </div>
                                </div>
                            ))}
                        </div>
                    </div>
                ))
            )}
        </div>
    );
};

export default OrderHistory;