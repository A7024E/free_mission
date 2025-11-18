package shopping.backend.exception;

public enum MemberException {
    EXCEPTION_VALID_EMPTY_NICKNAME("아이디에 공백을 입력할 수 없습니다."),
    EXCEPTION_VALID_CONTAINS_BLANK_NICKNAME("아이디에 빈칸을 입력할수 없습니다."),
    EXCEPTION_VALID_ID_PATTERN("아이디는 영어와 숫자만 가능합니다."),

    ;


    private final String message;

    MemberException(String message) {
        this.message = message;
    }

    public String message() {
        return "[ERROR] "+message;
    }
}
