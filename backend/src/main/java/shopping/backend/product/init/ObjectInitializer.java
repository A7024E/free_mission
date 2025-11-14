package shopping.backend.product.init;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import shopping.backend.product.model.Category;
import shopping.backend.product.model.ProductName;
import shopping.backend.product.model.Price;
import shopping.backend.product.model.Product;
import shopping.backend.product.repository.ProductRepository;
import shopping.backend.product.model.Stock;

@Component
public class ObjectInitializer {

    private final ProductRepository productRepository;

    public ObjectInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        saveIfNotExists("에어컨", 4400, 30, Category.OBJECT);
        saveIfNotExists("책상", 2000, 30, Category.OBJECT);
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
