package mo.project.sellbook.controller;

import mo.project.sellbook.dto.BookHomeDTO;
import mo.project.sellbook.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
