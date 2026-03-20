import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom'; // Thêm cái này
import { getHomeBooks, getBooksByCategory } from '../services/BookService'; // Thêm hàm get theo category
import BookCard from '../components/BookCard';
import '../assets/styles/HomePage.css';

const HomePage = () => {
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(true);
    
    // 1. Lấy query parameters từ URL
    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    const categoryId = queryParams.get("categoryId");

    useEffect(() => {
        const fetchBooks = async () => {
            setLoading(true);
            try {
                let data;
                if (categoryId) {
                    // Nếu có categoryId trên URL -> Gọi API lọc theo loại
                    data = await getBooksByCategory(categoryId);
                } else {
                    // Nếu không có -> Gọi API trang chủ như bình thường
                    data = await getHomeBooks();
                }
                setBooks(data);
            } catch (error) {
                console.error("Lỗi tải sách:", error);
            } finally {
                setLoading(false);
            }
        };

        fetchBooks();
    }, [categoryId]);

    if (loading) return <div className="loader">Đang tải dữ liệu sách...</div>;

    return (
        <div className="homepage-container">
            <div className="section-header">
                <h2 className="section-title">
                    {categoryId ? "Kết Quả Lọc Thể Loại" : "Khám Phá Sách Mới"}
                </h2>
                <p className="section-subtitle">
                    {categoryId 
                        ? `Tìm thấy ${books.length} cuốn sách phù hợp` 
                        : "Những cuốn sách hay nhất vừa cập bến tiệm sách của bạn"}
                </p>
            </div>
            
            <div className="book-grid">
                {books.length > 0 ? (
                    books.map(book => <BookCard key={book.id} book={book} />)
                ) : (
                    <p className="empty-msg">Hiện chưa có sách nào trong danh sách này.</p>
                )}
            </div>
        </div>
    );
};

export default HomePage;