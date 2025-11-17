package shopping.backend.product.model;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @AttributeOverride(name = "productName", column = @Column(name = "product_name"))
    private ProductName productName;
    @Embedded
    private Price price;
    @Embedded
    private Stock stock;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Embedded
    private Description description;

    public Product(ProductName productName, Price price, Stock stock, Category category, Description description) {
        this.productName = productName;
        this.price = price;
        this.stock = stock;
        this.category = category;
        this.description = description;
    }

    protected Product() {
    }

    public String name() {
        return productName.getName();
    }

    public int price() {
        return price.getPrice();
    }

    public int stock() {
        return stock.getStock();
    }

    public String category() {
        return category.label();
    }

    public Long id() {
        return id;
    }

    public String description() {
        return description.getDescription();
    }

    public void decreaseStock(int amount) {
        this.stock.minus(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) {
            return false;
        }
        return Objects.equals(id, product.id) && Objects.equals(productName, product.productName)
                && Objects.equals(price, product.price) && Objects.equals(stock, product.stock)
                && category == product.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, price, stock, category);
    }
}
