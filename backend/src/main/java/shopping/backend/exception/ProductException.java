package shopping.backend.exception;

public enum ProductException {
    EXCEPTION_VALID_NOT_FOUND_PRODUCT("상품 정보가 없습니다."),

    ;
    private final String message;

    ProductException(String message) {
        this.message = message;
    }

    public String message() {
        return "[ERROR] " + message;
    }
}
