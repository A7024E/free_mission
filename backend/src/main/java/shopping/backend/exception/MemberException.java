package shopping.backend.exception;

public enum MemberException {
    EXCEPTION_VALID_EMPTY_ID("아이디에 공백을 입력할 수 없습니다."),
    EXCEPTION_VALID_IS_BLANK_ID("아이디에 빈칸을 입력할수 없습니다."),
    EXCEPTION_VALID_ID_PATTERN("아이디는 영어와 숫자만 가능합니다."),

    EXCEPTION_VALID_EMPTY_NICK_NAME("닉네임에 공백을 사용할 수 없습니다"),
    EXCEPTION_VALID_IS_BLANK_NICK_NAME("닉네임에 공백을 사용할 수 없습니다"),
    EXCEPTION_VALID_PATTERN_NICK_NAME("닉네임에 공백을 사용할 수 없습니다"),

    EXCEPTION_VALID_EMPTY_PASSWORD("패스워드는 공백이 될 수 없습니다"),
    EXCEPTION_VALID_IS_BLANK_PASSWORD("패스워드는 빈칸을 입력할 수 없습니다"),
    EXCEPTION_VALID_RANGE_PASSWORD("패스워드는 9자리 이상으로 입력해야합니다"),

    EXCEPTION_VALID_CHOICE_GENDER("올바른 성별이 아닙니다."),

    ;


    private final String message;

    MemberException(String message) {
        this.message = message;
    }

    public String message() {
        return "[ERROR] " + message;
    }
}
