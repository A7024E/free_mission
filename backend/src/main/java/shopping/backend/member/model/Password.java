package shopping.backend.member.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import shopping.backend.exception.MemberException;

@Embeddable
public class Password {
    @Column(name = "password")
    private String password;

    public Password(String password) {
        validate(password);
        this.password = password;
    }

    public Password() {
    }

    public String getPassword() {
        return password;
    }

    private void validate(String password){
        if(password.length() < 8){
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_RANGE_PASSWORD.message());
        }

        if(password == null || password.isEmpty()){
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_EMPTY_PASSWORD.message());
        }

        if(password.isBlank()){
            throw new IllegalArgumentException(MemberException.EXCEPTION_VALID_IS_BLANK_PASSWORD.message());
        }
    }

    public boolean isSame(Password other){
        return this.password.equals(other.password);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Password password1)) {
            return false;
        }
        return Objects.equals(password, password1.password);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(password);
    }
}
