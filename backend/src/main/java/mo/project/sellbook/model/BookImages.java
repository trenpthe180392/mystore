package mo.project.sellbook.model;

import jakarta.persistence.*;

@Entity
@Table
public class BookImages {
    @Id
    private int id;
    private String imageUrl;
    private boolean isThumbnail;
    @OneToOne
    @JoinColumn(name = "book_id", unique = true)
    private Books book;

    public BookImages() {
    }

    public BookImages(int id, String imageUrl, boolean isThumbnail, Books book) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.isThumbnail = isThumbnail;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isThumbnail() {
        return isThumbnail;
    }

    public void setThumbnail(boolean thumbnail) {
        isThumbnail = thumbnail;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
}
