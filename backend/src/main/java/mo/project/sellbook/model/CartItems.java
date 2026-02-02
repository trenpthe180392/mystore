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

    public CartItems() {
    }

    public CartItems(int id, int quantity, LocalDate addedAt, Carts cart, Books book) {
        this.id = id;
        this.quantity = quantity;
        this.addedAt = addedAt;
        this.cart = cart;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDate addedAt) {
        this.addedAt = addedAt;
    }

    public Carts getCart() {
        return cart;
    }

    public void setCart(Carts cart) {
        this.cart = cart;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
}
