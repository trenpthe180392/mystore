package mo.project.sellbook.mapper;

import mo.project.sellbook.dto.BookDetailDTO;
import mo.project.sellbook.dto.BookHomeDTO;
import mo.project.sellbook.model.Books;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    // Mapper cho Home (giữ nguyên)
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "bookImages.imageUrl", target = "imageUrl")
    @Mapping(target = "authorName", expression = "java(book.getAuthors().isEmpty() ? \"Unknown\" : book.getAuthors().iterator().next().getName())")
    BookHomeDTO toHomeDTO(Books book);

    // Mapper cho Detail (Phải khớp với các field trong BookDetailDTO của bạn)
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "publisher.name", target = "publisherName")
    @Mapping(source = "bookImages.imageUrl", target = "imageUrl")
    @Mapping(target = "authorNames", expression = "java(book.getAuthors().stream().map(mo.project.sellbook.model.Authors::getName).collect(java.util.stream.Collectors.toList()))")
    BookDetailDTO toDetailDTO(Books book);

    List<BookHomeDTO> toHomeDTOList(List<Books> books);
}