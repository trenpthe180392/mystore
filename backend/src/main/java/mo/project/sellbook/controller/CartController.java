package mo.project.sellbook.controller;

import mo.project.sellbook.dto.request.CreateCartRequestDTO;
import mo.project.sellbook.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.jwt.Jwt;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin(origins = "*")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addProduct(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody CreateCartRequestDTO dto) {

        // Cách lấy ID an toàn nhất từ Claim
        Object idClaim = jwt.getClaim("id");

        if (idClaim == null) {
            return ResponseEntity.status(401).body("Token không chứa User ID!");
        }

        // Chuyển đổi an toàn về Integer (vì addToCart của bạn nhận int)
        Integer userId = Integer.parseInt(idClaim.toString());

        System.out.println(">>> Đang thực hiện add vào giỏ hàng cho User ID: " + userId);

        cartService.addToCart(userId, dto);

        return ResponseEntity.ok("Thêm vào giỏ hàng thành công!");
    }
    @GetMapping("/count")
    public ResponseEntity<Integer> getCartCount(@AuthenticationPrincipal Jwt jwt) {

        Integer userId = Integer.parseInt(jwt.getClaim("id").toString());

        int count = cartService.getCartCount(userId);

        return ResponseEntity.ok(count);
    }
}