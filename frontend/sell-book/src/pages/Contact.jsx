import React from "react";
import '../assets/styles/Contact.css';

export default function Contact() {
  return (
    <div className="contact-page">
      {/* HERO SECTION */}
      <section className="contact-hero">
        <h1>Liên hệ với chúng tôi</h1>
        <p>
          Chúng tôi luôn sẵn sàng hỗ trợ bạn trong việc quản lý và tổ chức sự kiện hiệu quả hơn.
        </p>
      </section>

      {/* MAIN CONTENT */}
      <section className="contact-content">
        {/* LEFT: INFO */}
        <div className="contact-info">
          <h2>Thông tin liên hệ</h2>

          <div className="info-item">
            <strong>Email:</strong>
            <p>support@eventflow.com</p>
          </div>

          <div className="info-item">
            <strong>Điện thoại:</strong>
            <p>0123 456 789</p>
          </div>

          <div className="info-item">
            <strong>Địa chỉ:</strong>
            <p>Hà Nội, Việt Nam</p>
          </div>

          <div className="social-section">
            <h3>Mạng xã hội</h3>
            <div className="social-links">
              <a href="https://facebook.com/yourpage" target="_blank">Facebook</a>
              <a href="https://instagram.com/yourpage" target="_blank">Instagram</a>
              <a href="https://twitter.com/yourpage" target="_blank">Twitter</a>
              <a href="https://linkedin.com/company/yourpage" target="_blank">LinkedIn</a>
            </div>
          </div>
        </div>

        {/* RIGHT: FORM */}
        <div className="contact-form">
          <h2>Gửi tin nhắn</h2>

          <form>
            <input type="text" placeholder="Họ và tên" required />
            <input type="email" placeholder="Email" required />
            <input type="text" placeholder="Chủ đề" />
            <textarea placeholder="Nội dung..." rows="5" required />

            <button type="submit">Gửi ngay</button>
          </form>
        </div>
      </section>

      {/* MAP / PLACEHOLDER */}
      <section className="contact-map">
        <h2>Vị trí của chúng tôi</h2>
        <div className="map-placeholder">
          Google Map sẽ hiển thị ở đây
        </div>
      </section>
    </div>
  );
}