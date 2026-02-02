package mo.project.sellbook.dto;

import java.time.LocalDate;
import java.util.List;

public class BookDetailDTO {
    private int id;
    private String title;
    private String summary;
    private String description;
    private String language;
    private int pages;
    private String dimensions;
    private double weight;
    private double basePrice;
    private double salePrice;
    private int stockQuantity;
    private String isbn10;
    private String isbn13;
    private LocalDate publishedDate;

    // Các trường Flat dữ liệu (Phẳng hóa từ các Entity liên quan)
    private String categoryName;    // Lấy từ category.getName()
    private String publisherName;   // Lấy từ publisher.getName()
    private String imageUrl;        // Lấy từ bookImages.getUrl()
    private List<String> authorNames; // Duyệt Set<Authors> lấy list tên

    public BookDetailDTO() {
    }

    public BookDetailDTO(int id, String title, String summary, String description, String language, int pages, String dimensions, double weight, double basePrice, double salePrice, int stockQuantity, String isbn10, String isbn13, LocalDate publishedDate, String categoryName, String publisherName, String imageUrl, List<String> authorNames) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.description = description;
        this.language = language;
        this.pages = pages;
        this.dimensions = dimensions;
        this.weight = weight;
        this.basePrice = basePrice;
        this.salePrice = salePrice;
        this.stockQuantity = stockQuantity;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.publishedDate = publishedDate;
        this.categoryName = categoryName;
        this.publisherName = publisherName;
        this.imageUrl = imageUrl;
        this.authorNames = authorNames;
    }

    public int getId() {
        return id;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getAuthorNames() {
        return authorNames;
    }

    public void setAuthorNames(List<String> authorNames) {
        this.authorNames = authorNames;
    }
}
