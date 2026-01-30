const BookCard = ({ book }) => {
    // Xử lý ảnh mặc định nếu imageUrl bị null
    const defaultImage = "https://via.placeholder.com/150x200?text=No+Image";

    return (
        <div style={cardStyle}>
            {/* 1. Hình ảnh sách */}
            <div style={imageContainer}>
                <img 
                    src={book.imageUrl || defaultImage} 
                    alt={book.title} 
                    style={imageStyle} 
                />
            </div>

            {/* 2. Thông tin chính */}
            <div style={contentStyle}>
                <h3 style={titleStyle} title={book.title}>
                    {book.title}
                </h3>
                
                <p style={authorStyle}>Tác giả: <strong>{book.authorName}</strong></p>
                <p style={categoryStyle}>Thể loại: {book.categoryName}</p>

                {/* 3. Giá tiền (Giá gốc & Giá giảm) */}
                <div style={priceContainer}>
                    <span style={salePriceStyle}>
                        {book.salePrice.toLocaleString()}đ
                    </span>
                    {book.basePrice > book.salePrice && (
                        <span style={basePriceStyle}>
                            {book.basePrice.toLocaleString()}đ
                        </span>
                    )}
                </div>
            </div>
        </div>
    );
};

// --- CSS Inline đơn giản ---
const cardStyle = {
    border: '1px solid #eee',
    borderRadius: '10px',
    overflow: 'hidden',
    backgroundColor: '#fff',
    boxShadow: '0 2px 8px rgba(0,0,0,0.1)',
    transition: 'transform 0.2s',
    display: 'flex',
    flexDirection: 'column'
};

const imageContainer = {
    width: '100%',
    height: '200px',
    backgroundColor: '#f5f5f5'
};

const imageStyle = {
    width: '100%',
    height: '100%',
    objectFit: 'cover'
};

const contentStyle = { padding: '12px' };

const titleStyle = {
    fontSize: '16px',
    fontWeight: 'bold',
    margin: '0 0 8px 0',
    height: '40px', // Giới hạn độ cao tiêu đề
    overflow: 'hidden',
    display: '-webkit-box',
    WebkitLineClamp: 2,
    WebkitBoxOrient: 'vertical'
};

const authorStyle = { fontSize: '14px', margin: '4px 0', color: '#555' };
const categoryStyle = { fontSize: '12px', color: '#888', fontStyle: 'italic' };

const priceContainer = { marginTop: '10px', display: 'flex', alignItems: 'baseline', gap: '8px' };
const salePriceStyle = { color: '#d70018', fontWeight: 'bold', fontSize: '18px' };
const basePriceStyle = { color: '#888', textDecoration: 'line-through', fontSize: '13px' };

export default BookCard;