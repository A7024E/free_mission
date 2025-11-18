package shopping.backend.exception;

public enum PurchaseException {
    EXCEPTION_INVALID_QUANTITY("수량은 1 이상이어야 합니다."),
    EXCEPTION_INVALID_IN_CART("장바구니에 없는 상품입니다"),
    EXCEPTION_INVALID_ENOUGH_POINT("포인트가 부족합니다"),
    EXCEPTION_INVALID_ENOUGH_INVENTORY("재고가 부족합니다"),
    EXCEPTION_INVALID_ENOUGH_ALERT("재고가 부족한 상품이 있습니다: "),
    ;

    private final String message;

    PurchaseException(String message) {
        this.message = message;
    }

    public String message() {
        return "[ERROR] " + message;
    }
}
