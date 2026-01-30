package mo.project.sellbook.repository;

import mo.project.sellbook.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends JpaRepository<Books, Integer> {
}
