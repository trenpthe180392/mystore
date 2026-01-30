import React, { useEffect, useState } from 'react';
import { getHomeBooks } from '../services/BookService';
import BookCard from '../components/BookCard';
import '../assets/styles/HomePage.css'; // Tạo file CSS này

const HomePage = () => {
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        setLoading(true);
        getHomeBooks()
            .then(data => {
                setBooks(data);
                setLoading(false);
            })
            .catch(() => setLoading(false));
    }, []);

    if (loading) return <div className="loader">Đang tải dữ liệu sách...</div>;

    return (
        <div className="homepage-container">
            <div className="section-header">
                <h2 className="section-title">Khám Phá Sách Mới</h2>
                <p className="section-subtitle">Những cuốn sách hay nhất vừa cập bến tiệm sách của bạn</p>
            </div>
            
            <div className="book-grid">
                {books.length > 0 ? (
                    books.map(book => <BookCard key={book.id} book={book} />)
                ) : (
                    <p>Hiện chưa có sách nào trong danh sách.</p>
                )}
            </div>
        </div>
    );
};

export default HomePage;