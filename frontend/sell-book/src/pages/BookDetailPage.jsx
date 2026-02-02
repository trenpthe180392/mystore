import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { getBookById } from '../services/BookService';
import { addToCart } from '../services/CartService'; 
import '../assets/styles/BookDetailPage.css';

const BookDetailPage = () => {
    const { id } = useParams();
    const [book, setBook] = useState(null);
    const [loading, setLoading] = useState(true);
    const [quantity, setQuantity] = useState(1);

    useEffect(() => {
        const fetchDetail = async () => {
            try {
                setLoading(true);
                const data = await getBookById(id);
                setBook(data);
            } catch (error) {
                console.error("Lỗi khi tải chi tiết:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchDetail();
    }, [id]);

    const handleAddToCart = async () => {
        try {
            const message = await addToCart(book.id, quantity);
            alert(message);
            setBook({ ...book, stockQuantity: book.stockQuantity - quantity });
            setQuantity(1);
        } catch (error) {
            alert(error);
        }
    };

    if (loading) return <div className="loading">Đang tải dữ liệu...</div>;
    if (!book) return <div className="error">Không tìm thấy sách!</div>;

    return (
        <div className="book-detail-container">
            <div className="book-main-section">
                {/* Ảnh sách */}
                <div className="book-image-column">
                    <img 
                        src={book.imageUrl || 'https://via.placeholder.com/300x450'} 
                        alt={book.title} 
                        className="book-image"
                    />
                </div>

                {/* Thông tin mua hàng */}
                <div className="book-info-column">
                    <h1 className="book-title">{book.title}</h1>
                    <p className="book-author">
                        <strong>Tác giả:</strong> {book.authorNames?.join(', ') || 'Đang cập nhật'}
                    </p>

                    <div className="price-box">
                        <span className="sale-price">{book.salePrice.toLocaleString()}đ</span>
                        {book.basePrice > book.salePrice && (
                            <span className="base-price">{book.basePrice.toLocaleString()}đ</span>
                        )}
                    </div>

                    <p><strong>Thể loại:</strong> {book.categoryName}</p>
                    <p><strong>Nhà xuất bản:</strong> {book.publisherName}</p>
                    <p><strong>Tình trạng:</strong> {book.stockQuantity > 0 ? `Còn hàng (${book.stockQuantity})` : 'Hết hàng'}</p>

                    <div className="cart-controls">
                        <div className="quantity-selector">
                            <button 
                                className="quantity-btn"
                                onClick={() => setQuantity(Math.max(1, quantity - 1))}
                            >-</button>
                            <input className="quantity-input" type="text" value={quantity} readOnly />
                            <button 
                                className="quantity-btn"
                                onClick={() => setQuantity(Math.min(book.stockQuantity, quantity + 1))}
                                disabled={quantity >= book.stockQuantity}
                            >+</button>
                        </div>

                        <button 
                            className="add-to-cart-btn"
                            onClick={handleAddToCart}
                            disabled={book.stockQuantity <= 0}
                        >
                            {book.stockQuantity > 0 ? 'Thêm vào giỏ hàng' : 'Hết hàng'}
                        </button>
                    </div>
                </div>
            </div>

            <hr style={{ margin: '40px 0', border: '0', borderTop: '1px solid #eee' }} />

            <div className="detail-grid">
                {/* Thông số kỹ thuật - Đầy đủ tất cả attribute */}
                <div>
                    <h3>Thông tin chi tiết</h3>
                    <table className="spec-table">
                        <tbody>
                            <tr><td className="spec-label">ISBN-10</td><td>{book.isbn10 || 'N/A'}</td></tr>
                            <tr><td className="spec-label">ISBN-13</td><td>{book.isbn13 || 'N/A'}</td></tr>
                            <tr><td className="spec-label">Số trang</td><td>{book.pages}</td></tr>
                            <tr><td className="spec-label">Ngôn ngữ</td><td>{book.language}</td></tr>
                            <tr><td className="spec-label">Trọng lượng</td><td>{book.weight}g</td></tr>
                            <tr><td className="spec-label">Kích thước</td><td>{book.dimensions}</td></tr>
                            <tr><td className="spec-label">Ngày xuất bản</td><td>{book.publishDate || 'Đang cập nhật'}</td></tr>
                        </tbody>
                    </table>
                </div>

                {/* Tóm tắt */}
                <div>
                    <h3>Tóm tắt nội dung</h3>
                    <p style={{ fontStyle: 'italic', color: '#555' }}>{book.summary}</p>
                </div>
            </div>

            {/* Mô tả dài */}
            <div className="description-section">
                <h3>Mô tả sản phẩm</h3>
                <p>{book.description}</p>
            </div>
        </div>
    );
};

export default BookDetailPage;