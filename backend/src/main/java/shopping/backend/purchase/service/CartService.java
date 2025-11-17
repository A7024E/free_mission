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

    public Object getItems(String memberId) {
        return findCarts(memberId).getItems();
    }
}
