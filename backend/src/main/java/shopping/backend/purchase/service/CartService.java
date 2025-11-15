package shopping.backend.purchase.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import shopping.backend.purchase.model.Carts;

@Service
public class CartService {
    private final Map<String, Carts> carts = new HashMap<>();

    private Carts findCarts(String memberId){
        return carts.computeIfAbsent(memberId, id -> new Carts());
    }

    public void addItem(String memberId, Long productId, int quantity) {
        findCarts(memberId).add(productId, quantity);
    }

    public void updateItem(String memberId, Long productId, int quantity) {
        findCarts(memberId).update(productId, quantity);
    }

    public void removeItem(String memberId, Long productId, int quantity) {
        findCarts(memberId).remove(productId);
    }

    public void clearCarts(String memberId) {
        carts.clear();
    }

    public Object getItems(String memberId) {
        return findCarts(memberId).getItems();
    }
}
