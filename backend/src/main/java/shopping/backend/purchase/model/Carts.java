package shopping.backend.purchase.model;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import java.util.HashMap;
import java.util.Map;

@Embeddable
public class Carts {
    @ElementCollection
    @CollectionTable(name = "member_cart", joinColumns = @JoinColumn(name = "member_id"))
    @MapKeyColumn(name = "product_id")
    Map<Long, CartItem> items = new HashMap<>();

    public Carts() {
    }

    public void add(Long productId, int quantity) {
        CartItem item = new CartItem(productId, quantity);
        if (item == null) {
            items.put(productId, new CartItem(productId, quantity));
        }
        item.add(quantity);
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }
}
