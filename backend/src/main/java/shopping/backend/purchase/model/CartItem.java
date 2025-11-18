package shopping.backend.purchase.model;

import jakarta.persistence.Embeddable;
import shopping.backend.exception.PurchaseException;

@Embeddable
public class CartItem {

    private Long productId;
    private int quantity;

    protected CartItem() {
    }

    public CartItem(Long productId, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException(PurchaseException.EXCEPTION_INVALID_QUANTITY.message());
        }
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem add(int quantity) {
        return new CartItem(productId, quantity + quantity);
    }

}
