import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { getAllAuthors } from '../services/AuthorService';
import '../assets/styles/AuthorList.css';

const AuthorList = () => {
    const [authors, setAuthors] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchAuthors = async () => {
            try {
                const data = await getAllAuthors();
                setAuthors(data || []);
            } catch (error) {
                console.error("Lỗi tải tác giả:", error);
            } finally {
                setLoading(false);
            }
        };
        fetchAuthors();
    }, []);

    if (loading) return <div className="loader">Đang tải dữ liệu...</div>;

    return (
        <div className="author-page-wrapper">
            <div className="container">
                
                {/* PHẦN 1: TIÊU ĐỀ - Nằm riêng biệt ở trên cùng */}
                <div className="section-header">
                    <h2 className="section-title">DANH SÁCH TÁC GIẢ</h2>
                    <div className="title-underline"></div>
                    <p className="results-count">Tìm thấy **{authors.length}** tác giả</p>
                </div>

                {/* PHẦN 2: LƯỚI CARD - Nằm ở dưới tiêu đề */}
                <div className="author-grid">
                    {authors.map(author => (
                        <div key={author.id} className="author-card">
                            <div className="author-img-box">
                                {author.image ? (
                                    <img src={author.image} alt={author.name} />
                                ) : (
                                    <div className="avatar-placeholder">{author.name.charAt(0)}</div>
                                )}
                            </div>
                            
                            <div className="author-details">
                                <h3 className="author-name">{author.name}</h3>
                                <p className="author-bio">
                                    {author.bio || "Thông tin tác giả đang được cập nhật..."}
                                </p>
                                <Link to={`/?authorId=${author.id}`} className="view-works-link">
                                    XEM TÁC PHẨM
                                </Link>
                            </div>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default AuthorList;