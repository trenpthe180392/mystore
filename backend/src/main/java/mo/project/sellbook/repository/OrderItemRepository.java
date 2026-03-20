package mo.project.sellbook.repository;

import mo.project.sellbook.model.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItems,Integer> {
}