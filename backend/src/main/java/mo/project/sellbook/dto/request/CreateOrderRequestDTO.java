package mo.project.sellbook.dto.request;

import java.util.List;

public class CreateOrderRequestDTO {

    private List<Integer> cartItemIds;

    private String shippingAddress;

    private String phoneNumber; // Bổ sung trường này

    private String paymentMethod;

    public CreateOrderRequestDTO() {
    }

    // Getters and Setters
    public List<Integer> getCartItemIds() {
        return cartItemIds;
    }

    public void setCartItemIds(List<Integer> cartItemIds) {
        this.cartItemIds = cartItemIds;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}