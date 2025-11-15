package shopping.backend.purchase.dto;

public record PurchaseResponse(String memberId,
                               Long productId,
                               int quantity,
                               int totalPrice,
                               int remainPoint,
                               int remainStock) {
}
