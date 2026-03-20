package mo.project.sellbook.mapper;

import mo.project.sellbook.dto.dtoModel.CartItemDTO;
import mo.project.sellbook.model.CartItems;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "id", target = "cartItemId")
    @Mapping(source = "book.id", target = "bookId")
    @Mapping(source = "book.title", target = "title")
    @Mapping(source = "book.slug", target = "slug")
    @Mapping(source = "book.bookImages.imageUrl", target = "image")
    @Mapping(source = "book.salePrice", target = "price")
    @Mapping(source = "book.stockQuantity", target = "stockQuantity")
    @Mapping(target = "subtotal", expression = "java(item.getBook().getSalePrice() * item.getQuantity())")
    CartItemDTO toDTO(CartItems item);

    List<CartItemDTO> toDTOList(List<CartItems> items);

    List<CartItemDTO> toDTOSet(Set<CartItems> items);
}