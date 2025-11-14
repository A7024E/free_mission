package shopping.backend.product.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import shopping.backend.product.dto.ProductListResponse;
import shopping.backend.product.dto.ProductPageResponse;
import shopping.backend.product.model.Category;
import shopping.backend.product.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductListResponse>> getProducts() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ProductPageResponse getProduct(@PathVariable("id") Long id) {
        return productService.findOne(id);
    }

    @GetMapping("/category/{label}")
    public List<ProductListResponse> getByCategory(@PathVariable("label") String label) {
        String decoded = URLDecoder.decode(label, StandardCharsets.UTF_8);
        Category category = Category.ofLabel(decoded);
        return productService.findByCategory(category.label());
    }



}
