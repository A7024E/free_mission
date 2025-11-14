package shopping.backend.product.init;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import shopping.backend.product.model.*;
import shopping.backend.product.repository.ProductRepository;

@Component
public class ClothesInitializer {

    private final ProductRepository productRepository;

    public ClothesInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        saveIfNotExists("레제 티셔츠",3000,30,Category.CLOTHES);
        saveIfNotExists("레제 쵸커",2500,40,Category.CLOTHES);
    }

    private void saveIfNotExists(String productName, int price, int stock, Category category) {
        if (productRepository.existsByProductName_ProductName(productName)) {
            return;
        }
        productRepository.save(new Product(
                new ProductName(productName),
                new Price(price),
                new Stock(stock),
                category
        ));
    }
}
