package mo.project.sellbook.service;

import mo.project.sellbook.model.Orders;
import mo.project.sellbook.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    public OrderService(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }
    public Orders createOrder(Orders order) {
        return orderRepo.save(order);
    }
}
