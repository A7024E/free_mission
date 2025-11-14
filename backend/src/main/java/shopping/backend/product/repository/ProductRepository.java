package shopping.backend.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shopping.backend.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByProductName_ProductName(String name);
}
