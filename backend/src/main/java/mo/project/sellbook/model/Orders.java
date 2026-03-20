package mo.project.sellbook.model;

import jakarta.persistence.*;
import org.apache.catalina.User;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(length = 20,nullable = false)
    private OrderStatus status;
    @Column(columnDefinition = "DECIMAL(19,4)",nullable = false)
    private double totalAmount;
    @Column(length = 255, nullable = false)
    private String shippingAddress;
    @Column(length = 15, nullable = false)
    private String phoneNumber;
    @Column(length = 255,nullable = false)
    private String paymentMethod;
    @Column(name = "created_at", nullable = false, updatable = false) // Đổi false thành true
    private LocalDateTime createdAt;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name="user_id")
    private Users user;
    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL, // BẮT BUỘC có dòng này để lưu items cùng order
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
private List<OrderItems> items;
    public Orders() {
    }
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public Orders(int id, OrderStatus status, double totalAmount, String shippingAddress, String phoneNumber, String paymentMethod, LocalDateTime createdAt, Users user, List<OrderItems> items) {
        this.id = id;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.phoneNumber = phoneNumber;
        this.paymentMethod = paymentMethod;
        this.createdAt = createdAt;
        this.user = user;
        this.items = items;
    }

    public List<OrderItems> getItems() {
        return items;
    }

    public void setItems(List<OrderItems> items) {
        this.items = items;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
