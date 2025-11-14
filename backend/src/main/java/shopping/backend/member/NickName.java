package shopping.backend.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.regex.Pattern;

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
            throw new IllegalArgumentException("닉네임은 한글,영어,숫자 및 2-8 글자까지 가능합니다");
        }

        if (nickName == null || nickName.isEmpty()) {
            throw new IllegalArgumentException("닉네임에 공백을 사용할 수 없습니다");
        }

        if (nickName.isBlank()) {
            throw new IllegalArgumentException("닉네임는 빈칸을 사용할 수 없습니다");
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
