package mo.project.sellbook.model;

import jakarta.persistence.*;
import org.apache.catalina.User;

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
    @Column(length = 255,nullable = false)
    private String paymentMethod;
@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
@JoinColumn(name="user_id")
    private Users user;
@OneToMany(
        mappedBy="order"
)
private List<OrderItems> items;
    public Orders() {
    }

    public Orders(int id, OrderStatus status, double totalAmount, String shippingAddress, String paymentMethod, Users user) {
        this.id = id;
        this.status = status;
        this.totalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.user = user;
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
