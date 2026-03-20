package mo.project.sellbook.repository;

import mo.project.sellbook.model.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItems, Integer> {

    @Query("""
        SELECT COUNT(ci.id)
        FROM CartItems ci
        WHERE ci.cart.user.id = :userId
    """)
    int countProductsByUserId(@Param("userId") Integer userId);

    List<CartItems> findAllByIdIn(List<Integer> ids);
}