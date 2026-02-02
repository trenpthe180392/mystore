import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../assets/styles/BookCard.css';

const BookCard = ({ book }) => {
    const navigate = useNavigate();
    const defaultImage = "https://via.placeholder.com/150x200?text=No+Image";

    const handleCardClick = () => {
        navigate(`/books/${book.id}`);
    };

    return (
        <div className="book-card-container" onClick={handleCardClick} >
            {/* 1. Hình ảnh sách */}
            <div className="book-card-image-container">
                <img 
                    src={book.imageUrl || defaultImage} 
                    alt={book.title} 
                    className="book-card-image" 
                />
            </div>

            {/* 2. Thông tin chính */}
            <div className="book-card-content">
                <h3 className="book-card-title" title={book.title}>
                    {book.title}
                </h3>
                
                <p className="book-card-author">Tác giả: <strong>{book.authorName}</strong></p>
                <p className="book-card-category">Thể loại: {book.categoryName}</p>

                {/* 3. Giá tiền */}
                <div className="book-card-price-container">
                    <span className="book-card-sale-price">
                        {book.salePrice.toLocaleString()}đ
                    </span>
                    {book.basePrice > book.salePrice && (
                        <span className="book-card-base-price">
                            {book.basePrice.toLocaleString()}đ
                        </span>
                    )}
                </div>
            </div>
        </div>
    );
};

export default BookCard;