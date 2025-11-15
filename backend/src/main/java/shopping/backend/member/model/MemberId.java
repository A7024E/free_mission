package shopping.backend.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class MemberId {

    private static final String ID_PATTERN = "^[A-Za-z0-9]+$";

    @Column(name = "id")
    private String id;

    public MemberId(String id) {
        validate(id);
        this.id = id;
    }

    public MemberId() {
    }

    public void validate(String id) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("아이디에 공백을 입력할 수 없습니다.");
        }

        if (id.contains(" ")) {
            throw new IllegalArgumentException("아이디에 빈칸을 입력할수 없습니다.");
        }

        if(!id.matches(ID_PATTERN)) {
            throw new IllegalArgumentException("아이디는 영어와 숫자만 가능합니다.");
        }

    }

    public String info() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MemberId memberId = (MemberId) o;
        return Objects.equals(id, memberId.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
