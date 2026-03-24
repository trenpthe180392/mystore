package mo.project.sellbook.controller;

import mo.project.sellbook.dto.response.BookDetailDTO;
import mo.project.sellbook.dto.response.BookHomeDTO;
import mo.project.sellbook.model.Books;
import mo.project.sellbook.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = "http://localhost:5173")
public class BookController {

    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/home")
    public ResponseEntity<List<BookHomeDTO>> getHomeBooks() {
        List<BookHomeDTO> books = bookService.getBooksForHomePage();
        return ResponseEntity.ok(books);
    }
    @GetMapping("/{id}")
    public ResponseEntity<BookDetailDTO> getBookDetail(@PathVariable int id) {
        BookDetailDTO detail = bookService.getBookDetail(id);

        if (detail == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(detail);
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<List<BookHomeDTO>> getByCategory(@PathVariable Integer id) {
        return ResponseEntity.ok(bookService.getBooksByCategoryId(id));
    }
    @GetMapping("/author/{id}")
    public ResponseEntity<List<BookHomeDTO>> getByAuthor(@PathVariable int id) {
        List<BookHomeDTO> books = bookService.getBooksByAuthorId(id);
        return ResponseEntity.ok(books);
    }


}
