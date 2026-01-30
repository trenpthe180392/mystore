package mo.project.sellbook.mapper;

import mo.project.sellbook.dto.BookHomeDTO;
import mo.project.sellbook.model.Books;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(source = "bookImages.imageUrl", target = "imageUrl")
    @Mapping(target = "authorName", expression = "java(book.getAuthors().isEmpty() ? \"Unknown\" : book.getAuthors().iterator().next().getName())")
    BookHomeDTO toHomeDTO(Books book);

    List<BookHomeDTO> toHomeDTOList(List<Books> books);
}