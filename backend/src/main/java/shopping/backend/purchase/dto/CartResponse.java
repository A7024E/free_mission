package shopping.backend.purchase.dto;

public record CartResponse(Long id, String name, int price, int quantity, int totalPrice) {
}
