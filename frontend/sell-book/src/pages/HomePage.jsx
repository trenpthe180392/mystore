import React, { useEffect, useState } from 'react';
import { useLocation } from 'react-router-dom';
import { getHomeBooks, getBooksByCategory, getBooksByAuthor } from '../services/BookService';
import BookCard from '../components/BookCard';
import '../assets/styles/HomePage.css';

const HomePage = () => {
    const [allBooks, setAllBooks] = useState([]); 
    const [loading, setLoading] = useState(true);
    const [currentPage, setCurrentPage] = useState(1);
    const itemsPerPage = 8; 

    const location = useLocation();
    const queryParams = new URLSearchParams(location.search);
    
    // Lấy các tham số từ URL
    const categoryId = queryParams.get("categoryId");
    const authorId = queryParams.get("authorId"); // Thêm lọc theo tác giả
    const searchName = queryParams.get("search") || "";

    useEffect(() => {
        const fetchBooks = async () => {
            setLoading(true);
            try {
                let data;
                
                // Ưu tiên kiểm tra: Tác giả -> Thể loại -> Mặc định
                if (authorId) {
                    data = await getBooksByAuthor(authorId);
                } else if (categoryId) {
                    data = await getBooksByCategory(categoryId);
                } else {
                    data = await getHomeBooks();
                }

                // Logic tìm kiếm (Client-side filtering)
                if (searchName) {
                    data = data.filter(book => 
                        book.title.toLowerCase().includes(searchName.toLowerCase())
                    );
                }

                setAllBooks(data || []);
                setCurrentPage(1); // Luôn reset về trang 1 khi URL thay đổi
            } catch (error) {
                console.error("Lỗi tải sách:", error);
                setAllBooks([]);
            } finally {
                setLoading(false);
            }
        };

        fetchBooks();
    }, [categoryId, authorId, searchName]); // Theo dõi cả 3 tham số này

    // Logic tính toán phân trang
    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentBooks = allBooks.slice(indexOfFirstItem, indexOfLastItem);
    const totalPages = Math.ceil(allBooks.length / itemsPerPage);

    const handlePageChange = (newPage) => {
        setCurrentPage(newPage);
        window.scrollTo({ top: 0, behavior: 'smooth' });
    };

    // Render tiêu đề động dựa trên bộ lọc
    const renderTitle = () => {
        if (searchName) return `Kết quả cho: "${searchName}"`;
        if (authorId) return "Sách Theo Tác Giả";
        if (categoryId) return "Sách Theo Thể Loại";
        return "Khám Phá Sách Mới";
    };

    if (loading) return (
        <div className="loader-container">
            <div className="loader"></div>
            <p>Đang tải dữ liệu sách...</p>
        </div>
    );

    return (
        <div className="homepage-container">
            <div className="section-header">
                <h2 className="section-title">{renderTitle()}</h2>
                <p className="section-subtitle">
                    Tìm thấy **{allBooks.length}** cuốn sách
                </p>
            </div>

            <div className="book-grid">
                {currentBooks.length > 0 ? (
                    currentBooks.map(book => <BookCard key={book.id} book={book} />)
                ) : (
                    <div className="no-results">
                        <span className="icon">🔭</span>
                        <p>Rất tiếc, không tìm thấy sách nào phù hợp.</p>
                    </div>
                )}
            </div>

            {/* Pagination UI */}
            {totalPages > 1 && (
                <div className="pagination">
                    <button 
                        className="btn-nav"
                        disabled={currentPage === 1} 
                        onClick={() => handlePageChange(currentPage - 1)}
                    >
                        Trước
                    </button>

                    {[...Array(totalPages)].map((_, index) => (
                        <button 
                            key={index + 1}
                            className={currentPage === index + 1 ? "active" : ""}
                            onClick={() => handlePageChange(index + 1)}
                        >
                            {index + 1}
                        </button>
                    ))}

                    <button 
                        className="btn-nav"
                        disabled={currentPage === totalPages} 
                        onClick={() => handlePageChange(currentPage + 1)}
                    >
                        Sau
                    </button>
                </div>
            )}
        </div>
    );
};

export default HomePage;