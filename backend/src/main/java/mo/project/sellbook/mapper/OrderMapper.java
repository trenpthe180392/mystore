package mo.project.sellbook.mapper;

import mo.project.sellbook.dto.response.OrderResponseDTO;
import mo.project.sellbook.dto.dtoModel.OrderItemDTO;
import mo.project.sellbook.model.Orders;
import mo.project.sellbook.model.OrderItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    // --- MAPPING CHO ĐƠN HÀNG (Orders -> OrderResponseDTO) ---
    @Mapping(source = "id", target = "orderId")
    @Mapping(source = "user.userName", target = "customerName")
    @Mapping(source = "paymentMethod", target = "paymentMethod")
    @Mapping(source = "items", target = "items")
    // ĐẶT Ở ĐÂY: Vì createdAt thuộc về class Orders
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "yyyy-MM-dd HH:mm:ss")
    OrderResponseDTO toResponseDTO(Orders order);

    // --- MAPPING CHO TỪNG MÓN HÀNG (OrderItems -> OrderItemDTO) ---
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "title")
    // Biểu thức lấy ảnh từ OneToOne
    @Mapping(target = "image", expression = "java(orderItem.getBook().getBookImages() != null ? orderItem.getBook().getBookImages().getImageUrl() : null)")
    OrderItemDTO toItemDTO(OrderItems orderItem);

    // --- MAPPING CHO DANH SÁCH ---
    List<OrderResponseDTO> toResponseDTOList(List<Orders> orders);
}