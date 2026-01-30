package mo.project.sellbook.dto;

public class BookHomeDTO {
    private int id;
    private String title;
    private String slug;
    private double salePrice;
    private double basePrice;
    private String categoryName; // Lấy từ Category Entity
    private String authorName;   // Lấy từ tập hợp Authors
    private String imageUrl;     // Lấy từ BookImages Entity

    public BookHomeDTO(int id, String title, String slug, double salePrice, double basePrice, String categoryName, String authorName, String imageUrl) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.salePrice = salePrice;
        this.basePrice = basePrice;
        this.categoryName = categoryName;
        this.authorName = authorName;
        this.imageUrl = imageUrl;
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

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}