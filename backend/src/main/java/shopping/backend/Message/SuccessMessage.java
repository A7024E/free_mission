package shopping.backend.Message;

public enum SuccessMessage {
    MEMBER_JOIN_SUCCESS("회원가입이 완료되었습니다. (50000 포인트 지급)"),
    MEMBER_DELETE_SUCCESS("회원 탈퇴가 완료되었습니다."),
    MEMBER_VERIFY_SUCCESS("본인 인증이 완료되었습니다.");

    private final String message;

    SuccessMessage(String message) {
        this.message = message;
    }

    public String message() {
        return message;
    }
}
