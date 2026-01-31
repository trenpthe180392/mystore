package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
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

}
