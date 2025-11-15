package shopping.backend.product.model;

import java.util.Arrays;

public enum Category {
    CLOTHES("옷"),
    ANIMAL("동물"),
    OBJECT("사물");

    private final String label;

    Category(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }


    public static Category ofLabel(String label) {
        return Arrays.stream(values())
                .filter(category -> category.label.equals(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 카테고리"));
    }
}
