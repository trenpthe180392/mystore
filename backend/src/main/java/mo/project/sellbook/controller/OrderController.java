package mo.project.sellbook.controller;

import mo.project.sellbook.dto.request.CreateOrderRequestDTO;
import mo.project.sellbook.dto.response.OrderResponseDTO;
import mo.project.sellbook.model.Users;
import mo.project.sellbook.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<OrderResponseDTO> createOrder(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody CreateOrderRequestDTO request) {

        // 1. Lấy userId từ claim của JWT giống như bên CartController
        Object idClaim = jwt.getClaim("id");
        if (idClaim == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Integer userId = Integer.parseInt(idClaim.toString());

        // 2. Truyền userId vào service thay vì truyền object Users rỗng
        OrderResponseDTO response = orderService.placeOrder(request, userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // Lấy danh sách tất cả đơn hàng của người dùng hiện tại
    @GetMapping("/my-orders")
    public ResponseEntity<List<OrderResponseDTO>> getMyOrders(@AuthenticationPrincipal Jwt jwt) {
        Object idClaim = jwt.getClaim("id");
        if (idClaim == null) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

        Integer userId = Integer.parseInt(idClaim.toString());
        List<OrderResponseDTO> orders = orderService.getOrdersByUserId(userId);

        return ResponseEntity.ok(orders);
    }

    // Xem chi tiết 1 đơn hàng cụ thể
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Integer orderId) {

        Object idClaim = jwt.getClaim("id");
        Integer userId = Integer.parseInt(idClaim.toString());

        OrderResponseDTO order = orderService.getOrderDetails(orderId, userId);
        return ResponseEntity.ok(order);
    }
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable Integer orderId) {

        Object idClaim = jwt.getClaim("id");
        Integer userId = Integer.parseInt(idClaim.toString());

        OrderResponseDTO updatedOrder = orderService.cancelOrder(orderId, userId);
        return ResponseEntity.ok(updatedOrder);
    }
}