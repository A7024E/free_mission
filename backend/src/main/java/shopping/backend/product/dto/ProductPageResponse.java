package shopping.backend.product.dto;

public record ProductPageResponse(Long id, String name, int price, int stock, String category,String description) {
}
