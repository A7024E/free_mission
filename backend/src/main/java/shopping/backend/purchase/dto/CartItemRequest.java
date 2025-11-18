package shopping.backend.purchase.dto;

public record CartItemRequest(String memberId, Long productId, int quantity) {
}
