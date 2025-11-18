package shopping.backend.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;
import shopping.backend.exception.MemberException;

@Embeddable
public class NickName {
    private static final String NICKNAME_PATTERN = "^[A-Za-z0-9가-힣]{2,8}$";
    private static final Pattern PATTERN = Pattern.compile(NICKNAME_PATTERN);

    @Column(name = "nick_name")
    private String NickName;


    public NickName(String nickName) {
        validate(nickName);
        NickName = nickName;
    }

    public NickName() {
    }

    private void validate(String nickName) {
        if (isValidNickName(nickName)) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_PATTERN_NICK_NAME.message());
        }

        if (nickName == null || nickName.isEmpty()) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_EMPTY_NICK_NAME.message());
        }

        if (nickName.isBlank()) {
            throw new IllegalArgumentException((MemberException.EXCEPTION_VALID_IS_BLANK_NICK_NAME.message()));
        }
    }

    private static boolean isValidNickName(String nickName) {
        return !PATTERN.matcher(nickName).matches();
    }

    public String value() {
        return NickName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NickName nickName = (NickName) o;
        return Objects.equals(NickName, nickName.NickName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(NickName);
    }
}
