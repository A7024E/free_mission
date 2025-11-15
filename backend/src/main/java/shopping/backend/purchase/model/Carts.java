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

    public void update(Long productId, int quantity) {
        CartItem item = items.get(productId);
        if(item == null){
            throw new IllegalArgumentException("장바구니에 없는 상품입니다");
        }
        item.update(quantity);
    }

    public void remove(Long productId) {
        items.remove(productId);
    }

    public void clear() {
        items.clear();
    }

    public Map<Long, CartItem> getItems() {
        return items;
    }
}
