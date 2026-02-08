package mo.project.sellbook.service;

import mo.project.sellbook.dto.request.CreateCartRequestDTO;
import mo.project.sellbook.model.*;
import mo.project.sellbook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CartService {
    @Autowired private CartRepository cartRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private BookRepository bookRepository;

    @Transactional
    public void addToCart(int userId, CreateCartRequestDTO dto) {
        // 1. Tìm hoặc tạo Giỏ hàng
        Carts cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Users user = userRepository.findById(userId)
                            .orElseThrow(() -> new RuntimeException("User không tồn tại"));
                    Carts newCart = new Carts();
                    newCart.setUser(user);
                    newCart.setCreatedAt(LocalDate.now());
                    newCart.setActive(true);
                    return cartRepository.save(newCart);
                });

        // 2. Kiểm tra sách và Trừ kho
        Books book = bookRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Sách không tồn tại"));

        if (book.getStockQuantity() < dto.getQuantity()) {
            throw new RuntimeException("Số lượng trong kho không đủ!");
        }

        // Thực hiện trừ kho
        book.setStockQuantity(book.getStockQuantity() - dto.getQuantity());

        // 3. Xử lý CartItems (Thêm mới hoặc cộng dồn số lượng trong giỏ)
        Optional<CartItems> existingItem = cart.getItems().stream()
                .filter(item -> item.getBook().getId() == book.getId())
                .findFirst();

        if (existingItem.isPresent()) {
            CartItems item = existingItem.get();
            item.setQuantity(item.getQuantity() + dto.getQuantity());
        } else {
            CartItems newItem = new CartItems();
            newItem.setCart(cart);
            newItem.setBook(book);
            newItem.setQuantity(dto.getQuantity());
            newItem.setAddedAt(LocalDate.now());
            cart.getItems().add(newItem);
        }

        // 4. Lưu lại (Lưu Carts sẽ tự động lưu CartItems và cập nhật Books nhờ Transaction)
        cart.setUpdatedAt(LocalDate.now());
        cartRepository.save(cart);
    }
}