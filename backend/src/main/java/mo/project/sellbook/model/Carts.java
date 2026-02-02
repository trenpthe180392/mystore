package mo.project.sellbook.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Carts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean isActive;

    @OneToOne
    @JoinColumn(name = "usersid")
    private Users user;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private Set<CartItems> items = new HashSet<>();

    public Carts() {
    }

    public Carts(int id, LocalDate createdAt, LocalDate updatedAt, boolean isActive, Users user, Set<CartItems> items) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.isActive = isActive;
        this.user = user;
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Set<CartItems> getItems() {
        return items;
    }

    public void setItems(Set<CartItems> items) {
        this.items = items;
    }
}
