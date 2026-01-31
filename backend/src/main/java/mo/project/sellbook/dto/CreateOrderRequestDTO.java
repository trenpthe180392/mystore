package mo.project.sellbook.dto;

public class CreateOrderRequestDTO {
    private int id;
    private int quantity;
    private String shippingAddress;
    private String paymentMethod;

    public CreateOrderRequestDTO() {
    }

    public CreateOrderRequestDTO(int id, int quantity, String shippingAddress, String paymentMethod) {
        this.id = id;
        this.quantity = quantity;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
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
}
