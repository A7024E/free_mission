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

    EXCEPTION_VALID_POINT_NEGATIVE("포인트는 음수가 될 수 없습니다."),
    EXCEPTION_VALID_POINT_LACK("포인트 부족가 부족합니다"),

    EXCEPTION_VALID_DUPLICATE_NICKNAME("중복된 닉네임이 존재합니다"),
    EXCEPTION_VALID_DUPLICATE_ID("중복된 아이디가 존재합니다"),
    EXCEPTION_VALID_NOT_FOUND_MEMBER("존재하지 않는 회원입니다"),
    EXCEPTION_VALID_PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다"),
    EXCEPTION_VALID_INVALID_LOGIN_PASSWORD("패스워드가 틀립니다 다시 확인해주세요");

    private final String message;

    MemberException(String message) {
        this.message = message;
    }

    public String message() {
        return "[ERROR] " + message;
    }
}
