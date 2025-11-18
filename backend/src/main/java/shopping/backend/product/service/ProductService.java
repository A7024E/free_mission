package shopping.backend.product.service;

import java.util.List;
import org.springframework.stereotype.Service;
import shopping.backend.product.dto.ProductListResponse;
import shopping.backend.product.dto.ProductPageResponse;
import shopping.backend.product.model.Category;
import shopping.backend.product.model.Product;
import shopping.backend.product.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductListResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(this::fromProductResponse).toList();
    }


    public ProductPageResponse findOne(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));

        return new ProductPageResponse(
                product.id(),
                product.name(),
                product.price(),
                product.stock(),
                product.category(),
                product.description()
        );
    }

    public List<ProductListResponse> findByCategory(String categoryLabel) {
        Category category = Category.ofLabel(categoryLabel);
        return productRepository.findByCategory(category).stream()
                .map(this::fromProductResponse)
                .toList();
    }

    public List<ProductListResponse> search(String keyword) {
        List<Product> products = productRepository.findByProductName_ProductNameContaining(keyword);
        return products.stream()
                .map(this::fromProductResponse).toList();
    }

    private ProductListResponse fromProductResponse(Product product) {
        return new ProductListResponse(
                product.id(),
                product.name(),
                product.price(),
                product.stock(),
                product.category()
        );
    }
}
