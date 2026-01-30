package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Authors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String bio;
    @Column
    private String image;
    @ManyToMany(mappedBy = "authors")
    private Set<Books> books = new HashSet<>();
    public Authors(int id, String name, String bio, String image) {
        this.id = id;
        this.name = name;
        this.bio = bio;
        this.image = image;
    }

    public Authors() {
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

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
