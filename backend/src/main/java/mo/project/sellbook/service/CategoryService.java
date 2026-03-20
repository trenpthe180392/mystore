package mo.project.sellbook.service;

import mo.project.sellbook.model.Categories;
import mo.project.sellbook.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepo;

    public CategoryService(CategoryRepository categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    public List<Categories> getAllCategories() {
        return categoryRepo.findAll();
    }
}