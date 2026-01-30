package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int ratingValue;
    private String comment;
    private int isVerified;
    private LocalDate createAt;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Books book;

    public Reviews() {
    }

    public Reviews(int id, int ratingValue, String comment, int isVerified, LocalDate createAt, Books book) {
        this.id = id;
        this.ratingValue = ratingValue;
        this.comment = comment;
        this.isVerified = isVerified;
        this.createAt = createAt;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Reviews{" +
                "id=" + id +
                ", ratingValue=" + ratingValue +
                ", comment='" + comment + '\'' +
                ", isVerified=" + isVerified +
                ", createAt=" + createAt +
                ", book=" + book +
                '}';
    }
}
