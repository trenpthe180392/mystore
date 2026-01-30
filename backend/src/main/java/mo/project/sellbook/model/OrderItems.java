package mo.project.sellbook.model;

import jakarta.persistence.*;

import java.awt.print.Book;

@Entity
@Table
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "INTEGER(10)",nullable = false)
    private int quantity;
    @Column(columnDefinition = "decimal(19,4)",nullable = false)
    private double price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orders_id")
    private Orders order;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="book_id")
    private Books book;

    public OrderItems() {
    }

    public OrderItems(int id, int quantity, double price, Orders order, Books book) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.order = order;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }
}
