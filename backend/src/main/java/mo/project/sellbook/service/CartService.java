package mo.project.sellbook.service;

import mo.project.sellbook.dto.dtoModel.CartItemDTO;
import mo.project.sellbook.dto.request.CreateCartRequestDTO;
import mo.project.sellbook.dto.request.UpdateCartItemRequestDTO;
import mo.project.sellbook.mapper.CartItemMapper;
import mo.project.sellbook.model.*;
import mo.project.sellbook.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    @Autowired private CartRepository cartRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private BookRepository bookRepository;
    @Autowired private CartItemRepository cartItemsRepository;
    private final CartItemMapper cartItemMapper;

    public CartService(CartItemMapper cartItemMapper) {
        this.cartItemMapper = cartItemMapper;
    }

    @Transactional
    public void addToCart(int userId, CreateCartRequestDTO dto) {

        // 1. Tìm hoặc tạo cart
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

        // 2. Lấy sách
        Books book = bookRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Sách không tồn tại"));

        // 3. Kiểm tra tồn kho
        if (dto.getQuantity() > book.getStockQuantity()) {
            throw new RuntimeException("Số lượng trong kho không đủ!");
        }

        // 4. Kiểm tra item đã tồn tại trong cart chưa
        Optional<CartItems> existingItem = cart.getItems()
                .stream()
                .filter(item -> item.getBook().getId() == book.getId())
                .findFirst();

        if (existingItem.isPresent()) {

            // Nếu đã có thì cộng thêm quantity
            CartItems item = existingItem.get();

            int newQuantity = item.getQuantity() + dto.getQuantity();

            if (newQuantity > book.getStockQuantity()) {
                throw new RuntimeException("Số lượng vượt quá tồn kho!");
            }

            item.setQuantity(newQuantity);

            cartItemsRepository.save(item);

        } else {

            // Nếu chưa có thì tạo mới
            CartItems newItem = new CartItems();
            newItem.setCart(cart);
            newItem.setBook(book);
            newItem.setQuantity(dto.getQuantity());
            newItem.setAddedAt(LocalDate.now());

            cartItemsRepository.save(newItem);
        }

        // 5. Update thời gian
        cart.setUpdatedAt(LocalDate.now());
        cartRepository.save(cart);
    }
    public int getCartCount(Integer userId) {
        return cartItemsRepository.countProductsByUserId(userId);
    }

    public List<CartItemDTO> viewCart(Integer userId) {

        Carts cart = cartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Cart không tồn tại"));

        return cartItemMapper.toDTOSet(cart.getItems());
    }

    public void updateQuantity(Integer userId, UpdateCartItemRequestDTO dto) {

        CartItems item = cartItemsRepository.findById(dto.getCartItemId())
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        if (dto.getQuantity() <= 0) {
            throw new RuntimeException("Quantity must be greater than 0");
        }

        item.setQuantity(dto.getQuantity());

        cartItemsRepository.save(item);
    }
}