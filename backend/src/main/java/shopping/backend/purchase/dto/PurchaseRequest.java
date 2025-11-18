package shopping.backend.purchase.dto;

public record PurchaseRequest(String memberId,Long productId,int quantity) {
}
