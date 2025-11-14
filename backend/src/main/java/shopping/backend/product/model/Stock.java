package shopping.backend.product.model;

import java.util.Objects;

public class Stock {
    private int stock;

    public Stock(int stock) {
        this.stock = stock;
    }

    public Stock() {
    }

    public int getStock() {
        return stock;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Stock stock1)) {
            return false;
        }
        return stock == stock1.stock;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stock);
    }
}
