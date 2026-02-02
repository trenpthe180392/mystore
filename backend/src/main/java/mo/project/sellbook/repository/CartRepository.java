package mo.project.sellbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import mo.project.sellbook.model.Carts;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Carts, Integer> {
    // Spring sẽ tự hiểu trả về Optional<Carts>
    Optional<Carts> findByUserId(int userId);
}
