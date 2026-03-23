package mo.project.sellbook.service;

import mo.project.sellbook.dto.request.CreateOrderRequestDTO;
import mo.project.sellbook.dto.response.OrderResponseDTO;
import mo.project.sellbook.mapper.OrderMapper;
import mo.project.sellbook.model.*;
import mo.project.sellbook.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired private OrderRepository orderRepository;
    @Autowired private CartItemRepository cartItemRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private OrderMapper orderMapper;
    @Autowired private BookRepository bookRepository;

    @Transactional // Đảm bảo nếu lỗi thì không trừ kho cũng không lưu đơn
    public OrderResponseDTO placeOrder(CreateOrderRequestDTO request, Integer userId) {
        // 1. Lấy thông tin User và các CartItems từ database
        Users user = userRepository.findById(userId).orElseThrow();
        List<CartItems> cartItems = cartItemRepository.findAllById(request.getCartItemIds());

        Orders order = new Orders();
        order.setShippingAddress(request.getShippingAddress());
        order.setPhoneNumber(request.getPhoneNumber());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setUser(user);
        order.setStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        List<OrderItems> orderItemsList = new ArrayList<>();
        double total = 0;

        for (CartItems cartItem : cartItems) {
            Books book = cartItem.getBook();
            int quantityToBuy = cartItem.getQuantity();

            // --- BƯỚC THÊM MỚI: KIỂM TRA VÀ TRỪ KHO ---
            if (book.getStockQuantity() < quantityToBuy) {
                throw new RuntimeException("Sách '" + book.getTitle() + "' không đủ số lượng trong kho!");
            }
            // Trừ số lượng
            book.setStockQuantity(book.getStockQuantity() - quantityToBuy);
            bookRepository.save(book);
            // ------------------------------------------

            OrderItems oi = new OrderItems();
            oi.setBook(book);
            oi.setQuantity(quantityToBuy);
            oi.setPrice(book.getSalePrice());
            oi.setOrder(order);
            orderItemsList.add(oi);

            total += book.getSalePrice() * quantityToBuy;
        }

        order.setTotalAmount(total);
        order.setItems(orderItemsList);

        Orders savedOrder = orderRepository.save(order);

        // Xóa giỏ hàng sau khi đặt xong
        cartItemRepository.deleteAll(cartItems);

        return orderMapper.toResponseDTO(savedOrder);
    }
    // Lấy danh sách đơn hàng (Sắp xếp mới nhất lên đầu)
    public List<OrderResponseDTO> getOrdersByUserId(Integer userId) {
        List<Orders> orders = orderRepository.findByUserIdOrderByCreatedAtDesc(userId);
        return orderMapper.toResponseDTOList(orders);
    }

    @Transactional(readOnly = true) // Thêm dòng này
    public OrderResponseDTO getOrderDetails(Integer orderId, Integer userId) {
        Orders order = orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Đơn hàng không tồn tại"
                ));

        // Bảo mật: So sánh ID người dùng
        if (!order.getUser().getId().equals(userId)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.FORBIDDEN, "Bạn không có quyền xem đơn hàng này"
            );
        }

        return orderMapper.toResponseDTO(order);
    }
    @Transactional
    public OrderResponseDTO cancelOrder(Integer orderId, Integer userId) {
        // 1. Tìm đơn hàng
        Orders order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Đơn hàng không tồn tại"));

        // 2. Kiểm tra quyền và trạng thái (Chỉ cho hủy nếu đang PENDING)
        if (!order.getUser().getId().equals(userId)) {
            throw new RuntimeException("Bạn không có quyền hủy đơn hàng này");
        }
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Chỉ có thể hủy đơn hàng khi đang ở trạng thái Chờ xử lý");
        }

        // --- BƯỚC THÊM MỚI: HOÀN SỐ LƯỢNG VỀ KHO ---
        for (OrderItems item : order.getItems()) {
            Books book = item.getBook();
            // Cộng lại số lượng
            book.setStockQuantity(book.getStockQuantity() + item.getQuantity());
            bookRepository.save(book);
        }
        // ------------------------------------------

        // 3. Cập nhật trạng thái đơn hàng thành CANCELLED
        order.setStatus(OrderStatus.CANCELLED);
        Orders updatedOrder = orderRepository.save(order);

        return orderMapper.toResponseDTO(updatedOrder);
    }
}