package mo.project.sellbook.repository;

import mo.project.sellbook.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {
    List<Books> findByCategoryId(Integer categoryId);
}
