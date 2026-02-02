package mo.project.sellbook.controller;

import mo.project.sellbook.dto.CreateCartRequestDTO;
import mo.project.sellbook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "http://localhost:5173")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(@RequestBody CreateCartRequestDTO dto) {
        // userId này sau này sẽ lấy từ JWT Token
        int userId = 1;
        cartService.addToCart(userId, dto);
        return ResponseEntity.ok("Thêm vào giỏ hàng thành công!");
    }
}