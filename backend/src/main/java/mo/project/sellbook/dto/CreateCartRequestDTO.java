package mo.project.sellbook.dto;

public class CreateCartRequestDTO {
    private Integer productId;
    private Integer quantity;

    public CreateCartRequestDTO() {
    }

    public CreateCartRequestDTO(Integer productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
