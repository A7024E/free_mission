package shopping.backend.purchase.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartItem {

    private Long productId;
    private int quantity;

    protected CartItem() { }

    public CartItem(Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("수량은 1 이상이어야 합니다.");
        }
        this.productId = productId;
        this.quantity = quantity;
    }

    public Long productId() {
        return productId;
    }

    public int quantity() {
        return quantity;
    }

    public CartItem add(int quantity) {
        return new CartItem(productId, quantity + quantity);
    }

    public CartItem update(int quantity) {
        return new CartItem(productId, quantity);
    }
}
