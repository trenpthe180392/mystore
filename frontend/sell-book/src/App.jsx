import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Header from './components/Header';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import BookDetailPage from './pages/BookDetailPage'; // 1. Import trang chi tiết
import './App.css';

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <main>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            
            {/* 2. Thêm Route cho trang chi tiết sách */}
            {/* :id là tham số động, nó sẽ hứng cái book.id từ Card truyền sang */}
            <Route path="/books/:id" element={<BookDetailPage />} />
            
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;