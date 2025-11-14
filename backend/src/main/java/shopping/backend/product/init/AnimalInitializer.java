package shopping.backend.product.init;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import shopping.backend.product.model.Category;
import shopping.backend.product.model.Description;
import shopping.backend.product.model.ProductName;
import shopping.backend.product.model.Price;
import shopping.backend.product.model.Product;
import shopping.backend.product.repository.ProductRepository;
import shopping.backend.product.model.Stock;

@Component
public class AnimalInitializer {

    private final ProductRepository productRepository;

    public AnimalInitializer(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        saveIfNotExists("3D 고양이", 5000, 30, Category.ANIMAL,"나만없어 고양이분들을 위한 3D고양이 하나 장만해보세요");
        saveIfNotExists("3D 강아지", 5000, 40, Category.ANIMAL,"이번 강아지 난 좀 다르게 본다 나는 3D로본다");
    }

    private void saveIfNotExists(String productName, int price, int stock, Category category,String description) {
        if (productRepository.existsByProductName_ProductName(productName)) {
            return;
        }
        productRepository.save(new Product(
                new ProductName(productName),
                new Price(price),
                new Stock(stock),
                category,
                new Description(description)
        ));
    }
}
