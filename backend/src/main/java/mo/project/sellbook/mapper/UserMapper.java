package mo.project.sellbook.mapper;

import mo.project.sellbook.dto.request.RegisterRequest;
import mo.project.sellbook.dto.response.RegisterResponse;
import mo.project.sellbook.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Register
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "passwordHash", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "cart", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    Users toEntity(RegisterRequest request);

    // Response
    RegisterResponse toResponse(Users user);
}

