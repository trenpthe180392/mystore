package mo.project.sellbook.dto.response;

import mo.project.sellbook.dto.dtoModel.CartItemDTO;

import java.time.LocalDate;
import java.util.List;

public class ViewCartResponseDTO {
    private int cartId;
    private int userId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private boolean isActive;

    private double totalPrice;

    private List<CartItemDTO> items;
}
