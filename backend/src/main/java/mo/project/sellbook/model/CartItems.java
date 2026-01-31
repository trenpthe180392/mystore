package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantity;
    private LocalDate addedAt;
    @ManyToOne
    @JoinColumn(name="cart_id")
    private Carts cart;

    @ManyToOne
    @JoinColumn(name="book_id")
    private Books book;
}
