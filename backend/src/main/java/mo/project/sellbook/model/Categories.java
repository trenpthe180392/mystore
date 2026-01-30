package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 255)
    private String name;
    @Column(nullable = false, length = 255)
    private String slug;
    @Column(columnDefinition = "TINYINT")
    private Integer level;
    @Column(nullable = false, length = 255)
    private String description;
    @Column(nullable = false)
    private int orderPriority;
    @OneToMany(
            mappedBy = "category"
    )
    private List<Books> books;

    public Categories() {
    }

    public Categories(int id, String name, String slug, Integer level, String description, int orderPriority, List<Books> books) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.level = level;
        this.description = description;
        this.orderPriority = orderPriority;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(int orderPriority) {
        this.orderPriority = orderPriority;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
