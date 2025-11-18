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
        saveIfNotExists("레제 티셔츠", 3000, 30, Category.CLOTHES, "화제에 오른 체인소맨 극장판 히로인이 입은 그 옷 이옷만 입으면 누구든 꼬십니다.");
        saveIfNotExists("레제 쵸커", 2500, 40, Category.CLOTHES, "누군가에게 화를 터트리고싶다구요 ? 하나 장만해보세요 BOOM 할 수 있습니다");
    }

    private void saveIfNotExists(String productName, int price, int stock, Category category, String description) {
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
