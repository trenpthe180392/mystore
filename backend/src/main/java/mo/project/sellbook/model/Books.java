package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false , length = 255)
    private String title;
    @Column(nullable = false , length = 255)
    private String slug;
    @Column(nullable = false , length = 255)
    private String isbn10;
    @Column(nullable = false , length = 255)
    private String isbn13;
    @Column(nullable = false , length = 255)
    private String summary;
    @Column(nullable = false , length = 255)
    private String description;
    @Column(nullable = false)
    private LocalDate publishedDate;
    @Column(nullable = false)
    private int pages;
    @Column(nullable = false , length = 255)
    private String dimensions;
    @Column(nullable = false)
    private double weight;
    @Column(nullable = false , length = 255)
    private String language;
    @Column(nullable = false)
    private double basePrice;
    @Column(nullable = false)
    private double salePrice;
    @Column(nullable = false)
    private int stockQuantity;
    @ManyToOne(fetch = FetchType.LAZY)
    private Categories category;
    @ManyToOne(fetch = FetchType.LAZY)
    private Publishers publisher;
    @OneToOne(mappedBy = "book")
    private BookImages bookImages;
    @ManyToMany
    @JoinTable(
            name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Authors> authors = new HashSet<>();
    public Books() {
    }

    public Books(int id, String title, String slug, String isbn10, String isbn13, String summary, String description, LocalDate publishedDate, int pages, String dimensions, double weight, String language, double basePrice, double salePrice, int stockQuantity, Categories category, Publishers publisher, BookImages bookImages) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.summary = summary;
        this.description = description;
        this.publishedDate = publishedDate;
        this.pages = pages;
        this.dimensions = dimensions;
        this.weight = weight;
        this.language = language;
        this.basePrice = basePrice;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.category = category;
        this.publisher = publisher;
        this.bookImages = bookImages;
    }

    public int getId() {
        return id;
    }

    public Set<Authors> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Authors> authors) {
        this.authors = authors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public Publishers getPublisher() {
        return publisher;
    }

    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public BookImages getBookImages() {
        return bookImages;
    }

    public void setBookImages(BookImages bookImages) {
        this.bookImages = bookImages;
    }
}
