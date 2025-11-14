package shopping.backend.product.model;

import java.util.Objects;

public class Price {
    private int price;

    public Price(int price) {
        this.price = price;
    }

    public Price() {
    }

    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Price price1)) {
            return false;
        }
        return price == price1.price;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(price);
    }
}
