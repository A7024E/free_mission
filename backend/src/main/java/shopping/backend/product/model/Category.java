package shopping.backend.product.model;

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
        for (Category category : values()) {
            if (category.label.equals(label)) {
                return category;
            }
        }
        throw new IllegalArgumentException("잘못된 카테고리입니다.");
    }
}
