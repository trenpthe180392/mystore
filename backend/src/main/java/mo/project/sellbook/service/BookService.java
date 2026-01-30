package mo.project.sellbook.service;

import mo.project.sellbook.dto.BookHomeDTO;
import mo.project.sellbook.mapper.BookMapper;
import mo.project.sellbook.model.Books;
import mo.project.sellbook.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepo;
    private final BookMapper bookMapper;

    // Inject tất cả qua Constructor (Best Practice)
    public BookService(BookRepository bookRepo, BookMapper bookMapper) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
    }

    public List<Books> getAllBooks() {
        return bookRepo.findAll();
    }

    @Transactional(readOnly = true) // Đảm bảo không lỗi Lazy Loading khi map DTO
    public List<BookHomeDTO> getBooksForHomePage() {
        List<Books> books = bookRepo.findAll();
        return bookMapper.toHomeDTOList(books);
    }

    public Books getBookById(int id) {
        return bookRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));
    }

    @Transactional
    public Books saveBook(Books book) {
        return bookRepo.save(book);
    }

    @Transactional
    public void deleteBookById(int id) {
        // Nên check tồn tại trước khi xóa để tránh lỗi ngầm
        if (!bookRepo.existsById(id)) {
            throw new RuntimeException("Cannot delete: Book not found");
        }
        bookRepo.deleteById(id);
    }

    @Transactional
    public Books updateBook(Books book) {
        // save() của JPA tự hiểu là Update nếu object đã có ID tồn tại
        return bookRepo.save(book);
    }
}