package mo.project.sellbook.controller;

import mo.project.sellbook.model.Categories;
import mo.project.sellbook.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*") // Để React gọi được API mà không bị lỗi CORS
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Categories>> getAll() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }
}