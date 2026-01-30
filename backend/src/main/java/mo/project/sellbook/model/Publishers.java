package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Publishers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,length = 255)
    private String name;
    @Column(nullable = false,length = 255)
    private String slug;
    @Column(nullable = false,length = 255)
    private String logoUrl;
    @Column(nullable = false,length = 255)
    private String description;
    @Column(nullable = false,length = 255)
    private String websiteUrl;
    @Column(nullable = false,length = 255)
    private String email;
    @Column(nullable = false,length = 255)
    private String number;
    @Column(nullable = false,columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;
    @OneToMany(mappedBy = "publisher")
    private List<Books> books;

    public Publishers() {
    }

    public Publishers(int id, String name, String slug, String logoUrl, String description, String websiteUrl, String email, String number, boolean isActive, List<Books> books) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.logoUrl = logoUrl;
        this.description = description;
        this.websiteUrl = websiteUrl;
        this.email = email;
        this.number = number;
        this.isActive = isActive;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
