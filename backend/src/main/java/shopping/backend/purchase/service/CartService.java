package shopping.backend.purchase.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import shopping.backend.product.repository.ProductRepository;
import shopping.backend.product.service.ProductService;

@Service
public class CartService {
    private final Map<Long,Integer> cart = new HashMap<>();
    private final ProductRepository productRepository;

    public CartService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void add(Long productId, int quantity) {
        cart.put(productId, cart.getOrDefault(productId, 0) + quantity);
    }


}
