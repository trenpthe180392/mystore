package mo.project.sellbook.repository;

import mo.project.sellbook.model.Authors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Authors, Integer> {
    // JpaRepository đã có sẵn findAll(), findById(),...
}