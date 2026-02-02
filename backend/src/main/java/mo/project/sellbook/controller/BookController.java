package mo.project.sellbook.controller;

import mo.project.sellbook.dto.BookDetailDTO;
import mo.project.sellbook.dto.BookHomeDTO;
import mo.project.sellbook.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok(detail);
    }

}
