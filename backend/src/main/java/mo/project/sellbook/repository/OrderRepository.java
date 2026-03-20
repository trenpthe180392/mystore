package mo.project.sellbook.repository;

import mo.project.sellbook.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {
    // Sắp xếp đơn hàng mới nhất hiện lên trên cùng
    List<Orders> findByUserIdOrderByCreatedAtDesc(Integer userId);
    @Query("SELECT o FROM Orders o LEFT JOIN FETCH o.items WHERE o.id = :orderId")
    Optional<Orders> findByIdWithItems(@Param("orderId") Integer orderId);
}
