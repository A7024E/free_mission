package shopping.backend.member;

import java.util.Objects;

public class NickName {
    private String NickName;

    public NickName(String nickName) {
        NickName = nickName;
    }

    public String getNickName() {
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
