package shopping.backend.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import shopping.backend.exception.MemberException;

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
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_EMPTY_NICKNAME.message());
        }

        if (id.contains(" ")) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_CONTAINS_BLANK_NICKNAME.message());
        }

        if(!id.matches(ID_PATTERN)) {
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_ID_PATTERN.message());
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
