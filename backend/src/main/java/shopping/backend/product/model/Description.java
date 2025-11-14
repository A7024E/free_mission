package shopping.backend.product.model;

import java.util.Objects;

public class Description {
    private String description;

    public Description(String description) {
        this.description = description;
    }

    public Description() {
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Description that)) {
            return false;
        }
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(description);
    }
}
