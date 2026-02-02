package mo.project.sellbook.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import mo.project.sellbook.dto.CreateCartRequestDTO;
import mo.project.sellbook.model.CartItems; // Đảm bảo import đúng Entity

@Mapper(componentModel = "spring")
public interface CartMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "book", ignore = true)     // Sẽ set thủ công trong Service
    @Mapping(target = "addedAt", ignore = true) // Sẽ set thủ công trong Service
    CartItems toCartItem(CreateCartRequestDTO request);
}