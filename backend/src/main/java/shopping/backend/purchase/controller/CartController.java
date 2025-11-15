package shopping.backend.purchase.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import shopping.backend.purchase.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getCart(@PathVariable String memberId) {
        return ResponseEntity.ok(cartService.getItems(memberId));
    }

}
