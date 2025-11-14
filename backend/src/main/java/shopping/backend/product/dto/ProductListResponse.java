package shopping.backend.product.dto;

public record ProductListResponse(
        Long id,
        String name,
        int price,
        int stock,
        String category
) {
}
