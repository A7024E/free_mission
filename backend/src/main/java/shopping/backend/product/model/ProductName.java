package shopping.backend.product.model;

import java.util.Objects;

public class ProductName {
    private String productName;

    public ProductName(String productName) {
        this.productName = productName;
    }

    public ProductName() {
    }

    public String getName() {
        return productName;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ProductName that)) {
            return false;
        }
        return Objects.equals(productName, that.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }
}
